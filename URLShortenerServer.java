import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class URLShortenerServer {
    private static final int PORT = 8080;
    private static URLShortener urlShortener = new URLShortener("https://localhost:" + PORT);

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/shorten", new ShortenHandler());
        server.createContext("/", new RedirectHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port " + PORT);
    }

    static class ShortenHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                String originalURL = new String(exchange.getRequestBody().readAllBytes());
                String shortURL = urlShortener.shortenURL(originalURL);
                exchange.sendResponseHeaders(200, shortURL.length());
                OutputStream os = exchange.getResponseBody();
                os.write(shortURL.getBytes());
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }

    static class RedirectHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            String shortURL = "https://localhost:" + PORT + path;
            String originalURL = urlShortener.getOriginalURL(shortURL);

            if (originalURL != null) {
                exchange.getResponseHeaders().set("Location", originalURL);
                exchange.sendResponseHeaders(302, -1); // Redirect
            } else {
                String response = "URL not found";
                exchange.sendResponseHeaders(404, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
}