/*	Developer: Jesse Lloyd
 * 	Description: this class is used to communicate with the server
 * 	and multiple Guis 
 */


import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;


public class Client
{
	//private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static User user;
	private static int userID;
	private static Registry registry;
	private static MainView stub;
	
	public static void main(String[] args) throws IOException, NotBoundException 
	{ 
		registry = LocateRegistry.getRegistry(/*"156.59.16.98",*/7070);
		stub = (MainView) registry.lookup("IMSystem");		
		login();
	}
//--------------------------------------------------------------------------------------
/*	purpose: create and monitor the login Gui the registration Gui
 * 	and its status while either gui is open
*/	
	private static void login()
	{
		LoginGui login = new LoginGui();
		while(login.isShowing())
		{
			switch(login.getChoice())
			{
			case 1:
				String[] loginDetails = login.getDetails();
				try 
				{
					userID=stub.Login(loginDetails[0], loginDetails[1]);
					if(userID==0)
						login.setLabel();
					else
					{
						loadUser(userID);
						login.dispose();
						StartGui();
					}
				} 
				catch (RemoteException e){e.printStackTrace();}
				break;
			
			case 2:
				RegGui regGui = new RegGui();
				while(regGui.isShowing())
				{
					login.dispose();
					String[] regDetails = regGui.getDetails();
					if(regDetails!=null)
					{
						try 
						{
							stub.register(regDetails);
						}
						catch (RemoteException e){e.printStackTrace();}
					}
				}
				login=new LoginGui();
				break;
				
			default:
				break;
			}
		}
	}
//--------------------------------------------------------------------------------------
/*	purpose: create and monitor the main Gui the EditGui and its status
 *  gets messages from Gui then sends it to the server and sends user
 *  details to the server when users wants to edit there profile 
 */
	private static void StartGui()
	{
		Gui gui = new Gui(user,0);
		try
		{
			while(gui.isShowing())
			{
				if(gui.getMsgCount() > stub.getMsgCount())
				{
					stub.catchMsg(gui.getMsg());
				}
				if(stub.getMsgCount() > gui.getMsgCount())
				{
					for(int i=gui.getMsgCount();i<stub.getMsgCount();i++)
					{
						gui.setMsgs(stub.getMsgs().get(i).toString());
					}
				}
				
				if(gui.editProfile())
				{
					gui.reset();
					gui.dispose();
					EditGui editGui = new EditGui(user);
					while(editGui.isShowing())
					{
						String[] editDetails = editGui.getDetails();
						if(editDetails!=null)
						{
							try 
							{
								stub.updateProfile(editDetails,userID);
							}
							catch (RemoteException e){e.printStackTrace();}
						}
					}
					loadUser(userID);
					gui = new Gui(user,0);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
//--------------------------------------------------------------------------------------
/*	purpose: asks the server to retrieve the users profile information and
 *  the chatGroups the user belongs to
*/	
	private static void loadUser(int ID)
	{
	try 
		{
			ArrayList<ArrayList> chats = stub.getUserGroups(userID);
			ArrayList<Chat> groups = new ArrayList<Chat>();
			
			if(chats.get(0).isEmpty())
			{
				stub.setGlobalChat(ID);
				loadUser(ID);
			}
			else
			{
				for(int i=0;i<chats.get(0).size();i++)
				{
					groups.add(new Chat((int) chats.get(0).get(i),(String) chats.get(1).get(i)));
				}
				ArrayList<String> userStuff = stub.getUser(ID);
				user=new User(Integer.parseInt(userStuff.get(0)),userStuff.get(1),userStuff.get(2),userStuff.get(3),
						userStuff.get(4),userStuff.get(5),groups);
			}
			
		} catch (RemoteException e){e.printStackTrace();}
		
	}
}
