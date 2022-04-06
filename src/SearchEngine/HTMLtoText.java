package SearchEngine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HTMLtoText {
	static Map<String, String> text_file_to_url = new HashMap<>();
	public void HTMLtoText_converter(String file_name) throws IOException {
		//Converts the .html file to .txt file
		Crawler obj = new Crawler();
		FileReader input_file = new FileReader("data//html//"+file_name);
		String html_file_name = "data//html//"+file_name;
		String url = obj.html_file_to_url.get(html_file_name);
	    htmlparser.HTMLtoText parser = new htmlparser.HTMLtoText();
	    parser.parse(input_file);
	    input_file.close();
	    String textHTML = parser.getText();
	    file_name = file_name.substring(0, file_name.length()-5);
	    file_name += ".txt";
	    text_file_to_url.put(file_name, url);
	    BufferedWriter writerTxt = new BufferedWriter(new FileWriter("data//Text//" +file_name));
	    writerTxt.write(textHTML);
	    writerTxt.close();
	}
	public void html_to_text() throws IOException {
		File files[] = new File("data//html").listFiles();
		List<String> file_names = new ArrayList<>();
		for(File filename: files) {
			if(filename.getName().equals("Text")) {
				continue;
			}
			if(filename.getName().equals(null)) {
				continue;
			}
			file_names.add(filename.getName());
		}
		for(int i=0; i<file_names.size(); i++) {
			String file_name = file_names.get(i);
			//System.out.println(file_name);
			HTMLtoText_converter(file_name);
		}
	}
}
