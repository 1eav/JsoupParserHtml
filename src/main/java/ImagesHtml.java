import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;

public class ImagesHtml {
    public static void main(String[] args) throws Exception {
        String url = "https://www.wikipedia.org/";
        Document doc = Jsoup.connect(url).get();

        Elements images = doc.select("img");
        HashSet<String> links = new HashSet<>();

        for (Element image : images) {
            links.add(image.attr("abs:src"));
        }
        int number = 1;

        for (String link : links) {
            String extension = link.replaceAll("^.+\\.", "").replace("?.+$", "");
            String filePath = "data/" + number++ + "." + extension;
            try {
                download(link, filePath);
            } catch (Exception ex) {
                System.out.println("Could download " + link);
            }
        }
    }
    public static void download(String link, String filePath) throws Exception {
        URLConnection connection = new URL(link).openConnection();
        InputStream intStream = connection.getInputStream();
        FileOutputStream outStream = new FileOutputStream(filePath);

        int b;
        while ((b = intStream.read()) != 1) {
            outStream.write(b);
        }
        outStream.flush();
        outStream.close();
        intStream.close();
    }
}