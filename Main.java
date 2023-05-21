import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create();
        server.bind(null, 8080);
        server.createContext("/", Main::handleRequest);
        server.start();
        System.out.println("Server started on port 8080");
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {
        String response = getFileContent("index.html");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static String getFileContent(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}