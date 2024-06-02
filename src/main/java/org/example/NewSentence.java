package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Random;

public class NewSentence
{
    public static void newBridge(org.example.Graph G) throws IOException
    {
        System.out.println("Please type new sentence.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String newInput = reader.readLine();
        String last = generateNewText(newInput,G);
        System.out.println(last);
    }

    public static String generateNewText(String inputText, Graph G) throws IOException
    {
        String[] newParts = input.newParts(inputText);
        LinkedList<String> linkedList = new LinkedList<>();
        for (int i = 0; i< newParts.length && newParts[i] != null; i++)
        {
            linkedList.add(newParts[i]);
        }
        int tmp=0;
        for(int i = 0; i< newParts.length - 1 && newParts[i+1] != null; i++)
        {
            String input1 = newParts[i];
            String input2 = newParts[i+1];
            String[] bridgeWords = G.getBridge(input1, input2);
            if(bridgeWords==null || bridgeWords[0] == null)
            {
                continue;
            }
            else
            {
                if(bridgeWords.length == 1)
                {
                    linkedList.add(i + 1 + tmp, bridgeWords[0]);
                }
                else
                {
                    Random random = new Random();
                    int randomInRange = random.nextInt(bridgeWords.length);
                    linkedList.add(i + 1 + tmp, bridgeWords[0]);
                }
                tmp++;
            }
        }
        StringBuilder last = new StringBuilder();
        for (String str : linkedList){
            last.append(str+" ");
        }
        return last.toString();
    }
}
