package idv.rozen.net_utils;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import org.jsoup.*;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import org.json.JSONObject;
import org.json.JSONPointerException;
import org.json.JSONException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class Coinmarket {
    /**
     * This class need find the Token Id in the first time and storage to dict
     */
    String http_url = "https://coinmarketcap.com/zh-tw/";
    String getTokenId_url = "https://api.coinmarketcap.com/data-api/v3/map/all?listing_status=active,untracked&exchangeAux=is_active,status&cryptoAux=is_active,status&start=1&limit=10000";
    Dictionary iwN = new Hashtable();
    Dictionary nwI = new Hashtable();
    
    MyClient myWSClient;
    public Coinmarket(){
        FindTokenId(); // using get request to get token id and name
        //myWSClient = new MyClient("ws://localhost:8887");
        //myWSClient.initClient();
        
    }
    public void FindTokenId() {
        try {
            URL url = new URL(this.getTokenId_url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            JsonNode jsonStr = Convert2Json(content);
            storage2Dict(jsonStr.get("data").get("exchangeMap"));
            
        } catch (IOException e) {
            //TODO: handle exception
            System.out.println("IOException happend: "+ e);
        } catch (JSONException e){
            System.out.println("JSONException happend: "+ e);
        }
    }
    public Dictionary FindPrice(ArrayList<String> wantToken){
        Dictionary nwP = new Hashtable();
        for (String i : wantToken){
            int id = (int) this.nwI.get(i);
            System.out.println(id);
        }
        return nwP;
    }
    public JsonNode Convert2Json(StringBuilder str) throws JsonMappingException, JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonStr = mapper.readTree(str.toString());
        return jsonStr;
    }
    public void storage2Dict(JsonNode json){
        Iterator<JsonNode> elements = json.iterator();
        while(elements.hasNext()){
            JsonNode element = elements.next();
            // token name
            this.iwN.put(element.get("id"), element.get("slug"));
            this.nwI.put(element.get("slug"), element.get("id"));
        }
    }
    public void listenWS(){
        myWSClient.connect();
    }
}
