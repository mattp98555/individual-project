import edu.stanford.nlp.simple.*;
import java.util.ArrayList;
import java.util.List;

public class CommitMessage { 
	private String header;
	private String body;
	private String suggestedHeader = "";
	private String suggestedBody = "";
	
	private String sha = "";
	
	private ArrayList<MessageTag> tags;
	public ArrayList<String> headerTokens;
	public ArrayList<String> headerPosTags;
	
	public CommitMessage(String message){
		String[] lines = message.split("\n");
		this.header = lines[0]; 
		this.suggestedHeader = header; 
		
		String body = "";
		
		if(lines.length > 1){
			for(int i = 1; i < lines.length; i++){
				body += lines[i];
			}
		}
		
		tags = new ArrayList<MessageTag>();
		
		tokeniseHeader();
	}
	
	public CommitMessage(String message, String sha){
		this.sha = sha;
		
		String[] lines = message.split("\n");
		this.header = lines[0]; 
		this.suggestedHeader = header; 
		
		String body = "";
		
		if(lines.length > 1){
			for(int i = 1; i < lines.length; i++){
				body += lines[i];
			}
		}
		
		tags = new ArrayList<MessageTag>();
		
		tokeniseHeader();
	}
	
	public void tokeniseHeader(){
		Sentence headerSentence = new Sentence(header);
		headerTokens = new ArrayList<String>(headerSentence.words());
		headerPosTags = new ArrayList<String>(headerSentence.posTags());
		
		BracketsFixer.correctBrackets(headerTokens);
	}
	
	public String getHeader(){
		return header;
	}
	
	public String getBody(){
		return body;
	}
	
	public List<String> getHeaderTokens(){
		return headerTokens;
	}
	
	public List<String> getHeaderPosTags(){
		return headerPosTags;
	}
	
	public ArrayList<MessageTag> getTags(){
		return tags;
	}
	
	public void addTag(MessageTag tag){
		tags.add(tag);
	}
	
	public void printTags(){
		System.out.println(header);
		
		for(MessageTag tag : tags){
			System.out.println(tag.toString());
		}
	}
	
	public String getSuggestedHeader(){
		return suggestedHeader;
	}
	
	public String getSuggestedBody(){
		return suggestedBody;
	}
	
	public void setSuggestedHeader(String suggestedHeader){
		this.suggestedHeader = suggestedHeader;
	}
	
	public void setSuggestedBody(String suggestedBody){
		this.suggestedBody = suggestedBody;
	}
	
	public String toString(){
		return String.format("%s | SHA: %s", header, sha);
	}
	
	public String getSha(){
		return sha;
	}
	
	public void generateSuggestions(){
		String headerSuggestion = "";
		
		for(int i = 0; i < headerTokens.size(); i++){
			boolean addSpace = true;

			headerSuggestion += headerTokens.get(i);
			
			if(i + 1 < headerTokens.size()){
				if(TokenChecker.isPunctuationToken(headerPosTags.get(i + 1)) || TokenChecker.isClosingBracket(headerTokens.get(i + 1))){
					addSpace = false;
				}
			}
			
			if(TokenChecker.isBracket(headerTokens.get(i))){
				addSpace = false;
			}

			if(i == headerTokens.size() - 1){
				addSpace = false;
			}
			
			if(addSpace){
				headerSuggestion += " ";
			}
		}
		
		suggestedHeader = headerSuggestion;
	}
}