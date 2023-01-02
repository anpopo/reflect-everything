package anpopo.reflect_constructor;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;

public class WebServer {

    public void startServer() throws IOException {
        HttpServer httpServer = HttpServer.create(ServerConfiguration.getInstance().getServerAddress(), 0);

        httpServer.createContext("/greet").setHandler(exchange -> {
            String responseMessage = ServerConfiguration.getInstance().getGreetingMessage();

            exchange.sendResponseHeaders(200, responseMessage.length());
            OutputStream responseBody = exchange.getResponseBody();
            responseBody
                    .write(responseMessage.getBytes());

            responseBody.flush();
            responseBody.close();
        });

        System.out.println("starting server on address : " + httpServer.getAddress().getHostName() + ":" + httpServer.getAddress().getPort());

        httpServer.start();
    }
}
