package idv.rozen.software.net_utils;
import org.jsoup.*;

public class Coinmarket {
    String url = "https://coinmarketcap.com/zh-tw/";
    String want_token = "btc";
    public Coinmarket(String want_token){
        this.want_token = want_token;
    }

    public void ModifyToeknName(String want_token){
        this.want_token = want_token;
    }
}
