import twitter4j.DirectMessage;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserStreamListener;

/**
 * TwitterStatusListener implements the {@link twitter4j.UserStreamListener} interface
 * this listener interface provide to listen on a stream and act to events on it
 * 
 * @author Patrick
 *
 */
public class TwitterStatusListener implements UserStreamListener {

	private Twitter twitter;
	private MessageLogic logic;

	public TwitterStatusListener(Twitter t) {
		this.twitter = t;
		this.logic = new MessageLogic();
	}

	/**
	 * act if a status come from a other twitter user
	 * 
	 * @param Status status actual Status that are tweeted
	 */
	public void onStatus(Status status) {
		if (status.getText().matches("\\@.* .*")) {
			String message = this.logic.searchMessage(status.getText());
			System.out.println("incomming Tweet... \non Status form @"
					+ status.getUser().getScreenName() + " --> "
					+ status.getText());
			try {
				Status s = this.twitter.updateStatus("@"
						+ status.getUser().getScreenName() + " "
						+ message);
				System.out.println(s.getText());
			} catch (TwitterException e) {
				System.out.println("--> "+message+"<--\n Dieser Status wurde schon von dir getwittert.");
			}
			System.out.print("\n=>");
		}
	}

	@Override
	public void onException(Exception ex) {
		System.out.println("onException:" + ex.getMessage());
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrubGeo(long arg0, long arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTrackLimitationNotice(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDeletionNotice(long arg0, long arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFavorite(User arg0, User arg1, Status arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFriendList(long[] arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnfavorite(User arg0, User arg1, Status arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBlock(User arg0, User arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDirectMessage(DirectMessage arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFollow(User arg0, User arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRetweet(User arg0, User arg1, Status arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnblock(User arg0, User arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserListCreation(User arg0, UserList arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserListDeletion(User arg0, UserList arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserListMemberAddition(User arg0, User arg1, UserList arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserListMemberDeletion(User arg0, User arg1, UserList arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserListSubscription(User arg0, User arg1, UserList arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserListUnsubscription(User arg0, User arg1, UserList arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserListUpdate(User arg0, UserList arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserProfileUpdate(User arg0) {
		// TODO Auto-generated method stub

	}
}
