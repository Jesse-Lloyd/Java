

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MainView extends Remote
{
	public void catchMsg(String message) throws RemoteException;
	public int Login(String name,String password) throws RemoteException;
	public ArrayList<String> getUser(int userID)throws RemoteException;
	public void register(String[] userDetails) throws RemoteException;
	public ArrayList<String> getMsgs() throws RemoteException;
	public int getMsgCount() throws RemoteException;
	public void updateProfile(String[] details,int id) throws RemoteException;
	public ArrayList getUserGroups(int userID) throws RemoteException;
	public boolean setGlobalChat(int userID) throws RemoteException;
}
