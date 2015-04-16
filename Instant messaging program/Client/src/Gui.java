/* Developer:Jesse Lloyd
 * Description: the main gui used after the user has logged in
 * 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Gui extends JFrame
{
	private static final long serialVersionUID = -1049958103353244632L;
	private final JTextArea current;
	private boolean editUser;
	protected ArrayList<String> log = new ArrayList<String>();

/*
 * creates and shows the main gui,the main gui is where the chat log,chat groups and
 * button to start editing the users profile are managed 
 */

	
	public Gui(final User user,int whichChat)
	{
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		editUser=false;
		
		JPanel inputField = new JPanel(new BorderLayout());
		inputField.setBackground(Color.white);
		final JTextField input = new JTextField("", 25);
		JButton send = new JButton("Send");
		send.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				onButtonClick(input.getText(),user);
				input.setText("");
			}
			
		});
		inputField.setBounds(270, 420,380, 70);
		input.setBackground(Color.white);
		inputField.add(input,BorderLayout.WEST);
		inputField.add(send,BorderLayout.EAST);
		
		JPanel currentGroup=new JPanel(new BorderLayout());
		currentGroup.setBackground(Color.cyan);
		JLabel chatTitle = new JLabel(user.getGroups().get(whichChat).getTitle());
		chatTitle.setHorizontalAlignment(JTextField.CENTER);
		chatTitle.setFont(new Font("SansSerif",Font.BOLD,14));
		current = new JTextArea();
		current.setLineWrap(true);
		current.setWrapStyleWord(true);
		current.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(current);
		current.setBackground(Color.lightGray);
		currentGroup.setBounds(270, 0,380, 420);
		currentGroup.add(chatTitle,BorderLayout.NORTH);
		currentGroup.add(scrollPane,BorderLayout.CENTER);
		
		JPanel userD = new JPanel(new BorderLayout());
		userD.setBackground(Color.white);
		userD.setBounds(0, 390,270, 100);
		JLabel name = new JLabel("Welcome "+user.getUserName());
		name.setFont(new Font("SansSerif",Font.BOLD,16));
		name.setHorizontalAlignment(JTextField.CENTER);
		JLabel email = new JLabel(user.getEmail());
		email.setHorizontalAlignment(JTextField.CENTER);
		JButton editProfile = new JButton("Edit Profile");
		editProfile.addActionListener(new ActionListener()
		{	@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				editUser=true;
			}
		});
		userD.add(name,BorderLayout.NORTH);
		userD.add(email,BorderLayout.CENTER);
		userD.add(editProfile,BorderLayout.SOUTH);
		
		
		JPanel chatGroups=new JPanel(new GridLayout(0,1));
		chatGroups.setBackground(Color.orange);
		chatGroups.setBounds(0, 0,270, 390);
		JLabel allGroups = new JLabel("Conversations");
		allGroups.setFont(new Font("SansSerif",Font.BOLD,16));
		allGroups.setHorizontalAlignment(JTextField.CENTER);
		chatGroups.add(allGroups);
		for(int i=0;i<user.getGroups().size();i++)
		{
			JButton groupI = new JButton(user.getGroups().get(i).getTitle());
			groupI.addActionListener(new ActionListener()
			{	@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					//when called chance the currentChat to the one that
					// that represents the button that was clicked(actionEvent.getActionCommand())
				}
			});
			
			groupI.setOpaque(false);
			groupI.setContentAreaFilled(false);
			groupI.setBorderPainted(false);
			chatGroups.add(groupI);
		}
		
		
		JPanel main = new JPanel(null);
		main.add(inputField);
		main.add(currentGroup);
		main.add(userD);
		main.add(chatGroups);
		main.setPreferredSize(new Dimension(640, 480));
		
		this.getContentPane().add(main);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	public void updateTextArea()
	{
		for(int i=0;i<log.size();i++)
		{
			if(i==0)
				current.setText(log.get(i));
			else
				current.append("\n"+log.get(i));
		}
	}

	public void setMsgs(String caught) // Receives the messages from all other clients
	{
		
		log.add(caught);
		updateTextArea();
	}
	private void onButtonClick(String msg,User user)//Receives the messages from the user then updates the log
	{
		msg=user.getUserName()+":\n"+msg;
		log.add(msg);
		updateTextArea();
	}
	public String getMsg()
	{
		return log.get(log.size()-1);
	}
	public int getMsgCount()
	{
		return log.size();
	}
	public boolean editProfile()
	{
		return editUser;
	}
	public void reset()
	{
		this.editUser=false;
	}
}
