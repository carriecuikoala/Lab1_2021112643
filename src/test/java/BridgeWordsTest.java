import org.example.Graph;
import org.example.input;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BridgeWordsTest {

    private Graph graph;

    @BeforeEach
    public void setUp() throws IOException {
        graph = input.doIt();
    }

    @Test
    public void testCase1() {
        String[] bridgeWords = graph.getBridge("explore", "new");
        //int result1 = graph.getNumber("explore");
        //int result2 = graph.getNumber("new");
        //assertEquals(1, result1);
        //assertEquals(3, result2);
        String result = (bridgeWords != null && bridgeWords[0] != null)
                ? "The bridge words from word1 to word2 are: " + bridgeWords[0] + "."
                : "";
        assertEquals("The bridge words from word1 to word2 are: strange.", result);
    }

    @Test
    public void testCase2() {
        String[] bridgeWords = graph.getBridge("explore", "apple");
        assertEquals("No word1 or word2 in the graph!", bridgeWords == null ? "No word1 or word2 in the graph!" : "");
    }

    @Test
    public void testCase3() {
        String[] bridgeWords = graph.getBridge("strange", "worlds");
        String result = (bridgeWords != null && bridgeWords[0] != null)
                ? "The bridge words from word1 to word2 are: " + bridgeWords[0] + "."
                : "";
        assertEquals("The bridge words from word1 to word2 are: new.", result);
    }

    @Test
    public void testCase4() {
        String[] bridgeWords = graph.getBridge("life", "civilizations");
        assertEquals("No bridge words from word1 to word2!", bridgeWords != null && bridgeWords[0] == null ? "No bridge words from word1 to word2!" : "");
    }

    @Test
    public void testCase5() {
        String[] bridgeWords = graph.getBridge("apple", "banana");
        assertEquals("No word1 or word2 in the graph!", bridgeWords == null ? "No word1 or word2 in the graph!" : "");
    }

    @Test
    public void testCase6() {
        String[] bridgeWords = graph.getBridge("life", "life");
        assertEquals("No bridge words from word1 to word2!", bridgeWords != null && bridgeWords[0] == null ? "No bridge words from word1 to word2!" : "");
    }

    @Test
    public void testCase7() {
        String[] bridgeWords = graph.getBridge("seek", "new");
        String result = (bridgeWords != null && bridgeWords[0] != null)
                ? "The bridge words from word1 to word2 are: " + bridgeWords[0] + "."
                : "";
        assertEquals("The bridge words from word1 to word2 are: out.", result);
    }

    @Test
    public void testCase8() {
        String[] bridgeWords = graph.getBridge("seek", "");
        assertEquals("No word1 or word2 in the graph!", bridgeWords == null ? "No word1 or word2 in the graph!" : "");
    }
}
