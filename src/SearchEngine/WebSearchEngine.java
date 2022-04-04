package SearchEngine;
import java.io.IOException;
import java.util.*;



public class WebSearchEngine {
	
	public static void options() {
		System.out.println("Choose one of the options from the below options list:");
		System.out.println("1. Search the pattern in the URL list of www.w3schools.com");
		System.out.println("2. Find the Pattern in the Text files");
		System.out.println("3. Word Search and finding top 5 pages");
		System.out.println("6. exit");
	}

	public static void start() throws IOException, InterruptedException {
		//Data Generation using Crawler and generating TXT files
		
		System.out.println("****Starting Data Generation*****");
		SearchEngine.Crawler crawl = new SearchEngine.Crawler();
		crawl.start_crawler();
		SearchEngine.HTMLtoText html_to_text = new SearchEngine.HTMLtoText();
		html_to_text.html_to_text();
		System.out.println("****Data is Generated*****");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter text you want to search in web search engine: ");
		String word = sc.nextLine();
		while(true) {
			options();
			int option = sc.nextInt();
			switch (option) {
			case 1:
				SearchEngine.Find_pattern_in_urls find_pattern_obj = new SearchEngine.Find_pattern_in_urls();
				find_pattern_obj.find_pattern(word);
				break;
			case 2:
				SearchEngine.BoyerMoore bm = new SearchEngine.BoyerMoore();
				bm.boyer_moore(word);
				break;
			case 3:
				SearchEngine.page_ranking pr = new SearchEngine.page_ranking();
				pr.rank_page(word);
				break;
			case 6:
				sc.close();
				System.exit(0);
			default:
				break;
			}
		}
	}
	
	public static void main(String args[]) throws IOException, InterruptedException {
		start();
	}
}
