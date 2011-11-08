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
 * The class hold the customer Application tokens and open the connection to twitter.
 * It implements the function to listen for retweet or to follow somebody
 * 
 * @author Patrick
 * @since 04.11.11
 */
public class TwitterClass {
	public static final String CONSUMER_KEY = "ZcKhqof2xoRSgqrI3GtEg";
	public static final String CONSUMER_SECRET = "HdroD1DX2RIbcqZMZwkNAi0qGtGMCTjctN9nN6qqQ9Y";

	public static final String REQUEST_URL = "http://api.twitter.com/oauth/request_token";
	public static final String ACCESS_URL = "http://api.twitter.com/oauth/access_token";
	public static final String AUTHORIZE_URL = "http://api.twitter.com/oauth/authorize";

	private Twitter twitter;
	private TwitterStream tweetStream;

	public TwitterClass() {
		this("404108139-ROxkeWd8nGQgXGCNjVp02Oglank2als3SxgTWORZ","u31NstzQKjQKFwD1j17GgCwRVEVZDSUJiXPRiZKzlnA");
	}

	/**
	 * Constructor with access information the set the twitter configuration for the application
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
	 * This method need an Array of Strings with usernames with the Persons 
	 * they are follow.
	 * 
	 * it use the {@link twitter4j.api.FriendshipMethods.createFriendship}
	 * 
	 * @param users String[] String-Array of usernames
	 * @throws TwitterException
	 * @see {@link twitter4j.api.FriendshipMethods}
	 */
	public void follow(String[] users) throws TwitterException {
		for (String userName : users) {
			User userByString = twitter.showUser(userName);

			twitter.createFriendship(userByString.getId());
			System.out.println("Du folgst jetzt -> "
					+ userByString.getScreenName());
		}
	}

	/**
	 * not in use comming soon
	 * 
	 * load the accessToken information from a Storage (src/conf.txt)
	 * 
	 * @return AccessToken accessToken authentification information
	 */
	@SuppressWarnings("unused")
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
	 * starts a stream to twitter an fires events for all user activities on Twitter
	 * 
	 */
	public void retweet() {
		this.tweetStream.user();

	}

	/**
	 * close the streams and twitter connection to the TwitterClient
	 * 
	 */
	public void shutdown() {
		this.tweetStream.shutdown();
		this.twitter.shutdown();
	}
}
