package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BridgeWords
{
    public static void Bridge(Graph G) throws IOException
    {
        System.out.println("Please devide every two words with'\\n'");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input1 = reader.readLine();
        String input2 = reader.readLine();
        String[] bridgeWords = G.getBridge(input1, input2);
        if(bridgeWords == null)
        {
            System.out.println("No word1 or word2 in the graph!");
            return;
        }
        if (bridgeWords[0] == null)
        {
            System.out.println("No bridge words from word1 to word2!");
            return;
        }
        System.out.print("The bridge words from word1 to word2 are: ");
        for (int i = 0; i < bridgeWords.length; i++)
        {
            String word = bridgeWords[i];
            if(bridgeWords[i+1] == null && i!=0) {
                System.out.print("and " + word + ".\n");
                break;
            }
            if(bridgeWords[i+1] == null && i==0) {
                System.out.print(word + ".\n");
                break;
            }
            else {
                System.out.print(word + ",");
            }
        }
    }

}
