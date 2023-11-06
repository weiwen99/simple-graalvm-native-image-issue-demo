import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * this will also produce the issue.
 * run and check:
 * ~/projects/simple-graalvm-native-image-issue-demo/src/main/java/simple (main*?) $ javac Example.java && native-image Example && rm -fr /tmp/xyz && ./example && tree /tmp/xyz
 */
public class Example {
    public static void main(String[] args) throws IOException {
        String dir = "/tmp/xyz";
        Files.createDirectories(Paths.get(dir));
        String fileName = dir + "/" + "我可以吞下玻璃而不伤害身体.txt";
        FileOutputStream outputStream = new FileOutputStream(fileName);
        outputStream.write("玻璃".getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }
}
