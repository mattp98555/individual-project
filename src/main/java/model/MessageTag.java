import java.util.ArrayList;

public class MessageTag {
	private String tagMessage;
	private String suggestedChange;
	private boolean positive;
	
	public MessageTag(String tagMessage, boolean positive){
		this.tagMessage = tagMessage;
		this.positive = positive;
	}
	
	public MessageTag(String tagMessage, String suggestedChange, boolean positive){
		this.tagMessage = tagMessage;
		this.suggestedChange = suggestedChange;
		this.positive = positive;
	}
	
	public String getTagMessage(){
		return this.tagMessage;
	}
	
	public String getSuggestedChange(){
		return this.suggestedChange;
	}
	
	public boolean isPositive(){
		return this.positive;
	}
	
	public String toString(){
		String str = (positive ? "+ " : "- ");
		str += tagMessage;
		
		return str;
	}
}