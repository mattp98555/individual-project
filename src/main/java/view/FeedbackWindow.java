import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FeedbackWindow {
	public static ArrayList<String> getTagsAsString(CommitMessage message){
		ArrayList<String> list = new ArrayList<String>();
		
		for(MessageTag tag : message.getTags()){
			list.add(tag.toString());
		}
		
		return list;
	}
	
	public FeedbackWindow(CommitMessage message) {	
		JFrame frame = new JFrame("Commit message feedback");
		frame.setSize(500, 200);
		
		JPanel panel = new JPanel(new BorderLayout());
		frame.add(panel);
		
		JLabel label = new JLabel(message.getHeader());
		panel.add(label, BorderLayout.PAGE_START);
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		ArrayList<String> tagStringList = getTagsAsString(message);
		
		for(String s : tagStringList){
			listModel.addElement(s);
		}
		
		JList<String> list = new JList<String>(listModel);
		panel.add(new JScrollPane(list), BorderLayout.CENTER);
		
		JLabel suggestedChange = new JLabel("Suggested new message: " + message.getSuggestedHeader());
		panel.add(suggestedChange, BorderLayout.PAGE_END);
		
		frame.setVisible(true);
	}
}