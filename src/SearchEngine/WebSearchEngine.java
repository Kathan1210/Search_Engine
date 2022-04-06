package SearchEngine;
import java.io.IOException;
import java.util.*;



public class WebSearchEngine {
	
	public static void options() {
		System.out.println("Choose one of the options from the below options list:");
		System.out.println("1. Search the pattern in the URL list");
		System.out.println("2. Find the Pattern in the Text files");
		System.out.println("3. Word Search and finding top 5 pages");
		System.out.println("4. Word file Suggestion");
		System.out.println("5. exit");
	}

	public static void start() throws IOException, InterruptedException {
		//Data Generation using Crawler and generating TXT files
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the website you want to crawl:");
		String website = sc.nextLine();
		System.out.println("****Starting Crawling*****");
		SearchEngine.Crawler crawl = new SearchEngine.Crawler();
		crawl.start_crawler(website);
		SearchEngine.HTMLtoText html_to_text = new SearchEngine.HTMLtoText();
		html_to_text.html_to_text();
		SearchEngine.DictionaryMaker dict_maker = new SearchEngine.DictionaryMaker();
		dict_maker.dictionary();
		System.out.println("****Ending Crawling*****");
		
		System.out.println("Please enter text you want to search in web search engine: ");
		String word = sc.nextLine();
		while(true) {
			options();
			int option = sc.nextInt();
			switch (option) {
			case 1:
				SearchEngine.Find_pattern_in_urls find_pattern_obj = new SearchEngine.Find_pattern_in_urls();
				find_pattern_obj.find_pattern(word);
				System.out.println();
				System.out.println();
				break;
			case 2:
				SearchEngine.BoyerMoore bm = new SearchEngine.BoyerMoore();
				bm.boyer_moore(word);
				System.out.println();
				System.out.println();
				break;
			case 3:
				SearchEngine.page_ranking pr = new SearchEngine.page_ranking();
				pr.rank_page(word);
				System.out.println();
				System.out.println();
				break;
			case 4:
				SearchEngine.Suggestion sug = new SearchEngine.Suggestion();
				sug.word_file_suggest(word);
				System.out.println();
				System.out.println();
				break;
			case 5:
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
