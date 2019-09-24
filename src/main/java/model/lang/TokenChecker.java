import java.util.HashMap;

public class TokenChecker {
	private static HashMap<String, Boolean> createVerbTokens() {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("VB", true);
		map.put("VBD", true);
		map.put("VBG", true);
		map.put("VBN", true);
		map.put("VBP", true);
		map.put("VBZ", true);
		
		return map; 
	}
	
	private static HashMap<String, Boolean> createNonImperativeVerbTokens() {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("VBD", true);
		map.put("VBG", true);
		map.put("VBN", true);
		map.put("VBZ", true);
		
		return map; 
	}
	
	private static HashMap<String, Boolean> createNounTokens() {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("NN", true);
		map.put("NNS", true);
		
		return map; 
	}
	
	private static HashMap<String, Boolean> createProperNounTokens() {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("NNP", true);
		map.put("NNPS", true);
		
		return map; 
	}
	
	private static HashMap<String, Boolean> createPunctuationTokens() {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("#", true);
		map.put("$", true);
		map.put(".", true);
		map.put(",", true);
		map.put(":", true);
		map.put("'", true);
		
		return map; 
	}
	
	private static HashMap<String, Boolean> createBrackets() {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("(", true);
		map.put(")", true);
		map.put("{", true);
		map.put("}", true);
		map.put("[", true);
		map.put("]", true);
		
		return map; 
	}
	
	private static HashMap<String, Boolean> createClosingBrackets() {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		map.put(")", true);
		map.put("}", true);
		map.put("]", true);
		
		return map; 
	}
	
	private static HashMap<String, Boolean> verbTokens = createVerbTokens();
	private static HashMap<String, Boolean> nonImperativeVerbTokens = createNonImperativeVerbTokens();
	private static HashMap<String, Boolean> nounTokens = createNounTokens();
	private static HashMap<String, Boolean> properNounTokens = createProperNounTokens();
	private static HashMap<String, Boolean> punctuationTokens = createPunctuationTokens();
	private static HashMap<String, Boolean> brackets = createBrackets();
	private static HashMap<String, Boolean> closingBrackets = createClosingBrackets();
	
	public static boolean isVerbToken(String token){
		if(verbTokens.get(token) != null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNonImperativeVerbToken(String token){
		if(nonImperativeVerbTokens.get(token) != null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNounToken(String token){
		if(nounTokens.get(token) != null){
			return true;
		}
		
		if(properNounTokens.get(token) != null){
			return true;
		}
		
		return false;
	}
	
	public static boolean isProperNounToken(String token){
		if(properNounTokens.get(token) != null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNonProperNounToken(String token){
		if(verbTokens.get(token) != null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isPunctuationToken(String token){
		if(punctuationTokens.get(token) != null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isBracket(String token){
		if(brackets.get(token) != null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isClosingBracket(String token){
		if(closingBrackets.get(token) != null){
			return true;
		}else{
			return false;
		}
	}
}