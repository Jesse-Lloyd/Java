/* Developer:Jesse Lloyd
 * Description: this class handles the communication
 * between the client and the database and the client and
 * server
 * 
 */
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Server extends java.rmi.server.UnicastRemoteObject implements MainView 
{
	private static final long serialVersionUID = -4498004565274445888L;
	
	private Registry registry;  
    private String address;
    private DatabaseHandler db = new DatabaseHandler();
    private ArrayList<String> messages = new ArrayList<String>();

	public static void main(String args[])
	{
		try 
		{
            Server s = new Server(7070);		 
        } 
		catch (RemoteException e) 
		{			
               e.printStackTrace();			
               System.exit(1);
        }
	}
	public Server(int port) throws RemoteException 
	{
        try
        {
           address = (InetAddress.getLocalHost()).toString();
        } 
        catch(java.net.UnknownHostException e)
        {
            throw new RemoteException("Cannot acquire address.");	
        }  
        System.out.println("Address:"+address+", Port:"+port);	
        try
        {
            registry = LocateRegistry.createRegistry(port);
            //Creates an RMI registry on the specified port number.
            registry.rebind("IMSystem", this);  
			//object to registry under the name "IMSystem"  	
        } 
        catch(RemoteException e)
        {
            throw e;
        }
	}
	
	@Override
	public int Login(String name, String password) throws RemoteException 
	{
		return db.login(name,password);
	}
	@Override
	public void register(String[] userDetails) throws RemoteException 
	{
		db.addUser(userDetails);
	}
	@Override
	public void catchMsg(String message) throws RemoteException 
	{
		message = message+"";
		messages.add(message);
	}
	@Override
	public ArrayList<String> getMsgs() throws RemoteException 
	{
		return messages;
	}
	@Override
	public int getMsgCount()
	{
		return messages.size();
	}
	@Override
	public ArrayList<String> getUser(int userID) throws RemoteException 
	{
		return db.getUser(userID);
	}
	@Override
	public void updateProfile(String[] details,int id) throws RemoteException 
	{
		db.updateUser(details,id);
	}
	@Override
	public ArrayList getUserGroups(int userID) throws RemoteException
	{
		return db.getUserChatGroups(userID);
	}
	@Override
	public boolean setGlobalChat(int userID) throws RemoteException 
	{
		return db.addGlobalChatGroup(userID);
	}
}
