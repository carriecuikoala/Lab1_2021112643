package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Draw
{
    public static void drawGraph(Graph G)
    {
        String[] array = G.printEdge();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("graph.txt",false)))
        {
            for (String str : array)
            {
                if (str == null || str.length() == 0)
                {
                    break;
                }
                writer.write(str);
                writer.newLine(); // 写入换行符
            }
            writer.close();
            System.out.println("字符串数组已写入文件。");
        } catch (IOException e)
        {
            System.out.println("写入文件时出错：" + e.getMessage());
        }
        try
        {
            // 构建 ProcessBuilder 对象
            ProcessBuilder pb = new ProcessBuilder("python", "draw.py");

            // 启动进程并等待其完成
            Process process = pb.start();
            int exitCode = 0;

            if (exitCode == 0)
            {
                System.out.println("Python 脚本成功执行。");
            }
            else
            {
                System.out.println("Python 脚本执行失败。");
            }
        }
        catch (IOException e)
        {
            System.out.println("调用 Python 脚本时出错：" + e.getMessage());
        }
    }
}
