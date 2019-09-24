import edu.stanford.nlp.simple.*;
import java.util.List;
import java.util.ArrayList;

public class MessageTagger {
	private static int messagesAnalysed = 0;
	
	public static void resetCounts(){
		messagesAnalysed = 0;
		HeaderLengthTagger.resetCount();
		HeaderVerbOrderTagger.resetCount();
		HeaderVerbTenseTagger.resetCount();
		HeaderGrammarTagger.resetCount();
		HeaderPunctuationTagger.resetCount();
		HeaderVaguenessTagger.resetCount();
	}
	
	public static void generateTags(CommitMessage message){
		messagesAnalysed++;
		HeaderLengthTagger.tagMessage(message);
		HeaderVerbOrderTagger.tagMessage(message);
		HeaderVerbTenseTagger.tagMessage(message);
		HeaderGrammarTagger.tagMessage(message);
		HeaderPunctuationTagger.tagMessage(message);
		HeaderVaguenessTagger.tagMessage(message);
		
		message.generateSuggestions();
	}
	
	public static void main(String[] args){	
		new MainWindow();
	}
	
	public static int getMessagesAnalysed(){
		return messagesAnalysed;
	}
}