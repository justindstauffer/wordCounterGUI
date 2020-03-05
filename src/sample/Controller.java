package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

import javafx.scene.text.Text;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Controller {
    public Label listTop20;
    public TextField urlInput;


    public void findTop20(ActionEvent actionEvent) {
        String result = "";
        String a = "";
        ArrayList<String> strings = new ArrayList<String>();
        Set<String> word_set = new HashSet<String>();
        Document document = null;

        try {
            //Get Document object after parsing the html from given url.
            document = Jsoup.connect(urlInput.getText()).get();

            // Create String from html document
            result = document.body().text();
            // Split String into words by space after getting rid of all punctuation
            String[] testArr = result.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
            // Create HashMap for string and count
            HashMap<String, Integer> wordCountPairs = new HashMap<>();
            // For each string get the count and put (String, Count) into HashMap
            for (String w : testArr) {
                Integer freq = wordCountPairs.get(w);
                wordCountPairs.put(w, (freq == null) ? 1 : freq + 1);
            }
            // Sort HashMap by Values
            Map<String, Integer> hm1 = sortByValue(wordCountPairs);
            // Loop through 20 times and print the Top 20 Words - outputting the word and its count
            for(int i =1; i < 21; i++){
                a = a + ("" + i + " - " + hm1.entrySet().toArray()[hm1.size()-i] + "\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        listTop20.setText(a);
    }

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

}
