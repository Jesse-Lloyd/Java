/* Developer:Jesse Lloyd
 * Description: Used to get the users details for
 * a new profile to login with
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


public class RegGui extends JFrame
{

	private static final long serialVersionUID = -7537514068280705370L;
	private boolean unlockDetails;
	private String[] details ={"","","","",""};
	
	public RegGui()
	{
		unlockDetails=false;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		final JTextField nameInput = new JTextField();
		final JTextField passInput = new JTextField();
		final JTextField emailInput = new JTextField();
		final JTextField seqQInput = new JTextField();
		final JTextField seqAInput = new JTextField();
		
		JButton reg = new JButton("register");
		reg.addActionListener(new ActionListener()
		{	@Override
			public void actionPerformed(ActionEvent e) 
			{
				details[0]=nameInput.getText();
				details[1]=passInput.getText();
				details[2]=emailInput.getText();
				details[3]=seqQInput.getText();
				details[4]=seqAInput.getText();
					
				if(!regCheck(details))
				{
					respones.setText("  All fields must be");
					responesTwo.setText("filled out");
				}
				else
				{
					close();
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
	private boolean regCheck(String[] details)
	{
		int count=0;
		for(int i=0;i<details.length;i++)
		{
			for(int j=0;j<details[i].length();j++)
			{
				if(details[i].charAt(j) == ' ')
					count++;
				else
					break;
			}
			if(count == details[i].length())
				return false;
		}
		unlockDetails=true;
		return true;
	}
	public String[] getDetails()
	{
		if(unlockDetails)
			return details;
		else
			return null;
	}
}
