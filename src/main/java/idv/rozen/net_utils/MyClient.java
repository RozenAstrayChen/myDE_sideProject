package idv.rozen.net_utils;
import java.net.URISyntaxException;
import java.util.Map;
import org.json.JSONObject;
import idv.rozen.net_utils.MsgWebSocketClient;
import org.java_websocket.drafts.Draft_6455;


public class MyClient{
    String url;
    Map header;
    public MyClient(String url){
        this.url = url;
    } 
    public void startClient() {
        try{
            MsgWebSocketClient client = new MsgWebSocketClient(url);
            WebClientEnum.CLIENT.initClient(client);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void sendMessage(int[] wantTokenId){
        String wantToeknStr = testSendMessage(wantTokenId);
        WebClientEnum.CLIENT.sendPackage(wantToeknStr); 
    }

    public String testSendMessage(int[] wantTokenId){
        JSONObject obj1 = new JSONObject();
        JSONObject obj2 = new JSONObject();
    
        obj2.put("cryptoIds", wantTokenId).put("index", "null").toString();
        
        String jsonStr = obj1.put("method", "subscribe").put("id", "price").put("data", obj2).toString();
        return jsonStr;

    }
}
