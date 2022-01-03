import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

public class Graph {// Creating the class Graph
	private static final Object graphLock = new Object();
	public static Object getGraphLock() {
		return graphLock;
	}

	private static final long serialVersionUID = 7572156739102733039L;
	private String title;
	static JFrame frame; // We define the frame of the project.
	JPanel centralPanel, buttonPanel; // We define three panels that appear on the screen
	JTextField username;
	Button introButton, mainButton, startButton, rulesButton, settingsButton, quitButton;// We define the basic buttons
	// of the game
	static Label heroName, godName, heroHpBar, godHBar, heroBackBar, godBackBar, heroEnergy; // Label
	// names(hero
	// and god
	// names)
	JButton swordButton, spearButton, meditateButton, shieldButton, noMoveButton;
	Button nextGod, checkpoint, gameOver;// We define the move buttons
	static Label heroHp, godHp;
	JLabel godImage, heroImage, zeusBackground;
	static Label mes1;
	TextArea rules;
	BufferedImage godIcon;
	Label battleWin, winMes, loseMes, checkpointMes;
	Button attackPlus1, attackPlus5, attackPlus10, attackReset, armourPlus1, armourPlus5, armourPlus10, armourReset,
			hpPlus1, hpPlus5, hpPlus10, hpReset, doneButton;// statistics buttons
	Label introLabel, mainLabel; // We define the basic labels that appear on the game
	Label attackBarLabel, armourBarLabel, hpBarLabel, attackRemain, armourRemain, hpRemain, apAttackLabel, apArmourLabel,
			apHpLabel, attributePoints, doneLabel;// We define the basic
	// statistics labels
	JProgressBar attackBar, armourBar, hpBar;// We define the progress bars that appear on the statistics screen
	Button lightMode, darkMode, greekButton, englishButton;// settings' buttons
	static final int WIDTH = 1280, HEIGHT = 800;// We define the width and the height of the window

	private String tempHeroName; //Variable that's to temporarily save
							// the username to set it when stageControl is called
	private Clip clip2; // For music.
	private boolean FirstGod = true; // Variable that's used to know whether to start the battleThread or to notify
	// it.
	private static int chosenMove;

	private static String language = "En";

	public String getTempHeroName() { return tempHeroName; }

	public static int getChosenMove() {	return chosenMove; }

	public static void setChosenMove(int move) { chosenMove = move; }

	public static String getLanguage() { return language; }
	
	//Returns the contents of the line of a file that is located the number specified.
	public static String getLine(final int numberOfLine, final String fileName) throws IOException {

		if (numberOfLine <= 0) return "Non positive number of line?";
		try (BufferedReader bf = new BufferedReader(
				new InputStreamReader(Objects.requireNonNull
						(Graph.class.getResourceAsStream(fileName))))) {
			for (int i = 1; i <= numberOfLine; i++) {
				if (i == numberOfLine) {
					return bf.readLine(); //Found the line.
				} else {
					bf.readLine();
				}
			}
		}
		return null;
	}

	private Thread battleThread;
	private Runnable battleTasks = () -> {
		try {
			Stages.stageControl();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};

	public Graph(String title) {// We create the constructor of the class Graph

		Runnable graphTasks = this::createFrame;
		Thread graphThread = new Thread(graphTasks);
		graphThread.start();

		this.title = title;
	}

	public void createFrame() {
		frame = new JFrame(title);// We give the frame a title
		frame.setBounds(0, 0, WIDTH, HEIGHT);// We define the bounds of the frame
		frame.setResizable(false); // We do not allow to resize the window
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);// We define that the window will appear
		frame.setLayout(new BorderLayout());// We define the layout that I'm going to use for the labels and the buttons

		createMainWindow();// We call the method createMainWindow that designs the first window that opens
		// when we start the game
	}

	public void createMainWindow() {// This method creates the first window that the user will see when he opens the
		// game
		centralPanel = new JPanel();// We define the main panel of the window
		centralPanel.setBackground(Color.BLACK);// We define the color of the panel centralpanel
		// based on rgb color
		centralPanel.setLayout(null);
		centralPanel.setLocation(0, 0);// We define the location of this panel
		centralPanel.setSize(WIDTH, HEIGHT);// We define the size of this panel

		/*
		 * bottompanel = new JPanel();// We define the secondary panel of the window
		 * bottompanel.setBackground(Color.DARK_GRAY);// We define the color of the
		 * panel bottompanel bottompanel.setLayout(null);// We define that we are not
		 * going to use a specific layout bottompanel.setLocation(0, 500);// We define
		 * the location of this panel bottompanel.setSize(WIDTH, HEIGHT / 15);// We
		 * define the size of this panel
		 */
		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 0, 0, 0);
		frame.add(centralPanel);// We add the panel centralpanel to the frame
		frame.add(buttonPanel);// We add the panel bottompanel to the frame

		introButton = new Button("LET'S BEGIN OUR ADVENTURE");// We define the button on the first window that leads to
		// main menu

		username = new JTextField("Enter Your Name Here");
		username.setBounds((WIDTH - 350) / 2, HEIGHT * 3 / 5, 350, 50);
		username.setBackground(Color.WHITE);
		username.setForeground(Color.BLUE);
		username.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));


		introButton.setLocation((WIDTH - 450) / 2, HEIGHT * 7 / 10);// We define the location of the button introbutton
		// based on the width and the height of the
		// window(so it could change if we change the size
		// of the window
		introButton.setSize(450, 100);// We define the size of the introbutton
		introButton.setBackground(Color.white);// We define the color of the introbutton
		introButton.setForeground(Color.black);// We define the font color of the introbutton
		introButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));// We define the size of the text of the
		// introButton and that the text is going to be
		// bold

		introLabel = new Label("Welcome to our Game!");// We define the label that appears on the first window
		introLabel.setLocation((WIDTH - 450) / 2, HEIGHT * 2 / 10);// We define the location of the introlabel
		introLabel.setSize(450, 200);// We define the size of the introlabel
		introLabel.setForeground(Color.WHITE);// We define the font color of the introlabel
		introLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));// We define the size of the text of the introlabel
		// and that the text is going to be bold

		centralPanel.add(introLabel);// We add the label introlabel to the main panel(centralpanel)
		centralPanel.add(introButton);// We add the button introbutton to the main panel(centralpanel)
		centralPanel.add(username);

		introButton.addActionListener(new ActionListener() {// We define that if the user clicks the button that says
			// "LET'S BEGIN OUR ADVENTURE", he's going to be returned
			// to the menu window
			public void actionPerformed(ActionEvent e) {
				tempHeroName = username.getText();
				//Stages.myHero.setName(username.getText());
				centralPanel.remove(introButton);// We remove button introbutton from the panel centralpanel
				centralPanel.remove(introLabel);// We remove label introlabel from the panel centralpanel
				centralPanel.remove(username);
				createMenuWindow();// We call the method createMenuWindow that creates the window with the menu
			}
		});
	}
//my path: "C:\\Users\\manoz\\IdeaProjects\\Game\\src\\"
	public void createMenuWindow() {// That method creates the window that contains the menu
		try {
			startButton = new Button(
					getLine(7,getLanguage()
							+ "-Graph.txt")); //Message: START THE GAME
			// We create the button with the name startbutton that leads the user
			// to the game
			modifyMenyButtons(startButton, 0);

			rulesButton = new Button(
					getLine(8,getLanguage()
							+ "-Graph.txt")); //Message: RULES
			// We create the button with the name statsbutton that leads the user to
			// his statistics
			modifyMenyButtons(rulesButton, 1);

			settingsButton = new Button(
					getLine(9,getLanguage()
							+ "-Graph.txt")); //Message: SETTINGS
			// We create the button with the name settingsbutton that leads the user
			// to the settings of the game
			modifyMenyButtons(settingsButton, 2);

			quitButton = new Button(
					getLine(10,getLanguage()
							+ "-Graph.txt")); //Message: QUIT
			// We create the button with the name quitbutton that exits the game
			modifyMenyButtons(quitButton, 3);
		} catch (IOException e) {
			e.printStackTrace();
		}

		mainButton = new Button("CODERUNNERS");// We create the main button that appears at the top of the screen if we
		// choose any of the menu options, so we can go back to the menu window
		mainButton.setLocation((WIDTH - 350) / 2, 0);
		mainButton.setSize(350, 50);
		mainButton.setBackground(Color.BLACK);
		mainButton.setForeground(Color.WHITE);
		mainButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

		centralPanel.add(startButton);// We add to the frame the button startbutton
		centralPanel.add(rulesButton);// We add to the frame the button statsbutton
		centralPanel.add(settingsButton);// We add to the frame the button settingsbutton
		centralPanel.add(quitButton);// We add to the frame the button quitbutton
		// centralpanel.add(mainlabel);
		centralPanel.add(mainButton);// We add to the frame the button startbutton

		startButton.addActionListener(new ActionListener() {// The user has press the button startbutton
			public void actionPerformed(ActionEvent e) {
				removeMenuButtons();
				centralPanel.remove(mainButton);
				createStartWindow();// We call the method that starts the game
			}
		});
		rulesButton.addActionListener(new ActionListener() {// The user has press the button statsbutton
			public void actionPerformed(ActionEvent e) {
				removeMenuButtons();

				createRulesWindow();// We call the method that controls the statistics of the player(Hero)
			}
		});
		settingsButton.addActionListener(new ActionListener() {// The user has press the button settingsbutton
			public void actionPerformed(ActionEvent e) {
				removeMenuButtons();

				createSettingsWindow();// We call the method that controls the settings
			}
		});
		quitButton.addActionListener(new ActionListener() {// The user has press the button quitbutton
			public void actionPerformed(ActionEvent e) {
				System.exit(0);// We exit the game
			}
		});
	}

	private void modifyMenyButtons(Button menuButton, int numOfButton) {
		menuButton.setLocation(WIDTH / 10, HEIGHT * (3 + 4 * numOfButton) / 20);// We define the location of the button
		// startbutton
		menuButton.setSize(350, 100);// We define the size of the button startbutton
		menuButton.setBackground(Color.white);// We define the color of the button startbutton
		menuButton.setForeground(Color.black);// We define the font color of the button startbutton
		menuButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));// We define the size of the text of the
	}

	private void removeMenuButtons() {
		centralPanel.remove(startButton);// We remove the button startbutton from the window
		centralPanel.remove(rulesButton);// We remove the button statsbutton from the window
		centralPanel.remove(settingsButton);// We remove the button settingsbutton from the window
		centralPanel.remove(quitButton);// We remove the button quitbutton from the window
	}

	public void createStartWindow() {// We create the window that the player is going to play to

		battleThread = new Thread(battleTasks);
		if (FirstGod) {
			battleThread.start();
			synchronized (graphLock) {
				try {
					graphLock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			synchronized (Battle.getLock()) {
				Battle.getLock().notify();
			}
		}

		heroName = new Label(Stages.myHero.getName());
		heroName.setBounds(50, HEIGHT / 10, 200, 50);
		heroName.setAlignment(Label.CENTER);
		heroName.setForeground(Color.BLACK);
		heroName.setBackground(Color.ORANGE);
		heroName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));

		godName = new Label();
		godName.setBounds(WIDTH - 250, HEIGHT / 10, 200, 50);
		godName.setForeground(Color.BLACK);
		godName.setBackground(Color.ORANGE);
		godName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
		godName.setAlignment(Label.CENTER);

		

		heroBackBar = new Label();
		heroBackBar.setBounds(50, HEIGHT / 10 + 50, 200, 30);
		heroBackBar.setBackground(Color.WHITE);

		heroHpBar = new Label();
		heroHpBar.setBounds(50, HEIGHT / 10 + 50, 200, 30);
		heroHpBar.setForeground(Color.BLACK);
		heroHpBar.setBackground(Color.GREEN);
		heroHpBar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
		heroHpBar.setAlignment(Label.CENTER);

		heroHp = new Label("HP: " + String.valueOf(Stages.myHero.getHp()));
		heroHp.setBounds(50, HEIGHT / 10 + 50 + 5 + 26, 200, 20);
		heroHp.setForeground(Color.BLACK);
		heroHp.setBackground(Color.RED);
		heroHp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		heroHp.setAlignment(Label.LEFT);

		heroEnergy = new Label("Energy: " + String.valueOf(Stages.myHero.getEnergy()));
		heroEnergy.setBounds(50, HEIGHT / 10 + 50 + 5 + 48, 200, 20);
		heroEnergy.setForeground(Color.BLACK);
		heroEnergy.setBackground(Color.CYAN);
		heroEnergy.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		heroEnergy.setAlignment(Label.LEFT);

		godBackBar = new Label();
		godBackBar.setBounds(WIDTH - 250, HEIGHT / 10 + 50, 200, 30);
		godBackBar.setBackground(Color.WHITE);

		godHBar = new Label();
		godHBar.setBounds(WIDTH - 250, HEIGHT / 10 + 50, 200, 30);
		godHBar.setForeground(Color.BLACK);
		godHBar.setBackground(Color.GREEN);
		godHBar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
		godHBar.setAlignment(Label.CENTER);

		godHp = new Label();
		godHp.setBounds(WIDTH - 250, HEIGHT / 10 + 50 + 31, 200, 20);
		godHp.setForeground(Color.BLACK);
		godHp.setBackground(Color.RED);
		godHp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		godHp.setAlignment(Label.LEFT);
		godHp.setText("HP: " + 100 + "%");
		
		mes1 = new Label();
		mes1.setBounds(0, HEIGHT * 9 / 10 - 280 + 5 * 40, WIDTH, 40);
		mes1.setBackground(Color.white);
		mes1.setForeground(Color.BLACK);
		mes1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		
		godImage = new JLabel();
		godImage.setBounds(WIDTH / 2 + 300, HEIGHT * 9 / 10 - 450, 240, 403);
		try {
			InputStream resourceBf = Graph.class.getResourceAsStream(Battle.god.getName() + ".jpg");
			BufferedImage bf = ImageIO.read(resourceBf);
			ImageIcon im = new ImageIcon(bf);
			godImage.setIcon(im);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		heroImage = new JLabel();
		heroImage.setBounds(WIDTH / 2 - 550, HEIGHT * 9 / 10 - 423, 212, 343);
		try {
			InputStream resourceBf = Graph.class.getResourceAsStream("Hero.jpg");
			BufferedImage bf = ImageIO.read(resourceBf);
			ImageIcon im = new ImageIcon(bf);
			heroImage.setIcon(im);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
		
		buttonPanel.setBackground(Color.DARK_GRAY);// We define the color of the panel centralpanel
		// based on rgb color
		buttonPanel.setLayout(null);
		buttonPanel.setLocation(0, HEIGHT - 130);// We define the location of this panel
		centralPanel.setSize(WIDTH, HEIGHT - 130);// We customize the size of the panel centralpanel, so we can insert
		buttonPanel.setSize(WIDTH, 130);// We define the size of this panel

		// centralpanel.setSize(0,0);
		// the panel buttonpanel
		if (Battle.god.getName().equals("Zeus")) {
			godImage.setLocation(WIDTH / 2 + 300, HEIGHT * 9 / 10 - 483);
			zeusBackground = new JLabel();
			zeusBackground.setBounds(0, 0, WIDTH, HEIGHT - 130);
			try {
				InputStream resourceBf3 = Graph.class.getResourceAsStream("zeusback1.jpg");
				BufferedImage bf = ImageIO.read(resourceBf3);
				ImageIcon im = new ImageIcon(bf);
				zeusBackground.setIcon(im);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		swordButton = new JButton("1. Sword");
		modifyMoveButtons(swordButton, 1, 90, 6);

		spearButton = new JButton("2. Spear");
		modifyMoveButtons(spearButton, 2, 70, 4);

		shieldButton = new JButton("3. Shield");
		modifyMoveButtons(shieldButton, 3, 0, 5);

		meditateButton = new JButton("4. Meditate");
		modifyMoveButtons(meditateButton, 4, 0, 10);

		noMoveButton = new JButton("5. No Move");
		modifyMoveButtons(noMoveButton, 5, 0, 0);

		addStartWindow();

		mainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centralPanel.setSize(WIDTH, HEIGHT);
				buttonPanel.setSize(0, 0);
				removeStartWindow();

				createMenuWindow();
			}
		});

		swordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized (Battle.getLock()) {
					setChosenMove(1);
					Battle.getLock().notify();
				}
			}
		});
		spearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized (Battle.getLock()) {
					setChosenMove(2);
					Battle.getLock().notify();
				}
			}
		});
		shieldButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized (Battle.getLock()) {
					setChosenMove(3);
					Battle.getLock().notify();
				}
			}
		});
		meditateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized (Battle.getLock()) {
					setChosenMove(4);
					Battle.getLock().notify();
				}
			}
		});
		noMoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized (Battle.getLock()) {
					setChosenMove(5);
					Battle.getLock().notify();
				}
			}
		});

	}

	private void modifyMoveButtons(JButton move, int numOfMove, int damage, int energy) {
		move.setBounds(40 * numOfMove + 200 * (numOfMove - 1), 20, 200, 50);
		move.setBackground(Color.WHITE);
		move.setForeground(Color.BLACK);
		move.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		move.setToolTipText("Damage:" + damage + " & Energy:" + energy);
	}

	private void addStartWindow() {
		if (Battle.god.getName().equals("Zeus")) {
			centralPanel.add(zeusBackground);
			zeusBackground.add(heroName);
			zeusBackground.add(godName);
			zeusBackground.add(heroHpBar);
			zeusBackground.add(heroBackBar);
			zeusBackground.add(godHBar);
			zeusBackground.add(godBackBar);
			zeusBackground.add(heroHp);
			zeusBackground.add(heroEnergy);
			zeusBackground.add(godHp);
			centralPanel.add(mes1);
			zeusBackground.add(godImage);
			zeusBackground.add(heroImage);
		} else {
			centralPanel.add(heroName);
			centralPanel.add(godName);
			centralPanel.add(heroHpBar);
			centralPanel.add(heroBackBar);
			centralPanel.add(godHBar);
			centralPanel.add(godBackBar);
			centralPanel.add(heroHp);
			centralPanel.add(heroEnergy);
			centralPanel.add(godHp);
			centralPanel.add(mes1);
			centralPanel.add(godImage);
			centralPanel.add(heroImage);
		}

		buttonPanel.add(swordButton);
		buttonPanel.add(spearButton);
		buttonPanel.add(meditateButton);
		buttonPanel.add(shieldButton);
		buttonPanel.add(noMoveButton);
	}

	private void removeStartWindow() {
		if (Battle.god.getName().equals("Zeus")) {
			centralPanel.remove(zeusBackground);
			zeusBackground.remove(heroName);
			zeusBackground.remove(godName);
			zeusBackground.remove(heroHpBar);
			zeusBackground.remove(heroBackBar);
			zeusBackground.remove(godHBar);
			zeusBackground.remove(godBackBar);
			zeusBackground.remove(heroHp);
			zeusBackground.remove(heroEnergy);
			zeusBackground.remove(godHp);
			zeusBackground.remove(mes1);
			zeusBackground.remove(godImage);
			zeusBackground.remove(heroImage);
		} else {
			centralPanel.remove(mainButton);
			centralPanel.remove(heroName);
			centralPanel.remove(godName);
			centralPanel.remove(heroHpBar);
			centralPanel.remove(heroBackBar);
			centralPanel.remove(godHBar);
			centralPanel.remove(godBackBar);
			centralPanel.remove(heroHp);
			centralPanel.remove(heroEnergy);
			centralPanel.remove(godHp);
			centralPanel.remove(mes1);
			centralPanel.remove(godImage);
			centralPanel.remove(heroImage);
		}

		buttonPanel.remove(swordButton);
		buttonPanel.remove(spearButton);
		buttonPanel.remove(meditateButton);
		buttonPanel.remove(shieldButton);
		buttonPanel.remove(noMoveButton);
	}

	public static void modifyHpLabels(Character hero, int hp, int initialHp, int herodamage) {
		if (hero.getName().equals(Stages.myHero.getName())) {
			heroHpBar.setSize(200 * hp / initialHp, 30);
			heroHp.setText("HP: " + hp);
		} else {
			godHBar.setSize(200 * hp / initialHp, 30);
			godHp.setText("HP: " + hp * 100 / initialHp + "%");
		}
	}

	public static void modifyEnergyLabel(int energy) {
		heroEnergy.setText("Energy: " + energy);
	}

	public void modifyMes(String text) {
		mes1.setText(text);
	}

	public void clearMes() {
		/*
		 * mes1.setText(""); mes2.setText(""); mes3.setText(""); mes4.setText("");
		 * mes5.setText("");
		 */
		mes1.setText("");
	}

	public void createLoseWindow() {
		centralPanel.setSize(WIDTH, HEIGHT);
		buttonPanel.setSize(0, 0);
		removeStartWindow();

		try {
			loseMes = new Label();
			loseMes.setBounds(WIDTH / 2 - 100, HEIGHT / 2 - 50, 200, 100);
			loseMes.setForeground(Color.RED);
			loseMes.setText(
					getLine(11,getLanguage()
							+ "-Graph.txt")); //Message: GAME OVER!
			loseMes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
			loseMes.setAlignment(Label.CENTER);

			gameOver = new Button(
					getLine(12,getLanguage()
							+ "-Graph.txt")); //Message: Return to the main menu
			gameOver.setBounds(WIDTH / 2 - 150, HEIGHT / 2 + 150, 300, 70);
			gameOver.setForeground(Color.BLACK);
			gameOver.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		} catch (IOException e) {
			e.printStackTrace();
		}

		centralPanel.add(loseMes);
		centralPanel.add(gameOver);

		gameOver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centralPanel.remove(loseMes);
				centralPanel.remove(gameOver);
				createMenuWindow();
			}
		});

	}

	public void createWinWindow() {
		centralPanel.setSize(WIDTH, HEIGHT);
		buttonPanel.setSize(0, 0);
		removeStartWindow();

		winMes = new Label();
		winMes.setBounds(0, HEIGHT / 2 - 50, WIDTH, 100);
		winMes.setForeground(new Color(255, 215, 0));
		try {
			winMes.setText(
					getLine(1,getLanguage()
							+ "-Graph.txt")); //Message: YOU WON! YOU HAVE CONQUERED OLYMPUS!
		} catch (IOException e) {
			e.printStackTrace();
		}
		winMes.setFont(new Font(Font.SERIF, Font.BOLD, 32));
		winMes.setAlignment(Label.CENTER);

		centralPanel.add(winMes);
	}

	public void createBattleWinWindow(String godName) {
		centralPanel.setSize(WIDTH, HEIGHT);
		buttonPanel.setSize(0, 0);
		removeStartWindow();

		battleWin = new Label();
		battleWin.setBounds(0, HEIGHT / 2 - 150, WIDTH, 200);
		battleWin.setForeground(Color.RED);
		battleWin.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
		battleWin.setAlignment(Label.CENTER);
		try {
			battleWin.setText(
					getLine(2,getLanguage()
							+ "-Graph.txt") + godName); //Message YOU WON $
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			nextGod = new Button(
					getLine(3,getLanguage()
							+ "-Graph.txt")); //Message Upgrade Your Hero's Statistics!
		} catch (IOException e) {
			e.printStackTrace();
		}

		nextGod.setBounds((WIDTH - 600) / 2, HEIGHT / 2 - 150 + 250, 600, 75);
		nextGod.setForeground(Color.RED);
		nextGod.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));

		centralPanel.add(battleWin);
		centralPanel.add(nextGod);

		nextGod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centralPanel.remove(battleWin);
				centralPanel.remove(nextGod);
				createStatisticsWindow();
			}
		});

	}

	public void createCheckpointWindow() {
		centralPanel.setSize(WIDTH, HEIGHT);
		buttonPanel.setSize(0, 0);
		removeStartWindow();

		try {
			checkpointMes = new Label(
					getLine(4,getLanguage()
							+ "-Graph.txt")); //Message: You Lost, but you can continue from the checkpoint!
		} catch (IOException e) {
			e.printStackTrace();
		}
		checkpointMes.setBounds((WIDTH - 300) / 2, (HEIGHT - 100) / 2, 300, 100);
		checkpointMes.setForeground(Color.RED);
		checkpointMes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		checkpointMes.setAlignment(Label.CENTER);

		checkpoint = new Button("CHECKPOINT");
		checkpoint.setBounds((WIDTH - 150) / 2, (HEIGHT - 100) / 2 + 200, 150, 100);
		checkpoint.setBackground(Color.WHITE);
		checkpoint.setForeground(Color.BLACK);
		checkpoint.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

		centralPanel.add(checkpointMes);
		centralPanel.add(checkpoint);

		checkpoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centralPanel.remove(checkpointMes);
				centralPanel.remove(checkpoint);
				synchronized (Battle.getLock()) {
					Battle.getLock().notify();
				}
				createStartWindow();
			}
		});
	}

	public void createStatisticsWindow() {// We create the window that the play can see and upgrade his statistics

		try (InputStream file2 = Graph.class.getResourceAsStream("Song3.wav")) {

			AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(
					new BufferedInputStream(Objects.requireNonNull(file2)));
			clip2 = AudioSystem.getClip();
			clip2.open(audioStream2);
			clip2.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int maxAp = Stages.getAttributePoints() / 2;
		FirstGod = false;

		attackBarLabel = new Label("ATTACK: " + Stages.myHero.getAttack());// We create the label for the statistic bar
		// for attack
		attackBarLabel.setBounds(WIDTH / 10, 110, 325, 75);// We define the location and the size of the label
		// attackbarlabel
		attackBarLabel.setForeground(Color.WHITE);// We define the font color of the label attackbarlabel
		attackBarLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));// We define the size of the text of the label
		// attackbarlabel and that the text is going
		// to be bold

		attackBar = new JProgressBar();// We create the progress bar for the statistic bar for attack
		attackBar.setValue((int) (double) Stages.myHero.getAttack() * 100
				/ (Stages.myHero.getAttack() + Stages.myHero.getArmour() + Stages.myHero.getHp()));// We set the first
		// value of the
		// progress bar
		// attackbar4
		attackBar.setStringPainted(true);// The progress bar appear on the screen
		attackBar.setBackground(Color.red);// We define the color of the progress bar
		attackBar.setBounds(WIDTH / 10, HEIGHT * 1 / 5, 325, 75);// We define the location and the size of the progress
		// bar

		apAttackLabel = new Label(String.valueOf(Stages.getApAttack()));
		apAttackLabel.setBounds((WIDTH / 10) + 350, HEIGHT * 1 / 5 + 25 / 2, 75, 50);
		apAttackLabel.setBackground(Color.WHITE);
		apAttackLabel.setForeground(Color.BLACK);
		apAttackLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		attackPlus1 = new Button("+1");
		modigyPlusButtons(attackPlus1, 0, 1, 1);

		attackPlus5 = new Button("+5");
		modigyPlusButtons(attackPlus5, 1, 1, 1);

		attackPlus10 = new Button("+10");
		modigyPlusButtons(attackPlus10, 2, 1, 1);

		attackRemain = new Label(" (MAX: " + Stages.getAttributePoints() / 2 + ")");
		attackRemain.setBounds((WIDTH / 10) + 800, HEIGHT * 1 / 5 + 25 / 2, 180, 50);
		attackRemain.setForeground(Color.RED);
		attackRemain.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		armourBarLabel = new Label("ARMOUR: " + Stages.myHero.getArmour());// We create the label for the statistic bar
		// for armor
		armourBarLabel.setBounds(WIDTH / 10, 270, 325, 75);// We define the location and the size of the label
		// armorbarlabel
		armourBarLabel.setForeground(Color.WHITE);// We define the font color of the label armourBarLabel
		armourBarLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));// We define the size of the text of the label
		// armorbarlabel and that the text is going to
		// be bold
		attackReset = new Button("Reset");
		modigyPlusButtons(attackReset, 3, 1, 2);

		armourBar = new JProgressBar();// We create the progress bar for the statistic bar for armor
		armourBar.setValue((int) (double) Stages.myHero.getArmour() * 100
				/ (Stages.myHero.getAttack() + Stages.myHero.getArmour() + Stages.myHero.getHp()));// We set the first
		// value of the
		// progress bar
		// armorbar
		armourBar.setStringPainted(true);// The progress bar appear on the screen
		armourBar.setBackground(Color.red);// We define the color of the progress bar
		armourBar.setBounds(WIDTH / 10, HEIGHT * 2 / 5, 325, 75);// We define the location and the size of the progress
		// bar

		apArmourLabel = new Label(String.valueOf(Stages.getApArmour()));
		apArmourLabel.setBounds((WIDTH / 10) + 350, HEIGHT * 2 / 5 + 25 / 2, 75, 50);
		apArmourLabel.setBackground(Color.WHITE);
		apArmourLabel.setForeground(Color.BLACK);
		apArmourLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		armourPlus1 = new Button("+1");
		modigyPlusButtons(armourPlus1, 0, 2, 1);

		armourPlus5 = new Button("+5");
		modigyPlusButtons(armourPlus5, 1, 2, 1);

		armourPlus10 = new Button("+10");
		modigyPlusButtons(armourPlus10, 2, 2, 1);

		armourRemain = new Label(" (MAX: " + Stages.getAttributePoints() / 2 + ")");
		armourRemain.setBounds((WIDTH / 10) + 800, HEIGHT * 2 / 5 + 25 / 2, 180, 50);

		armourRemain.setForeground(Color.RED);
		armourRemain.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		armourReset = new Button("Reset");
		modigyPlusButtons(armourReset, 3, 2, 2);

		hpBarLabel = new Label("HEALTH POWER: " + Stages.myHero.getHp());// We create the label for the statistic bar
		// for health power
		hpBarLabel.setBounds(WIDTH / 10, 430, 325, 75);// We define the location and the size of the label hpbarlabel
		hpBarLabel.setForeground(Color.WHITE);// We define the font color of the label hpbarlabel
		hpBarLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));// We define the size of the text of the label
		// hpbarlabel and that the text is going to be
		// bold

		hpBar = new JProgressBar();// We create the progress bar for the statistic bar for health power
		hpBar.setValue((int) (double) Stages.myHero.getHp() * 100
				/ (Stages.myHero.getAttack() + Stages.myHero.getArmour() + Stages.myHero.getHp()));// We set the first
		// value of the
		// progress bar
		// armourBar
		hpBar.setStringPainted(true);// The progress bar appear on the screen
		hpBar.setBackground(Color.red);// We define the color of the progress bar
		hpBar.setBounds(WIDTH / 10, HEIGHT * 3 / 5, 325, 75);// We define the location and the size of the progress bar

		apHpLabel = new Label(String.valueOf(Stages.getApHp()));
		apHpLabel.setBounds((WIDTH / 10) + 350, HEIGHT * 3 / 5 + 25 / 2, 75, 50);
		apHpLabel.setBackground(Color.WHITE);
		apHpLabel.setForeground(Color.BLACK);
		apHpLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		hpPlus1 = new Button("+1");
		modigyPlusButtons(hpPlus1, 0, 3, 1);

		hpPlus5 = new Button("+5");
		modigyPlusButtons(hpPlus5, 1, 3, 1);

		hpPlus10 = new Button("+10");
		modigyPlusButtons(hpPlus10, 2, 3, 1);

		hpRemain = new Label(" (MAX: " + Stages.getAttributePoints() / 2 + ")");
		hpRemain.setBounds((WIDTH / 10) + 800, HEIGHT * 3 / 5 + 25 / 2, 180, 50);
		hpRemain.setForeground(Color.RED);
		hpRemain.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		hpReset = new Button("Reset");
		modigyPlusButtons(hpReset, 3, 3, 2);

		String string = null; //Variable to be used on attributePoints Label
		try {
			string = 
					getLine(5,getLanguage()
							+ "-Graph.txt"); //Message: Remaining Attribute points: 
		} catch (IOException e) {
			e.printStackTrace();
		}
		final String remAp = string;
		
		attributePoints = new Label(remAp + Stages.getAttributePoints());
		attributePoints.setBounds(WIDTH / 10, HEIGHT * 4 / 5, 375, 75);
		attributePoints.setBackground(Color.WHITE);
		attributePoints.setForeground(Color.BLACK);
		attributePoints.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		doneLabel = new Label();
		doneLabel.setBounds((WIDTH / 10) + 750, HEIGHT * 4 / 5, 200, 75);
		doneLabel.setBackground(Color.BLACK);
		doneLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		doneButton = new Button("DONE");
		doneButton.setBounds((WIDTH / 10) + 755, HEIGHT * 4 / 5 + 5, 190, 65);
		doneButton.setBackground(Color.WHITE);
		doneButton.setForeground(Color.BLACK);
		doneButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		centralPanel.add(attackBar);// We add the progress bar attackbar at the panel
		centralPanel.add(armourBar);// We add the progress bar armorbar at the panel
		centralPanel.add(hpBar);// We add the progress bar hpbar at the panel

		centralPanel.add(attackBarLabel);// We add the label attackbarlabel at the panel
		centralPanel.add(armourBarLabel);// We add the label armorbarlabel at the panel
		centralPanel.add(hpBarLabel);// We add the label hpbarlabel at the panel
		centralPanel.add(attributePoints);
		centralPanel.add(doneButton);
		centralPanel.add(doneLabel);

		centralPanel.add(apAttackLabel);
		centralPanel.add(attackPlus1);
		centralPanel.add(attackPlus5);
		centralPanel.add(attackPlus10);
		centralPanel.add(attackRemain);
		centralPanel.add(attackReset);

		centralPanel.add(apArmourLabel);
		centralPanel.add(armourPlus1);
		centralPanel.add(armourPlus5);
		centralPanel.add(armourPlus10);
		centralPanel.add(armourRemain);
		centralPanel.add(armourReset);

		centralPanel.add(apHpLabel);
		centralPanel.add(hpPlus1);
		centralPanel.add(hpPlus5);
		centralPanel.add(hpPlus10);
		centralPanel.add(hpRemain);
		centralPanel.add(hpReset);

		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Stages.getAttributePoints() == 0) {

					Stages.giveAttributesPoints();

					clip2.stop();

					centralPanel.remove(attackBar);// We remove the progress bar attackbar from the panel
					centralPanel.remove(armourBar);// We remove the progress bar armorbar from the panel
					centralPanel.remove(hpBar);// We remove the progress bar hpbar from the panel

					centralPanel.remove(attackBarLabel);// We remove the progress bar attackbarlabel from the panel
					centralPanel.remove(armourBarLabel);// We remove the progress bar armorbarlabel from the panel
					centralPanel.remove(hpBarLabel);// We remove the progress bar hpbarlabel from the panel
					centralPanel.remove(attributePoints);
					centralPanel.remove(doneButton);
					centralPanel.remove(doneLabel);

					centralPanel.remove(apAttackLabel);
					centralPanel.remove(attackPlus1);
					centralPanel.remove(attackPlus5);
					centralPanel.remove(attackPlus10);
					centralPanel.remove(attackRemain);
					centralPanel.remove(attackReset);

					centralPanel.remove(apArmourLabel);
					centralPanel.remove(armourPlus1);
					centralPanel.remove(armourPlus5);
					centralPanel.remove(armourPlus10);
					centralPanel.remove(armourRemain);
					centralPanel.remove(armourReset);

					centralPanel.remove(apHpLabel);
					centralPanel.remove(hpPlus1);
					centralPanel.remove(hpPlus5);
					centralPanel.remove(hpPlus10);
					centralPanel.remove(hpRemain);
					centralPanel.remove(hpReset);

					centralPanel.remove(mainButton);// We remove the progress bar mainbutton from the panel
					Stages.setApStatsToZero();

					createStartWindow();
				} else {
					try {
						JOptionPane.showMessageDialog(null, 
								getLine(6,getLanguage()
										+ "-Graph.txt")); //Message: You haven't distributed all the attribute points!
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		attackPlus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Stages.getApAttack() + 1 <= maxAp) && (Stages.getAttributePoints() - 1 >= 0)) {
					Stages.setApAttack(1);
					apAttackLabel.setText(String.valueOf(Stages.getApAttack()));
					attributePoints.setText(remAp + Stages.getAttributePoints());
				}
			}
		});
		attackPlus5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Stages.getApAttack() + 5 <= maxAp) && (Stages.getAttributePoints() - 5 >= 0)) {
					Stages.setApAttack(5);
					apAttackLabel.setText(String.valueOf(Stages.getApAttack()));
					attributePoints.setText(remAp + Stages.getAttributePoints());
				}
			}
		});
		attackPlus10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Stages.getApAttack() + 10 <= maxAp) && (Stages.getAttributePoints() - 10 >= 0)) {
					Stages.setApAttack(10);
					apAttackLabel.setText(String.valueOf(Stages.getApAttack()));
					attributePoints.setText(remAp + Stages.getAttributePoints());
				}
			}
		});
		attackReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stages.setAttackZero();
				apAttackLabel.setText(String.valueOf(Stages.getApAttack()));
				attributePoints.setText(remAp + Stages.getAttributePoints());
			}
		});
		armourPlus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Stages.getApArmour() + 1 <= maxAp) && (Stages.getAttributePoints() - 1 >= 0)) {
					Stages.setApArmour(1);
					apArmourLabel.setText(String.valueOf(Stages.getApArmour()));
					attributePoints.setText(remAp + Stages.getAttributePoints());
				}
			}
		});
		armourPlus5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Stages.getApArmour() + 5 <= maxAp && Stages.getAttributePoints() - 5 >= 0) {
					Stages.setApArmour(5);
					apArmourLabel.setText(String.valueOf(Stages.getApArmour()));
					attributePoints.setText(remAp + Stages.getAttributePoints());
				}
			}
		});
		armourPlus10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Stages.getApArmour() + 10 <= maxAp) && (Stages.getAttributePoints() - 10 >= 0)) {
					Stages.setApArmour(10);
					apArmourLabel.setText(String.valueOf(Stages.getApArmour()));
					attributePoints.setText(remAp + Stages.getAttributePoints());
				}
			}
		});
		armourReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stages.setArmorZero();
				apArmourLabel.setText(String.valueOf(Stages.getApArmour()));
				attributePoints.setText(remAp + Stages.getAttributePoints());
			}
		});
		hpPlus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Stages.getApHp() + 1 <= maxAp) && (Stages.getAttributePoints() - 1 >= 0)) {
					Stages.setApHp(1);
					apHpLabel.setText(String.valueOf(Stages.getApHp()));
					attributePoints.setText(remAp + Stages.getAttributePoints());
				}
			}
		});
		hpPlus5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Stages.getApHp() + 5 <= maxAp) && (Stages.getAttributePoints() - 5 >= 0)) {
					Stages.setApHp(5);
					apHpLabel.setText(String.valueOf(Stages.getApHp()));
					attributePoints.setText(remAp + Stages.getAttributePoints());
				}
			}
		});
		hpPlus10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Stages.getApHp() + 10 <= maxAp) && (Stages.getAttributePoints() - 10 >= 0)) {
					Stages.setApHp(10);
					apHpLabel.setText(String.valueOf(Stages.getApHp()));
					attributePoints.setText(remAp + Stages.getAttributePoints());
				}
			}
		});
		hpReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stages.setHpZero();
				apHpLabel.setText(String.valueOf(Stages.getApHp()));
				attributePoints.setText(remAp + Stages.getAttributePoints());
			}
		});
	}

	private void modigyPlusButtons(Button plus, int numOfPlus, int typeOfAttribute, int reset) {
		plus.setBounds((WIDTH / 10) + 450 + numOfPlus * 75, HEIGHT * typeOfAttribute / 5 + 25 / 2, reset * 50, 50);
		plus.setBackground(Color.WHITE);
		plus.setForeground(Color.BLACK);
		plus.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
	}
	
	public void createRulesWindow() {
		int i = 1;
		String rulesDoc = "";
		while (i <= 16) {
			try {
				rulesDoc = rulesDoc + "\n" + getLine(i, getLanguage() + "-Rules.txt");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			i = i + 1;
		}

		rules = new TextArea(rulesDoc, 22, 40);
		rules.setBounds(0, 100, WIDTH - 10, HEIGHT - 135);
		rules.setForeground(Color.BLACK);
		rules.setBackground(Color.WHITE);
		rules.setFont(new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 12));

		centralPanel.add(mainButton);
		centralPanel.add(rules);

		mainButton.addActionListener(new ActionListener() {// If the user clicks the button mainbutton the game returns
			// to the menu window
			public void actionPerformed(ActionEvent e) {
				centralPanel.remove(rules);
				createMenuWindow();
			}
		});
	}

	public void createSettingsWindow() {
		lightMode = new Button("Light Mode");
		lightMode.setBounds(WIDTH / 10, HEIGHT / 5, 200, 75);
		lightMode.setBackground(Color.WHITE);
		lightMode.setForeground(Color.BLACK);
		lightMode.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		darkMode = new Button("Dark Mode");
		darkMode.setBounds(WIDTH / 10 + 250, HEIGHT / 5, 200, 75);
		darkMode.setBackground(Color.WHITE);
		darkMode.setForeground(Color.BLACK);
		darkMode.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		greekButton = new Button("Greek");
		greekButton.setBounds(WIDTH / 10, HEIGHT * 2 / 5, 200, 75);
		greekButton.setBackground(Color.WHITE);
		greekButton.setForeground(Color.BLACK);
		greekButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		englishButton = new Button("English");
		englishButton.setBounds(WIDTH / 10 + 250, HEIGHT * 2 / 5, 200, 75);
		englishButton.setBackground(Color.WHITE);
		englishButton.setForeground(Color.BLACK);
		englishButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		centralPanel.add(darkMode);
		centralPanel.add(lightMode);
		centralPanel.add(greekButton);
		centralPanel.add(englishButton);
		

		lightMode.addActionListener(new ActionListener() {// The user has press the button quitbutton
			public void actionPerformed(ActionEvent e) {
				centralPanel.setBackground(Color.GRAY);
			}
		});
		darkMode.addActionListener(new ActionListener() {// The user has press the button quitbutton
			public void actionPerformed(ActionEvent e) {
				centralPanel.setBackground(Color.black);
			}
		});

		greekButton.addActionListener(new ActionListener() {// The user has set the language to Greek.
			public void actionPerformed(ActionEvent e) { Graph.language = "Gr"; }
		});
		englishButton.addActionListener(new ActionListener() {// The user has set the language to English
			public void actionPerformed(ActionEvent e) { Graph.language = "En"; }
		});


		mainButton.addActionListener(new ActionListener() {// If the user clicks the button mainbutton the game returns
			// to the menu window
			public void actionPerformed(ActionEvent e) {
				centralPanel.remove(mainButton);// We remove the progress bar mainbutton from the panel
				centralPanel.remove(darkMode);
				centralPanel.remove(lightMode);
				centralPanel.remove(greekButton);
				centralPanel.remove(englishButton);
				createMenuWindow();
			}
		});
	}

}
