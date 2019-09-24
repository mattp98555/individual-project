import org.junit.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class VerbOrderTaggerTests {
	@Test
	public void TestFindsCorrectOrder(){
		CommitMessage message = new CommitMessage("remove bug");
		String tagMessage = "The message should start with a verb.";
		
		HeaderVerbOrderTagger.tagMessage(message);
		
		boolean hasTag = false;
		
		for(MessageTag tag : message.getTags()){
			if(tag.getTagMessage().equals(tagMessage)){
				hasTag = true;
			}
		}
		
		assertFalse(hasTag);
	}
	
	@Test
	public void TestFindsIncorrectOrder(){
		CommitMessage message = new CommitMessage("bug removed");
		String tagMessage = "The message should start with a verb.";
		
		HeaderVerbOrderTagger.tagMessage(message);
		
		boolean hasTag = false;
		
		for(MessageTag tag : message.getTags()){
			if(tag.getTagMessage().equals(tagMessage)){
				hasTag = true;
			}
		}
		
		assertTrue(hasTag);
	}
	
	@Test
	public void TestChangesIncorrectOrder(){
		CommitMessage message = new CommitMessage("bug removed");
		HeaderVerbOrderTagger.tagMessage(message);
		message.generateSuggestions();
		
		assertEquals(message.getSuggestedHeader(), "removed bug");
	}
}