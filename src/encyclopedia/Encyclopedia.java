/*

To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/
package encyclopedia;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface Encyclopedia extends Remote {
int count() throws RemoteException;
String[] repeatedWords() throws RemoteException;
String longest() throws RemoteException;
String shortest() throws RemoteException;
Map<String, Integer> repeat() throws RemoteException;
}
