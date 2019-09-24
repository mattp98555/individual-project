import org.junit.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class VaguenessTaggerTests {
	@Test
	public void TestNonVagueMessage(){
		CommitMessage message = new CommitMessage("Add Polish translation");
		String tagMessage1 =  "Message is too vague: does not describe what change was made at all";
		String tagMessage2 = "Message is too vague: does not contain any meaningful verbs";
		String tagMessage3 = "Message is too vague: all nouns in the message are considered vague. Consider using more descriptive nouns";
		String tagMessage4 = "Message is too vague: does not contain any meaningful nouns";
		
		HeaderVaguenessTagger.tagMessage(message);
		
		boolean hasTag = false;
		
		for(MessageTag tag : message.getTags()){
			if(tag.getTagMessage().equals(tagMessage1)){
				hasTag = true;
			}
			
			if(tag.getTagMessage().equals(tagMessage2)){
				hasTag = true;
			}
			
			if(tag.getTagMessage().equals(tagMessage3)){
				hasTag = true;
			}
			
			if(tag.getTagMessage().equals(tagMessage4)){
				hasTag = true;
			}
		}
		
		assertFalse(hasTag);
	}
	
	@Test
	public void TestOverlyVagueMessage(){
		CommitMessage message = new CommitMessage("0");
		String tagMessage =  "Message is too vague: does not describe what change was made at all";
		
		HeaderVaguenessTagger.tagMessage(message);
		
		boolean hasTag = false;
		
		for(MessageTag tag : message.getTags()){
			if(tag.getTagMessage().equals(tagMessage)){
				hasTag = true;
			}
		}
		
		assertTrue(hasTag);
	}
	
	@Test
	public void TestVagueNoVerbMessage(){
		CommitMessage message = new CommitMessage("Cosmetic improvements");
		String tagMessage =  "Message is too vague: does not contain any meaningful verbs";
		
		HeaderVaguenessTagger.tagMessage(message);
		
		boolean hasTag = false;
		
		for(MessageTag tag : message.getTags()){
			if(tag.getTagMessage().equals(tagMessage)){
				hasTag = true;
			}
		}
		
		assertTrue(hasTag);
	}
	
	@Test
	public void TestVagueNoNounMessage(){
		CommitMessage message = new CommitMessage("Added");
		String tagMessage =  "Message is too vague: does not contain any meaningful nouns";
		
		HeaderVaguenessTagger.tagMessage(message);
		
		boolean hasTag = false;
		
		for(MessageTag tag : message.getTags()){
			if(tag.getTagMessage().equals(tagMessage)){
				hasTag = true;
			}
		}
		
		assertTrue(hasTag);
	}
	
	@Test
	public void TestVagueNounsMessage(){
		CommitMessage message = new CommitMessage("Fix bug");
		String tagMessage =  "Message is too vague: all nouns in the message are considered vague. Consider using more descriptive nouns";
		
		HeaderVaguenessTagger.tagMessage(message);
		
		boolean hasTag = false;
		
		for(MessageTag tag : message.getTags()){
			if(tag.getTagMessage().equals(tagMessage)){
				hasTag = true;
			}
		}
		
		assertTrue(hasTag);
	}
}