import java.util.ArrayList;

public class User
{
	private int userID;
	private String userName;
	private String password;
	private String email;
	private String securityQuestion;
	private String securityQuestionAnswer;
	private ArrayList<Chat> groups;
	/*private ArrayList<String> ipAdress;
	private String avatar;
	private String city;
	private boolean pirvacy;*/
	
	public User()
	{
		//pirvacy=false;
	}
	public User(int string,String name,String pass,String userEmail,String seqQ,String seqA,ArrayList<Chat> g)
	{
		this.userID=string;
		this.userName=name;
		this.password=pass;
		this.email=userEmail;
		this.securityQuestion=seqQ;
		this.securityQuestionAnswer=seqA;
		this.groups=g;
	}
	public String getUserName()
	{
		return this.userName;
	}
	public void setUserName(String newUserName)
	{
		this.userName=newUserName;
	}
	public void setPassword(String newPassword)
	{
		this.password=newPassword;
	}
	public String getPassword()
	{
		return this.password;
	}
	public void setEmail(String newEmail)
	{
		this.email=newEmail;
	}
	public String getEmail()
	{
		return this.email;
	}
	public void setSecurityQuestion(String question)
	{
		this.securityQuestion=question;
	}
	public String getSecurityQuestion()
	{
		return this.securityQuestion;
	}
	public void setSecurityQuestionAnswer(String answer)
	{
		this.securityQuestionAnswer=answer;
	}
	public String getSecurityQuestionAnswer()
	{
		return this.securityQuestionAnswer;
	}
	public int getID()
	{
		return this.userID;
	}
	public void addGroup(Chat chat)
	{
		this.groups.add(chat);
	}
	public ArrayList<Chat> getGroups()
	{
		return this.groups;
	}
	/*public void addIpAddress(String address)
	{
		this.ipAdress.add(address);
	}
	public ArrayList<String> getIpaddresses()
	{
		return this.ipAdress;
	}
	public void setAvatar(String fileName)
	{
		this.avatar=fileName;
	}
	public String getAvatar()
	{
		return this.avatar;
	}*/
}
