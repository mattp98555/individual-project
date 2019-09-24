import edu.stanford.nlp.simple.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class HeaderVerbOrderTagger implements Tagger {
	private static int count = 0;
	
	public static void tagMessage(CommitMessage message){
		boolean hasIncorrectOrder = false;
		int firstVerbIndex = -1;
		
		ArrayList<String> tokens = message.headerTokens;
		ArrayList<String> posTags = message.headerPosTags;
		
		if(!TokenChecker.isVerbToken(posTags.get(0))){
			hasIncorrectOrder = true;
		}
		
		for(int i = 0; i < posTags.size(); i++){
			if(TokenChecker.isVerbToken(posTags.get(i))){
				firstVerbIndex = i;
				break;
			}
		}

		if(hasIncorrectOrder){
			MessageTag tag = new MessageTag("The message should start with a verb.", false);
			message.addTag(tag);
			
			if(firstVerbIndex > 0){
				String firstVerb = tokens.get(firstVerbIndex);
				String firstVerbPosTag = posTags.get(firstVerbIndex);
				
				tokens.remove(firstVerbIndex);
				posTags.remove(firstVerbIndex);
				
				tokens.add(0, firstVerb);
				posTags.add(0, firstVerbPosTag);
			}
			
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