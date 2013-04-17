package net.bcglex.gproto.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.bcglex.gproto.model.AccessToken;
import net.bcglex.gproto.service.OauthHelp;

import org.codehaus.jackson.map.ObjectMapper;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestLocator;

public class LoginController extends Controller {
    
    private final static Logger LOG = Logger.getLogger(IndexController.class.getName());
    private final static String[] OAUTH_SCOPES = {"https://www.google.com/m8/feeds/profiles"};
	
    @Override
    public Navigation run() throws Exception {
	   	//トークンがないor期限切れのため取得
        response.setContentType("application/json"); 
		
        String error = param("error");
		/** Callbackのパラメータにerrorが含まれていればPermission Errorにする */
        if (error != null) {
            response.setContentType("text/plain");
            try {
                response.getWriter().println("There was a problem during authentication: " + error);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            LOG.severe("There was a problem during authentication: " + error);
            return null;
        }
        
        String code = param("code");
        /** Callbackのパラメータにcodeが含まれていればOAuth2認証成功 */
        
        if (code == null || code.isEmpty()) {
        	/** 認証スタートするところ(パラメータにcodeが含まれていない) */
        	System.out.println(OauthHelp.CLIENT_ID);
        	System.out.println(OauthHelp.REDIRECT_URL);
        	System.out.println(Arrays.asList(OAUTH_SCOPES));
        	String urlString="https://accounts.google.com/o/oauth2/auth?scope="+URLEncoder.encode(this.join(OAUTH_SCOPES, "+"), "UTF-8")+
        			"&redirect_uri="+URLEncoder.encode(OauthHelp.REDIRECT_URL, "UTF-8")+"&response_type=code&client_id="+OauthHelp.CLIENT_ID+"&approval_prompt=force";
        			
//            AuthorizationRequestUrl authorizeUrl = new AuthorizationRequestUrl(OauthHelp.CLIENT_ID, OauthHelp.REDIRECT_URL, Arrays.asList(OAUTH_SCOPES));
//            String authorizationUrl = authorizeUrl.build();
    	   	HttpServletRequest req=RequestLocator.get();
    	   	HttpSession session=req.getSession(true);
    	   	session.invalidate();

            LOG.info("Redirecting browser for OAuth 2.0 authorization to " + urlString);
            try {
            	/** 認証スタート */
                response.sendRedirect(urlString);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return forward("Login.jsp");
//            return null;
        }else{
        	/** Callbackがくるところ */
            LOG.info("Exchanging OAuth code for access token using server side call");             
			LOG.info("callback start");
		   	String returnCode=param("code");
		    String accessTokenJson=OauthHelp.getAccessToken(returnCode);
		   	System.out.println(accessTokenJson);
		   	AccessToken actk=parseTokenJSON(accessTokenJson);
//		   	Map<String, String> obj=JSON.decode(accessTokenJson, String.class);
//		   	JSONObject jsonObj=(JSONObject)JSONSerializer.toJSON(accessTokenJson);
//		   	JsonConfig jsonConfig=new JsonConfig();
//		   	jsonConfig.setRootClass(AccessToken.class);
//		   	AccessToken actk=(AccessToken)JSONSerializer.toJava(jsonObj, jsonConfig);	//new AccessToken();
//		   	actk.setToken(obj.get("access_token"));
//		   	Calendar cal=Calendar.getInstance();
//		   	cal.add(Calendar.SECOND, Integer.parseInt(obj.get("expires_in"),10));
//		   	actk.setExpiration(cal);
//		   	HttpServletRequest req=RequestLocator.get();
//		   	HttpSession session=req.getSession(true);
//		   	session.setAttribute("token", actk);
		   	OauthHelp.saveToken(actk);
		   	return redirect("/");
         }
    }

    private String join(String[] strLot, String delimit){
    	String str=strLot[0];
    	for(int i=1; i<strLot.length; i++){
    		str+=delimit+strLot[i];
    	}
    	return str;
    }
    private AccessToken parseTokenJSON(String jsonStr) throws Exception{
    	ObjectMapper mapper=new ObjectMapper();
    	AccessToken result=mapper.readValue(jsonStr, AccessToken.class);
    	return result;
    }
    


}
