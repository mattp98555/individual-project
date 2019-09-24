import org.junit.*;
import static org.junit.Assert.assertTrue;

public class GrammarTaggerTests {
	@Test
	public void TestGrammarCorrect(){
		CommitMessage message = new CommitMessage("This has correct grammar");
		String tagMessage =  "The message has correct grammar";
		
		HeaderGrammarTagger.tagMessage(message);
		
		boolean hasTag = false;
		
		for(MessageTag tag : message.getTags()){
			if(tag.getTagMessage().equals(tagMessage)){
				hasTag = true;
			}
		}
		
		assertTrue(hasTag);
	}
	
	@Test
	public void TestGrammarIncorrectNoun(){
		CommitMessage message = new CommitMessage("This has incorrect Grammar");
		String tagMessage =  "One or more words in the message is incorrectly capitalised - words should be lower case unless they are the first word in the sentence, or proper nouns";
		
		HeaderGrammarTagger.tagMessage(message);
		
		boolean hasTag = false;
		
		for(MessageTag tag : message.getTags()){
			if(tag.getTagMessage().equals(tagMessage)){
				hasTag = true;
			}
		}
		
		assertTrue(hasTag);
	}
	
	@Test
	public void TestGrammarIncorrectProperNoun(){
		CommitMessage message = new CommitMessage("Fix london");
		String tagMessage =  "One or more proper nouns in the message is incorrectly capitalised - proper nouns should always be capitalised";
		
		HeaderGrammarTagger.tagMessage(message);
		
		boolean hasTag = false;
		
		for(MessageTag tag : message.getTags()){
			if(tag.getTagMessage().equals(tagMessage)){
				hasTag = true;
			}
		}
		
		assertTrue(hasTag);
	}
}