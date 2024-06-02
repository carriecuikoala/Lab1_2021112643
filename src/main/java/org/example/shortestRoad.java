package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class shortestRoad
{
    public static void getRoad(org.example.Graph G) throws IOException
    {
        System.out.println("Please devide every two words with'\\n'");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input1 = reader.readLine();
        String input2 = reader.readLine();
        String bridgeRoads = G.calcShortestPath(input1, input2);
        int dis = G.calcShortestDic(input1, input2);
        if(bridgeRoads == null) System.out.println("There are no roads between the two words");
        else {
            System.out.println("The shortest distance is: " + dis);
            System.out.println("The shortest path is: " + bridgeRoads);
        }
    }
}
