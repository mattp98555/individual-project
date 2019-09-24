import java.util.HashMap;
import java.util.ArrayList;

public class BracketsFixer {
	private static HashMap<String, String> tokensToBrackets = new HashMap<String, String>();
	private static boolean generatedMap = false;

	public static void generateMap(){
		tokensToBrackets.put("-LRB-", "(");
		tokensToBrackets.put("-RRB-", ")");
		tokensToBrackets.put("-LCB-", "{");
		tokensToBrackets.put("-RCB-", "}");
		tokensToBrackets.put("-LSB-", "[");
		tokensToBrackets.put("-RSB-", "]");

		generatedMap = true;
	}

	public static void correctBrackets(ArrayList<String> tokens){
		if(!generatedMap){
			generateMap();
		}
		
		for(int i = 0; i < tokens.size(); i++){
			String token = tokens.get(i);
			
			if(tokensToBrackets.get(token) != null){
				tokens.set(i, tokensToBrackets.get(token));
			}
		}
	}
}