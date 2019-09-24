import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AnalyticsWindow {	
	public AnalyticsWindow() {	
		JFrame frame = new JFrame("Analytics");
		frame.setSize(600, 200);
		
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		
		JLabel messagesAnalysed = new JLabel("Messages analysed: " + MessageTagger.getMessagesAnalysed());
		
		JLabel headerData = new JLabel("- Message header data -");
		JLabel lengthTags = new JLabel("Messages with incorrect header length: " + HeaderLengthTagger.getCount());
		JLabel grammarTags = new JLabel("Messages with incorrect grammar: " + HeaderGrammarTagger.getCount());
		JLabel punctuationTags = new JLabel("Messages with incorrect punctuation: " + HeaderPunctuationTagger.getCount());
		JLabel verbTenseTags = new JLabel("Messages with incorrect verb tense: " + HeaderVerbTenseTagger.getCount());
		JLabel verbOrderTags = new JLabel("Messages with incorrect verb order: " + HeaderVerbOrderTagger.getCount());
		JLabel vagueTags = new JLabel("Messages that are considered vague: " + HeaderVaguenessTagger.getCount());
		
		panel.add(messagesAnalysed);
		panel.add(headerData);
		panel.add(lengthTags);
		panel.add(grammarTags);
		panel.add(punctuationTags);
		panel.add(verbTenseTags);
		panel.add(verbOrderTags);
		panel.add(vagueTags);
		
		frame.add(panel);
		
		frame.setVisible(true);
	}
}