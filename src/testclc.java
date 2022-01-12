
public class testclc {
    public static void main(String[] args) {
        String phone = "(202)-710  1250";
        phone = phone.replaceAll("[()\\s+\\-]", "");
        System.out.println(phone);
        System.out.println(phone.matches("[\\d]+"));
    }
}
