/*
 * CSC 335 Project "Jukebox Iteration: The View"
 * By Kegan Schaub & Brian Lee
 */

package model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashMap;

public class JukeboxAccount implements Serializable{
	
	HashMap<String, String> account = new HashMap<String, String>();
	HashMap<String, Integer> accountInfo = new HashMap<String, Integer>();
	
	/*
	 * Sets up two hashmaps. One for recording user name and password,
	 * and the other for each user name and an integer that represents
	 * the amount of seconds they have used.
	 */
	public JukeboxAccount(){
		account.put("Ali", "1111");
		account.put("Chris", "2222");
		account.put("River", "3333");
		account.put("Ryan", "4444");
		
		accountInfo.put("Ali", 1500);
		accountInfo.put("Chris", 1500);
		accountInfo.put("River", 1500);
		accountInfo.put("Ryan", 1500);
	}
	
	String currUser = "";
	/*
	 * Taking in the user name and password as parameters,
	 * this method checks if the name is contained in the
	 * hashmap, and if so, will then continue to see if the 
	 * password matches. A boolean is then returned.
	 */
	public boolean registered(String user, String password){
		if ((account.containsKey(user) && account.containsValue(password))){
			if (account.get(user).compareTo(password) == 0){
				currUser = user;
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	/*
	 * This method checks if the user has seconds left
	 */
	public boolean hasSeconds(){
		if (accountInfo.get(currUser) >= 0){
			return true;
		}
		else{
			return false;
		}
	}
	public String getSeconds(){
		return "" + accountInfo.get(currUser);
	}
	/*
	 * This method adds seconds to the user's value
	 */
	public void subSeconds(int sec){
		int totalSec = accountInfo.get(currUser) - sec;
		accountInfo.put(currUser, totalSec);
	}
	
	public String timer(String user) {
		int time = accountInfo.get(currUser);
		
		int hr = time / 3600;
		int min = (time - (hr * 3600)) / 60;
		int sec = time - (hr * 3600) - (min * 60);
		DecimalFormat df = new DecimalFormat("00");
		
		String result = df.format(hr) + ":" + df.format(min) + ":" + df.format(sec);
		
		return result;
	}
}
