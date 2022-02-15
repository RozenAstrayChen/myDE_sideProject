package idv.rozen;

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
        Coinmarket coin_market = new Coinmarket("btc");
        coin_market.listenWS();
        
    }
}
