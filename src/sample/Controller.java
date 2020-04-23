package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.*;


public class Controller {
    public Label listTop20;
    public TextField urlInput;
    public void findTop20(ActionEvent actionEvent) {
        // HtmlParser class to parse HTML from URL and get the top 20
        HtmlParser parser = new HtmlParser(urlInput.getText());
        // Get the top 20 words and save to a hashmap
        HashMap<String, Integer> hm1 = parser.getTopTwentyWords();
        // Result string
        String result = "";
        // Loop through 20 times and print the Top 20 Words - outputting the word and its count
        for(int i =1; i < 21; i++){
            result = result + ("" + i + " - " + hm1.entrySet().toArray()[hm1.size()-i] + "\n\n");
        }
        // Print top 20 to the label in the GUI
        listTop20.setText(result);
        System.out.println(hm1.size());
        StoreInDatabase db = new StoreInDatabase(hm1);
    }
}
