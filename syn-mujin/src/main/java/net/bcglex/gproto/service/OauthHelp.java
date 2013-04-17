package net.bcglex.gproto.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Logger;

import net.bcglex.gproto.model.AccessToken;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;


public class OauthHelp {

    private final static Logger LOG = Logger.getLogger(OauthHelp.class.getName());
    public static final String CLIENT_ID = "685966928465.apps.googleusercontent.com";
    public static final String CLIENT_SECRET = "YgCFel4FR8JJO4dCR1IhG7IC";
    public final static String REDIRECT_URL = "http://localhost:8888/login";
    public final static String STORE_KEY_ID="recepbot.access.token.key.id";
//    private final static String GOOGLE_API_KEY = "";
    public static void saveToken(AccessToken token){
    	Key key=Datastore.createKey(AccessToken.class, STORE_KEY_ID);
    	token.setKey(key);
    	Datastore.put(token);
    }
    public static AccessToken loadToken(){
    	Key key=Datastore.createKey(AccessToken.class, STORE_KEY_ID);
    	AccessToken token;
    	try{
        	token=Datastore.get(AccessToken.class, key);
    	} catch(org.slim3.datastore.EntityNotFoundRuntimeException e) {
    		return null;
    	}
    	return token;
    }
    public static void deleteToken(){
    	Key key=Datastore.createKey(AccessToken.class, STORE_KEY_ID);
    	try{
        	Datastore.delete(key);
    	} catch(org.slim3.datastore.EntityNotFoundRuntimeException e) {
    		return;
    	}
    }
	public static String getAccessToken(String returnCode) throws Exception{
		/** AccessTokenRequestを取得 */
		String requestAccessTokenStr="code="+returnCode+"&client_id="+CLIENT_ID+"&client_secret="
				+CLIENT_SECRET+"&redirect_uri="+URLEncoder.encode(REDIRECT_URL, "UTF-8")+"&grant_type=authorization_code";
		URL googleAccessTokenUrl=new URL("https://accounts.google.com/o/oauth2/token");
		HttpURLConnection conn=(HttpURLConnection)googleAccessTokenUrl.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
		if (requestAccessTokenStr.length() > 0) osw.write(requestAccessTokenStr);
		osw.flush();
		osw.close();
		osw = null;
		
       // 入力ストリームを取得
       InputStream is = conn.getInputStream();
       // リーダーを作成
       BufferedReader reader = new BufferedReader(new InputStreamReader(is));
       // レスポンスデータを取得
       String s;
       String responseStr = "";
       while ((s = reader.readLine()) != null) responseStr += s;
       // 入力ストリームを閉じる
       reader.close();
       reader = null;
       is.close();
       is = null;

       // コネクション切断
       conn.disconnect();
       conn = null;

        /** AccessTokenがとれたーー */
        LOG.info("Storing authentication token into the session");
//        LOG.info("accessToken: " + accessTokenResponse.getAccessToken());
//        LOG.info("refreshToken: " + accessTokenResponse.getRefreshToken());
        return responseStr;
	}
}
