package SearchEngine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class Suggestion {

        
        public void word_file_suggest(String word) throws IOException {
                

                Suggestion suggestion= new Suggestion();
                DictionaryMaker dictionaryMaker= new DictionaryMaker();
                
        //        String input= "solid f121 agreement";
                String input= word;
                String[] processedWordsList=dictionaryMaker.preprocessing(dictionaryMaker.stopSet,input);
                        suggestion.findSuggestions(processedWordsList);
                        
        }



        
        private  void findSuggestions(String[] inputArr) {
                // TODO Auto-generated method stub
                
                try {
                        HashMap<String,HashSet<String>> hashMap= readFromFile();
                        System.out.println("read hash map"+hashMap);
                        
                        List<HashSet<String>> inputFileSet= new ArrayList<HashSet<String>>();
                        for(int i=0;i<inputArr.length;i++) {
                                if(hashMap.containsKey(inputArr[i]))
                                        inputFileSet.add(hashMap.get(inputArr[i]));
                                System.out.println("Keyword: "+ inputArr[i] + "\t" + "list: " + hashMap.get(inputArr[i]));
                        }
                        System.out.println();
                        //System.out.println("input file sets--> "+ inputFileSet);
                        if(inputFileSet.isEmpty()) {
                        	System.out.println("Not Found");
                        	System.exit(0);
                        }
                        HashSet<String> intersectionSet= inputFileSet.get(0);
                        for(int i=1;i< inputFileSet.size();i++ ) {
                                intersectionSet.retainAll(inputFileSet.get(i));
                        }
                        System.out.println("Intersection:");
                        for(String s : intersectionSet) {
                        	String url = SearchEngine.HTMLtoText.text_file_to_url.get(s);
                        	System.out.println(s + "--->" + "\t" +url);
                        }
                        
                } catch (ClassNotFoundException | IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }

        private HashMap<String, HashSet<String>> readFromFile() throws ClassNotFoundException, IOException {
                // TODO Auto-generated method stub
            FileInputStream fileIn = new FileInputStream("data//dictionary//dictionary.txt");
        ObjectInputStream in = new ObjectInputStream(fileIn);
                HashMap<String, HashSet<String>> hashMap =  (HashMap<String, HashSet<String>>) in.readObject();
        in.close();
        fileIn.close();
                return hashMap;
        }

        
}

