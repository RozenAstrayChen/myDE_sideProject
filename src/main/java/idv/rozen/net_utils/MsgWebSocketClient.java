package idv.rozen.net_utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;

import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class MsgWebSocketClient extends WebSocketClient{
    public String recvMessage = ""; 
    public MsgWebSocketClient(String url) throws URISyntaxException{
        super(new URI(url), new Draft_6455());
    }
    @Override
    public void onOpen(ServerHandshake shake){
        System.out.println("shake hand");
    }
    @Override
    public void onMessage(String paramString){
        System.out.println("recv: "+paramString);
        this.recvMessage = paramString;
    }
    @Override
    public void onClose(int paramInt, String paramString, boolean paramBoolean){
        System.out.println("close....");
    }
    @Override
    public void onError(Exception e){
        System.out.println("error happened.."+e);
    }
}
