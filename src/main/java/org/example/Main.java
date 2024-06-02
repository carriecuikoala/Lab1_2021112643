package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
    public static void main(String[] args) throws IOException {
        Graph G = input.doIt();
        Draw.drawGraph(G);
        System.out.println("查询桥接词输入1，根据桥接词生成新文本输入2，计算最短路径输入3，随机游走输入4,退出输入0:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int op = Integer.parseInt(reader.readLine());
        while(op!=0){
            switch (op) {
                case 1:
                    org.example.BridgeWords.Bridge(G);
                    break;
                case 2:
                    org.example.NewSentence.newBridge(G);
                    break;
                case 3:
                    shortestRoad.getRoad(G);
                    break;
                case 4:
                    randomWalking.pathTodisk(G);
                    break;
            }
            op = Integer.parseInt(reader.readLine());
        }
    }
}