import org.junit.*;
import static org.junit.Assert.assertTrue;

public class PunctuationTaggerTests {
	@Test
	public void TestPunctuationTaggerFindssFinalPunctuation(){
		CommitMessage message = new CommitMessage("There should be no punctuation.");
		String tagMessage =  "Header should not end with punctuation mark, as it is a title";
		
		HeaderPunctuationTagger.tagMessage(message);
		
		boolean hasTag = false;
		
		for(MessageTag tag : message.getTags()){
			if(tag.getTagMessage().equals(tagMessage)){
				hasTag = true;
			}
		}
		
		assertTrue(hasTag);
	}
}