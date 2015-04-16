/* Developer:Jesse LLoyd
 * Description: gui that prompts the user for there
 * userName and Password to allow them to login and enter 
 * the main Gui.
 * also allows the users the create a new profile
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LoginGui extends JFrame
{
	private static final long serialVersionUID = 6127201165293119945L;
	private int choice;
	private String userName,userPassword;
	private JLabel msg;
	public LoginGui()
	{
		choice=0;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel(new BorderLayout());
		final JTextField name = new JTextField("", 10);
		final JTextField password = new JTextField("", 10);
		msg = new JLabel("User Details");
		JButton login = new JButton("login");
		login.addActionListener(new ActionListener()
		{	@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				userName=name.getText();
				userPassword=password.getText();
				choice=1;
			}
		});
		JButton register = new JButton("register");
		register.addActionListener(new ActionListener()
		{	@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				choice=2;
			}
		});
		panel.add(register,BorderLayout.NORTH);
		panel.add(login,BorderLayout.SOUTH);
		panel.add(name, BorderLayout.CENTER);
		panel.add(password, BorderLayout.EAST);
		panel.add(msg, BorderLayout.WEST);
		
		panel.setPreferredSize(new Dimension(300, 70));
		this.getContentPane().add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	public int getChoice()
	{
		int send=0;
		if(choice !=0)
		{
			send=choice;
			choice=0;
		}
		return send;
	}
	public String[] getDetails()
	{
		String[] details= {userName,userPassword};
		return details;
	}
	public void setLabel()
	{
		msg.setText("Login Failed");
	}
}
