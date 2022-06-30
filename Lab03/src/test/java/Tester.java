import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tester {

    private static Thread server;



    @BeforeClass
    public static void init() throws IOException {
        server = new Thread(new GreetServer());
        server.start();

    }



    @Test
    public void givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect() throws IOException {
        GreetClient client = new GreetClient();
        client.startConnection("127.0.0.1", 6666);
        client.sendMessage("hello server");
        GreetClient newClient = new GreetClient();
        newClient.startConnection("127.0.0.1", 6666);
        newClient.sendMessage("hello server");
        String response = client.readMessage();
        String newResponse = newClient.readMessage();
        assertEquals("hello client", response);
    }

    @AfterClass
    public static void stop() throws InterruptedException {

        server.join();

    }

}
