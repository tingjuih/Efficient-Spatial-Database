import java.util.*;

/**
 * QuadInternalNode object implement QuadNode interface to represent the
 * internal node of PRQuadtree.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-10-16
 */
public class QuadInternalNode implements QuadNode {
    private QuadNode[] children;

    /**
     * The constructor of QuadInternalNode. All children are initially set as
     * QuadNull.
     */
    public QuadInternalNode() {
        children = new QuadNode[4];
        Arrays.fill(children, QuadNull.get());
    }


    /**
     * Indicate if current node if a leaf.
     * 
     * @return
     *         the boolean to indicate if current node if a leaf.
     */
    public boolean isLeaf() {
        return false;
    }


    /**
     * Insert the point into current QuadNode. The internal node will choose the
     * suitable sub-region and pass the insertion commend down to a leaf.
     * 
     * @param point
     *            the Point wished to be inserted
     * @param x
     *            the x-coordinate of the current QuadNode
     * @param y
     *            the y-coordinate of the current QuadNode
     * @param dimension
     *            the dimension of the current QuadNode
     * @return a QuadPackage carrying current QuadNode and inserted Point
     */
    public QuadPackage insert(Point point, int x, int y, int dimension) {
        dimension >>= 1;
        int medianX = x + dimension;
        int medianY = y + dimension;
        QuadPackage quadPackage = null;
        if (point.getY() < medianY) {
            if (point.getX() < medianX) {
                quadPackage = children[0].insert(point, x, y, dimension);
                children[0] = quadPackage.getNode();
            }
            else {
                quadPackage = children[1].insert(point, medianX, y, dimension);
                children[1] = quadPackage.getNode();
            }
        }
        else {
            if (point.getX() < medianX) {
                quadPackage = children[2].insert(point, x, medianY, dimension);
                children[2] = quadPackage.getNode();
            }
            else {
                quadPackage = children[3].insert(point, medianX, medianY,
                    dimension);
                children[3] = quadPackage.getNode();
            }
        }
        quadPackage.setNode(this);
        return quadPackage;
    }


    /**
     * Remove the point into current QuadNode. The internal node will choose the
     * suitable sub-region and pass the removal commend down to a leaf. The
     * leaf will be merged if there
     * 
     * @param point
     *            the Point wished to be removed
     * @param x
     *            the x-coordinate of the current QuadNode
     * @param y
     *            the y-coordinate of the current QuadNode
     * @param dimension
     *            the dimension of the current QuadNode
     * @return a QuadPackage carrying current QuadNode and removed Point
     */
    public QuadPackage remove(Point point, int x, int y, int dimension) {
        dimension >>= 1;
        int medianX = x + dimension;
        int medianY = y + dimension;
        QuadPackage quadPackage = null;
        if (point.getY() < medianY) {
            if (point.getX() < medianX) {
                quadPackage = children[0].remove(point, x, y, dimension);
                children[0] = quadPackage.getNode();
            }
            else {
                quadPackage = children[1].remove(point, medianX, y, dimension);
                children[1] = quadPackage.getNode();
            }
        }
        else {
            if (point.getX() < medianX) {
                quadPackage = children[2].remove(point, x, medianY, dimension);
                children[2] = quadPackage.getNode();
            }
            else {
                quadPackage = children[3].remove(point, medianX, medianY,
                    dimension);
                children[3] = quadPackage.getNode();
            }
        }
        Point removed = quadPackage.getPoint();
        quadPackage.setNode(merge(x, y, dimension));
        quadPackage.setPoint(removed);
        return quadPackage;
    }


    /**
     * Merge children into current node if the total number of points stored in
     * children are lesser than 4 or there is only 0~1 non-empty child.
     * 
     * @param x
     *            the x-coordinate of the current QuadNode
     * @param y
     *            the y-coordinate of the current QuadNode
     * @param dimension
     *            the dimension of the current QuadNode
     * @return the new root after merging
     */
    private QuadNode merge(int x, int y, int dimension) {
        int pointNum = 0;
        int leaf = 0;
        int empty = 0;
        QuadNode emptyNode = QuadNull.get();
        for (QuadNode child : children) {
            pointNum += child.getQuantity();
            leaf += child.isLeaf() ? 1 : 0;
            empty += child == emptyNode ? 1 : 0;
        }
        if ((pointNum > 3 && leaf > 1) || (leaf + empty < 4)) {
            return this;
        }
        QuadNode node = QuadNull.get();
        for (int i = 0; i < children.length ; i++) {
            for (Point subPoint : children[i].getPoints()) {
                node = node.insert(subPoint, x, y, dimension).getNode();
            }
        }
        return node;
    }


    /**
     * Get the total quantity of points stored in the current node.
     * 
     * @return the total quantity of points stored in the current node
     */
    public int getQuantity() {
        return 0;
    }


    /**
     * Get all points stored in the current node
     * 
     * @return a List of points stored in the current node
     */
    public List<Point> getPoints() {
        return new ArrayList<>();
    }


    /**
     * search the points with specific name. The internal node will traverse all
     * children to find the points with specific name.
     * 
     * @param name
     *            the target name
     * @return a List of Point with the specific name
     */
    public List<Point> search(String name) {
        List<Point> result = new ArrayList<>();
        for (QuadNode child : children) {
            for (Point point : child.search(name)) {
                result.add(point);
            }
        }
        return result;
    }


    /**
     * Search and print the points contained in the specific region. The method
     * will cut the region up to 4 legal sub-region and pass down to the next
     * level.
     * 
     * @param regionX
     *            the x-coordinate of the target region
     * @param regionY
     *            the y-coordinate of the target region
     * @param regionW
     *            the width of the target region
     * @param regionH
     *            the height of the target region
     * @param x
     *            the x-coordinate of the current QuadNode
     * @param y
     *            the y-coordinate of the current QuadNode
     * @param dimension
     *            the dimension of the current QuadNode
     * @return the number of visited nodes
     */
    public int regionsearch(
        int regionX,
        int regionY,
        int regionW,
        int regionH,
        int x,
        int y,
        int dimension) {
        dimension >>= 1;
        int medianX = x + dimension;
        int medianY = y + dimension;
        int visited = 1;

        if (regionX < medianX && regionY < medianY) {
            int subRegionW = Math.min(regionW + regionX, medianX) - regionX;
            int subRegionH = Math.min(regionH + regionY, medianY) - regionY;
            visited += children[0].regionsearch(regionX, regionY, subRegionW,
                subRegionH, x, y, dimension);
        }
        if (medianX <= regionX + regionW && regionY < medianY) {
            int subRegionX = Math.max(regionX, medianX);
            int subRegionW = Math.min(regionW + regionX, medianX + dimension)
                - subRegionX;
            int subRegionH = Math.min(regionH, medianY - regionY);
            visited += children[1].regionsearch(subRegionX, regionY, subRegionW,
                subRegionH, medianX, y, dimension);
        }
        if (regionX < medianX && medianY <= regionY + regionH) {
            int subRegionY = Math.max(regionY, medianY);
            int subRegionW = Math.min(regionW, medianX - regionX);
            int subRegionH = Math.min(regionH + regionY, medianY + dimension)
                - subRegionY;
            visited += children[2].regionsearch(regionX, subRegionY, subRegionW,
                subRegionH, x, medianY, dimension);
        }
        if (medianX <= regionX + regionW && medianY <= regionY + regionH) {
            int subRegionX = Math.max(regionX, medianX);
            int subRegionY = Math.max(regionY, medianY);
            int subRegionW = Math.min(regionW + regionX, medianX + dimension)
                - subRegionX;
            int subRegionH = Math.min(regionH + regionY, medianY + dimension)
                - subRegionY;
            visited += children[3].regionsearch(subRegionX, subRegionY,
                subRegionW, subRegionH, medianX, medianY, dimension);
        }
        return visited;
    }


    /**
     * search and print the duplicated coordinates. The internal node will
     * traverse all children to print the duplicated coordinates.
     */
    public void duplicates() {
        for (QuadNode child : children) {
            child.duplicates();
        }
    }


    /**
     * Print the current node, sub-node, saved points in a specific format.
     * 
     * @param header
     *            the indentation
     * @param x
     *            the x-coordinate of the current QuadNode
     * @param y
     *            the y-coordinate of the current QuadNode
     * @param dimension
     *            the dimension of the current QuadNode
     * @return the total number of nodes in the subtree
     */
    public int dump(String header, int x, int y, int dimension) {
        System.out.print(header);
        System.out.print("Node at ");
        System.out.print(x);
        System.out.print(", ");
        System.out.print(y);
        System.out.print(", ");
        System.out.print(dimension);
        System.out.println(": Internal");
        header = header + "  ";
        int totalNode = 1;

        dimension >>= 1;
        int medianX = x + dimension;
        int medianY = y + dimension;
        totalNode += children[0].dump(header, x, y, dimension);
        totalNode += children[1].dump(header, medianX, y, dimension);
        totalNode += children[2].dump(header, x, medianY, dimension);
        totalNode += children[3].dump(header, medianX, medianY, dimension);
        return totalNode;
    }
}
