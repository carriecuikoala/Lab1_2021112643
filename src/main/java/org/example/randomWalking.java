package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class randomWalking
{
    public static void pathTodisk(Graph G)
    {
        String walkPath = G.randomWalk();
        try
        {
            // 创建 FileWriter 对象，第二个参数为 true 表示追加写入
            FileWriter fileWriter = new FileWriter("output.txt", false);

            // 创建 BufferedWriter 对象
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // 将字符串写入文件
            bufferedWriter.write(walkPath);
            bufferedWriter.newLine(); // 写入换行符

            // 关闭 BufferedWriter
            bufferedWriter.close();

            System.out.println("内容已写入文件。");
        } catch (IOException e) {
            System.out.println("写入文件时出错：" + e.getMessage());
        }
    }
}
