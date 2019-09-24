import org.junit.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class VerbTenseTaggerTests {
	@Test
	public void TestFindsCorrectTense(){
		CommitMessage message = new CommitMessage("Fix bug");
		String tagMessage = "All verbs are in the present imperative form.";
		
		HeaderVerbTenseTagger.tagMessage(message);
		
		boolean hasTag = false;
		
		for(MessageTag tag : message.getTags()){
			if(tag.getTagMessage().equals(tagMessage)){
				hasTag = true;
			}
		}
		
		assertTrue(hasTag);
	}
	
	@Test
	public void TestFindsIncorrectTense(){
		CommitMessage message = new CommitMessage("Fixed bug");
		String tagMessage = "Verbs should be in the present imperative form";
		
		HeaderVerbTenseTagger.tagMessage(message);
		
		boolean hasTag = false;
		
		for(MessageTag tag : message.getTags()){
			if(tag.getTagMessage().equals(tagMessage)){
				hasTag = true;
			}
		}
		
		assertTrue(hasTag);
	}
	
	@Test
	public void TestChangesIncorrectTense(){
		CommitMessage message = new CommitMessage("Fixed bug");
		HeaderVerbTenseTagger.tagMessage(message);
		message.generateSuggestions();
		
		assertEquals(message.getSuggestedHeader(), "Fix bug");
	}
}