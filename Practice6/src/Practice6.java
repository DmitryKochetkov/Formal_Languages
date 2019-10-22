public class Practice6 {
    public static void main(String[] args) {
        String text = "x+2-3";
        Parser parser = new Parser(text);
        Node node = parser.parse2();
        System.out.println("Evaluation: " + parser.eval2(node));
    }
}