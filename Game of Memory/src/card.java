/*	Developer:Jesse Lloyd
 *  About: this class is used for the playing cards it contains the data related to the cards on the playing board
 *  there face images,back images,is card still in play(boolean active) etc...
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class card implements ActionListener
{
	int pairID; 											//used to decide if 2 cards are a match
	ImageIcon faceImage,backImage,faceImage2;				// card faces
	JButton cardFace;
	static int setCardID=1,temp;
	int cardID; 
	boolean active;
	
	public card(int i,char suit)
	{
		active = true;
		setImagesAndButton(i,suit);
		setCardID();
	}
	private void setImagesAndButton(int i,char suit)
	{
		String directory = null;
		String directory2 = null;
		backImage = new ImageIcon("resources\\active cards\\back.PNG");
		if(i <=10)
		{
			switch (suit)
			{
				case 'c':
					directory = "resources\\active cards\\"+i+" of c.PNG";
					directory2 = "resources\\deactive cards\\"+i+" of c.PNG";
					pairID = setCardID;
					break;
				case 's':
					directory = "resources\\active cards\\"+i+" of s.PNG";
					directory2 = "resources\\deactive cards\\"+i+" of s.PNG";
					pairID = setCardID;
					break;
				case 'h':
					directory = "resources\\active cards\\"+i+" of h.PNG";
					directory2 = "resources\\deactive cards\\"+i+" of h.PNG";
					pairID = setCardID;
					break;
				case 'd':
					directory = "resources\\active cards\\"+i+" of d.PNG";
					directory2 = "resources\\deactive cards\\"+i+" of d.PNG";
					pairID = setCardID;
					setCardID=setCardID+1;
					break;
			}
		}
		else if(i == 11)
		{
			switch(suit)
			{
				case 'c':
					directory = "resources\\active cards\\jack of c.PNG";
					directory2 = "resources\\deactive cards\\jack of c.PNG";
					pairID = setCardID;
					break;
				case 's':
					directory = "resources\\active cards\\jack of s.PNG";
					directory2 = "resources\\deactive cards\\jack of s.PNG";
					pairID = setCardID;
					break;
				case 'h':
					directory = "resources\\active cards\\jack of h.PNG";
					directory2 = "resources\\deactive cards\\jack of h.PNG";
					pairID = setCardID;
					break;
				case 'd':
					directory = "resources\\active cards\\jack of d.PNG";
					directory2 = "resources\\deactive cards\\jack of d.PNG";
					pairID = setCardID;
					setCardID=setCardID+1;
					break;
			}
		}
		else if(i==12)
		{
			switch(suit)
			{
				case 'c':
					directory = "resources\\active cards\\queen of c.PNG";
					directory2 = "resources\\deactive cards\\queen of c.PNG";
					pairID = setCardID;
					break;
				case 's':
					directory = "resources\\active cards\\queen of s.PNG";
					directory2 = "resources\\deactive cards\\queen of s.PNG";
					pairID = setCardID;
					break;
				case 'h':
					directory = "resources\\active cards\\queen of h.PNG";
					directory2 = "resources\\deactive cards\\queen of h.PNG";
					pairID = setCardID;
					break;
				case 'd':
					directory = "resources\\active cards\\queen of d.PNG";
					directory2 = "resources\\deactive cards\\queen of d.PNG";
					pairID = setCardID;
					setCardID=setCardID+1;
					break;
			}
		}
		else
			switch(suit)
			{
				case 'c':
					directory = "resources\\active cards\\king of c.PNG";
					directory2 = "resources\\deactive cards\\king of c.PNG";
					pairID = setCardID;
					break;
				case 's':
					directory = "resources\\active cards\\king of s.PNG";
					directory2 = "resources\\deactive cards\\king of s.PNG";
					pairID = setCardID;
					break;
				case 'h':
					directory = "resources\\active cards\\king of h.PNG";
					directory2 = "resources\\deactive cards\\king of h.PNG";
					pairID = setCardID;
					break;
				case 'd':
					directory = "resources\\active cards\\king of d.PNG";
					directory2 = "resources\\deactive cards\\king of d.PNG";
					pairID = setCardID;
					break;
			}
		faceImage = new ImageIcon(directory);
		faceImage2 = new ImageIcon(directory2);
		cardFace = new JButton(backImage);
		cardFace.addActionListener(this);
		cardFace.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
	}
	private void setCardID()
	{
		cardID =temp;
		temp=temp+1;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		board.setCardImageToBackImage();		
		if(this.active == true)
		{			
			this.cardFace.setIcon(this.faceImage);
			
			if(board.choiceOne !=0 && this.cardID !=board.choiceOneCardID)
			{
				board.choiceTwoCardID = this.cardID;
				board.choiceTwo = this.pairID;
				board.checkPair();
			}
			else
			{
				board.choiceOneCardID=this.cardID;
				board.choiceOne =this.pairID;
			}
		}
	}
}