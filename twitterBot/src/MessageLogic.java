/**
 * 
 * @author Patrick
 *
 */
public class MessageLogic {

	private static final String[] MESSAGES = { "Es geht mir Gut",
			"Ich bin Arbeiten", "Ich hab jetzt keine Zeit","charmant","eloquent"};

	/**
	 * 
	 * @param text
	 * @return
	 */
	public String searchMessage(String text) {
		if(text.matches("\\@.* Wie geht es dir?")){
			return MessageLogic.MESSAGES[0];
		}else if(text.matches("\\@.* Was machst du .*")){
			return MessageLogic.MESSAGES[1];
		}else if(text.matches("\\@.* .*") && text.length() > 20){
			return MessageLogic.MESSAGES[3];
		}else if(text.matches("\\@.* .*") && text.length() > 30){
			return MessageLogic.MESSAGES[4];
		}else if(text.matches("\\@.* .*") && text.length() > 50){
			return MessageLogic.MESSAGES[2];
		}
		return "42";
	}

}
