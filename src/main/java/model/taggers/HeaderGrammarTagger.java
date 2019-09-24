import edu.stanford.nlp.simple.*;
import java.lang.Character;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class HeaderGrammarTagger implements Tagger {
	private static int count = 0;
	
	private static boolean checkIsCapitalised(String word){
		return Character.isUpperCase(word.charAt(0));
	}
	
	public static String setCapitalised(String word, boolean capitalised){
		String w = word.toLowerCase();
		
		if(capitalised){
			char c = Character.toUpperCase(word.charAt(0));
			w = c + w.substring(1);
		}
		
		return w;
	}
	
	private static boolean checkNonProperNounGrammar(CommitMessage message){
		boolean incorrectGrammar = false;
		
		List<String> tokens = message.headerTokens;
		List<String> posTags = message.headerPosTags;
		
		for(int i = 0; i < tokens.size(); i++){
			if(!TokenChecker.isProperNounToken(posTags.get(i))){
				if(checkIsCapitalised(tokens.get(i))){
					if(i > 0){
						incorrectGrammar = true; 
						message.headerTokens.set(i, setCapitalised(message.headerTokens.get(i), false));
					}
				}else{
					if(i == 0){
						incorrectGrammar = true;
						message.headerTokens.set(i, setCapitalised(message.headerTokens.get(i), true));
					}
				}
			}
		}
		
		if(incorrectGrammar){
			MessageTag tag = new MessageTag("One or more words in the message is incorrectly capitalised - words should be lower case unless they are the first word in the sentence, or proper nouns", false);
			message.addTag(tag);
		}
		
		return incorrectGrammar;
	}
	
	private static boolean checkProperNounGrammar(CommitMessage message){
		boolean incorrectProperNounGrammar = false;
		
		List<String> tokens = message.headerTokens;
		List<String> posTags = message.headerPosTags;
		
		for(int i = 0; i < tokens.size(); i++){
			if(TokenChecker.isProperNounToken(posTags.get(i))){
				if(!checkIsCapitalised(tokens.get(i))){
					incorrectProperNounGrammar = true; 
					
					message.headerTokens.set(i, setCapitalised(message.headerTokens.get(i), true));
				}
			}
		}
		
		if(incorrectProperNounGrammar){
			MessageTag tag = new MessageTag("One or more proper nouns in the message is incorrectly capitalised - proper nouns should always be capitalised", false);
			message.addTag(tag);
		}
		
		return incorrectProperNounGrammar;
	}
	
	public static void tagMessage(CommitMessage message){
		boolean incorrectNounGrammar = checkNonProperNounGrammar(message);
		boolean incorrectProperNounGrammar = checkProperNounGrammar(message);
		
		if(!incorrectNounGrammar && !incorrectProperNounGrammar){
			MessageTag tag = new MessageTag("The message has correct grammar", true);
			message.addTag(tag);
		}else{
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