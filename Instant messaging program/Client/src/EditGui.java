/* Developer:Jesse Lloyd
 * Description: Used to get the users input, when created
 * each JTextfields content are set to each of the users 
 * current profile details
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class EditGui extends JFrame
{
	
	private static final long serialVersionUID = 7869341536158621899L;
	private int check;
	private String[] details ={"","","","",""};
	private boolean unlockDetails;
	
	public EditGui(User user)
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		check=0;
		unlockDetails=false;
		JPanel main = new JPanel();
		this.setTitle("Register");
		main=new JPanel(new GridLayout(0,2));
		main.setBackground(Color.white);
		JLabel name = new JLabel("Username");
		JLabel password = new JLabel("Password");
		JLabel email = new JLabel("email");
		JLabel seqQ = new JLabel("Security Question");
		JLabel seqA = new JLabel("Security Answer");
		final JLabel respones = new JLabel();
		final JLabel responesTwo = new JLabel();

		final JTextField nameInput = new JTextField(user.getUserName());

		final JTextField passInput = new JTextField(user.getPassword());

		final JTextField emailInput = new JTextField(user.getEmail());

		final JTextField seqQInput = new JTextField(user.getSecurityQuestion());

		final JTextField seqAInput = new JTextField(user.getSecurityQuestionAnswer());
		
		final JButton reg = new JButton("Update");

		reg.addActionListener(new ActionListener()
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
				check++;	
				if(check==2)
				{
					details[0]=nameInput.getText();
					details[1]=passInput.getText();
					details[2]=emailInput.getText();
					details[3]=seqQInput.getText();
					details[4]=seqAInput.getText();
					unlockDetails=true;
				}
				else
				{
					respones.setText("  Are you sure you");
					responesTwo.setText("want to do this?");
					reg.setText("Yes");
				}
				
			}
		});
		JButton cancel = new JButton("never mind");
		cancel.addActionListener(new ActionListener()
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
				close();
			}
		});
		
		main.add(name);
		main.add(nameInput);
		main.add(password);
		main.add(passInput);
		main.add(email);
		main.add(emailInput);
		main.add(seqQ);
		main.add(seqQInput);
		main.add(seqA);
		main.add(seqAInput);
		main.add(respones);
		main.add(responesTwo);
		main.add(cancel);
		main.add(reg);

		this.getContentPane().add(main);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	private void close()
	{
		this.dispose();
	}
	public String[] getDetails()
	{
		if(unlockDetails)
		{
			close();
			return details;
		}
		else
			return null;
	}
}
