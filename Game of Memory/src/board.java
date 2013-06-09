/*	Developer:Jesse Lloyd
 *  About: the games board, here all the cards are created, shuffled then added to a JPanel
 *  the jpanel displays the players score and turn remaining if playing on photographic difficulty
 *  and provides the player with a choice to restart or change difficulty
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class board extends JFrame implements MouseMotionListener 
{		    
	private JPanel background;
	private static Container con;
	private JButton restart,changeDiff;
	private static JLabel scoreDisplay,turnsLeftDisplay;
	private static ArrayList<card> cards;
	public static int choiceOne,choiceTwo,choiceOneCardID,choiceTwoCardID,score,nextChoice,currentDiff,turnsLeft,pairs;
	boolean hint;
	
	public board(int diff) 
	{   
		score=0;
		choiceOne=0;
		pairs=0;
		currentDiff = diff;
		hint = true;
		addMouseMotionListener(this);
		
		
		if(currentDiff ==3)
		{
			turnsLeft=75;
		}
		else
			turnsLeft=-1;
		
		restart=new JButton("Restart");
		restart.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{	
				restart();
			}
		});
		
		changeDiff=new JButton("Change Difficulty");
		changeDiff.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{	
				selectDiff();
			}
		});		
	    cards=new ArrayList<card>();
	    for(int i=1;i<=13;i++)
	    {
	    	card temp1 = new card(i,'c');
	    	cards.add(temp1);
	        card temp2 = new card(i,'s');
	        cards.add(temp2);
	        card temp3 = new card(i,'h');
	        cards.add(temp3);
	        card temp4 = new card(i,'d');
	        cards.add(temp4);
	    }
	    
	    scoreDisplay=new JLabel("Score: "+getScore());	    
	    scoreDisplay.setForeground(Color.WHITE);
	    turnsLeftDisplay=new JLabel("Turns remaining: "+Integer.toString(turnsLeft));
	    turnsLeftDisplay.setForeground(Color.WHITE);
	    preGameHint();
	    setPlayingBoard();
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setVisible(true);
	    
	}	 
	private void refresh()
	{
		background.revalidate();
		this.repaint();
	}
	private void selectDiff()
	{
		MainMenu menu = new MainMenu();
	}
	private void restart()
	{
		this.dispose();
		board newGame = new board(currentDiff);
		refresh();
	}
	private void setPlayingBoard()  //draw background and cards on the game board
	{
		background = new JPanel()															
	    {
	        public void paintComponent(Graphics g) 
	        {
	            Image img = new ImageIcon("resources\\background.png").getImage();
	            g.drawImage(img, 0, 0, null);
	            setResizable(false);
	        } 
	    };
	    con = getContentPane();										
	    con.setLayout(null);
	    
	    int[] cardPos = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,
	    					32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51};	
	    ArrayList<Integer> randomNum = new ArrayList<Integer>();   
	    for(int j=0;j<cardPos.length;j++)
	    	randomNum.add(cardPos[j]);
	    
	    long seed = System.nanoTime();
	    Collections.shuffle(randomNum, new Random(seed));		//randomize the values within the ArrayList randomNum     
	    for(int i=0;i<=51;i++)									
	    {//start of setting cards on the playing board
	    	con.add(cards.get(randomNum.get(i)).cardFace);
	    	Insets insets = con.getInsets();
			Dimension size = cards.get(randomNum.get(i)).cardFace.getPreferredSize();
			if(i>10 && i<22)
				cards.get(randomNum.get(i)).cardFace.setBounds(((i-11)*107 + insets.left), 175 + insets.top,size.width, size.height);
				else if(i>21 && i<33)
						cards.get(randomNum.get(i)).cardFace.setBounds(((i-22)*107 + insets.left), 320 + insets.top,size.width, size.height);
				else if(i>32 && i<44)
						cards.get(randomNum.get(i)).cardFace.setBounds(((i-33)*107 + insets.left), 465 + insets.top,size.width, size.height);
				else if(i>43)
						cards.get(randomNum.get(i)).cardFace.setBounds(((i-44)*107 + insets.left), 610 + insets.top,size.width, size.height);				
				else
					cards.get(randomNum.get(i)).cardFace.setBounds((i*107 + insets.left), 30 + insets.top,size.width, size.height);
			
	    }//end of setting cards
	    
	    background.setLayout(null);
	    ImageIcon ii = new ImageIcon("resources\\background.png");				
	    setSize(1200,750); 
	    con.add(background);
	    setComponents();
	    background.setBounds(0, 0, ii.getIconWidth(), ii.getIconHeight());	
	    refresh();
	}	
	private void setComponents()
	{
		if(hint)
		{
			final JButton endHint = new JButton("Click to continue");
			endHint.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					for(card temp : cards)
						temp.cardFace.setEnabled(true);
					
					hint=false;
					background.removeAll();
					setComponents();
					refresh();
				}
			});
			background.add(endHint);
			endHint.setBounds(850, 613, 300, 35);
		}
		else
		{
		    restart.setBounds(850, 667, 300, 35);
		    changeDiff.setBounds(850, 613, 300, 35);
		    scoreDisplay.setBounds(505, 0, 200, 25);
		    if(currentDiff ==3)
		    {
			    turnsLeftDisplay.setBounds(575,0,200,25);
			    background.add(turnsLeftDisplay);
		    }
		    background.add(scoreDisplay);
		    background.add(restart);
		    background.add(changeDiff);
		}
	}
	private void endGame()
	{
		this.dispose();
		ScoreBoard endGame = new ScoreBoard(score,turnsLeft,currentDiff);
	}
	public static void checkPair()
	{
		if(choiceOne == choiceTwo)
		{
			score = score+2;
			pairs=pairs+1;
			for(card C1 : cards)
				if(C1.cardID == choiceOneCardID)
				{
					C1.active = false;
					C1.cardFace.setIcon(C1.faceImage2);
				}
			for(card C2 : cards)
				if(C2.cardID == choiceTwoCardID)
				{
					C2.active = false;
					C2.cardFace.setIcon(C2.faceImage2);
				}
		}
		else
		{
			if(currentDiff >=2)
				score = score-1;

			nextChoice = 1;
		}
		if(currentDiff ==3)
		{
			turnsLeft=turnsLeft-1;
			turnsLeftDisplay.setText("Turns remaining: "+Integer.toString(turnsLeft));
		}
		choiceOne = 0;
		choiceTwo = 0;
		scoreDisplay.setText("Score: "+getScore());
	}
	private static String getScore()
	{
		return Integer.toString(score);
	}
	private void preGameHint()
	{
		for(card temp : cards)
		{
			temp.cardFace.setEnabled(false);
			temp.cardFace.setDisabledIcon(temp.faceImage);
		}
	}
	public static void setCardImageToBackImage()
	{
		if(choiceOne == 0 && nextChoice ==1)
		{
			for(card C1 : board.cards)
				if(C1.cardID == board.choiceOneCardID)
					C1.cardFace.setIcon(C1.backImage);
				
			for(card C2 : board.cards)
				if(C2.cardID == board.choiceTwoCardID)
					C2.cardFace.setIcon(C2.backImage);
			
			nextChoice = 0;
		}
	}
	public void mouseDragged(MouseEvent e){}
	public void mouseMoved(MouseEvent e) 
	{	
		if(pairs==26 || turnsLeft==0)
			endGame();
		
		setCardImageToBackImage();
	}
}