package SearchEngine;
import java.io.File;

public class BoyerMoore {
	public void boyer_moore_util(String pattern) {
		int position;
		File webPages = new File("data//Text//");
		File[] listFiles = webPages.listFiles();
		for(int i =0; i<listFiles.length; i++) {
			if(listFiles[i].isFile()) {
				textprocessing.In filename = new textprocessing.In(listFiles[i]);
				String text = "";
				while (!filename.isEmpty()) {
					String str1 = filename.readLine();
					text += str1;
				}
				textprocessing.BoyerMoore bm = new textprocessing.BoyerMoore(text);
				position = bm.search(pattern);
				if(position != 0) {
					System.out.println("Position of the pattern in " + listFiles[i] + " is " + position);
				}
			}
		}
	}
	public void boyer_moore(String pattern) {
		boyer_moore_util(pattern);
	}
}
