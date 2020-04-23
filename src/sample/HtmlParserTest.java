package sample;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class HtmlParserTest {

    @Test
    void parseHtml() {
        String testUrl = "http://shakespeare.mit.edu/macbeth/full.html";
        HtmlParser parser = new HtmlParser("http://shakespeare.mit.edu/macbeth/full.html");
        HashMap<String, Integer> result = parser.getTopTwentyWords();
    }

    @Test
    void getTopTwentyWords() {
    }

    @Test
    void sortByValue() {
    }
}