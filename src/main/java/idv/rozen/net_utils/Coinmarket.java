package idv.rozen.net_utils;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import org.jsoup.*;
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
    
    Dictionary<Integer, String> iwN = new Hashtable<Integer, String>();
    Dictionary<String, Integer> nwI = new Hashtable<String, Integer>();
    
    MyClient myWSClient;
    public Coinmarket(){
        findTokenId(); // using get request to get token id and name
        //myWSClient = new MyClient("ws://localhost:8887");
        //myWSClient.initClient();
        
    }
    public void findTokenId() {
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
            JsonNode jsonStr = convert2Json(content);
            storage2Dict(jsonStr.get("data").get("cryptoCurrencyMap"));
            
        } catch (IOException e) {
            //TODO: handle exception
            System.out.println("IOException happend: "+ e);
        } catch (JSONException e){
            System.out.println("JSONException happend: "+ e);
        }
    }
    public Dictionary findPrice(ArrayList<String> wantToken){
        ArrayList<Integer> wantTokenID = new ArrayList<>();
        for (String i : wantToken){
            wantTokenID.add(this.nwI.get(i));
        }
        System.out.println(wantTokenID);
        return nwI;
    }
    public JsonNode convert2Json(StringBuilder str) throws JsonMappingException, JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonStr = mapper.readTree(str.toString());
        return jsonStr;
    }
    public void storage2Dict(JsonNode json){
        Iterator<JsonNode> elements = json.iterator();
        while(elements.hasNext()){
            JsonNode element = elements.next();
            // token name
            this.iwN.put(element.get("id").intValue(), element.get("symbol").textValue());
            this.nwI.put(element.get("symbol").textValue(), element.get("id").intValue());
        }
    }
}
