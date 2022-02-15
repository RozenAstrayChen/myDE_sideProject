package idv.rozen.net_utils;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URISyntaxException;

import org.jsoup.*;

public class Coinmarket {
    String http_url = "https://coinmarketcap.com/zh-tw/";
    String want_token = "btc";
    MyClient myWSClient;
    public Coinmarket(String want_token){
        this.want_token = want_token;
        myWSClient = new MyClient("ws://localhost:8887");
        myWSClient.initClient();
        
    }
    public void listenWS(){
        myWSClient.connect();
    }

    public void ModifyToeknName(String want_token){
        this.want_token = want_token;
    }
}
