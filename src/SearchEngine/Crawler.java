package SearchEngine;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    static List<String> web_links = new ArrayList<String>();
    static Set<String> links_crawled = new HashSet<String>();
    static Map<String, String> html_file_to_url = new HashMap<>();
    Document doc;
    int count = 1;
	public boolean validateUrls(String url) {

//		for(int i=0; i<links_crawled.size(); i++) {
//			if(links_crawled.get(i).equals(url)) {
//				return false;
//			}
//		}
		if(links_crawled.contains(url)) {
			return false;
		}
		if(url.equals("javascript:void(0)")) {
			return false;
		}
		if(url.contains("ads")) {
            return false;
        }
		if(url.contains("instagram")) {
            System.out.println("Instagram is Blocked");
            return false;
        }
        if(url.contains("linkedin")) {
            System.out.println("Linkedin in Blocked");
            return false;
        }
        if(url.contains("facebook")) {
            System.out.println("facebook is Blocked");
            return false;
        }
        if(url.contains("twitter")) {
            System.out.println("Twitter is Blocked");
            return false;
        }
        return true;
	}

	public boolean crawl(String url) {
		if(validateUrls(url) == false) {
			return false;
		}
		//For handling of Malformed URL exception
		if(url.startsWith("https://")) {
			links_crawled.add(url);
		}
		else if(url.startsWith("http://")) {
			links_crawled.add(url);
		}
		else {
			return false;
		}
        try {
        	Connection conn = Jsoup.connect(url).userAgent(USER_AGENT);
            doc = conn.get();
            int response_code = conn.response().statusCode();
            if(response_code == 200) { //200 status shows that HTTP response is successful
            	try {
            		org.jsoup.nodes.Document document = Jsoup.connect(url).get();
					String html = document.html();
					String file_name = "data//html//"+ "w3schools" + count+ ".html";
					File html_file = new File(file_name);
					html_file_to_url.put(file_name, url);
					html_file.createNewFile();
					PrintWriter html_writer_obj = new PrintWriter(html_file);
					html_writer_obj.println(html);
					html_writer_obj.close();
					count++;
            	} 
	    		catch (Exception e) {
	    			e.printStackTrace();
	    		}
            }
            Elements links_on_web_page = doc.select("a[href]");
            for(Element link : links_on_web_page) {
            	if(validateUrls(link.toString())) {
            		web_links.add(link.absUrl("href"));
            	}
            }
            return true;
        }
        catch(IOException ioe) {
            return false;
        }
	}
	public void start_crawler() throws IOException {
		Crawler gd_obj = new Crawler();
		gd_obj.crawl("https://www.w3schools.com/");
		List<String> links = web_links;
		try {
			File url_file = new File("data//URLs//"+ "urls.txt");;
			url_file.createNewFile();
			PrintWriter writer_obj = new PrintWriter(url_file);
			for(int i=0; i<100; i++) {
				if(links.get(i) != "" && validateUrls(links.get(i))) {
					writer_obj.println(links.get(i));
					String url = links.get(i);
					if(url.equals("javascript:void(0)")) {
						continue;
					}
					gd_obj.crawl(links.get(i));
					ArrayList<String> crawledUrls = new ArrayList<String>(web_links);
					writer_obj.println();
					writer_obj.println(crawledUrls);
					crawledUrls.clear();
				}
			}
			writer_obj.close();
		}
		catch(Exception e) {
			System.out.println("Exception: " +e);
		}
		System.out.println(links_crawled.size());
	}
}