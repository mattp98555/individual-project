import java.util.HashMap;

public class VagueNounChecker {
	private static HashMap<String, Boolean> vagueNouns = new HashMap<String, Boolean>();
	private static boolean generatedList = false;

	public static void generateVagueNounList(){
		vagueNouns.put("bug", true);
		vagueNouns.put("bugs", true);
		vagueNouns.put("feature", true);
		vagueNouns.put("features", true);
		vagueNouns.put("it", true);
		vagueNouns.put("thing", true);
		vagueNouns.put("things", true);
		vagueNouns.put("stuff", true);
		
		generatedList = true;
	}

	public static boolean isVagueNoun(String noun){
		if(!generatedList){
			generateVagueNounList();
		}
		
		String n = noun.toLowerCase();
		
		return (vagueNouns.get(n) != null);
	}
}