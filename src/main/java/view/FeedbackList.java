import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FeedbackList {	
	public FeedbackList(ArrayList<CommitMessage> messages) {	
		JFrame frame = new JFrame("Repository feeback");
		frame.setSize(500, 800);
		
		JPanel panel = new JPanel(new BorderLayout());
		frame.add(panel);
		
		JLabel label = new JLabel("Double click on a message to see the feedback for that message");
		panel.add(label, BorderLayout.PAGE_START);
		
		DefaultListModel<CommitMessage> listModel = new DefaultListModel<CommitMessage>();
		
		for(CommitMessage message : messages){
			listModel.addElement(message);
		}
		
		JList<CommitMessage> list = new JList<CommitMessage>(listModel);
		
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt){
					if(evt.getClickCount() == 2){
						int index = list.locationToIndex(evt.getPoint());
						CommitMessage message = listModel.getElementAt(index);
						
						new FeedbackWindow(message);
					}
			}
		});
		
		panel.add(new JScrollPane(list), BorderLayout.CENTER);
		
		JButton analyticsButton = new JButton("Analytics");
		
		analyticsButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				new AnalyticsWindow();
			}
		});
		
		panel.add(analyticsButton, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
}