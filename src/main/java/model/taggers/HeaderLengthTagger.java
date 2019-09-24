public class HeaderLengthTagger implements Tagger {
	private static int count = 0;

	public static void tagMessage(CommitMessage message){
		int length = message.getHeader().length();
		MessageTag tag;
		
		if(length < 50){
			tag = new MessageTag("The length of the header is fine", true);
		}else if(length >= 50 && length < 72){
			tag = new MessageTag("The length of the header may be too long", false);
			count++;
		}else{
			tag = new MessageTag("The length of the header is too long", false);
			count++;
		}
		
		message.addTag(tag);
	}
	
	public static int getCount(){
		return count;
	}
	
	public static void resetCount(){
		count = 0;
	}
}