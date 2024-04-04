import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetDocument {
    public static void main(String[] args) {
        try {
            Document document = Jsoup.connect("https://ru.wikipedia.org/").get();
            Elements titleElements = document.select("a");//<h3>, <h1>, <p>, <a>
            for (var element : titleElements) {
                System.out.println(element.attr("href"));//href, src, class
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}