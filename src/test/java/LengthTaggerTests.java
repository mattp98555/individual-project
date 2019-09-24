import org.junit.*;
import static org.junit.Assert.assertTrue;

public class LengthTaggerTests {
	@Test
	public void TestHeaderLengthTaggerOK(){
		CommitMessage OKMessage = new CommitMessage("This message is below 50 characters");
		String lengthMessage =  "The length of the header is fine";
		
		HeaderLengthTagger.tagMessage(OKMessage);
		
		boolean hasTag = false;
		
		for(MessageTag tag : OKMessage.getTags()){
			if(tag.getTagMessage().equals(lengthMessage)){
				hasTag = true;
			}
		}
		
		assertTrue(hasTag);
	}
	
	@Test
	public void TestHeaderLengthTaggerDubious(){
		CommitMessage dubiousMessage = new CommitMessage("This message is over 50 characters but not over 72");
		String lengthMessage = "The length of the header may be too long";
		
		HeaderLengthTagger.tagMessage(dubiousMessage);
		
		boolean hasTag = false;
		
		for(MessageTag tag : dubiousMessage.getTags()){
			if(tag.getTagMessage().equals(lengthMessage)){
				hasTag = true;
			}
		}
		
		assertTrue(hasTag);
	}
	
	@Test
	
	public void TestHeaderLengthTaggerTooLong(){
		CommitMessage tooLongMessage = new CommitMessage("This message is definitely far too long and should be tagged accordingly.");
		String lengthMessage = "The length of the header is too long";
		
		HeaderLengthTagger.tagMessage(tooLongMessage);
		
		boolean hasTag = false;
		
		for(MessageTag tag : tooLongMessage.getTags()){
			if(tag.getTagMessage().equals(lengthMessage)){
				hasTag = true;
			}
		}
		
		assertTrue(hasTag);
	}
}