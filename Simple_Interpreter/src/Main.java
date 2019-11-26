import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("E:\\Github Repositories\\Formal_Languages\\Simple_Interpreter\\src\\program.txt"))
        {
            String program = "";
            int c = 0;
            while ((c = reader.read()) != -1)
                program += (char)c;
            System.out.println(program);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
