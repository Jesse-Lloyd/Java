
public class Chat 
{
	private int chatID;
	private String chatTitle;
	public Chat(int Id,String title)
	{
		chatID=Id;
		chatTitle=title;
	}
	public void setTitle(String title)
	{
		this.chatTitle=title;
	}
	public String getTitle()
	{
		return this.chatTitle;
	}
	public int getID()
	{
		return this.chatID;
	}
}
