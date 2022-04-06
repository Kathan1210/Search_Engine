package SearchEngine;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.io.File;


public class page_ranking {
        
    public HashMap<String, Integer> wordFreq = new HashMap<String, Integer>();
    
    public void wordcount(String word, HashMap<String, Integer> hashMap) throws IOException {
        File dir = new File("data/Text");
        File[] allFiles =  dir.listFiles();           
        for (int i = 0  ; i < allFiles.length ;i++)
        {
            if(allFiles[i].isFile()) {
                Scanner file = new Scanner(allFiles[i]);
                while(file.hasNextLine()) {
                    int count = 0;
                    String check = file.nextLine();
                    String[] wordsLists = check.split(" ");
                    for(String temp : wordsLists) {
                        if (temp.equalsIgnoreCase(word)){
                            if (hashMap.containsKey(allFiles[i].getName())) {
                                count = hashMap.get(allFiles[i].getName())+1;
                                hashMap.put(allFiles[i].getName(), count);
                            }
                            else {
                            	hashMap.put(allFiles[i].getName(), 1);
                            }
                        }
                    }
                }                
            }
        }
        LinkedHashMap<String, Integer> hashmap1 = new LinkedHashMap<>();
         hashMap.entrySet() 
                        .stream() 
                        .sorted(HashMap.Entry.comparingByValue(Comparator.reverseOrder()))
                        .forEachOrdered(entry -> hashmap1.put(entry.getKey(), entry.getValue()));
         int count = 0;
         System.out.println("Top 5 Results");
         for(Map.Entry<String, Integer> entry : hashmap1.entrySet()) {
        	 String key = entry.getKey();
        	 String url = SearchEngine.HTMLtoText.text_file_to_url.get(key);
        	 System.out.println(key + "----->" +"    "+url);
        	 count++;
        	 if(count == 5) {
        		 break;
        	 }
         }
     }
    
    public void rank_page (String word) throws IOException {
    	wordcount(word, wordFreq);
    }
}

