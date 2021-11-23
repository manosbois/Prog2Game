import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Graph {
	JFrame frame;// We define the frame of the project.
	JPanel centralpanel, buttonpanel, bottompanel;// We define three panels that appear on the screen
	Button introbutton, mainbutton, startbutton, statsbutton, settingsbutton, quitbutton;// We define the basic buttons
																							// of the game
	Button swordbutton, spearbutton, shieldbutton, meditatebutton, nomovebutton;// We define the move buttons
	Button attackplus1, attackplus5, attackplus10, attackreset, armorplus1, armorplus5, armorplus10, armorreset,
			hpplus1, hpplus5, hpplus10, hpreset;
	Label introlabel, mainlabel; // We define the basic labels that appear on the game
	Label attackbarlabel, armorbarlabel, hpbarlabel, attackremain, armorremain, hpremain;// We define the basic
																							// statistics labels
	JProgressBar attackbar, armorbar, hpbar;// We define the progress bars that appear on the statistics screen
	final int WIDTH = 1280, HEIGHT = 800;// We define the the width and the height of the window
	Stages myStages = new Stages();
	
	public Graph(String title) {// We create the constructor of the class Graph

		frame = new JFrame(title);// We give the frame a title
		frame.setBounds(0, 0, WIDTH, HEIGHT);// We define the bounds of the frame
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);// We define that the window can close if I want to close it
		frame.setResizable(false);// We do not allow to resize the window
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);// We define that the window will appear
		frame.setLayout(new BorderLayout());// We define the layout that I'm going to use for the labels and the buttons

		createMainWindow();// We call the method createMainWindow that designs the first window that opens
							// when we start the game
	}

	public void createMainWindow() {// This method creates the first window that the user will see when he opens the
									// game
		centralpanel = new JPanel();// We define the main panel of the window
		centralpanel.setBackground(new Color(42, 82, 142));// We define the color of the panel centralpanel
															// based on rgb color
		centralpanel.setLayout(null);
		centralpanel.setLocation(0, 0);// We define the location of this panel
		centralpanel.setSize(WIDTH, HEIGHT);// We define the size of this panel

		/*
		 * bottompanel = new JPanel();// We define the secondary panel of the window
		 * bottompanel.setBackground(Color.DARK_GRAY);// We define the define the color
		 * of the panel bottompanel bottompanel.setLayout(null);// We define that we are
		 * not going to use a specific layout bottompanel.setLocation(0, 500);// We
		 * define the location of this panel bottompanel.setSize(WIDTH, HEIGHT / 15);//
		 * We define the size of this panel
		 */

		frame.add(centralpanel);// We add the panel centralpanel to the frame
		// frame.add(bottompanel);// We add the panel bottompanel to the frame

		introbutton = new Button("LET'S BEGIN OUR ADVENTURE");// We define the button on the first window that leads to
																// main menu
		introbutton.setLocation((WIDTH - 350) / 2, HEIGHT * 7 / 10);// We define the location of the button introbutton
																	// based on the width and the height of the
																	// window(so it could change if we change the size
																	// of the window
		introbutton.setSize(350, 50);// We define the size of the introbutton
		introbutton.setBackground(Color.RED);// We define the color of the introbutton
		introbutton.setForeground(Color.WHITE);// We define the font color of the introbutton
		introbutton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));// We define the size of the text of the
																		// introbutton and that the text is going to be
																		// bold

		introlabel = new Label("Welcome to our Game!");// We define the label that appears on the first window
		introlabel.setLocation((WIDTH - 450) / 2, HEIGHT * 2 / 10);// We define the location of the introlabel
		introlabel.setSize(450, 200);// We define the size of the introlabel
		introlabel.setForeground(Color.WHITE);// We define the font color of the introlabel
		introlabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));// We define the size of the text of the introlabel
																		// and that the text is going to be bold

		centralpanel.add(introlabel);// We add the label introlabel to the main panel(centralpanel)
		centralpanel.add(introbutton);// We add the button introbutton to the main panel(centralpanel)
		introbutton.addActionListener(new ActionListener() {// We define that if the user clicks the button that says
															// "LET'S BEGIN OUR ADVENTURE", he's goin to be returned
															// to the menu window
			public void actionPerformed(ActionEvent e) {
				centralpanel.remove(introbutton);// We remove button introbutton from the panel centralpanel
				centralpanel.remove(introlabel);// We remove label introlabel from the panel centralpanel
				createMenuWindow();// We call the method createMenuWindow that creates the window with the menu
			}
		});
	}

	public void createMenuWindow() {// That method creates the window that contains the menu
		startbutton = new Button("START THE GAME");// We create the button with the name startbutton that leads the user
													// to the game
		startbutton.setLocation(WIDTH / 10, HEIGHT * 3 / 20);// We define the location of the button startbutton
		startbutton.setSize(350, 100);// We define the size of the button startbutton
		startbutton.setBackground(Color.RED);// We define the color of the button startbutton
		startbutton.setForeground(Color.WHITE);// We define the font color of the button startbutton
		startbutton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));// We define the size of the text of the
																		// startbutton and that the text is going to be
																		// bold

		statsbutton = new Button("STATISTICS");// We create the button with the name statsbutton that leads the user to
												// his statistics
		statsbutton.setLocation(WIDTH / 10, HEIGHT * 7 / 20);// We define the location of the button statsbutton
		statsbutton.setSize(350, 100);// We define the size of the button statsbutton
		statsbutton.setBackground(Color.RED);// We define the size of the button statsbutton
		statsbutton.setForeground(Color.WHITE);// We define the font color of the button statsbutton
		statsbutton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));// We define the size of the text of the
																		// statsbutton and that the text is going to be
																		// bold

		settingsbutton = new Button("SETTINGS");// We create the button with the name settingsbutton that leads the user
												// to the settings of the game
		settingsbutton.setLocation(WIDTH / 10, HEIGHT * 11 / 20);// We define the location of the button settingsbutton
		settingsbutton.setSize(350, 100);// We define the size of the button settingsbutton
		settingsbutton.setBackground(Color.RED);// We define the size of the button settingsbutton
		settingsbutton.setForeground(Color.WHITE);// We define the font color of the button settingsbutton
		settingsbutton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));// We define the size of the text of the
																			// settingsbutton and that the text is going
																			// to be bold

		quitbutton = new Button("QUIT");// We create the button with the name quitbutton that exits the game
		quitbutton.setLocation(WIDTH / 10, HEIGHT * 15 / 20);// We define the location of the button quitbutton
		quitbutton.setSize(350, 100);// We define the size of the button quitbutton
		quitbutton.setBackground(Color.RED);// We define the size of the button quitbutton
		quitbutton.setForeground(Color.WHITE);// We define the font color of the button quitbutton
		quitbutton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));// We define the size of the text of the quitbutton
																		// and that the text is going to be bold

		mainbutton = new Button("CODERUNNERS");// We create the main button that appears on the top of the screen if we
												// choose any of the menu options so we can go back to the menu window
		mainbutton.setLocation((WIDTH - 350) / 2, 0);
		mainbutton.setSize(350, 50);
		mainbutton.setBackground(new Color(42, 82, 142));
		mainbutton.setForeground(Color.WHITE);
		mainbutton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

		/*
		 * mainlabel = new Label("Welcome to our Game!");//I
		 * mainlabel.setLocation((WIDTH - 350) / 2, 0); mainlabel.setSize(450, 100);
		 * mainlabel.setForeground(Color.WHITE); mainlabel.setFont(new
		 * Font(Font.SANS_SERIF, Font.BOLD, 30));
		 */

		centralpanel.add(startbutton);// We add to the frame the button startbutton
		centralpanel.add(statsbutton);// We add to the frame the button statsbutton
		centralpanel.add(settingsbutton);// We add to the frame the button settingsbutton
		centralpanel.add(quitbutton);// We add to the frame the button quitbutton
		// centralpanel.add(mainlabel);
		centralpanel.add(mainbutton);// We add to the frame the button startbutton

		startbutton.addActionListener(new ActionListener() {// The user has press the button startbutton
			public void actionPerformed(ActionEvent e) {
				centralpanel.remove(startbutton);// We remove the button startbutton from the window
				centralpanel.remove(statsbutton);// We remove the button statsbutton from the window
				centralpanel.remove(settingsbutton);// We remove the button settingsbutton from the window
				centralpanel.remove(quitbutton);// We remove the button quitbutton from the window
				// centralpanel.remove(mainlabel);

				createStartWindow();// We call the method that starts the game
			}
		});
		statsbutton.addActionListener(new ActionListener() {// The user has press the button statsbutton
			public void actionPerformed(ActionEvent e) {
				centralpanel.remove(startbutton);// We remove the button startbutton from the window
				centralpanel.remove(statsbutton);// We remove the button statsbutton from the window
				centralpanel.remove(settingsbutton);// We remove the button settingsbutton from the window
				centralpanel.remove(quitbutton);// We remove the button quitbutton from the window
				// centralpanel.remove(mainlabel);

				createStatisticsWindow();// We call the method that controls the statistics of the player(Hero)
			}
		});
		settingsbutton.addActionListener(new ActionListener() {// The user has press the button settingsbutton
			public void actionPerformed(ActionEvent e) {
				centralpanel.remove(startbutton);// We remove the button startbutton from the window
				centralpanel.remove(statsbutton);// We remove the button statsbutton from the window
				centralpanel.remove(settingsbutton);// We remove the button settingsbutton from the window
				centralpanel.remove(quitbutton);// We remove the button quitbutton from the window
				// centralpanel.remove(mainlabel);

				createSettingsWindow();// We call the method that controls the settings
			}
		});
		quitbutton.addActionListener(new ActionListener() {// The user has press the button quitbutton
			public void actionPerformed(ActionEvent e) {
				System.exit(0);// We exit the game
			}
		});
	}

	public void createStartWindow() {// We create the window that the player is going to play to
		buttonpanel = new JPanel();
		buttonpanel.setBackground(Color.BLACK);// We define the color of the panel centralpanel
												// based on rgb color
		buttonpanel.setLayout(null);
		buttonpanel.setLocation(0, HEIGHT * 4 / 5);// We define the location of this panel
		centralpanel.setSize(WIDTH, HEIGHT * 4 / 5);
		buttonpanel.setSize(WIDTH, HEIGHT / 5);// We define the size of this panel
		centralpanel.setSize(WIDTH, HEIGHT * 4 / 5);// We customize the size of the panel centralpanel so we can insert
													// the panel buttonpanel
		frame.add(buttonpanel);

		mainbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centralpanel.setSize(WIDTH, HEIGHT);
				centralpanel.remove(mainbutton);
				createMenuWindow();
			}
		});
	}

	public void createStatisticsWindow() {// We create the window that the play can see and upgrade his statistics
		attackbarlabel = new Label("ATTACK");// We create the label for the statistic bar for attack
		attackbarlabel.setLocation(WIDTH / 10, 110);// We define the location of the label attackbarlabel
		attackbarlabel.setSize(300, 75);// We define the size of the label attackbarlabel
		attackbarlabel.setForeground(Color.WHITE);// We define the font color of the label attackbarlabel
		attackbarlabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));// We define the size of the text of the label
																			// attackbarlabel and that the text is going
																			// to be bold

		attackbar = new JProgressBar(0, 300);// We create the progress bar for the statistic bar for attack
		attackbar.setValue(100);// We set the first value of the progress bar attackbar
		attackbar.setStringPainted(true);// The progress bar appear on the screen
		attackbar.setBackground(Color.red);// We define the color of the progress bar
		attackbar.setBounds(WIDTH / 10, HEIGHT * 1 / 5, 300, 75);// We define the location and the size of the progress
																	// bar

		attackplus1 = new Button("+1");
		attackplus1.setBounds((WIDTH / 10) + 450, HEIGHT * 1 / 5 + 25 / 2, 50, 50);// To 25/2 einai wste na einai
																					// symmetrika me to bar
		attackplus1.setBackground(Color.WHITE);
		attackplus1.setForeground(Color.BLACK);
		attackplus1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		attackplus5 = new Button("+5");
		attackplus5.setBounds((WIDTH / 10) + 525, HEIGHT * 1 / 5 + 25 / 2, 50, 50);
		attackplus5.setBackground(Color.WHITE);
		attackplus5.setForeground(Color.BLACK);
		attackplus5.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		attackplus10 = new Button("+10");
		attackplus10.setBounds((WIDTH / 10) + 600, HEIGHT * 1 / 5 + 25 / 2, 50, 50);
		attackplus10.setBackground(Color.WHITE);
		attackplus10.setForeground(Color.BLACK);
		attackplus10.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		attackremain = new Label(" Remaining: " + 15);
		attackremain.setBounds((WIDTH / 10) + 675, HEIGHT * 1 / 5 + 25 / 2, 230, 50);
		attackremain.setBackground(Color.WHITE);
		attackremain.setForeground(Color.BLACK);
		attackremain.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		armorbarlabel = new Label("ARMOR");// We create the label for the statistic bar for armor
		armorbarlabel.setLocation(WIDTH / 10, 270);// We define the location of the label armorbarlabel
		armorbarlabel.setSize(300, 75);// We define the size of the label armorbarlabel
		armorbarlabel.setForeground(Color.WHITE);// We define the font color of the label armorbarlabel
		armorbarlabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));// We define the size of the text of the label
																		// armorbarlabel and that the text is going to
																		// be bold
		attackreset = new Button("Reset");
		attackreset.setBounds((WIDTH / 10) + 930, HEIGHT * 1 / 5 + 25 / 2, 100, 50);
		attackreset.setBackground(Color.WHITE);
		attackreset.setForeground(Color.BLACK);
		attackreset.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		armorbar = new JProgressBar(0, 300);// We create the progress bar for the statistic bar for armor
		armorbar.setValue(100);// We set the first value of the progress bar armorbar
		armorbar.setStringPainted(true);// The progress bar appear on the screen
		armorbar.setBackground(Color.red);// We define the color of the progress bar
		armorbar.setBounds(WIDTH / 10, HEIGHT * 2 / 5, 300, 75);// We define the location and the size of the progress
																// bar
		armorplus1 = new Button("+1");
		armorplus1.setBounds((WIDTH / 10) + 450, HEIGHT * 2 / 5 + 25 / 2, 50, 50);// To 25/2 einai wste na einai
																					// symmetrika me to bar
		armorplus1.setBackground(Color.WHITE);
		armorplus1.setForeground(Color.BLACK);
		armorplus1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		armorplus5 = new Button("+5");
		armorplus5.setBounds((WIDTH / 10) + 525, HEIGHT * 2 / 5 + 25 / 2, 50, 50);
		armorplus5.setBackground(Color.WHITE);
		armorplus5.setForeground(Color.BLACK);
		armorplus5.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		armorplus10 = new Button("+10");
		armorplus10.setBounds((WIDTH / 10) + 600, HEIGHT * 2 / 5 + 25 / 2, 50, 50);
		armorplus10.setBackground(Color.WHITE);
		armorplus10.setForeground(Color.BLACK);
		armorplus10.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		armorremain = new Label(" Remaining: " + 15);
		armorremain.setBounds((WIDTH / 10) + 675, HEIGHT * 2 / 5 + 25 / 2, 230, 50);
		armorremain.setBackground(Color.WHITE);
		armorremain.setForeground(Color.BLACK);
		armorremain.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		
		armorreset = new Button("Reset");
		armorreset.setBounds((WIDTH / 10) + 930, HEIGHT * 2 / 5 + 25 / 2, 100, 50);
		armorreset.setBackground(Color.WHITE);
		armorreset.setForeground(Color.BLACK);
		armorreset.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		hpbarlabel = new Label("HEALTH POWER");// We create the label for the statistic bar for health power
		hpbarlabel.setLocation(WIDTH / 10, 430);// We define the location of the label hpbarlabel
		hpbarlabel.setSize(300, 75);// We define the size of the label hpbarlabel
		hpbarlabel.setForeground(Color.WHITE);// We define the font color of the label hpbarlabel
		hpbarlabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));// We define the size of the text of the label
																		// hpbarlabel and that the text is going to be
																		// bold

		hpbar = new JProgressBar(0, 300);// We create the progress bar for the statistic bar for health power
		hpbar.setValue(100);// We set the first value of the progress bar armorbar
		hpbar.setStringPainted(true);// The progress bar appear on the screen
		hpbar.setBackground(Color.red);// We define the color of the progress bar
		hpbar.setBounds(WIDTH / 10, HEIGHT * 3 / 5, 300, 75);// We define the location and the size of the progress bar

		hpplus1 = new Button("+1");
		hpplus1.setBounds((WIDTH / 10) + 450, HEIGHT * 3 / 5 + 25 / 2, 50, 50);// To 25/2 einai wste na einai symmetrika
																				// me to bar
		hpplus1.setBackground(Color.WHITE);
		hpplus1.setForeground(Color.BLACK);
		hpplus1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		hpplus5 = new Button("+5");
		hpplus5.setBounds((WIDTH / 10) + 525, HEIGHT * 3 / 5 + 25 / 2, 50, 50);
		hpplus5.setBackground(Color.WHITE);
		hpplus5.setForeground(Color.BLACK);
		hpplus5.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		hpplus10 = new Button("+10");
		hpplus10.setBounds((WIDTH / 10) + 600, HEIGHT * 3 / 5 + 25 / 2, 50, 50);
		hpplus10.setBackground(Color.WHITE);
		hpplus10.setForeground(Color.BLACK);
		hpplus10.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		
		hpremain = new Label(" Remaining: " + 15);
		hpremain.setBounds((WIDTH / 10) + 675, HEIGHT * 3 / 5 + 25 / 2, 230, 50);
		hpremain.setBackground(Color.WHITE);
		hpremain.setForeground(Color.BLACK);
		hpremain.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		
		hpreset = new Button("Reset");
		hpreset.setBounds((WIDTH / 10) + 930, HEIGHT * 3 / 5 + 25 / 2, 100, 50);
		hpreset.setBackground(Color.WHITE);
		hpreset.setForeground(Color.BLACK);
		hpreset.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		centralpanel.add(attackbar);// We add the progress bar attackbar at the panel
		centralpanel.add(armorbar);// We add the progress bar armorbar at the panel
		centralpanel.add(hpbar);// We add the progress bar hpbar at the panel

		centralpanel.add(attackbarlabel);// We add the label attackbarlabel at the panel
		centralpanel.add(armorbarlabel);// We add the label armorbarlabel at the panel
		centralpanel.add(hpbarlabel);// We add the label hpbarlabel at the panel

		centralpanel.add(attackplus1);
		centralpanel.add(attackplus5);
		centralpanel.add(attackplus10);
		centralpanel.add(attackremain);
		centralpanel.add(attackreset);

		centralpanel.add(armorplus1);
		centralpanel.add(armorplus5);
		centralpanel.add(armorplus10);
		centralpanel.add(armorremain);
		centralpanel.add(armorreset);
		
		centralpanel.add(hpplus1);
		centralpanel.add(hpplus5);
		centralpanel.add(hpplus10);
		centralpanel.add(hpremain);
		centralpanel.add(hpreset);

		mainbutton.addActionListener(new ActionListener() {// If the user clicks the button mainbutton the game returns
															// to the menu window
			public void actionPerformed(ActionEvent e) {
				centralpanel.remove(attackbar);// We remove the progress bar attackbar from the panel
				centralpanel.remove(armorbar);// We remove the progress bar armorbar from the panel
				centralpanel.remove(hpbar);// We remove the progress bar hpbar from the panel

				centralpanel.remove(attackbarlabel);// We remove the progress bar attackbarlabel from the panel
				centralpanel.remove(armorbarlabel);// We remove the progress bar armorbarlabel from the panel
				centralpanel.remove(hpbarlabel);// We remove the progress bar hpbarlabel from the panel

				centralpanel.remove(attackplus1);
				centralpanel.remove(attackplus5);
				centralpanel.remove(attackplus10);
				centralpanel.remove(attackremain);
				centralpanel.remove(attackreset);

				centralpanel.remove(armorplus1);
				centralpanel.remove(armorplus5);
				centralpanel.remove(armorplus10);
				centralpanel.remove(armorremain);
				centralpanel.remove(armorreset);

				centralpanel.remove(hpplus1);
				centralpanel.remove(hpplus5);
				centralpanel.remove(hpplus10);
				centralpanel.remove(hpremain);
				centralpanel.remove(hpreset);

				centralpanel.remove(mainbutton);// We remove the progress bar mainbutton from the panel
				createMenuWindow();
			}
		});
	}

	public void createSettingsWindow() {

		mainbutton.addActionListener(new ActionListener() {// If the user clicks the button mainbutton the game returns
															// to the menu window
			public void actionPerformed(ActionEvent e) {
				centralpanel.remove(mainbutton);// We remove the progress bar mainbutton from the panel
				createMenuWindow();
			}
		});
	}
}
