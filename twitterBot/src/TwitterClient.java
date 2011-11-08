import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import twitter4j.TwitterException;

/**
 * 
 * @author Patrick
 *
 */
public class TwitterClient{

	/**
	 * Stats the Application. 
	 * Here are handled the commandline function.
	 * 
	 * @param args not in use 
	 */
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String command = "";

		System.out.println("TwitterClient starts...");
		TwitterClass twitter = new TwitterClass();
		twitter.retweet();
		
		TwitterClient.sleep();
		
		while (!command.matches("exit|quit")) {
			try {
				System.out.print("=> ");
				command = reader.readLine();
				if (command.matches("-f .*")) {
					String[] split = command.split(" ");
					String[] users = new String[split.length - 1];
					for (int i = 1; i < split.length; i++) {
						users[i - 1] = split[i];
					}
					try {
						twitter.follow(users);
					} catch (TwitterException e) {
						System.out.println(e.getMessage());
					}
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Stream wird geschlossen...");
		twitter.shutdown();
		System.out.println("... Ende ...");
	}

	/**
	 * set the main thread sleep but waiting for the open the connaction need some time
	 * and the open stream run in a other thread
	 * 
	 * sleep for 2 sec
	 */
	public static void sleep(){
		try { Thread.sleep(2000);} catch (InterruptedException e) {System.out.println(e.getMessage());}
	}
}
