import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * this will also produce the issue.
 * run and check:
 * ~ $ javac Example.java && native-image Example && rm -fr /tmp/xyz && ./example && tree /tmp/xyz
 */
public class Example {

    private static String dir = "/tmp/xyz";

    public static void main(String[] args) throws IOException {
        // Chinese
        writeFile("我可以吞下玻璃而不伤害身体.txt");
        // Japanese
        writeFile("正規表現は非常に役に立つツールテキストを操作することです");
        // Korean
        writeFile("정규 표현식은 매우 유용한 도구 텍스트를 조작하는 것입니다");
    }

    private static void writeFile(String name) throws IOException {
        Files.createDirectories(Paths.get(dir));
        String fileName = dir + "/" + name + ".txt";
        FileOutputStream outputStream = new FileOutputStream(fileName);
        outputStream.write(name.getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }
}
