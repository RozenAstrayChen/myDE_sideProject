package idv.rozen.net_utils;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

public class MyClient{
    Client client;
    String url = "";
    public MyClient(String url){
        this.url = url;
    } 
    void initClient(){
        try {
            client =  new Client(new URI(this.url));
        } catch (URISyntaxException e) {
            //TODO: handle exception
            System.out.println("URISyntaxException error: " + e);
        }
    }
    void connect(){
        client.connect();
    }
}

class Client extends WebSocketClient{
    //Constructor
    public Client(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }
    
    public Client(URI serverURI) {
    super(serverURI);
    }

    public Client(URI serverUri, Map<String, String> httpHeaders) {
    super(serverUri, httpHeaders);
    }
    
    @Override
    public void onOpen(ServerHandshake handshakedata) {
        send("Hello, it is me. Mario :)");
        System.out.println("opened connection");
        // if you plan to refuse connection based on ip or httpfields overload: onWebsocketHandshakeReceivedAsClient
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The close codes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println(
            "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

}

