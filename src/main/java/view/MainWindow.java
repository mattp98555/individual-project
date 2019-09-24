import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.util.ArrayList;

public class MainWindow {
	public MainWindow () {
		JFrame frame = new JFrame();
		frame.setSize(500, 300);
		frame.setMinimumSize(new Dimension(500, 300));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		
		JLabel welcome = new JLabel("Welcome to Git commit message analysis through natural language processing");
		JLabel inputMessage = new JLabel("Please input a commit message for analysis:");
		JLabel inputRepo = new JLabel("or type the web address of a repository:");
		JLabel inputFile = new JLabel("or type the address of a file:");
		
		JTextField singleMessageTextField = new JTextField();
		JButton singleMessageButton = new JButton("Analyse single message");
		
		singleMessageButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				CommitMessage message = new CommitMessage(singleMessageTextField.getText());
				MessageTagger.generateTags(message);
				new FeedbackWindow(message);
			}
		});
		
		JTextField repoTextField = new JTextField();
		JButton repoButton = new JButton("Analyse repository");
		
		repoButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				MessageTagger.resetCounts();
				
				ArrayList<CommitMessage> messages = MessageGetter.getCommitMessagesFromRepository(repoTextField.getText());
				
				messages.parallelStream().forEach(message -> MessageTagger.generateTags(message));
				new FeedbackList(messages);
			}
		});
		
		JTextField fileTextField = new JTextField();
		JButton fileButton = new JButton("Analyse file");
		
		fileButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				MessageTagger.resetCounts();
				
				ArrayList<CommitMessage> messages = MessageGetter.getCommitMessagesFromFile(fileTextField.getText());
				
				messages.parallelStream().forEach(message -> MessageTagger.generateTags(message));
				new FeedbackList(messages);
			}
		});
		
		panel.add(welcome);
		panel.add(inputMessage);
		panel.add(singleMessageTextField);
		panel.add(singleMessageButton);
		panel.add(inputRepo);
		panel.add(repoTextField);
		panel.add(repoButton);
		panel.add(inputFile);
		panel.add(fileTextField);
		panel.add(fileButton);
		
		frame.add(panel);
		frame.setVisible(true);
	}
}