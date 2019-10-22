public class Practice4 {
    public static void main(String[] args) {
        String text = "N+N";
        Parser p = new Parser(text.toCharArray());
        boolean ok = p.run();
        System.out.println(ok);
    }
}
