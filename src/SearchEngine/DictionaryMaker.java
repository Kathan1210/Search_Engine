package SearchEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import textprocessing.In;

public class DictionaryMaker {
        HashMap<String,HashSet<String>> hashMap= new HashMap<>();
        CharArraySet stopSet;
        
        
        public File[] readTextFiles() {
                File[] allTextFiles = new File("data//Text").listFiles();
                
                return allTextFiles;
        }
        
        public void writeIntoFile(HashMap<String,HashSet<String>> hashMap) throws IOException {
                // TODO Auto-generated method stub
                FileOutputStream fileOut =new FileOutputStream("data//dictionary//"+"dictionary.txt");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(hashMap);
        out.close();
        fileOut.close();
        }
        
        public void dictionaryPreprocessing() {
                Scanner scanner;
                ArrayList<String> stopWords = new ArrayList<String>();
                try {
                        scanner = new Scanner(new File("data//dictionary//english stopwords.txt"));
                        while (scanner.hasNext()){
                                stopWords.add(scanner.next());
                        }
                } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                
                 stopSet = new CharArraySet(stopWords, true);
        }
        
        public String[] preprocessing(CharArraySet stopSet, String input) throws IOException  {
        	// TODO Auto-generated method stub
             ArrayList<String> result = new ArrayList<String>();
             Analyzer analyzer = new EnglishAnalyzer(stopSet); // Only uses the given "stopSet" but also runs a stemmer, so the result might not look like what you expected.
             TokenStream tokenStream = analyzer.tokenStream("contents", new StringReader(input));
             CharTermAttribute term = tokenStream.addAttribute(CharTermAttribute.class);
             tokenStream.reset();
             while(tokenStream.incrementToken()) {
                System.out.print("[" + term.toString() + "] ");
                result.add(term.toString());
             }
             tokenStream.close();
             analyzer.close();
             String[] resultArr= new String[result.size()];
             result.toArray(resultArr);
             return resultArr;
        }
        
        public void createDictionary() throws IOException {
                
                File[] allTextFiles= readTextFiles() ;
                for(int i=0;i<allTextFiles.length;i++) {
        //                System.out.println(allTextFiles[i].getName());
                        In in = new In(allTextFiles[i]);
                //        System.out.println(in.n);
                        while(in.hasNextLine()) {
                                String[] line= preprocessing(stopSet, in.readLine());
                                
                                for(String word: line) {
                                        
                                        if(hashMap.containsKey(word)) {
                                                HashSet<String> hashSet= hashMap.get(word);
                                                if(!hashSet.contains(allTextFiles[i].getName())) {
                                                                hashSet.add(allTextFiles[i].getName());
                                                                hashMap.put(word, hashSet);
                                                }
                                        }else {
                                                HashSet<String> hashSetNew= new HashSet<String>();
                                                hashSetNew.add(allTextFiles[i].getName());
                                                hashMap.put(word, hashSetNew);
                                        }
                                }
                                
                        }
                }
                
                System.out.println(hashMap);
                writeIntoFile(hashMap);
                
        }
        

        public void dictionary() throws IOException {
                // TODO Auto-generated method stub
                DictionaryMaker dictionaryMaker= new DictionaryMaker();
                dictionaryMaker.dictionaryPreprocessing();
                dictionaryMaker.createDictionary();
        }

}

