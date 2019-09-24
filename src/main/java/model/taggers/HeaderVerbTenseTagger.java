import edu.stanford.nlp.simple.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class HeaderVerbTenseTagger implements Tagger {
	private static int count = 0;

	private static boolean checkIsCapitalised(String word){
		return Character.isUpperCase(word.charAt(0));
	}
	
	private static String getBaseForm(String word, boolean capitalise){
		String w = new Sentence(word).lemma(0);
		
		if(capitalise){
			w = HeaderGrammarTagger.setCapitalised(w, true);
		}
		
		return w;
	}
	
	public static void tagMessage(CommitMessage message){
		boolean hasVerbs = false;
		boolean hasIncorrectTense = false;
		boolean checkTense = true; 
		
		List<String> tokens = message.headerTokens;
		List<String> posTags = message.headerPosTags;
		
		for(int i = 0; i < tokens.size(); i++){
			String tag = posTags.get(i);
			
			if(tag.equals("RP") || tag.equals("IN")){
				checkTense = false;
			}
			
			if(TokenChecker.isVerbToken(tag) && checkTense){
				hasVerbs = true;
			}
			
			if(TokenChecker.isNonImperativeVerbToken(tag) && checkTense){
				hasIncorrectTense = true;
				message.headerTokens.set(i, getBaseForm(message.headerTokens.get(i), checkIsCapitalised(message.headerTokens.get(i))));
			}
		}
		
		if(hasIncorrectTense){
			MessageTag tag = new MessageTag("Verbs should be in the present imperative form", false);
			message.addTag(tag);
			
			count++;
		}else if(hasVerbs){
			MessageTag tag = new MessageTag("All verbs are in the present imperative form.", true);
			message.addTag(tag);
		}
	}
	
	public static int getCount(){
		return count;
	}
	
	public static void resetCount(){
		count = 0;
	}
}