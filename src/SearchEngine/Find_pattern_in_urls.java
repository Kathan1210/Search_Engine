package SearchEngine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Find_pattern_in_urls {
	
	public void FindUrlsinFile(String Text, String url_pattern) {
		int present = 0;
		String regex_pattern = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		Set<String> hs = new HashSet<>();
		Pattern p = Pattern.compile(regex_pattern);
		Matcher m = p.matcher(Text);
	    while (m.find( )) {
	    	if(m.group().contains(url_pattern) && !hs.contains(m.group())) {
	    		present = 1;
	    		System.out.println("URL: " + m.group());
	    		hs.add(m.group());
	    	}
	    } 
	    if(present == 0) {
	    	System.out.println("Not found");
	    }
	}
	
	public void read_input_url_txt_file(String pattern) throws IOException {
		File file = new File("data//URLs//urls.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String text = "";
		String s;
		while((s = br.readLine()) != null) {
			text += s;
		}
		FindUrlsinFile(text, pattern);
		br.close();
	}
	
	public void find_pattern(String pattern) throws IOException {
		read_input_url_txt_file(pattern);
	}
}
