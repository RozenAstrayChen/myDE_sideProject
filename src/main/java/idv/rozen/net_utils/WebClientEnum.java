package idv.rozen.net_utils;

import java.lang.System.Logger;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.sound.midi.Track;
import org.apache.commons.lang3.ObjectUtils;
import org.java_websocket.enums.ReadyState;
import idv.rozen.net_utils.MsgWebSocketClient;


public enum WebClientEnum {
    /**
     * This class is use to enum websocket client
     */
    CLIENT;
    private static MsgWebSocketClient socketClient = null;

    public static void initClient(MsgWebSocketClient client) throws Exception{
        // define SSL need parameters
        // pls look here https://stackoverflow.com/questions/44572162/java-websocket-cannot-resolve-setwebsocketfactory
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] myTrustedAnchors = new X509Certificate[0];
                return myTrustedAnchors;
            }
    
            @Override
            public void checkClientTrusted(X509Certificate[] certs,
                                           String authType) {}
    
            @Override
            public void checkServerTrusted(X509Certificate[] certs,
                                           String authType) {}
        } };
        socketClient = client;
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts,  new SecureRandom());
        SSLSocketFactory factory = sslContext.getSocketFactory();
        socketClient.setSocketFactory(factory);
        socketClient.connectBlocking();
        // If you dont use header then socket client can't connet to coinmarketcap.
        //socketClient.addHeader("origin", "https://coinmarketcap.com");
        socketClient.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.82 Safari/537.36");
        socketClient.connect();
        while(!socketClient.getReadyState().equals(ReadyState.OPEN)){
            System.out.println("connecting... and client state is " + socketClient.getReadyState());
            Thread.sleep(2000);
            if(socketClient.getReadyState().equals(ReadyState.CLOSING) || socketClient.getReadyState().equals(ReadyState.CLOSED)){          
                socketClient.reconnect();
            }
            System.out.println("After reconnect statte:"+socketClient.getReadyState());
        }
        //socketClient.send("bytes");
        
    }
    public void sendPackage(String str) {
        /**
         * Send data to 
         */
        System.out.println("Send data"+ str);
        socketClient.send(str);
    }
}
