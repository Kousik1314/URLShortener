import java.util.HashMap;
import java.util.Map;

public class URLShortener {
    private Map<String, String> urlMap;
    private String domain;
    private int urlId;

    public URLShortener(String domain) {
        this.urlMap = new HashMap<>();
        this.domain = domain;
        this.urlId = 0;
    }

    public String shortenURL(String originalURL) {
        String shortURL = domain + "/" + Integer.toHexString(urlId++);
        urlMap.put(shortURL, originalURL);
        return shortURL;
    }

    public String getOriginalURL(String shortURL) {
        return urlMap.get(shortURL);
    }
}