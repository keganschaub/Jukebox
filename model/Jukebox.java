/*
 * CSC 335 Project "Jukebox Iteration: The View"
 * By Kegan Schaub & Brian Lee
 */

package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Deque;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class Jukebox implements Serializable{
	
	private String currUser = "";

	/*
	 * Using JukeboxAccount, this method calls the method
	 * registered in order to check if the user name and
	 * password match up
	 */
	public boolean accountRegistered(String user, String password){
		currUser = user;
		
		if (!account.registered(user, password)){
			return false;
		}
		else{
			return true;
		}
	}
	
	JukeboxAccount account = new JukeboxAccount();
	SongPlays plays = new SongPlays(currUser);
	Deque<String> playlist = new LinkedList<String>();
	ObjectWaitingForSongToEnd waiter = new ObjectWaitingForSongToEnd();
	
	/*
	 * Using JukeboxAccount, this method adds the seconds
	 * of a song to the user's total seconds use. Using
	 * SongPlays to check the Usage for the song and user, 
	 * a boolean is returned as a result.
	 */
	public void addSong(String songTitle, int sec){
		account.subSeconds(sec);
		playlist.add(songTitle.toLowerCase());
	}
	
	public boolean canAdd(String songTitle, int sec){
		if (account.hasSeconds() && plays.canAddTotal(currUser, songTitle) && plays.addToSongPlays(currUser, songTitle)){
			plays.addToTotalPlays(currUser, songTitle);
			return true;
		}
		return false;
	}
	/*
	 * plays the queue
	 */
	public void play() {
		SongPlayer.playFile(waiter, baseDirect + playlist.poll());
	}
	/*
	 * Using JukeboxAccount, this method simply checks
	 * if the user has seconds left
	 */
	public boolean hasSecondsLeft(){
		return (account.hasSeconds());
	}
	/*
	 * returns the amount of seconds the user has left
	 */
	public String getSeconds(String user){
		return "" + account.getSeconds();
	}
	
	public String timer(String user) {
		return "" + account.timer(user);
	}
	/*
	 * Pretend it is tomorrow
	 */
	public void tomorrowHasCome(){
		plays.tomorrowsPlays();
	}
	
	private class ObjectWaitingForSongToEnd implements EndOfSongListener, Serializable{

	    public void songFinishedPlaying(EndOfSongEvent eosEvent) {
	      System.out.print("Finished " + eosEvent.fileName());
	      GregorianCalendar finishedAt = eosEvent.finishedTime();
	      System.out.println(" at " + finishedAt.get(Calendar.HOUR_OF_DAY) + ":"
	          + finishedAt.get(Calendar.MINUTE) + ":"
	          + finishedAt.get(Calendar.SECOND));
	      
	      //Waits 1 seconds until next song
	      wait(1000);
	      if (!playlist.isEmpty() ) {
	    	  play();
	    	  
	      }
	    }
	    
	    private void wait(int time) {
			long initTime, finalTime;
			initTime = System.currentTimeMillis();
			do {
				finalTime = System.currentTimeMillis();
			}
			while ( finalTime - initTime <= time );
		}
	    
	  }
	/**
	   * baseDir will be the fully qualified path to the directory in which this
	   * program is running on any machine. System.getProperty("file.separator")
	   * returns "\" when running on Unix or "/" when running on windows.
	   */
	  public static String baseDirect = System.getProperty("user.dir")
	      + System.getProperty("file.separator") + "songfiles"
	      + System.getProperty("file.separator");
}

