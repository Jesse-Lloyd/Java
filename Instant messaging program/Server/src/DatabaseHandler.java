/* Developer:Jesse Lloyd
 * Description: this class handles the communication 
 * with the database */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DatabaseHandler
{
	private Connection connector;
	private Statement statement;
	private ResultSet result;
	private PreparedStatement insert;
	
	public DatabaseHandler(){}
	
	public void startConnection()
	{
		try 
		{
			connector = DriverManager.getConnection("jdbc:mysql://localhost/instantmsg?"+"user=root&password=Phoenix7");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public void endConnection()
	{
		connector=null;
		statement=null;
		result=null;
		try 
		{
			if(statement != null)
				statement.close();
			if(result != null)
				result.close();
			if(connector !=null)
				connector.close();
			
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public int login(String userName,String password)
	{
		startConnection();
		try 
		{
			statement = connector.createStatement();
			result = statement.executeQuery("select userID from instantmsg.user where UserName ='"+userName+"' and password ='"+password+"';");
			if(result.next())
			{
				return result.getInt("userID");
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		endConnection();
		return 0;
	}
	public ArrayList<String> getUser(int userID) 
	{
		startConnection();
		ArrayList<String> user = new ArrayList<String>();
		try 
		{
			statement = connector.createStatement();
			result = statement.executeQuery("select * from instantmsg.user where UserID ="+userID+";");
			while(result.next())
			{
				user.add(result.getString("UserID"));
				user.add(result.getString("username"));
				user.add(result.getString("password"));
				user.add(result.getString("userEmail"));
				user.add(result.getString("seqQuestion"));
				user.add(result.getString("seqAnswer"));
			}
		} 
		catch (SQLException e1) {e1.printStackTrace();}

		endConnection();
		return user;
	}
	public boolean addUser(String[] userDetails)
	{
		for(int i=0;i<userDetails.length;i++)
		{
			System.out.println(userDetails[i]);
		}
		startConnection();
		try 
		{
			insert=connector.prepareStatement("insert into instantmsg.user(username,password,useremail,seqQuestion,seqAnswer) values(?,?,?,?,?)");
			insert.setString(1, userDetails[0]);
			insert.setString(2, userDetails[1]);
			insert.setString(3, userDetails[2]);
			insert.setString(4, userDetails[3]);
			insert.setString(5, userDetails[4]);
			insert.execute();
			
			return true;
			
		}catch (SQLException e) 
		{
			e.printStackTrace();
		}
		endConnection();
		return false;
	}
	public boolean addGlobalChatGroup(int userID)
	{
		startConnection();
		try 
		{
			System.out.println("hit 2");
			insert=connector.prepareStatement("insert into instantmsg.group(groupChatID,memberID) values(?,?)");
			insert.setInt(1,1);
			insert.setInt(2,userID);
			insert.execute();
		} 
		catch (SQLException e){e.printStackTrace();}
		endConnection();
		return false;
	}
	public boolean updateUser(String[] userDetails,int id)
	{
		startConnection();
		try 
		{
			insert=connector.prepareStatement("UPDATE instantmsg.user SET username = ?,password=?,useremail=?," +
					"seqQuestion=?,seqAnswer=? where userid = "+id+";");
			
			insert.setString(1, userDetails[0]);
			insert.setString(2, userDetails[1]);
			insert.setString(3, userDetails[2]);
			insert.setString(4, userDetails[3]);
			insert.setString(5, userDetails[4]);
			insert.executeUpdate();

			return true;
		} 
		catch (SQLException e){e.printStackTrace();}
		endConnection();
		return false;
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> getUserChatGroups(int userID)
	{
		startConnection();
		ArrayList<ArrayList> chatDetails = new ArrayList<ArrayList>();
		ArrayList<String> chatTitles = new ArrayList<String>();
		ArrayList<Integer> chatID = new ArrayList<Integer>();
		try 
		{
			statement = connector.createStatement();
			result = statement.executeQuery("select chatTitle,chatID from instantmsg.chat,instantmsg.group " +
					"where memberID ="+userID+" and groupChatID = ChatID ;");
			
			while(result.next())
			{
				chatTitles.add(result.getString("ChatTitle"));
				chatID.add(result.getInt("chatID"));
			}
			chatDetails.add(chatID);
			chatDetails.add(chatTitles);
			return chatDetails;
		} 
		catch (SQLException e){e.printStackTrace();}
		endConnection();
		return null;
	}
}
