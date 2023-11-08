package simple

import java.net.URL
import java.io.{File, FileOutputStream}
import java.nio.charset.StandardCharsets
import sys.process.*

@main def main(args: String*): Unit =
  def write(fileName: String): Unit = {
    val outputStream = new FileOutputStream(s"/tmp/xyz/$fileName.txt")
    outputStream.write(s"$fileName\n".getBytes(StandardCharsets.UTF_8))
    outputStream.close()
  }
  println("中文文件名测试!") // console output works fine
  "mkdir -pv /tmp/xyz".!
  "touch /tmp/xyz/normal.txt".!
  // the chinese file name will become ?????????
  "touch /tmp/xyz/something_wrong_我可以吞下玻璃而不伤害身体.txt".!
  "mkdir -pv /tmp/wrong_dir哈哈".!
  // write by java, the chinese file name will become ?????????
  val fileName = "/tmp/xyz/java生成的文件名.conf"
  val outputStream = new FileOutputStream(fileName)
  // the chinese content is correct
  outputStream.write("我可以吞下玻璃而不伤害身体\n".getBytes(StandardCharsets.UTF_8))
  outputStream.close()
  // Japanese test
  write("正規表現は非常に役に立つツールテキストを操作することです")
  // Korean
  write("정규 표현식은 매우 유용한 도구 텍스트를 조작하는 것입니다")

  println("ls /tmp/xyz".!!)
