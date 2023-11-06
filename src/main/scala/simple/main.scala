package simple

import java.net.URL
import java.io.{File, FileOutputStream}
import java.nio.charset.StandardCharsets
import sys.process.*

@main def main(args: String*): Unit =
  println("中文文件名测试!")
  "mkdir -pv /tmp/xyz".!
  "touch /tmp/xyz/normal.txt".!
  "touch /tmp/xyz/something_wrong_我可以吞下玻璃而不伤害身体.txt".!
  "mkdir -pv /tmp/wrong_dir哈哈".!
  // write by java, the chinese file name will become ?????????
  val fileName = "/tmp/xyz/java生成的文件名.conf"
  val outputStream = new FileOutputStream(fileName)
  // the chinese content is correct
  outputStream.write("我可以吞下玻璃而不伤害身体\n".getBytes(StandardCharsets.UTF_8))
  outputStream.close()
  // but
  println("ls /tmp/xyz".!!)
