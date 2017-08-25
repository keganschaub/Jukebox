package model;
import java.io.Serializable;
import java.util.HashMap;

import usage.Usage;


public class SongPlays implements Serializable{
	
	HashMap<String, Usage> totalPlays = new HashMap<String, Usage>();
	HashMap<String, Usage> aliPlays = new HashMap<String, Usage>();
	HashMap<String, Usage> chrisPlays = new HashMap<String, Usage>();
	HashMap<String, Usage> riverPlays = new HashMap<String, Usage>();
	HashMap<String, Usage> ryanPlays = new HashMap<String, Usage>();
	
	/*
	 * This method sets up a hashmap for the total plays for
	 * a song, as well as the amount of plays each user has.
	 * Song title is put in as key, and amount of plays per day
	 * (Usage) as value.
	 */
	public SongPlays(String user){
		
		totalPlays.put("BlueRidgeMountainMist.mp3", new Usage(5));
		totalPlays.put("DeterminedTumbao.mp3", new Usage(5));
		totalPlays.put("flute.aif", new Usage(5));
		totalPlays.put("spacemusic.au", new Usage(5));
		totalPlays.put("SwingCheese.mp3", new Usage(5));
		totalPlays.put("tada.wav", new Usage(5));
		totalPlays.put("UntameableFire.mp3", new Usage(5));
		
		aliPlays.put("BlueRidgeMountainMist.mp3", new Usage(2));
		aliPlays.put("DeterminedTumbao.mp3", new Usage(2));
		aliPlays.put("flute.aif", new Usage(2));
		aliPlays.put("spacemusic.au", new Usage(2));
		aliPlays.put("SwingCheese.mp3", new Usage(2));
		aliPlays.put("tada.wav", new Usage(2));
		aliPlays.put("UntameableFire.mp3", new Usage(2));
		
		chrisPlays.put("BlueRidgeMountainMist.mp3", new Usage(2));
		chrisPlays.put("DeterminedTumbao.mp3", new Usage(2));
		chrisPlays.put("flute.aif", new Usage(2));
		chrisPlays.put("spacemusic.au", new Usage(2));
		chrisPlays.put("SwingCheese.mp3", new Usage(2));
		chrisPlays.put("tada.wav", new Usage(2));
		chrisPlays.put("UntameableFire.mp3", new Usage(2));
		
		riverPlays.put("BlueRidgeMountainMist.mp3", new Usage(2));
		riverPlays.put("DeterminedTumbao.mp3", new Usage(2));
		riverPlays.put("flute.aif", new Usage(2));
		riverPlays.put("spacemusic.au", new Usage(2));
		riverPlays.put("SwingCheese.mp3", new Usage(2));
		riverPlays.put("tada.wav", new Usage(2));
		riverPlays.put("UntameableFire.mp3", new Usage(2));
		
		ryanPlays.put("BlueRidgeMountainMist.mp3", new Usage(2));
		ryanPlays.put("DeterminedTumbao.mp3", new Usage(2));
		ryanPlays.put("flute.aif", new Usage(2));
		ryanPlays.put("spacemusic.au", new Usage(2));
		ryanPlays.put("SwingCheese.mp3", new Usage(2));
		ryanPlays.put("tada.wav", new Usage(2));
		ryanPlays.put("UntameableFire.mp3", new Usage(2));
	}
	
	/*
	 * This method records the total plays for player
	 * 
	 */
	public boolean addToSongPlays(String user, String songTitle){
		
		if(user.compareTo("Ali") == 0){
			return aliPlays.get(songTitle).use();
		}
		else if (user.compareTo("Chris") == 0){
			return chrisPlays.get(songTitle).use();
		}
		else if (user.compareTo("River") == 0){
			return riverPlays.get(songTitle).use();
		}
		else if (user.compareTo("Ryan") == 0){
			return ryanPlays.get(songTitle).use();
		}
		return aliPlays.get(songTitle).use();
	}
	
	/*
	 * A method that records the total plays for a song title
	 */
	public boolean addToTotalPlays(String user, String songTitle){
		return totalPlays.get(songTitle).use();
	}
	
	/*
	 * This method is used to check that it hasn't been used before calling use again
	 */
	public boolean canAddTotal(String user, String songTitle){
		return totalPlays.get(songTitle).canUse();
	}
	/*
	 * This method pretends it is tomorrow for total plays and user plays
	 */
	public void tomorrowsPlays(){
		String[] songs = {"BlueRidgeMountainMist.mp3", "DeterminedTumbao.mp3", "flute.aif", "spacemusic.au", "tada.wav", "UntameableFire.mp3"};
		for (int i = 0; i < songs.length -1; i ++){
			totalPlays.get(songs[i]).pretendItIsTomorrow();
			aliPlays.get(songs[i]).pretendItIsTomorrow();
			chrisPlays.get(songs[i]).pretendItIsTomorrow();
			riverPlays.get(songs[i]).pretendItIsTomorrow();
			ryanPlays.get(songs[i]).pretendItIsTomorrow();
		}
	}
}
