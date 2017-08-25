package tests;

import static org.junit.Assert.*;
import model.Jukebox;

import org.junit.Test;

import songplayer.EndOfSongEvent;
import view.JukeboxGUI;

public class JukeboxTesting {
	
	@Test
	public void testJukebox(){
		Jukebox jukeOne = new Jukebox();
		
		//Test if accountRegistered method is doing its job

		assertTrue(jukeOne.accountRegistered("Ali", "1111"));
		assertFalse(jukeOne.accountRegistered("Ali", "2222")); 
		assertFalse(jukeOne.accountRegistered("ali", "1111")); 
		assertFalse(jukeOne.accountRegistered("Ali", "11")); 
		
		assertTrue(jukeOne.accountRegistered("Chris", "2222"));
		assertFalse(jukeOne.accountRegistered("Chris", "5560"));
		
		assertTrue(jukeOne.accountRegistered("River", "3333"));
		assertFalse(jukeOne.accountRegistered("River", "1111"));
		
		assertTrue(jukeOne.accountRegistered("Ryan", "4444"));
		assertFalse(jukeOne.accountRegistered("Ryan", "23"));
		
		assertFalse(jukeOne.accountRegistered("Kino", "444"));
		
		//Test if addSong method is doing its job
		
		jukeOne.accountRegistered("Ali", "1111");
		
		assertTrue(jukeOne.hasSecondsLeft());
		jukeOne.addSong("flute.aif", 1000);
		assertTrue(jukeOne.hasSecondsLeft());
		jukeOne.addSong("flute.aif", 100);
		jukeOne.addSong("flute.aif", 100);
		jukeOne.addSong("flute.aif", 100);
		
		
		jukeOne.accountRegistered("Chris", "2222");
		jukeOne.addSong("flute.aif", 100);
		jukeOne.addSong("flute.aif", 100);
		jukeOne.addSong("flute.aif", 100);
		
		jukeOne.accountRegistered("River", "3333");
		
		jukeOne.addSong("flute.aif", 100);
		
		jukeOne.addSong("flute.aif", 100);
		
		//Test if tomorrowHasCome is working correctly
		
		jukeOne.tomorrowHasCome();
		jukeOne.accountRegistered("Ryan", "4444");
		jukeOne.addSong("flute.aif", 100);
		jukeOne.accountRegistered("Ali", "1111");
		jukeOne.addSong("flute.aif", 100);
		jukeOne.canAdd("flute.aif", 20);
		jukeOne.play();
		
		//jukeOne.songFinishedPlaying(eosEvent);
		//assertFalse(jukeOne.canAdd("tada", 3));
		//wait(50);
		
		assertEquals("00:01:40", jukeOne.timer("Chris"));
		assertEquals("100", jukeOne.getSeconds("Chris"));
		jukeOne.addSong("untameablefire.mp3", 100);
		
		JukeboxGUI.main(new String[]{});
	}

}
