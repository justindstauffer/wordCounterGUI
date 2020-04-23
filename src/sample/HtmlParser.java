

package sample;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

public class HtmlParser {
    String url;
    public HtmlParser(String url){
        this.url = url;
    }
    public HashMap<String, Integer> parseHtml(){
        Document document;
        String result;
        Map<String, Integer> finalResult;
        try {
            //Get Document object after parsing the html from given url.
            document = Jsoup.connect(url).get();
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
            return wordCountPairs;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public HashMap<String, Integer> getTopTwentyWords(){
        return sortByValue(parseHtml());
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