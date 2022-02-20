package idv.rozen;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.HashMap;
import idv.rozen.net_utils.Coinmarket;
import idv.rozen.net_utils.MyClient;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Test WS client");
        /*
        ArrayList<String> wantToekn = new ArrayList<String>();
        wantToekn.add("BTC");
        wantToekn.add("ETH");
        Coinmarket coin_market = new Coinmarket();
        coin_market.findPrice(wantToekn);
        */
        Map<String, String> header = new HashMap<String, String>();

        MyClient myClient = new MyClient("wss://stream.coinmarketcap.com/price/latest");
        
        int[] wantToken = {1};
        myClient.startClient();
        myClient.sendMessage(wantToken);
    }
}
