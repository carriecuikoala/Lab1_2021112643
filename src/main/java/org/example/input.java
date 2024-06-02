package org.example;
import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.io.*;
import java.util.*;


public class input
{
    private static StringBuilder fileRead(String filename)
    {
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    private static StringBuilder processor(StringBuilder filewordOrigin)
    {
        for(int i = 0; i < filewordOrigin.length(); i++)
        {
            char ch = filewordOrigin.charAt(i);
            if(Character.isUpperCase(ch))
            {
                filewordOrigin.setCharAt(i, Character.toLowerCase(ch));
            }
            else if(Character.isLowerCase(ch))
            {
            }
            else
            {
                filewordOrigin.setCharAt(i, ' ');
            }
        }
        return filewordOrigin;
    }

    private static StringBuilder processor1(StringBuilder filewordOrigin)
    {
        for(int i = 0; i < filewordOrigin.length(); i++)
        {
            char ch = filewordOrigin.charAt(i);
            if(Character.isLetter(ch))
            {
            }

            else
            {
                filewordOrigin.setCharAt(i, ' ');
            }
        }
        return filewordOrigin;
    }
    //***********
    //
    private static String[] wordDeviding(StringBuilder fileword)
    {
        String str = fileword.toString();
        String[] parts = str.split("\\s+");
        //String[] parts = str.split("[\\s\\p{Punct}]+");
        return parts;
    }
    //***********
    //去重
    private static String[] projection(String[] parts)
    {
        //Set<String> projectedParts = new HashSet<String> ();
        Set<String> projectedParts = new LinkedHashSet<String>();
        Collections.addAll(projectedParts, parts);
        String[] arrayParts = projectedParts.toArray(new String[projectedParts.size()]);
        return arrayParts;
    }

    //proparts是去重后的单词表
    private static org.example.Graph newGraph(String[] proparts, String[] parts)
    {
        org.example.Graph G = new org.example.Graph(proparts.length, proparts);
        for(int i = 0; i < parts.length-1; i++)
        {
            String pre = parts[i];
            String after = parts[i+1];
            G.addEdge(pre,after);
        }
        return G;
    }
    public static org.example.Graph doIt() throws IOException {
        System.out.println("请输入待读入的文件名称：");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        StringBuilder filewordOrigin = fileRead(filename);
        StringBuilder fileword = processor(filewordOrigin);
        String[] fileparts= wordDeviding(fileword);
        String[] projectedParts = projection(fileparts);
        //for(int i = 0; i < fileparts.length; i++) System.out.println(fileparts[i]);
        //for(int i = 0; i < projectedParts.length; i++) System.out.println(projectedParts[i]);

        org.example.Graph G = newGraph(projectedParts, fileparts);
        return G;
    }

    public static String[] newParts(String newSentence)
    {
        //StringBuilder fileword = processor1(new StringBuilder(newSentence));
        String[] fileparts= wordDeviding(new StringBuilder(newSentence));
        //for(int i = 0; i < fileparts.length; i++) System.out.println(fileparts[i]);
        return fileparts;
    }

}

