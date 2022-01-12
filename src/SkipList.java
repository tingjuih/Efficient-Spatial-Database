import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author Ting-Jui Hsu
 * 
 * @version 2021-09-18
 * 
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class SkipList<K extends Comparable<? super K>, V>
    implements Iterable<KVPair<K, V>> {
    private SkipNode head; // First element of the top level
    private int size; // number of entries in the Skip List
    private Random value;

    /**
     * Initializes the fields head, size, level, and random object
     */
    public SkipList() {
        head = new SkipNode(null, 0);
        size = 0;
        value = new Random(50);
    }


    /**
     * Returns a random level number which is used as the depth of the SkipNode
     * 
     * @return a random level number
     */
    private int randomLevel() {
        int lev;
        for (lev = 0; (value.nextInt() & 1) == 0; lev++) {
            // Do nothing
        }
        return lev; // returns a random level
    }


    /**
     * Searches for the KVPair using the key which is a Comparable object.
     * 
     * @param key
     *            key to be searched for
     * @return the ArrayList of KVPair with the target key
     */
    public ArrayList<KVPair<K, V>> search(K key) {
        ArrayList<KVPair<K, V>> results = new ArrayList<>();
        if (size == 0) {
            return results;
        }
        // find the node with the maximum key smaller than the target's
        SkipNode node = head;

        for (int level = head.getLevel(); level >= 0; level--) {
            SkipNode next = node.forward[level];
            while (next != null && (next.element().getKey().compareTo(
                key) < 0)) {
                node = next;
                next = node.forward[level];
            }
        }

        // store the results
        node = node.forward[0];
        while (node != null && node.element().getKey().compareTo(key) == 0) {
            results.add(node.element());
            node = node.forward[0];
        }
        return results;
    }


    /**
     * @return the size of the SkipList
     */
    public int size() {
        return size;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicoragraphical order.
     * 
     * @param it
     *            the KVPair to be inserted
     */
    public void insert(KVPair<K, V> it) {
        int level = randomLevel();
        adjustHead(level);
        SkipNode node = new SkipNode(it, level);
        SkipNode parent = head;
        for (int i = level; i >= 0; i--) {
            SkipNode curr = parent.forward[i];
            while (curr != null && curr.element().compareTo(it) < 0) {
                parent = curr;
                curr = curr.forward[i];
            }
            node.forward[i] = curr;
            parent.forward[i] = node;
        }
        size++;
    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices than the head.
     * 
     * @param newLevel
     *            the number of levels to be added to head
     */
    @SuppressWarnings("unchecked")
    private void adjustHead(int newLevel) {
        if (head.getLevel() < newLevel) {
            SkipNode[] newForward = (SkipNode[])Array.newInstance(
                SkipList.SkipNode.class, newLevel + 1);
            System.arraycopy(head.forward, 0, newForward, 0,
                head.forward.length);
            head.forward = newForward;
            head.level = newLevel;
        }
    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     * 
     * @param key
     *            the key of KVPair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */
    public KVPair<K, V> remove(K key) {
        // find the first parent with the highest forward level
        SkipNode target = null;
        SkipNode parent = head;
        for (int level = head.getLevel(); level >= 0; level--) {
            SkipNode current = parent.forward[level];
            while (current != null && current.element().getKey().compareTo(
                key) < 0) {
                parent = current;
                current = current.forward[level];
            }
            if (current != null && current.element().getKey().compareTo(
                key) == 0) {
                target = current;
                break;
            }
        }
        if (target == null) {
            return null;
        }

        // remove the target
        for (int level = target.getLevel(); level >= 0; level--) {
            SkipNode current = parent.forward[level];
            while (current != target) {
                parent = current;
                current = current.forward[level];
            }
            parent.forward[level] = current.forward[level];
        }
        size--;
        return target.element();
    }


    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val
     *            the value of the KVPair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */
    public KVPair<K, V> removeByValue(V val) {
        // find the target
        SkipNode node = head.forward[0];
        SkipNode target = null;
        while (node != null) {
            if (node.element().getValue().equals(val)) {
                target = node;
                break;
            }
            node = node.forward[0];
        }

        if (target == null) {
            return null;
        }
        // find the first parent with the highest forward level
        SkipNode parent = head;
        int level = target.getLevel();
        for (SkipNode current = null; current != target; level--) {
            current = parent.forward[level];
            while (current != target) {
                parent = current;
                current = current.forward[level];
            }
        }
        // remove the target
        for (level++; level >= 0; level--) {
            SkipNode current = parent.forward[level];
            while (current != target) {
                parent = current;
                current = current.forward[level];
            }
            parent.forward[level] = current.forward[level];
        }
        size--;
        return target.element();
    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     * 
     * @param pair
     *            the KVPair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */
    public KVPair<K, V> remove(KVPair<K, V> pair) {
        // find the first parent with the highest forward level
        if (size == 0) {
            return null;
        }
        // find the node with the maximum key smaller than the target's
        K key = pair.getKey();
        SkipNode node = head;

        for (int level = head.getLevel(); level >= 0; level--) {
            SkipNode next = node.forward[level];
            while (next != null && (next.element().getKey().compareTo(
                key) < 0)) {
                node = next;
                next = node.forward[level];
            }
        }

        // find the target
        V val = pair.getValue();
        SkipNode target = null;
        node = node.forward[0];
        while (node != null && node.element().getKey().compareTo(key) == 0) {
            if (node.element().getValue().equals(val)) {
                target = node;
                break;
            }
            node = node.forward[0];
        }
        if (target == null) {
            return null;
        }

        // remove the target
        SkipNode parent = head;
        for (int level = target.getLevel(); level >= 0; level--) {
            SkipNode current = parent.forward[level];
            while (current != target) {
                parent = current;
                current = current.forward[level];
            }
            parent.forward[level] = current.forward[level];
        }
        size--;
        return target.element();
    }


    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump() {
        System.out.println("SkipList Dump:");
        System.out.println(toString());
    }


    @Override
    /**
     * Returns the elements and size in the current skip list in the form of
     * string
     * 
     * @return string consist of elements and size in the current skip list
     */
    public String toString() {
        StringBuilder output = new StringBuilder();
        SkipNode node = head;
        while (node != null) {
            output.append(node);
            output.append('\n');
            node = node.forward[0];
        }
        output.append("The SkipList's Size is: ");
        output.append(size());
        return output.toString();
    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     * 
     * @author Ting-Jui Hsu
     * 
     * @version 2021-09-18
     */
    private class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        // what is this
        private SkipNode[] forward;
        // the number of levels
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from the random level method in the SkipList.
         * 
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            forward = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
                level + 1);
            this.level = level;
        }


        /**
         * Returns the KVPair stored in the SkipList.
         * 
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }


        /**
         * Returns the SkipNode's number of levels.
         * 
         * @return the the number of levels that the SkipNode should have
         */
        public int getLevel() {
            return level;
        }


        /**
         * Returns the data of SkipNode in a human readable string
         * 
         * @return the string consist of the maximum depth and a key-value pair
         */
        public String toString() {
            StringBuilder output = new StringBuilder();
            output.append("level: ");
            output.append(forward.length);
            output.append(" Value: ");
            if (pair == null) {
                output.append("null");
            }
            else {
                output.append(pair);
            }
            return output.toString();
        }
    }


    /**
     * This class is for the iterator interface of SkipList
     */
    private class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current;

        /**
         * Initialize the iterative pointer to the head.
         */
        public SkipListIterator() {
            current = head;
        }


        /**
         * @return the boolean indicating if there are unseen elements
         */
        @Override
        public boolean hasNext() {
            return current.forward[0] != null;
        }


        /**
         * @return the next KVPair<K, V> element in the list for iteration
         */
        @Override
        public KVPair<K, V> next() {
            current = current.forward[0];
            return current.element();
        }

    }

    /**
     * @return iterator of KVPair<K, V> for the skip list
     */
    @Override
    public Iterator<KVPair<K, V>> iterator() {
        return new SkipListIterator();
    }

}
