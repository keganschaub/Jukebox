/*
 * CSC 335 Project "Jukebox Iteration: The View"
 * By Kegan Schaub & Brian Lee
 * This is the Execution File.
 */

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Jukebox;
import songplayer.EndOfSongListener;

public class JukeboxGUI extends JFrame implements Serializable{
	
	JPanel total;
	JPanel panel_SongList, panel_PlayList;
	
	JList playList = new JList();
	
	DefaultListModel playModel = new DefaultListModel();
	
	JButton login, songAdd, songPlay;
	JLabel signin, loginID, loginPW;
	JLabel label_SongAdd, label_SongPlay;
	JTextArea accountInfo_01, accountInfo_01_01, accountInfo_01_02;
	JTextArea accountInfo_02, accountInfo_03, accountInfo_04;
	
	JTextField id, pw;
	JTextField hello;
	
	String username, password;
	private int canPlaySongNumber = 0;
	
	String [] columnName = {"Artist", "Title", "Seconds"};			// JTable Setting
	Object [][] data = {{"Ralph Schuckett", "Blue Ridge Mountain Mist", 38}, 
			{"FreePlay Music", "Determined Tumbao", 20},
			{"Sun Microsystems", "Flute", 5},
			{"Unknown", "Space Music", 6},
			{"FreePlay Music", "Swing Cheese", 15},
			{"Microsoft", "tada", 2},
			{"Peirre Langer", "Untameable Fire", 282}};
	
	JTable table = new JTable(data, columnName);
	
	TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());	
	
	private Jukebox juke;

	public static String baseDir = System.getProperty("user.dir")
	      + File.separator + "serializedObjects" + File.separator;

	public static final String FILE_NAME_WHERE_ACCOUNTS_ARE_STORED = baseDir
	      + "jukeBox.object";

	
	public static void main(String[] args) {
		
		JukeboxGUI jukeWindow = new JukeboxGUI();
		jukeWindow.setVisible(true);
	}
	
	public JukeboxGUI() {

		playList.setModel(playModel);
		
		total = new JPanel();
		total.setLayout(null);

		juke = null;
	    readObjects();
	    
		loginGUI();

	    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    // Program now keeps running on windowClosing if the user so chooses.

	    // Instead of terminating the program, when the user attempts to
	    // close the window, the window remains visible. Instead of program
	    // termination, the windowClosing method of the WindowListener will
	    // prompt the user to do one of these three things:
	    //
	    // 0: Save objects and quit.
	    // 1: Quit without saving the objects.
	    // 2: The application keeps on running.
	    //
	    // To accomplish this different behavior, a WindowListener object
	    // will be registered to wait for the window closing event and
	    // handle all three possibilities. Another private inner class
	    // is added to this JFrame to handle all windowClosing events.
	    WindowClosingListener windowClosingListener = new WindowClosingListener();

	    // JFrames generate WindowEvents and send messages to WindowListeners
	    this.addWindowListener(windowClosingListener);
	}
	
	  // Read both collection objects from their disk files.
	  // Both file names are stored as class constants.
	  private void readObjects() {

	    int option = JOptionPane.showOptionDialog(null,
	        "Start from existing serialized objects?", "Select an Option",
	        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
	        null);
	 // the user can do three things:
	    // 1.  select Yes
	    // 2.  select No
	    // 3.  close the dialog box
	    // We want to make sure we handle all three scenarios
	    
	    if (option == JOptionPane.NO_OPTION || option == JOptionPane.CLOSED_OPTION) {
	      juke = new Jukebox();
	    } 
	    
	    else {
	    	try {
	        
		        FileInputStream inFile = new FileInputStream(
		            FILE_NAME_WHERE_ACCOUNTS_ARE_STORED);
		        ObjectInputStream inputStream = new ObjectInputStream(inFile);
		        juke = (Jukebox) inputStream.readObject();
		        inputStream.close();

	    	} 
	    	
	    	catch (Exception e) {
		        String message = "Error reading serialzed objects\n";
		        /*message += "";
		        */
		        JOptionPane.showMessageDialog(null, message);
	    	}
	    }
	  }

	  // This new type of Listener will offer three choices to any
	  // user that decides to close the window while this application
	  // is running (see the comments written in the constructor).
	  private class WindowClosingListener extends WindowAdapter implements Serializable{

	    public void windowClosing(WindowEvent we) {
	  
	      int choice = JOptionPane.showConfirmDialog(null, "Save data to disk?");
	      if (choice == JOptionPane.YES_OPTION) { // Save the current state of the
	                                              // collections and
	        // quit.
	        saveObjects();
	        System.exit(0);
	      }
	      else if (choice == JOptionPane.NO_OPTION) { // Quit without saving
	        System.exit(0);
	      }
	      else if (choice == JOptionPane.CANCEL_OPTION
	          || choice == JOptionPane.CLOSED_OPTION) { // Let the user continue to
	                                                    // use the
	        // program
	          JOptionPane.showMessageDialog(null, "Stay Calm\n Carry on ");
	      }
	    } // end the one of seven methods used in this WindowListener

	  }// end class WindowClosingListener

	  // Save both collection objects to disk.
	  // The old files will be replaced with the updated state of the objects.
	  private void saveObjects() {
	    try {
	      // Write the Jukebox()
	      FileOutputStream outFile = new FileOutputStream(
	          FILE_NAME_WHERE_ACCOUNTS_ARE_STORED);
	      ObjectOutputStream outputStream = new ObjectOutputStream(outFile);
	      outputStream.writeObject(juke);
	      // Do NOT forget to close the output stream!
	      outputStream.close();
	      
	    } catch (IOException ioe) {
	      String message = "Error writing objects to disk: " + "\n" + ioe
	          + "\nHope you had data backed up...";
	      JOptionPane.showMessageDialog(null, message);
	    }
	  } // end saveObjects

	private void loginGUI() {		// The Object for the First Login Window
		setTitle ("Jukebox v.1 made by Kegan & Brian");
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		setSize (500, 650);
		setLocation (500, 100);
		
		signin = new JLabel ("Sign in");
		signin.setFont(new Font ("Arial", Font.PLAIN, 17));
		signin.setSize(100, 30);
		signin.setLocation(160, 110);
		
		loginID = new JLabel ("Username");
		loginID.setSize(100, 30);
		loginID.setLocation(160, 150);
		
		loginPW = new JLabel ("Password");
		loginPW.setSize(100, 30);
		loginPW.setLocation(160, 210);
		

		id = new JTextField();
		id.setSize(200, 30);
		id.setLocation(160, 180);
		
		
		pw = new JPasswordField();
		pw.setSize(200, 30);
		pw.setLocation(160, 240);
		
		login = new JButton ("Sign in");
		login.setSize(80, 45);
		login.setLocation(160, 290);
		
		total.add (signin);
		total.add (loginID);
		total.add (loginPW);
		total.add (id);
		total.add (pw);
		total.add (login);
		
		login.addActionListener(new loginListener());

		this.add (total);
	}
	
	private void memberGUI() {		// When members login, it would be activated. 

		setSize (800, 650);
		setLocation (500, 100);
		
		total.setLayout(null);
		total.setBackground (Color.LIGHT_GRAY);

		table.setRowSorter(sorter);
		
		account();
		songList();
		playList();
		selectSongWindow();

		this.add (total);
	}
	
	private void account() {		// The information on the Top. (Member ID, Remaining Time, The Number of Songs can be Played)
		
		accountInfo_01 = new JTextArea("Username:");
		accountInfo_01.setBackground (Color.LIGHT_GRAY);
		accountInfo_01.setFont(new Font ("", Font.BOLD, 12));
		accountInfo_01.setSize(70, 15);
		accountInfo_01.setLocation(50, 0);
		
		accountInfo_01_01 = new JTextArea(id.getText());
		accountInfo_01_01.setBackground (Color.LIGHT_GRAY);
		accountInfo_01_01.setFont(new Font ("", Font.BOLD, 12));
		accountInfo_01_01.setForeground(Color.BLUE);
		accountInfo_01_01.setSize(100, 15);
		accountInfo_01_01.setLocation(120, 0);
		
		accountInfo_01_02 = new JTextArea("Time Remaining:");
		accountInfo_01_02.setBackground (Color.LIGHT_GRAY);
		accountInfo_01_02.setFont(new Font ("", Font.BOLD, 12));
		accountInfo_01_02.setSize(100, 15);
		accountInfo_01_02.setLocation(250, 0);
		
		accountInfo_02 = new JTextArea(juke.timer(id.getText()));	
		accountInfo_02.setBackground (Color.LIGHT_GRAY);
		accountInfo_02.setFont(new Font ("", Font.BOLD, 12));
		accountInfo_02.setForeground(Color.RED);
		accountInfo_02.setSize(50, 15);
		accountInfo_02.setLocation(355, 0);
		
		accountInfo_03 = new JTextArea("The number of songs can be played:");
		accountInfo_03.setBackground (Color.LIGHT_GRAY);
		accountInfo_03.setFont(new Font ("", Font.BOLD, 12));
		accountInfo_03.setSize(210, 15);
		accountInfo_03.setLocation(480, 0);
		
		accountInfo_04 = new JTextArea("" + canPlaySongNumber);
		accountInfo_04.setBackground (Color.LIGHT_GRAY);
		accountInfo_04.setFont(new Font ("", Font.BOLD, 12));
		accountInfo_04.setForeground(Color.RED);
		accountInfo_04.setSize(50, 15);
		accountInfo_04.setLocation(695, 0);
		
		total.add(accountInfo_01);
		total.add(accountInfo_01_01);
		total.add(accountInfo_01_02);
		total.add(accountInfo_02);
		total.add(accountInfo_03);
		total.add(accountInfo_04);
	}
	
	private void songList() {				// A Table of Total Songs 
		
		panel_SongList = new JPanel();
		panel_SongList.setSize(700, 150);
		panel_SongList.setLocation(50, 100);
		
		table.setPreferredScrollableViewportSize(new Dimension(690, 115));
		table.setFillsViewportHeight(true);
		
		JScrollPane scroll = new JScrollPane(table);
		
		panel_SongList.add(scroll);
		total.add(panel_SongList);
	}
	
	private void playList() {				// A List of Selected Songs
		
		panel_PlayList = new JPanel();
		panel_PlayList.setSize(350, 200);
		panel_PlayList.setLocation(50, 360);
		
		playList.setPreferredSize(new Dimension(350, 190));
		playList.setLocation(0, 0);
		playList.setBackground(Color.WHITE);
		
		panel_PlayList.add(playList);
		total.add(panel_PlayList);
	}
	
	private void selectSongWindow() {		// The Settings for Notification Labels & Button Design
		label_SongAdd = new JLabel ("Click the song you want to add");
		label_SongAdd.setFont(new Font ("", Font.BOLD, 12));
		label_SongAdd.setSize(400, 30);
		label_SongAdd.setLocation(50, 70);
		
		label_SongPlay = new JLabel ("Play List (Song at the Top is Playing)");
		label_SongPlay.setFont(new Font ("", Font.BOLD, 12));
		label_SongPlay.setSize(700, 30);
		label_SongPlay.setLocation(50, 330);
		
		songAdd = new JButton("Add Song");
		songAdd.setSize(100, 30);
		songAdd.setLocation(170, 280);

		songPlay = new JButton("Play Song");
		songPlay.setSize(100, 30);
		songPlay.setLocation(420, 400);
		
		songAdd.addActionListener(new addListener());
		songPlay.addActionListener(new playListener());
		
		total.add(label_SongAdd);
		total.add(label_SongPlay);
		total.add(songAdd);
		total.add(songPlay);
	}
	
	private class loginListener implements ActionListener, Serializable {		// The Setting for 'Login' Button in LoginGUI() Window

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if (juke.accountRegistered(id.getText(), pw.getText())) {		// A Case of a member ID & PW are validated
				JOptionPane.showMessageDialog (null, "Welcome, " + id.getText() + "!");
				total.removeAll();
				memberGUI();				// Then, Start memberGUI()
			}
			
			else {
				JOptionPane.showMessageDialog (null, "Invalid username or password. Try again.");	// A Case of invalid ID or PW
				id.setText("");
				pw.setText("");
			}
		}
	}

	private class addListener implements ActionListener, Serializable{		// The Setting for 'Song Add' button in memberGUI()

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			try {												// If no Song is Selected, show a Message.
				int selectedRowIndex = table.getSelectedRow();
				Object index1 = (Object) table.getModel().getValueAt(selectedRowIndex, 0);
			}
			
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Select a Song");
				return;
			}
			
			int secondsToAdd = 0;

			if (juke.hasSecondsLeft()) {
				
				int selectedRowIndex = table.getSelectedRow();
				
				String viewRow = "" + data[selectedRowIndex][1];
				
				if (viewRow.equals("Blue Ridge Mountain Mist")) {
					viewRow = "BlueRidgeMountainMist.mp3";
					secondsToAdd = 38;
				}
				
				else if (viewRow.equals("Determined Tumbao")) {
					viewRow = "DeterminedTumbao.mp3";
					secondsToAdd = 20;
				}
				
				else if (viewRow.equals("Flute")) {
					viewRow = "flute.aif";
					secondsToAdd = 5;
				}
				
				else if (viewRow.equals("Space Music")) {
					viewRow = "spacemusic.au";
					secondsToAdd = 6;
				}
				
				else if (viewRow.equals("Swing Cheese")) {
					viewRow = "SwingCheese.mp3";
					secondsToAdd = 15;
				}
				
				else if (viewRow.equals("tada")) {
					viewRow = "tada.wav";
					secondsToAdd = 2;
				}
				
				else if (viewRow.equals("Untameable Fire")) {
					viewRow = "UntameableFire.mp3";
					secondsToAdd = 282;
				}
				else{
					viewRow = "flute.aif";
				}
				
				Object index1 = (Object) table.getModel().getValueAt(selectedRowIndex, 0);		// Artist
				Object index2 = (Object) table.getModel().getValueAt(selectedRowIndex, 1);		// Song Title
				Object index3 = (Object) table.getModel().getValueAt(selectedRowIndex, 2);		// Length of Song
				
				int time = (int) index3;
				int min = time / 60;
				int sec = time - (min * 60);
				DecimalFormat df = new DecimalFormat("00");
				
				String list = min + ":" + df.format(sec) + " \"" + index2 + "\" by " + index1;		// The Number of Songs can be Played (On Top)
				
				if (juke.canAdd(viewRow, secondsToAdd)){
					juke.addSong(viewRow, secondsToAdd);
					playModel.addElement(list);
					canPlaySongNumber++;
					
					
					playList.setModel(playModel);				// Add to JList 
					playList.setForeground(Color.BLUE);
				}
				else{
					playModel.addElement(list);
					playModel.removeElement(list);

					playList.setModel(playModel);				// Add to JList 
					playList.setForeground(Color.BLUE);
				}

			}

			else
				JOptionPane.showMessageDialog(null, "No Time Left");
		
			total.removeAll();
			total.invalidate();
			total.validate();
			total.repaint();
			memberGUI();
		}		
	}
	
	private class playListener implements ActionListener, Serializable {		// A Setting for 'Play Song' button in memberGUI()

		private List<EndOfSongListener> listeners = new ArrayList<EndOfSongListener>();
		
		@Override
		public void actionPerformed(ActionEvent e) {
			juke.play();			
			canPlaySongNumber = 0;
			playModel.removeAllElements();
		}
	}
}
