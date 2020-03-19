package sample;

import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TestClass {

    public static void main(String args[]){
        String result = "";
        ArrayList<String> strings = new ArrayList<String>();
        Set<String> word_set = new HashSet<String>();
        print("Top 20:");
        Document document;
        getTop20("http://shakespeare.mit.edu/macbeth/full.html");
        print("done");
    }

    public static void getTop20(String URL) {
        Document document;
        String result;
        try {
            //Get Document object after parsing the html from given url.
            document = Jsoup.connect(URL).get();
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
                System.out.println("" + i + " - " + hm1.entrySet().toArray()[hm1.size()-i] );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Sort HashMap by Values from lowest to highest
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

    public static void print(String string) {
        System.out.println(string);
    }
}
