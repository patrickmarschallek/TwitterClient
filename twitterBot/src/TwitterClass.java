import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * 
 * @author Patrick
 *
 */
public class TwitterClass {
	public static final String CONSUMER_KEY = "ZcKhqof2xoRSgqrI3GtEg";
	public static final String CONSUMER_SECRET = "HdroD1DX2RIbcqZMZwkNAi0qGtGMCTjctN9nN6qqQ9Y";

	public static final String REQUEST_URL = "http://api.twitter.com/oauth/request_token";
	public static final String ACCESS_URL = "http://api.twitter.com/oauth/access_token";
	public static final String AUTHORIZE_URL = "http://api.twitter.com/oauth/authorize";

	private Twitter twitter;
	private TwitterStream tweetStream;

	/**
	 * 
	 */
	public TwitterClass() {
		this("404108139-ROxkeWd8nGQgXGCNjVp02Oglank2als3SxgTWORZ","u31NstzQKjQKFwD1j17GgCwRVEVZDSUJiXPRiZKzlnA");
	}

	/**
	 * 
	 * @param accessToken
	 * @param accessTokenSecret
	 */
	public TwitterClass(String accessToken, String accessTokenSecret) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				// .setHttpProxyHost("proxy.fh-brandenburg.de")
				// .setHttpProxyPort(3128)
				.setOAuthConsumerKey(CONSUMER_KEY)
				.setOAuthConsumerSecret(CONSUMER_SECRET)
				.setOAuthAccessToken(accessToken)
				.setOAuthAccessTokenSecret(accessTokenSecret);
		Configuration conf = cb.build();
		this.twitter = new TwitterFactory(conf).getInstance();
		this.tweetStream = new TwitterStreamFactory(conf).getInstance();

		this.tweetStream.addListener(new TwitterStatusListener(twitter));
	}

	/**
	 * 
	 * @param users
	 * @return
	 * @throws TwitterException
	 */
	public boolean follow(String[] users) throws TwitterException {
		for (String userName : users) {
			User userByString = twitter.showUser(userName);

			twitter.createFriendship(userByString.getId());
			System.out.println("Du folgst jetzt -> "
					+ userByString.getScreenName());
		}

		return true;

	}

	/**
	 * 
	 * @return
	 */
	private AccessToken loadAccessToken() {
		try {
			BufferedReader fileStream = new BufferedReader(new FileReader("src/conf.txt"));

			String token;

			token = fileStream.readLine().replaceAll("AccessToken=", "");
			String tokenSecret = fileStream.readLine().replaceAll(
					"AccesTokenSecret=", "");

			return new AccessToken(token, tokenSecret);
		} catch (FileNotFoundException e) {
			System.out
					.println("Configurationsdatei konnte nicht geöffnet werden!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	/**
	 * 
	 */
	public void retweet() {
		this.tweetStream.user();

	}

	/**
	 * 
	 */
	public void shutdown() {
		this.tweetStream.shutdown();
		this.twitter.shutdown();
	}
}
