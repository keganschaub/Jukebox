package model;

public interface IFJukebox {
	
	/*
	 * Using JukeboxAccount, this method calls the method
	 * registered in order to check if the user name and
	 * password match up
	 */
	public boolean accountRegistered(String user, int password);
	
	/*
	 * Using JukeboxAccount, this method adds the seconds
	 * of a song to the user's total seconds use. Using
	 * SongPlays to check the Usage for the song and user, 
	 * a boolean is returned as a result.
	 */
	public void addSong(String songTitle, int min);
	
	/*
	 * Using JukeboxAccount, this method simply checks
	 * if the user has seconds left
	 */
	public boolean hasSecondsLeft(String songTitle);
}
