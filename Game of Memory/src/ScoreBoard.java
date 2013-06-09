/*	Developer:Jesse Lloyd
 *  About: this class is used to display a score board with the players final score it is called when either the user
 *  runs out of turn(Photographic difficulty) or when the player has matched all cards with one another, the player can also
 *  start a new game of quit from here
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ScoreBoard extends JFrame
{
	int finalScore,turnsRemaining;
	
	public ScoreBoard(int score,int turnsLeft,int diff)
	{
		finalScore=score;
		setPanel();
		display();
	}
	private void setPanel()
	{
		Container con=getContentPane();									
	    con.setLayout(null);
	    setResizable(false);
		JLabel title=new JLabel("GAME OVER");
		title.setForeground(Color.WHITE);
		JLabel scoreTitle=new JLabel("Final Score");
		scoreTitle.setForeground(Color.WHITE);
		JLabel score=new JLabel(Integer.toString(finalScore));
		score.setForeground(Color.WHITE);
		JButton newGame=new JButton("New Game");
		newGame.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				startMenu();
			}
		});
		JButton exit=new JButton("Quit");
		exit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				quit();
			}
		});
		setSize(600,450);
		con.setBackground(Color.darkGray);
		con.add(title);
		con.add(scoreTitle);
		con.add(score);
		con.add(newGame);
		con.add(exit);
		title.setFont(new Font("Serif",Font.BOLD,16));
		title.setBounds(234, 100, 200, 25);
		scoreTitle.setBounds(248, 120, 200, 25);
		score.setBounds(269, 137, 200, 25);
		newGame.setBounds(70, 200, 200, 25);
		exit.setBounds(275, 200, 200, 25);
	}
	private void quit()
	{
		this.dispose();
	}
	private void startMenu()
	{
		this.dispose();
		MainMenu newMenu=new MainMenu();
	}
	private void display()
	{
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setVisible(true);
	}
}
