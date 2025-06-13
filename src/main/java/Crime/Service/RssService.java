package Crime.Service;

import Crime.Model.Event;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class RssService {

    public List<Event> fetchMalmöEvents() throws Exception {
        URL url = new URL("https://polisen.se/aktuellt/rss/skane/handelser-rss---skane/");
        Document doc = (Document) DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url.openStream());

        NodeList items = doc.getElementsByTagName("item");
        List<Event> events = new ArrayList<>();

        for (int i = 0; i < items.getLength(); i++) {
            Element element = (Element) items.item(i);
            String title = element.getElementsByTagName("title").item(0).getTextContent();
            String link = element.getElementsByTagName("link").item(0).getTextContent();
            String description = element.getElementsByTagName("description").item(0).getTextContent();
            String pubDate = element.getElementsByTagName("pubDate").item(0).getTextContent();

            if (title.contains("Malmö") || description.contains("Malmö")) {
                String area = extractArea(title, description);
                Event evt = new Event();
                evt.setTitle(title);
                evt.setLink(link);
                evt.setDescription(description);
                evt.setPubDate(pubDate);
                evt.setArea(area);
                events.add(evt);
            }
        }

        return events;
    }

    private String extractArea(String title, String description) {
        String[] parts = title.split(",");
        if (parts.length >= 3) {
            String areaRaw = parts[2].trim();
            if (!areaRaw.equalsIgnoreCase("malmö")) {
                return areaRaw;
            }
        }
        for (MalmoDistrict d : MalmoDistrict.values()) {
            if (description.toLowerCase().contains(d.getName().toLowerCase())) {
                return d.getName();
            }
        }
        return "Malmö";
    }
}
