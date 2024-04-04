import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HtmlDownloadImageParser {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://www.wikipedia.org/").get();

        Elements images = doc.select("img");
        for (Element image : images) {
            String imageUrl = image.absUrl("src");
            String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
            File destinationFile = new File(System.getProperty("user.home") + "/Documents/" + fileName);

            try {
                FileUtils.copyURLToFile(new URL(imageUrl), destinationFile);
                System.out.println("Image uploaded: " + fileName);
            } catch (IOException e) {
                System.err.println("Error loading image: " + e.getMessage());
            }
        }
    }
}