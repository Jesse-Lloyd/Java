/*	Developer:Jesse Lloyd
 * 	About: this class represents the main menu, here the user can select the difficulty they wish to challenge
 * 	or quit the game. upon difficulty selection an instance of class board is created.
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MainMenu extends JFrame
{
	private JButton easy,normal,hard,quit;
	private int diffChoice;
	private Container con;
	static ArrayList<board> game = new ArrayList<board>();
	
	public MainMenu()
	{	
		easy=new JButton("Oblivious");
		easy.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				diffChoice=1;
				quit();
				createGame();
			}
		});
		normal=new JButton("Mediocre");
		normal.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				diffChoice=2;
				quit();
				createGame();
			}
		});
		hard=new JButton("Photographic");
		hard.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				diffChoice=3;
				quit();
				createGame();
			}
		});
		quit=new JButton("Exit");
		quit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				quit();
			}
		});
		setMenuComponents();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
	private void createGame()
	{
		board gameBoard = new board(diffChoice);
		game.add(gameBoard);
		if(game.size()>1)			//if there is more than	1 board object then close and remove the first object	
		{
			game.get(0).dispose();
			game.remove(0);
		}
	}
	private void quit()
	{
		this.dispose();
	}
	private void setMenuComponents()	//adds and sets the positions of the JLabel and JButtons
	{
		JLabel title = new JLabel("How Good is Your Memory?");
		title.setForeground(Color.WHITE);
		JLabel subTitle = new JLabel("click quit to exit the game");
		subTitle.setForeground(Color.WHITE);
		con=getContentPane();										
	    con.setLayout(null);
	    con.setBackground(Color.darkGray);
	    setSize(600,450);
		con.add(easy);
		con.add(normal);
		con.add(hard);
		con.add(quit);
		con.add(title);
		con.add(subTitle);
		subTitle.setBounds(222, 10, 200, 25);
		title.setBounds(220, 0, 200, 25);
		easy.setBounds(200, 50, 200, 25);
		normal.setBounds(200, 100, 200, 25);
		hard.setBounds(200, 150, 200, 25);
		quit.setBounds(200, 200, 200, 25);
	}
	public static void main(String[] args) 
	{
		MainMenu mainMenu = new MainMenu();
	}
}
