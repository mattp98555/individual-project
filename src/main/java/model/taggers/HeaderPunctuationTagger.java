import edu.stanford.nlp.simple.*;
import java.util.List;

public class HeaderPunctuationTagger implements Tagger {
	private static int count = 0;
	private static String concatTokens(List<String> tokens, int upTo){
		String str = "";
		
		for(int i = 0; i < upTo; i++){
			str += tokens.get(i) + " ";
		}
		
		return str;
	}
	
	public static void tagMessage(CommitMessage message){
		List<String> tokens = message.headerTokens;
		List<String> posTags = message.headerPosTags;
		
		int index = posTags.size() - 1; 
		
		String lastTag = posTags.get(index);
		
		if(TokenChecker.isPunctuationToken(lastTag)){
			String suggestion = concatTokens(tokens, tokens.size() - 1);
			MessageTag tag = new MessageTag("Header should not end with punctuation mark, as it is a title", suggestion, false);
			message.addTag(tag);
			
			message.headerTokens.remove(index);
			message.headerPosTags.remove(index);
			
			count++;
			return;
		}
	}
	
	public static int getCount(){
		return count;
	}
	
	public static void resetCount(){
		count = 0;
	}
}