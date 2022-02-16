package idv.rozen;

import java.util.ArrayList;

import idv.rozen.net_utils.Coinmarket;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Test WS client");
        ArrayList<String> wantToekn = new ArrayList<String>();
        wantToekn.add("btc");
        wantToekn.add("eth");
        Coinmarket coin_market = new Coinmarket();
        coin_market.FindPrice(wantToekn);
    }
}
