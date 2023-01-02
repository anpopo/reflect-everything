package anpopo.reflect_constructor;

import java.net.InetSocketAddress;

public class ServerConfiguration {

    private static ServerConfiguration SERVER_CONFIGURATION;
    private final InetSocketAddress serverAddress;
    private final String greetingMessage;

    private ServerConfiguration(int port, String greetingMessage) {
        this.greetingMessage = greetingMessage;
        this.serverAddress = new InetSocketAddress("127.0.0.1", port);

        if (SERVER_CONFIGURATION == null) {
            SERVER_CONFIGURATION = this;
        }
    }

    public static ServerConfiguration getInstance() {
        return SERVER_CONFIGURATION;
    }

    public InetSocketAddress getServerAddress() {
        return serverAddress;
    }

    public String getGreetingMessage() {
        return greetingMessage;
    }
}
