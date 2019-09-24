import org.junit.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class HolisticTests {
	@Test
	public void TestMessage1(){
		CommitMessage message = new CommitMessage("fix bug.");
		MessageTagger.generateTags(message);
		message.generateSuggestions();
		assertEquals(message.getSuggestedHeader(), "Fix bug");
	}
	
	@Test
	public void TestMessage2(){
		CommitMessage message = new CommitMessage("translation added");
		MessageTagger.generateTags(message);
		message.generateSuggestions();
		assertEquals(message.getSuggestedHeader(), "Add translation");
	}
	
	@Test
	public void TestMessage3(){
		CommitMessage message = new CommitMessage("added music, added game, removed feature");
		MessageTagger.generateTags(message);
		message.generateSuggestions();
		assertEquals(message.getSuggestedHeader(), "Add music, add game, remove feature");
	}
}