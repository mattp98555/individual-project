import edu.stanford.nlp.simple.*;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class HeaderVaguenessTagger implements Tagger {
	private static int count = 0;
	
	private static boolean checkForVerb(CommitMessage message){		
		for(String tag : message.headerPosTags){
			if(TokenChecker.isVerbToken(tag)){
				return true;
			}
		}
		
		return false;
	}
	
	private static boolean checkForNoun(CommitMessage message){
		for(String tag : message.headerPosTags){
			if(TokenChecker.isNounToken(tag)){
				return true;
			}
		}
		
		return false;
	}
	
	private static boolean checkForVagueNouns(CommitMessage message){
		int nouns = 0;
		int vagueNouns = 0;
		
		for(String tag : message.headerPosTags){
			if(TokenChecker.isNounToken(tag)){
				nouns++;
			}
		}
		
		for(String tag : message.headerTokens){
			if(VagueNounChecker.isVagueNoun(tag)){
				vagueNouns++;
			}
		}
		
		return (vagueNouns >= nouns && nouns > 0);
	}
	
	private static String getFileStatus(CommitMessage message){
		if(MessageGetter.currentRepository.equals("")){ return ""; }
		if(message.getSha().equals("")){ return ""; }
		
		JSONObject messageData = MessageGetter.getCommitMessageData(MessageGetter.currentRepository, message.getSha());
		String firstFileStatus = messageData.getJSONArray("files").getJSONObject(0).getString("status");
		
		return new Sentence(firstFileStatus).lemma(0);
	}
	
	private static String getFileName(CommitMessage message){
		if(MessageGetter.currentRepository.equals("")){ return ""; }
		if(message.getSha().equals("")){ return ""; }
		
		JSONObject messageData = MessageGetter.getCommitMessageData(MessageGetter.currentRepository, message.getSha());
		String firstFile = messageData.getJSONArray("files").getJSONObject(0).getString("filename");
		
		return firstFile;
	}
	
	public static void tagMessage(CommitMessage message){
		boolean hasVerb = checkForVerb(message);
		boolean hasNoun = checkForNoun(message);
		boolean hasOnlyVagueNouns = checkForVagueNouns(message);
		
		if(!hasVerb && !hasNoun){
			MessageTag tag = new MessageTag("Message is too vague: does not describe what change was made at all", "add a verb and a noun", false);
			message.addTag(tag);
			
			count++;
		}else if(!hasVerb){
			MessageTag tag = new MessageTag("Message is too vague: does not contain any meaningful verbs", "add a verb", false);
			message.addTag(tag);
			
			if(hasOnlyVagueNouns){
				MessageTag tag2 = new MessageTag("Message is too vague: all nouns in the message are considered vague. Consider using more descriptive nouns", "add a more descriptive noun", false);
				message.addTag(tag2);
			}
			
			count++;
		}else if(!hasNoun){
			MessageTag tag = new MessageTag("Message is too vague: does not contain any meaningful nouns", "add a noun", false);
			message.addTag(tag);
			
			count++;
		}else if(hasOnlyVagueNouns){
			MessageTag tag = new MessageTag("Message is too vague: all nouns in the message are considered vague. Consider using more descriptive nouns", "add a more descriptive noun", false);
			message.addTag(tag);
				
			count++;
		}
	}
	
	public static int getCount(){
		return count;
	}
	
	public static void resetCount(){
		count = 0;
	}
}