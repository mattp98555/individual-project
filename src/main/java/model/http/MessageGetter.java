import com.mashape.unirest.http.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class MessageGetter{
	private static String username = "REDACTED";
	private static String OAuthToken = "REDACTED";
	public static String GithubAPIUrl = "https://api.github.com";
	
	public static String currentRepository = "";
	
	public static String[] parseUrl(String repositoryUrl){
		String newUrl = repositoryUrl.replace("https://github.com/", "");
		String[] parsedUrl = newUrl.split("/");
		return parsedUrl;
	}
	
	public static JSONObject getCommitMessageData(String repositoryUrl, String sha){
		String[] parsedUrl = parseUrl(repositoryUrl);
		String username = parsedUrl[0];
		String repo = parsedUrl[1];
		
		JSONObject object = new JSONObject();
		
		try{
			HttpResponse<JsonNode> firstResponse = Unirest.get(GithubAPIUrl + "/repos/" + username + "/" + repo + "/commits/" + sha).basicAuth(username, OAuthToken).asJson();
			
			object = firstResponse.getBody().getObject();
		}catch(Exception e){
			
		}
		
		return object;
	}
	
	public static ArrayList<CommitMessage> getCommitMessagesFromRepository(String repositoryUrl){
		currentRepository = repositoryUrl;
		
		ArrayList<CommitMessage> messages = new ArrayList<CommitMessage>();
		
		String[] parsedUrl = parseUrl(repositoryUrl);
		String username = parsedUrl[0];
		String repo = parsedUrl[1];
		
		try{
			HttpResponse<JsonNode> firstResponse = Unirest.get(GithubAPIUrl + "/repos/" + username + "/" + repo + "/commits?per_page=300").basicAuth(username, OAuthToken).asJson();
			
			JSONArray array = firstResponse.getBody().getArray();
			
			for(int i = 0; i < array.length(); i++){
				String sha = array.getJSONObject(i).getString("sha");
				String message = array.getJSONObject(i).getJSONObject("commit").getString("message");
				messages.add(new CommitMessage(message, sha));
			}
		}catch(Exception e){
			
		}
		
		return messages;
	}
	
	public static ArrayList<CommitMessage> getCommitMessagesFromFile(String filePath){
		currentRepository = "";
		
		ArrayList<CommitMessage> messages = new ArrayList<CommitMessage>();
		
		try{
			File file = new File(filePath);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st = br.readLine();
			
			while(st != null){
				CommitMessage message = new CommitMessage(st);
				messages.add(message);
				st = br.readLine();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return messages;
	}
}