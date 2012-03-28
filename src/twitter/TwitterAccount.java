package twitter;

import java.awt.Frame;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * @author shinya-m
 *
 */
public class TwitterAccount {

	private Twitter tw;
	private RequestToken rqToken;
	private AccessToken acToken;
	private static final String key="6mT4MQoMjiaYKifqSIj1QA";
	private static final String secret="vuPib9Qhg8yZnGkmVNijXxk7YwXqAlXFPqTwGhe4";
	
	public TwitterAccount(Frame owner) throws TwitterException, URISyntaxException, IOException {
		tw=TwitterFactory.getSingleton();
		tw.setOAuthConsumer(key,secret);
		if(!readAccessToken()){
			rqToken=tw.getOAuthRequestToken();
			new OAuthFrame(owner,this);
		}
		
	}
	
	public String getOAuthURL(){
		return rqToken.getAuthorizationURL();
	}
	
	public boolean makeAccessToken(String PIN) throws IOException{
		try {
			acToken=tw.getOAuthAccessToken(rqToken,PIN);
			writeAccessToken();
			return true;
		} catch (TwitterException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean readAccessToken() throws IOException{
		FileInputStream fip = null;
		ObjectInputStream ois = null;
		try{
			fip=new FileInputStream("twitter/token.dat");
			ois=new ObjectInputStream(fip);
			acToken=(AccessToken)ois.readObject();
			tw.setOAuthAccessToken(acToken);
			fip.close();
			ois.close();
			System.out.println("readAccessToken success");
			return true;
		}catch(Exception e){
			//e.printStackTrace();
			return false;
		}
	}
	
	private boolean writeAccessToken() throws IOException{
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try{
			fos=new FileOutputStream("twitter/token.dat");
			oos=new ObjectOutputStream(fos);
			oos.writeObject(acToken);
			fos.close();
			oos.close();
			System.out.println("writeAccessToken success");
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public void tweet(String s) throws TwitterException{
		tw.updateStatus(s);
	}
	
	public String shrinkString(String s,int length){
		if(s.length()>length){
			return s.substring(0,length-1)+"…";
		}
		return s;
	}
}
