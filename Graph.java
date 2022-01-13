import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.*;

/**
 * The class Graph creates a graphic display of the game and the all the
 * functions of the other classes. This class is being used by Stages and Battle
 */
public class Graph {// Creating the class Graph
	/** A lock for the thread graphThread */
	private static final Object graphLock = new Object();

	/** @return the value of the object graphLock */
	public static Object getGraphLock() {
		return graphLock;
	}

	/** The constant that defines the title of the frame. */
	private final String title;
	JFrame frame;
	/**
	 * We define the basic panels of the game the centralPanel contains all most
	 * everything of the game and the buttonPanel contains the buttons that are the
	 * moves of the hero
	 */
	JPanel centralPanel, buttonPanel;
	/** We define the block where the user can write his/her username */
	JTextField username;
	/** We define the introductory label that welcomes the user */
	Label introLabel;
	/**
	 * We define the buttons that are contained on the introductory and the menu
	 * window
	 */
	Button introButton, mainButton, startButton, rulesButton, settingsButton, creditButton, quitButton, continueButton;
	/**
	 * We define the labels that are referred to the hero's and the god's health
	 * power's bar and name
	 */
	static Label heroName, godName, heroHpBar, godHBar, heroBackBar, godBackBar, heroEnergy;
	/** We define the buttons that are the moves of the hero */
	JButton swordButton, spearButton, meditateButton, shieldButton, noMoveButton;
	/**
	 * We define the buttons that are beings used on the Checkpoint, the Lose and
	 * the Battle Win window
	 */
	Button nextGod, checkpoint, gameOver, exitGame;
	/**
	 * We define the labels that are referred to the hero's and the god's health
	 * power
	 */
	static Label heroHp, godHp;
	/**
	 * We define the labels that we insert the images of the hero, of the gods and
	 * of the background
	 */
	JLabel godImage, heroImage, background, logo;
	/** We define the label that contains the messages during the battle */
	static Label mes1;
	/** We define the text areas that contains the story and the rules */
	TextArea rules, story, creditText;
	/**
	 * We define the label that contains the win, the win of the battle, the lose
	 * and the checkpoint message
	 */
	Label battleWin, winMes, loseMes, checkpointMes;
	/** We define the buttons that set the attribute points of the hero */
	Button attackPlus1, attackPlus5, attackPlus10, attackReset, armourPlus1, armourPlus5, armourPlus10, armourReset,
			hpPlus1, hpPlus5, hpPlus10, hpReset, doneButton;
	/** We define the labels of the statistics window */
	Label attackBarLabel, armourBarLabel, hpBarLabel, attackRemain, armourRemain, hpRemain, apAttackLabel,
			apArmourLabel, apHpLabel, attributePoints, doneLabel;
	/**
	 * We define the progress bars that shows how the attributes of the hero are
	 * distributed
	 */
	JProgressBar attackBar, armourBar, hpBar;
	/**
	 * We define the buttons of the settings window that sets the language and the
	 * mode of the game
	 */
	Button lightMode, darkMode, greekButton, englishButton;
	/** We define the dimension of the frame */
	final int WIDTH = 1280, HEIGHT = 800;
	/** We define the variable for the temporary name of the hero */
	private String tempHeroName;
	/**
	 * We define the boolean variable that is being used on the method
	 * createStartWindow
	 */
	private boolean firstGod = true;
	/** We define the move that hero has chosen */
	private static int chosenMove;
	/**
	 * We define the string variable that indicates the language that the user has
	 * chosen and the default value En for english
	 */
	private static String language = "En";

	/** @return the temporary name of the Hero */
	public String getTempHeroName() {
		return tempHeroName;
	}

	/** @return the move that the user has chosen */
	public static int getChosenMove() {
		return chosenMove;
	}

	/**
	 * @param move with the move the player chose
	 */
	public static void setChosenMove(int move) {
		chosenMove = move;
	}

	/** @return the language of the game */
	public static String getLanguage() {
		return language;
	}

	/**
	 * @param numberOfLine indicates the number of the line of the file that we want
	 *                     to read
	 * @param fileName     shows the name of the file which we are reading
	 * @return Returns the contents of the line of a file that is located the number
	 *         specified
	 * @throws IOException because file may not exist
	 */
	public static String getLine(final int numberOfLine, final String fileName) throws IOException {

		if (numberOfLine <= 0)
			return "Non positive number of line?";
		try (BufferedReader bf = new BufferedReader(new InputStreamReader(
				Objects.requireNonNull(Graph.class.getResourceAsStream(fileName)), StandardCharsets.UTF_8))) {
			for (int i = 1; i <= numberOfLine; i++) {
				if (i == numberOfLine) {
					return bf.readLine();
				} else {
					bf.readLine();
				}
			}
		}
		return null;
	}

	private final Runnable battleTasks = Stages::stageControl;

	/**
	 * @param numOfLines indicates the number of the line of the file that we want
	 *                     to read
	 * @param fileName     shows the name of the file which we are reading
	 * @return Returns a String containing all lines of a file
	 */
	public static String getAllText(final int numOfLines, final String fileName) {
		if (numOfLines <= 0)
			return "Non positive number of lines?";
		int i = 1;
		StringBuilder sb = new StringBuilder();
		while (i <= numOfLines) {
			try {
				sb.append("\n").append(getLine(i, fileName));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			i++;
		}
		return String.valueOf(sb);
	}

	/**
	 * @param title that is the title of the frame This is the constructor of the
	 *              class Graph
	 */
	public Graph(String title) {

		Runnable graphTasks = this::createFrame;
		Thread graphThread = new Thread(graphTasks);
		graphThread.start();

		this.title = title;
	}

	/**
	 * The method createFrame creates the frame of the game
	 */
	public void createFrame() {
		frame = new JFrame(title);
		frame.setBounds(0, 0, WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());

		createMainWindow();
	}

	/**
	 * The method createMainWindow creates the first window that the user will see
	 * when he opens the game and designs the area where the user inserts his
	 * username
	 */
	public void createMainWindow() {
		centralPanel = new JPanel();
		centralPanel.setBackground(new Color(0, 51, 51));
		centralPanel.setLayout(null);
		centralPanel.setLocation(0, 0);
		centralPanel.setSize(WIDTH, HEIGHT);

		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 0, 0, 0);
		frame.add(centralPanel);
		frame.add(buttonPanel);

		introButton = new Button("LET'S BEGIN OUR ADVENTURE");

		username = new JTextField("Enter Your Name Here");
		username.setBounds((WIDTH - 350) / 2, HEIGHT * 3 / 5, 350, 50);
		username.setBackground(new Color(0, 74, 74));
		username.setForeground(new Color(255, 204, 51));
		username.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));

		introButton.setLocation((WIDTH - 450) / 2, HEIGHT * 7 / 10);
		introButton.setSize(450, 100);
		introButton.setBackground(new Color(223, 255, 255));
		introButton.setForeground(new Color(14, 114, 110));
		introButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		introLabel = new Label("WELCOME TO OUR GAME");
		introLabel.setLocation((WIDTH - 600) / 2, HEIGHT * 2 / 10);
		introLabel.setSize(600, 200);
		introLabel.setForeground(new Color(255, 204, 51));
		introLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		introLabel.setAlignment(Label.CENTER);

		centralPanel.add(introLabel);
		centralPanel.add(introButton);
		centralPanel.add(username);

		introButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempHeroName = username.getText();
				centralPanel.remove(introButton);
				centralPanel.remove(introLabel);
				centralPanel.remove(username);
				createMenuWindow();
			}
		});
	}

	/**
	 * The method createMenuWindow creates the window that contains the menu
	 */
	public void createMenuWindow() {
		logo = new JLabel();
		logo.setBounds(WIDTH * 13 / 20, 150, 300, 500);

		try {
			InputStream resourceBf = Graph.class.getResourceAsStream("logo.png");
			BufferedImage bf = ImageIO.read(Objects.requireNonNull(resourceBf));
			ImageIcon im = new ImageIcon(bf);
			logo.setIcon(im);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			startButton = new Button(getLine(7, getLanguage() + "-Graph.txt"));
			modifyMenuButtons(startButton, 0);

			rulesButton = new Button(getLine(8, getLanguage() + "-Graph.txt"));
			modifyMenuButtons(rulesButton, 1);

			settingsButton = new Button(getLine(9, getLanguage() + "-Graph.txt"));
			modifyMenuButtons(settingsButton, 2);

			creditButton = new Button("CREDITS");
			modifyMenuButtons(creditButton, 3);

			quitButton = new Button(getLine(10, getLanguage() + "-Graph.txt"));
			modifyMenuButtons(quitButton, 4);
		} catch (IOException e) {
			e.printStackTrace();
		}

		mainButton = new Button("CODERUNNERS");
		mainButton.setLocation((WIDTH - 350) / 2, 0);
		mainButton.setSize(350, 50);
		mainButton.setBackground(Color.BLACK);
		mainButton.setForeground(new Color(255, 204, 51));
		mainButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

		centralPanel.add(startButton);
		centralPanel.add(rulesButton);
		centralPanel.add(settingsButton);
		centralPanel.add(creditButton);
		centralPanel.add(quitButton);
		centralPanel.add(logo);

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeMenuButtons();
				centralPanel.remove(mainButton);
				createStoryWindow();
			}
		});
		rulesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeMenuButtons();

				createRulesWindow();
			}
		});
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeMenuButtons();

				createSettingsWindow();
			}
		});
		creditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeMenuButtons();

				createCreditsWindow();
			}
		});
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * @param menuButton  is the button we give from the menu
	 * @param numOfButton is the number the button in the row
	 */
	private void modifyMenuButtons(Button menuButton, int numOfButton) {
		menuButton.setLocation(WIDTH / 10, HEIGHT * (3 + 4 * numOfButton) / 25);
		menuButton.setSize(350, 100);
		menuButton.setBackground(new Color(0, 153, 153));
		menuButton.setForeground(Color.WHITE);
		menuButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
	}

	/**
	 * The method removeMenuButtons removes all the elements of the Menu Window
	 */
	private void removeMenuButtons() {
		centralPanel.remove(startButton);
		centralPanel.remove(rulesButton);
		centralPanel.remove(settingsButton);
		centralPanel.remove(creditButton);
		centralPanel.remove(quitButton);
		centralPanel.remove(logo);

	}

	/**
	 * The method createStoryWindow displays the story of the game
	 */
	public void createStoryWindow() {
		String storyText = getAllText(2, getLanguage() + "-Story.txt");

		story = new TextArea(storyText, 20, 10, TextArea.SCROLLBARS_NONE);
		story.setBounds(0, 100, WIDTH - 10, 550);
		story.setForeground(Color.WHITE);
		story.setBackground(new Color(0, 51, 51));
		story.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
		story.setEditable(false);

		try {
			continueButton = new Button(getLine(13, getLanguage() + "-Graph.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		continueButton.setBounds((WIDTH - 150) / 2, 675, 150, 50);
		continueButton.setForeground(Color.BLACK);
		continueButton.setBackground(new Color(255, 204, 51));
		continueButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));

		centralPanel.add(story);
		centralPanel.add(continueButton);

		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centralPanel.remove(story);
				centralPanel.remove(continueButton);

				createStartWindow();
			}
		});
	}

	/**
	 * The method createStartWindow creates the battle between the hero and the god
	 */
	public void createStartWindow() {

		Thread battleThread = new Thread(battleTasks);
		if (firstGod) {
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
			synchronized (graphLock) {
				try {
					System.out.println("WAITING");
					graphLock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		heroName = new Label(Stages.myHero.getName());
		heroName.setBounds(50, HEIGHT / 10, 200, 50);
		heroName.setAlignment(Label.CENTER);
		heroName.setForeground(new Color(223, 255, 255));
		heroName.setBackground(new Color(0, 0, 102));
		heroName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));

		godName = new Label();
		godName.setBounds(WIDTH - 250, HEIGHT / 10, 200, 50);
		godName.setForeground(new Color(223, 255, 255));
		godName.setBackground(new Color(0, 0, 102));
		godName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
		godName.setAlignment(Label.CENTER);

		heroBackBar = new Label();
		heroBackBar.setBounds(50, HEIGHT / 10 + 50, 200, 30);
		heroBackBar.setBackground(Color.WHITE);

		heroHpBar = new Label();
		heroHpBar.setBounds(50, HEIGHT / 10 + 50, 200, 30);
		heroHpBar.setForeground(Color.BLACK);
		heroHpBar.setBackground(new Color(102, 204, 0));
		heroHpBar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
		heroHpBar.setAlignment(Label.CENTER);

		heroHp = new Label("HP: " + Stages.myHero.getHp());
		heroHp.setBounds(50, HEIGHT / 10 + 50 + 5 + 26, 200, 20);
		heroHp.setForeground(new Color(223, 255, 255));
		heroHp.setBackground(new Color(22, 49, 87));
		heroHp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		heroHp.setAlignment(Label.LEFT);

		heroEnergy = new Label("Energy: " + Stages.myHero.getEnergy());
		heroEnergy.setBounds(50, HEIGHT / 10 + 50 + 5 + 48, 200, 20);
		heroEnergy.setForeground(new Color(223, 255, 255));
		heroEnergy.setBackground(new Color(22, 49, 87));
		heroEnergy.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		heroEnergy.setAlignment(Label.LEFT);

		godBackBar = new Label();
		godBackBar.setBounds(WIDTH - 250, HEIGHT / 10 + 50, 200, 30);
		godBackBar.setBackground(Color.WHITE);

		godHBar = new Label();
		godHBar.setBounds(WIDTH - 250, HEIGHT / 10 + 50, 200, 30);
		godHBar.setForeground(Color.BLACK);
		godHBar.setBackground(new Color(102, 204, 0));
		godHBar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
		godHBar.setAlignment(Label.CENTER);

		godHp = new Label();
		godHp.setBounds(WIDTH - 250, HEIGHT / 10 + 50 + 31, 200, 20);
		godHp.setForeground(new Color(223, 255, 255));
		godHp.setBackground(new Color(22, 49, 87));
		godHp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		godHp.setAlignment(Label.LEFT);
		godHp.setText("HP: " + 100 + "%");

		mes1 = new Label();
		mes1.setBounds(0, HEIGHT * 9 / 10 - 280 + 5 * 40, WIDTH, 40);
		mes1.setBackground(new Color(205, 217, 237));
		mes1.setForeground(Color.BLACK);
		mes1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

		godImage = new JLabel();
		godImage.setBounds(WIDTH / 2 + 300, HEIGHT * 9 / 10 - 450, 240, 403);

		try {
			InputStream resourceBf = Graph.class.getResourceAsStream(Battle.god.getName() + ".jpg");
			System.out.println(Battle.god.getName() + ".jpg");
			BufferedImage bf = ImageIO.read(Objects.requireNonNull(resourceBf));
			ImageIcon im = new ImageIcon(bf);
			godImage.setIcon(im);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		heroImage = new JLabel();
		heroImage.setBounds(WIDTH / 2 - 550, HEIGHT * 9 / 10 - 423, 212, 343);
		try {
			InputStream resourceBf = Graph.class.getResourceAsStream("Hero.jpg");
			BufferedImage bf = ImageIO.read(Objects.requireNonNull(resourceBf));
			ImageIcon im = new ImageIcon(bf);
			heroImage.setIcon(im);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		buttonPanel.setBackground(new Color(0, 51, 51));
		// based on rgb color
		buttonPanel.setLayout(null);
		buttonPanel.setLocation(0, HEIGHT - 130);
		centralPanel.setSize(WIDTH, HEIGHT - 130);
		buttonPanel.setSize(WIDTH, 130);
		background = new JLabel();
		background.setBounds(0, 0, WIDTH, HEIGHT - 130);
		background.setBackground(Color.BLACK);

		if (Battle.god.getName().equals("Zeus")) {
			godImage.setLocation(WIDTH / 2 + 300, HEIGHT * 9 / 10 - 480);
		}

		try {
			InputStream resourceBf3 = Graph.class.getResourceAsStream("background.jpg");
			BufferedImage bf = ImageIO.read(Objects.requireNonNull(resourceBf3));
			ImageIcon im = new ImageIcon(bf);
			background.setIcon(im);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		swordButton = new JButton("1. " + Stages.myHero.getDamagingMove1().getName());
		modifyMoveButtons(swordButton, 1, Stages.myHero.getDamagingMove1().getDamage(),
				Stages.myHero.getDamagingMove1().getEnergy());

		spearButton = new JButton("2. "+ Stages.myHero.getDamagingMove2().getName());
		modifyMoveButtons(spearButton, 2, Stages.myHero.getDamagingMove2().getDamage(),
				Stages.myHero.getDamagingMove2().getEnergy());

		shieldButton = new JButton("3. " + Stages.myHero.getProtectiveMove().getName());
		modifyMoveButtons(shieldButton, 3, 0, Stages.myHero.getProtectiveMove().getEnergy());

		meditateButton = new JButton("4. " + Stages.myHero.getBuffMove().getName());
		modifyMoveButtons(meditateButton, 4, 0, Stages.myHero.getBuffMove().getEnergy());

		noMoveButton = new JButton("5. " + Stages.myHero.getNoMove().getName());
		modifyMoveButtons(noMoveButton, 5, 0, Stages.myHero.getNoMove().getEnergy());

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

	/**
	 * @param move      is the JButton for each move
	 * @param numOfMove is the number of each move
	 * @param damage    is the damage that of each move
	 * @param energy    is the energy that of each move
	 */
	private void modifyMoveButtons(JButton move, int numOfMove, int damage, int energy) {
		move.setBounds(40 * numOfMove + 200 * (numOfMove - 1), 20, 200, 50);
		move.setBackground(new Color(205, 217, 237));
		move.setForeground(new Color(0, 0, 0));
		move.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		move.setToolTipText("Damage:" + damage + " & Energy:" + energy);
	}

	/**
	 * The method addStartWindow add all the elements on the Start the Game window
	 */
	private void addStartWindow() {
		centralPanel.add(background);
		background.add(heroName);
		background.add(godName);
		background.add(heroHpBar);
		background.add(heroBackBar);
		background.add(godHBar);
		background.add(godBackBar);
		background.add(heroHp);
		background.add(heroEnergy);
		background.add(godHp);
		background.add(mes1);
		background.add(godImage);
		background.add(heroImage);

		buttonPanel.add(swordButton);
		buttonPanel.add(spearButton);
		buttonPanel.add(meditateButton);
		buttonPanel.add(shieldButton);
		buttonPanel.add(noMoveButton);
	}

	/**
	 * The method removeStartWindow remove all the elements on the Start the Game
	 * window
	 */
	private void removeStartWindow() {
		centralPanel.remove(background);
		background.remove(heroName);
		background.remove(godName);
		background.remove(heroHpBar);
		background.remove(heroBackBar);
		background.remove(godHBar);
		background.remove(godBackBar);
		background.remove(heroHp);
		background.remove(heroEnergy);
		background.remove(godHp);
		background.remove(mes1);
		background.remove(godImage);
		background.remove(heroImage);

		buttonPanel.remove(swordButton);
		buttonPanel.remove(spearButton);
		buttonPanel.remove(meditateButton);
		buttonPanel.remove(shieldButton);
		buttonPanel.remove(noMoveButton);
	}

	/**
	 * @param hero      the object of the hero either is a hero object or a god
	 *                  object
	 * @param currentHp is the value of the health power of the object hero after
	 *                  his opponent has made his move
	 * @param initialHp is the value of the health power of the object hero with
	 *                  which the character starts the battle
	 */
	public static void modifyHpLabels(Character hero, int currentHp, int initialHp) {
		if (hero.getName().equals(Stages.myHero.getName())) {
			heroHpBar.setSize(200 * currentHp / initialHp, 30);
			heroHp.setText("HP: " + currentHp);
		} else {
			godHBar.setSize(200 * currentHp / initialHp, 30);
			godHp.setText("HP: " + currentHp * 100 / initialHp + "%");
		}
	}

	/**
	 * @param energy is the energy of the character
	 */
	public static void modifyEnergyLabel(int energy) {
		heroEnergy.setText("Energy: " + energy);
	}

	/**
	 * @param text is the content of the label mes1
	 */
	public void modifyMes(String text) {
		mes1.setText(text);
	}

	/**
	 * The method clearMes delete the content of the label mes1
	 */
	public void clearMes() {
		mes1.setText("");
	}

	/**
	 * The method createLoseWindow creates the window which appears when the player
	 * loses and has not checkpoint
	 */
	public void createLoseWindow() {
		centralPanel.setSize(WIDTH, HEIGHT);
		buttonPanel.setSize(0, 0);
		removeStartWindow();

		com.sun.javafx.application.PlatformImpl.startup(() -> {
		});
		URL file4 = Graph.class.getResource("GameOver.mp3");
		Media hit4 = new Media(Objects.requireNonNull(file4).toString());
		MediaPlayer mediaPlayer4 = new MediaPlayer(hit4);
		mediaPlayer4.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer4.play();

		try {
			loseMes = new Label();
			loseMes.setBounds(0, HEIGHT / 2 - 50, WIDTH, 100);
			loseMes.setForeground(Color.RED);
			loseMes.setText(getLine(11, getLanguage() + "-Graph.txt"));
			loseMes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));
			loseMes.setAlignment(Label.CENTER);

			gameOver = new Button(getLine(12, getLanguage() + "-Graph.txt"));
			gameOver.setBounds(WIDTH / 2 - 300, HEIGHT / 2 + 150, 600, 70);
			gameOver.setBackground(new Color(0, 204,204));
			gameOver.setForeground(Color.white);
			gameOver.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
		} catch (IOException e) {
			e.printStackTrace();
		}

		centralPanel.add(loseMes);
		centralPanel.add(gameOver);

		gameOver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstGod = true;
				centralPanel.remove(loseMes);
				centralPanel.remove(gameOver);
				createMenuWindow();
				mediaPlayer4.stop();
			}
		});

	}

	/**
	 * The method createWinWindow creates the window which appears when the player
	 * wins the game
	 */
	public void createWinWindow() {
		centralPanel.setSize(WIDTH, HEIGHT);
		buttonPanel.setSize(0, 0);
		removeStartWindow();

		winMes = new Label();
		winMes.setBounds(0, HEIGHT / 2 - 150, WIDTH, 100);
		winMes.setForeground((new Color(255, 204, 51)).darker());
		try {
			winMes.setText(getLine(1, getLanguage() + "-Graph.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		winMes.setFont(new Font(Font.SERIF, Font.BOLD, 45));
		winMes.setAlignment(Label.CENTER);

		exitGame = new Button("Exit The Game");
		exitGame.setBounds((WIDTH-400)/2, HEIGHT/2 + 100, 400, 100);
		exitGame.setBackground(new Color(0, 204,204));
		exitGame.setForeground(Color.white);
		exitGame.setFont(new Font(Font.SERIF, Font.BOLD, 35));

		centralPanel.add(winMes);
		centralPanel.add(exitGame);

		exitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centralPanel.remove(exitGame);
				centralPanel.remove(winMes);
				System.exit(0);
			}
		});

	}

	/**
	 * The method createBattleWinWindow creates the window which appears when the
	 * player wins the current battle he fights
	 */
	public void createBattleWinWindow(String godName) {
		centralPanel.setSize(WIDTH, HEIGHT);
		buttonPanel.setSize(0, 0);
		removeStartWindow();

		battleWin = new Label();
		battleWin.setBounds(0, HEIGHT / 2 - 150, WIDTH, 200);
		battleWin.setForeground(new Color(102,0,204));
		battleWin.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));
		battleWin.setAlignment(Label.CENTER);
		try {
			battleWin.setText(getLine(2, getLanguage() + "-Graph.txt") + godName); // Message YOU WON $
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			nextGod = new Button(getLine(3, getLanguage() + "-Graph.txt")); // Message Upgrade Your Hero's Statistics!
		} catch (IOException e) {
			e.printStackTrace();
		}

		nextGod.setBounds((WIDTH - 750) / 2, HEIGHT / 2 - 150 + 250, 750, 75);
		nextGod.setBackground(new Color(0, 204,204));
		nextGod.setForeground(Color.white);
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

	/**
	 * The method createCheckpointWindow creates the window which appears when the
	 * player loses, but has checkpoint
	 */
	public void createCheckpointWindow() {
		centralPanel.setSize(WIDTH, HEIGHT);
		buttonPanel.setSize(0, 0);
		removeStartWindow();

		try {
			checkpointMes = new Label(getLine(4, getLanguage() + "-Graph.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		checkpointMes.setBounds(0, (HEIGHT - 100) / 2, WIDTH, 100);
		checkpointMes.setForeground(new Color(102,0,204));
		checkpointMes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));
		checkpointMes.setAlignment(Label.CENTER);

		checkpoint = new Button("CHECKPOINT");
		checkpoint.setBounds((WIDTH - 150) / 2, (HEIGHT - 100) / 2 + 200, 150, 50);
		checkpoint.setBackground(new Color(255, 204, 51));
		checkpoint.setForeground(Color.black);
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

	/**
	 * The method createStatisticsWindow creates the window which appears when the
	 * player loses and has not checkpoint
	 */
	public void createStatisticsWindow() {
		com.sun.javafx.application.PlatformImpl.startup(() -> {
		});
		URL file3 = Graph.class.getResource("Song3.mp3");
		Media hit3 = new Media(Objects.requireNonNull(file3).toString());
		MediaPlayer mediaPlayer3 = new MediaPlayer(hit3);
		mediaPlayer3.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer3.play();

		final int maxAp = ((Stages.getAttributePoints() == 1) ? 1 : Stages.getAttributePoints() / 2);
		firstGod = false;

		attackBarLabel = new Label("ATTACK: " + Stages.myHero.getAttack());
		attackBarLabel.setBounds(WIDTH / 10, 110, 325, 75);
		attackBarLabel.setForeground(new Color(0, 153, 153));
		attackBarLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		attackBar = new JProgressBar();
		attackBar.setValue((int) (double) Stages.myHero.getAttack() * 100
				/ (Stages.myHero.getAttack() + Stages.myHero.getArmour() + Stages.myHero.getHp()));
		attackBar.setStringPainted(true);
		attackBar.setBackground(new Color(102, 0, 102));
		attackBar.setBounds(WIDTH / 10, HEIGHT / 5, 325, 75);

		apAttackLabel = new Label(String.valueOf(Stages.getApAttack()));
		apAttackLabel.setBounds((WIDTH / 10) + 350, HEIGHT / 5 + 25 / 2, 75, 50);
		apAttackLabel.setBackground(Color.WHITE);
		apAttackLabel.setForeground(Color.BLACK);
		apAttackLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		attackPlus1 = new Button("+1");
		modifyPlusButtons(attackPlus1, 0, 1, 1);

		attackPlus5 = new Button("+5");
		modifyPlusButtons(attackPlus5, 1, 1, 1);

		attackPlus10 = new Button("+10");
		modifyPlusButtons(attackPlus10, 2, 1, 1);

		attackRemain = new Label(" (MAX: " + Stages.getAttributePoints() / 2 + ")");
		attackRemain.setBounds((WIDTH / 10) + 800, HEIGHT / 5 + 25 / 2, 180, 50);
		attackRemain.setForeground(new Color(0, 153, 153));
		attackRemain.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		armourBarLabel = new Label("ARMOUR: " + Stages.myHero.getArmour());
		armourBarLabel.setBounds(WIDTH / 10, 270, 325, 75);
		armourBarLabel.setForeground(new Color(0, 153, 153));
		armourBarLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		attackReset = new Button("Reset");
		modifyPlusButtons(attackReset, 3, 1, 2);

		armourBar = new JProgressBar();
		armourBar.setValue((int) (double) Stages.myHero.getArmour() * 100
				/ (Stages.myHero.getAttack() + Stages.myHero.getArmour() + Stages.myHero.getHp()));
		armourBar.setStringPainted(true);
		armourBar.setBackground(new Color(102, 0, 102));
		armourBar.setBounds(WIDTH / 10, HEIGHT * 2 / 5, 325, 75);

		apArmourLabel = new Label(String.valueOf(Stages.getApArmour()));
		apArmourLabel.setBounds((WIDTH / 10) + 350, HEIGHT * 2 / 5 + 25 / 2, 75, 50);
		apArmourLabel.setBackground(Color.WHITE);
		apArmourLabel.setForeground(Color.BLACK);
		apArmourLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		armourPlus1 = new Button("+1");
		modifyPlusButtons(armourPlus1, 0, 2, 1);

		armourPlus5 = new Button("+5");
		modifyPlusButtons(armourPlus5, 1, 2, 1);

		armourPlus10 = new Button("+10");
		modifyPlusButtons(armourPlus10, 2, 2, 1);

		armourRemain = new Label(" (MAX: " + Stages.getAttributePoints() / 2 + ")");
		armourRemain.setBounds((WIDTH / 10) + 800, HEIGHT * 2 / 5 + 25 / 2, 180, 50);

		armourRemain.setForeground(new Color(0, 153, 153));
		armourRemain.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		armourReset = new Button("Reset");
		modifyPlusButtons(armourReset, 3, 2, 2);

		hpBarLabel = new Label("HEALTH POWER: " + Stages.myHero.getHp());
		hpBarLabel.setBounds(WIDTH / 10, 430, 325, 75);
		hpBarLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		hpBarLabel.setForeground(new Color(0, 153, 153));

		hpBar = new JProgressBar();
		hpBar.setValue((int) (double) Stages.myHero.getHp() * 100
				/ (Stages.myHero.getAttack() + Stages.myHero.getArmour() + Stages.myHero.getHp()));
		hpBar.setStringPainted(true);
		hpBar.setBackground(new Color(102, 0, 102));
		hpBar.setBounds(WIDTH / 10, HEIGHT * 3 / 5, 325, 75);

		apHpLabel = new Label(String.valueOf(Stages.getApHp()));
		apHpLabel.setBounds((WIDTH / 10) + 350, HEIGHT * 3 / 5 + 25 / 2, 75, 50);
		apHpLabel.setBackground(Color.WHITE);
		apHpLabel.setForeground(Color.BLACK);
		apHpLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		hpPlus1 = new Button("+1");
		modifyPlusButtons(hpPlus1, 0, 3, 1);

		hpPlus5 = new Button("+5");
		modifyPlusButtons(hpPlus5, 1, 3, 1);

		hpPlus10 = new Button("+10");
		modifyPlusButtons(hpPlus10, 2, 3, 1);

		hpRemain = new Label(" (MAX: " + Stages.getAttributePoints() / 2 + ")");
		hpRemain.setBounds((WIDTH / 10) + 800, HEIGHT * 3 / 5 + 25 / 2, 180, 50);
		hpRemain.setForeground(new Color(0, 153, 153));
		hpRemain.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		hpReset = new Button("Reset");
		modifyPlusButtons(hpReset, 3, 3, 2);

		String string = null;
		try {
			string = getLine(5, getLanguage() + "-Graph.txt");
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

		centralPanel.add(attackBar);
		centralPanel.add(armourBar);
		centralPanel.add(hpBar);

		centralPanel.add(attackBarLabel);
		centralPanel.add(armourBarLabel);
		centralPanel.add(hpBarLabel);
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

					centralPanel.remove(attackBar);
					centralPanel.remove(armourBar);
					centralPanel.remove(hpBar);

					centralPanel.remove(attackBarLabel);
					centralPanel.remove(armourBarLabel);
					centralPanel.remove(hpBarLabel);
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

					centralPanel.remove(mainButton);
					Stages.setApStatsToZero();

					mediaPlayer3.stop();

					createStartWindow();
				} else {
					try {
						JOptionPane.showMessageDialog(null, getLine(6, getLanguage() + "-Graph.txt"));
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

	/**
	 * @param plus            is the button we want to modify
	 * @param numOfPlus       is the number of attribute points the button increases
	 *                        its attribute
	 * @param typeOfAttribute is the attribute its button increases
	 * @param reset           indicates if the button is a reset button. The value
	 *                        of the variable reset is either 1(for plus buttons) or
	 *                        2(for reset buttons)
	 */
	private void modifyPlusButtons(Button plus, int numOfPlus, int typeOfAttribute, int reset) {
		plus.setBounds((WIDTH / 10) + 450 + numOfPlus * 75, HEIGHT * typeOfAttribute / 5 + 25 / 2, reset * 50, 50);
		plus.setBackground(Color.WHITE);
		plus.setForeground(Color.BLACK);
		plus.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
	}

	/**
	 * The method createRulesWindow displays the rules of the game
	 */
	public void createRulesWindow() {
		String rulesDoc = getAllText(23, getLanguage() + "-Rules.txt");

		rules = new TextArea(rulesDoc, 25, 40, TextArea.SCROLLBARS_NONE);
		rules.setBounds(0, 100, WIDTH - 10, HEIGHT - 135);
		rules.setForeground(Color.BLACK);
		rules.setBackground(new Color(204, 229, 255));
		rules.setFont(new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 20));
		rules.setEditable(false);

		centralPanel.add(mainButton);
		centralPanel.add(rules);

		mainButton.addActionListener(new ActionListener() {
			// to the menu window
			public void actionPerformed(ActionEvent e) {
				centralPanel.remove(rules);
				centralPanel.remove(mainButton);
				createMenuWindow();
			}
		});
	}

	/**
	 * The method createSettingsWindow gives the permission to handle the settings
	 * of the game
	 */
	public void createSettingsWindow() {
		lightMode = new Button("Light Mode");
		lightMode.setBounds((WIDTH - 450) / 2, (HEIGHT - 150 - HEIGHT / 5) / 2, 200, 75);
		lightMode.setBackground(Color.WHITE);
		lightMode.setForeground(Color.BLACK);
		lightMode.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		darkMode = new Button("Dark Mode");
		darkMode.setBounds((WIDTH - 450) / 2 + 250, (HEIGHT - 150 - HEIGHT / 5) / 2, 200, 75);
		darkMode.setBackground(Color.WHITE);
		darkMode.setForeground(Color.BLACK);
		darkMode.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		greekButton = new Button("Greek");
		greekButton.setBounds((WIDTH - 450) / 2, (HEIGHT - 150 - HEIGHT / 5) / 2 + HEIGHT / 5, 200, 75);
		greekButton.setBackground(Color.WHITE);
		greekButton.setForeground(Color.BLACK);
		greekButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		englishButton = new Button("English");
		englishButton.setBounds((WIDTH - 450) / 2 + 250, (HEIGHT - 150 - HEIGHT / 5) / 2 + HEIGHT / 5, 200, 75);
		englishButton.setBackground(Color.WHITE);
		englishButton.setForeground(Color.BLACK);
		englishButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		centralPanel.add(darkMode);
		centralPanel.add(lightMode);
		centralPanel.add(greekButton);
		centralPanel.add(englishButton);
		centralPanel.add(mainButton);

		lightMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centralPanel.setBackground(new Color(180, 228, 223));
			}
		});
		darkMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centralPanel.setBackground(new Color(0, 51, 51));
			}
		});

		greekButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Graph.language = "Gr";
				JOptionPane.showMessageDialog(null, "Language set to Greek");
			}
		});
		englishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Graph.language = "En";
				JOptionPane.showMessageDialog(null, "Language set to English");
			}
		});

		mainButton.addActionListener(new ActionListener() {
			// to the menu window
			public void actionPerformed(ActionEvent e) {
				centralPanel.remove(mainButton);
				centralPanel.remove(darkMode);
				centralPanel.remove(lightMode);
				centralPanel.remove(greekButton);
				centralPanel.remove(englishButton);
				createMenuWindow();
			}
		});
	}

	public void createCreditsWindow() {
		creditText = new TextArea(getAllText(14, "En-Credits.txt"), 20, 20, TextArea.SCROLLBARS_VERTICAL_ONLY);
		creditText.setBounds(0, 100, WIDTH - 15, HEIGHT - 110);
		creditText.setBackground(new Color(0, 51, 51));
		creditText.setForeground(Color.white);
		creditText.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));

		centralPanel.add(creditText);
		centralPanel.add(mainButton);

		mainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centralPanel.remove(creditText);
				centralPanel.remove(mainButton);
				createMenuWindow();
			}
		});
	}

	/**
	 * @param character is the of object of the character This method shows the move
	 *                  of the character
	 */
	public void moveImage(Character character) {
		final int WAIT_TIME = 1500;
		final int TO_THE_RIGHT = 100;
		final int TO_THE_LEFT = -100;

		Runnable runnable = () -> {

			if (character instanceof God) {
				godImage.setBounds(WIDTH / 2 + 300 + TO_THE_LEFT, HEIGHT * 9 / 10 - 450, 240, 403);
			} else {
				heroImage.setBounds(WIDTH / 2 - 550 + TO_THE_RIGHT, HEIGHT * 9 / 10 - 423, 212, 343);
			}
			try {
				Thread.sleep(WAIT_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (character instanceof God) {
				godImage.setBounds(WIDTH / 2 + 300, HEIGHT * 9 / 10 - 450, 240, 403);
			} else {
				heroImage.setBounds(WIDTH / 2 - 550, HEIGHT * 9 / 10 - 423, 212, 343);
			}

		};

		new Thread(runnable).start();
	}
}
