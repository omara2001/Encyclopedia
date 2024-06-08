/*

To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/
package encyclopedia;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EncyclopediaImpl extends UnicastRemoteObject implements Encyclopedia {
private static final long serialVersionUID = 1L;
private String data;
public EncyclopediaImpl(String data) throws RemoteException {
    super();
    this.data = data;
}

@Override
public int count() throws RemoteException {
    return data.length();
}

@Override
public String[] repeatedWords() throws RemoteException {
    String[] words = data.split("\\s+");
    Map<String, Integer> wordCounts = new HashMap<>();
    for (String word : words) {
        if (wordCounts.containsKey(word)) {
            wordCounts.put(word, wordCounts.get(word) + 1);
        } else {
            wordCounts.put(word, 1);
        }
    }
    return wordCounts.entrySet().stream()
            .filter(entry -> entry.getValue() > 1)
            .map(Map.Entry::getKey)
            .toArray(String[]::new);
}

@Override
public String longest() throws RemoteException {
    String[] words = data.split("\\s+");
    return Arrays.stream(words).max((a, b) -> a.length() - b.length()).orElse("");
}

@Override
public String shortest() throws RemoteException {
    String[] words = data.split("\\s+");
    return Arrays.stream(words).min((a, b) -> a.length() - b.length()).orElse("");
}

@Override
public Map<String, Integer> repeat() throws RemoteException {
    String[] words = data.split("\\s+");
    Map<String, Integer> wordCounts = new HashMap<>();
    for (String word : words) {
        if (wordCounts.containsKey(word)) {
            wordCounts.put(word, wordCounts.get(word) + 1);
        } else {
            wordCounts.put(word, 1);
        }
    }
    return wordCounts;
}
}