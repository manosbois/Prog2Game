import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Graph {// Creating the class Graph
	private static final long serialVersionUID = 7572156739102733039L;
	private String title;
	static JFrame frame; // We define the frame of the project.
	JPanel centralpanel, buttonpanel, bottompanel; // We define three panels that appear on the screen
	JTextField username;
	Button introbutton, mainbutton, startbutton, statsbutton, settingsbutton, quitbutton;// We define the basic buttons
	// of the game
	static Label heroname, godname, herohpbar, godhpbar, herobackbar, godbackbar, heroenergy, godenergy; // Label
																											// names(hero
																											// and god
																											// names)
	JButton swordbutton, spearbutton, meditatebutton, shieldbutton, nomovebutton;
	Button nextGod, checkpoint, gameOver;// We define the move buttons
	static Label heroEnergy, heroHp, godHp;
	static Label mes1;
	BufferedImage godIcon;
	Label battleWin, winMes, loseMes, checkpointMes;
	Button attackplus1, attackplus5, attackplus10, attackreset, armorplus1, armorplus5, armorplus10, armorreset,
			hpplus1, hpplus5, hpplus10, hpreset, donebutton;// statistics buttons
	Label introlabel, mainlabel; // We define the basic labels that appear on the game
	Label attackbarlabel, armorbarlabel, hpbarlabel, attackremain, armorremain, hpremain, apattacklabel, aparmorlabel,
			aphplabel, attributepoints, donelabel;// We define the basic
	// statistics labels
	JProgressBar attackbar, armorbar, hpbar;// We define the progress bars that appear on the statistics screen
	Button lightmode, darkmode, greekbutton, englishbutton;// settings' buttons
	static final int WIDTH = 1280, HEIGHT = 800;// We define the width and the height of the window

	private Clip clip2; // For music.
	private boolean FirstGod = true; // Variable that's used to know whether to start the battleThread or to notify
										// it.
	private static int chosenMove;

	public static int getChosenMove() {
		return chosenMove;
	}

	public static void setChosenMove(int move) {
		chosenMove = move;
		System.out.println(move);
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
		centralpanel = new JPanel();// We define the main panel of the window
		centralpanel.setBackground(Color.BLACK);// We define the color of the panel centralpanel
		// based on rgb color
		centralpanel.setLayout(null);
		centralpanel.setLocation(0, 0);// We define the location of this panel
		centralpanel.setSize(WIDTH, HEIGHT);// We define the size of this panel

		/*
		 * bottompanel = new JPanel();// We define the secondary panel of the window
		 * bottompanel.setBackground(Color.DARK_GRAY);// We define the color of the
		 * panel bottompanel bottompanel.setLayout(null);// We define that we are not
		 * going to use a specific layout bottompanel.setLocation(0, 500);// We define
		 * the location of this panel bottompanel.setSize(WIDTH, HEIGHT / 15);// We
		 * define the size of this panel
		 */
		buttonpanel = new JPanel();
		buttonpanel.setBounds(0, 0, 0, 0);
		frame.add(centralpanel);// We add the panel centralpanel to the frame
		frame.add(buttonpanel);// We add the panel bottompanel to the frame

		introbutton = new Button("LET'S BEGIN OUR ADVENTURE");// We define the button on the first window that leads to
		// main menu

		username = new JTextField("Hercules");
		username.setBounds((WIDTH - 350) / 2, HEIGHT * 3 / 5, 350, 50);
		username.setBackground(Color.WHITE);
		username.setForeground(Color.BLUE);
		username.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
		Stages.myHero.setName(username.getText());

		introbutton.setLocation((WIDTH - 450) / 2, HEIGHT * 7 / 10);// We define the location of the button introbutton
		// based on the width and the height of the
		// window(so it could change if we change the size
		// of the window
		introbutton.setSize(450, 100);// We define the size of the introbutton
		introbutton.setBackground(Color.white);// We define the color of the introbutton
		introbutton.setForeground(Color.black);// We define the font color of the introbutton
		introbutton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));// We define the size of the text of the
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
		centralpanel.add(username);

		introbutton.addActionListener(new ActionListener() {// We define that if the user clicks the button that says
			// "LET'S BEGIN OUR ADVENTURE", he's goin to be returned
			// to the menu window
			public void actionPerformed(ActionEvent e) {
				Stages.myHero.setName(username.getText());
				centralpanel.remove(introbutton);// We remove button introbutton from the panel centralpanel
				centralpanel.remove(introlabel);// We remove label introlabel from the panel centralpanel
				centralpanel.remove(username);
				createMenuWindow();// We call the method createMenuWindow that creates the window with the menu
			}
		});
	}

	public void createMenuWindow() {// That method creates the window that contains the menu
		startbutton = new Button("START THE GAME");// We create the button with the name startbutton that leads the user
		// to the game
		modifyMenyButtons(startbutton, 0);

		statsbutton = new Button("STATISTICS");// We create the button with the name statsbutton that leads the user to
		// his statistics
		modifyMenyButtons(statsbutton, 1);

		settingsbutton = new Button("SETTINGS");// We create the button with the name settingsbutton that leads the user
		// to the settings of the game
		modifyMenyButtons(settingsbutton, 2);

		quitbutton = new Button("QUIT");// We create the button with the name quitbutton that exits the game
		modifyMenyButtons(quitbutton, 3);

		mainbutton = new Button("CODERUNNERS");// We create the main button that appears at the top of the screen if we
		// choose any of the menu options, so we can go back to the menu window
		mainbutton.setLocation((WIDTH - 350) / 2, 0);
		mainbutton.setSize(350, 50);
		mainbutton.setBackground(Color.BLACK);
		mainbutton.setForeground(Color.WHITE);
		mainbutton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

		centralpanel.add(startbutton);// We add to the frame the button startbutton
		centralpanel.add(statsbutton);// We add to the frame the button statsbutton
		centralpanel.add(settingsbutton);// We add to the frame the button settingsbutton
		centralpanel.add(quitbutton);// We add to the frame the button quitbutton
		// centralpanel.add(mainlabel);
		centralpanel.add(mainbutton);// We add to the frame the button startbutton

		startbutton.addActionListener(new ActionListener() {// The user has press the button startbutton
			public void actionPerformed(ActionEvent e) {
				removeMenuButtons();
				centralpanel.remove(mainbutton);
				createStartWindow();// We call the method that starts the game
			}
		});
		statsbutton.addActionListener(new ActionListener() {// The user has press the button statsbutton
			public void actionPerformed(ActionEvent e) {
				removeMenuButtons();

				createStatisticsWindow();// We call the method that controls the statistics of the player(Hero)
			}
		});
		settingsbutton.addActionListener(new ActionListener() {// The user has press the button settingsbutton
			public void actionPerformed(ActionEvent e) {
				removeMenuButtons();

				createSettingsWindow();// We call the method that controls the settings
			}
		});
		quitbutton.addActionListener(new ActionListener() {// The user has press the button quitbutton
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
		centralpanel.remove(startbutton);// We remove the button startbutton from the window
		centralpanel.remove(statsbutton);// We remove the button statsbutton from the window
		centralpanel.remove(settingsbutton);// We remove the button settingsbutton from the window
		centralpanel.remove(quitbutton);// We remove the button quitbutton from the window
	}

	public void createStartWindow() {// We create the window that the player is going to play to

		heroname = new Label(Stages.myHero.getName());
		heroname.setBounds(50, HEIGHT / 10, 200, 50);
		heroname.setAlignment(Label.CENTER);
		heroname.setForeground(Color.BLACK);
		heroname.setBackground(Color.ORANGE);
		heroname.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));

		godname = new Label();
		godname.setBounds(WIDTH - 250, HEIGHT / 10, 200, 50);
		godname.setForeground(Color.BLACK);
		godname.setBackground(Color.ORANGE);
		godname.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
		godname.setAlignment(Label.CENTER);

		battleThread = new Thread(battleTasks);
		if (FirstGod) {
			battleThread.start();
		} else {
			synchronized (Battle.getLock()) {
				Battle.getLock().notify();
			}
		}

		herobackbar = new Label();
		herobackbar.setBounds(50, HEIGHT / 10 + 50, 200, 30);
		herobackbar.setBackground(Color.WHITE);

		herohpbar = new Label();
		herohpbar.setBounds(50, HEIGHT / 10 + 50, 200, 30);
		herohpbar.setForeground(Color.BLACK);
		herohpbar.setBackground(Color.GREEN);
		herohpbar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
		herohpbar.setAlignment(Label.CENTER);

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

		godbackbar = new Label();
		godbackbar.setBounds(WIDTH - 250, HEIGHT / 10 + 50, 200, 30);
		godbackbar.setBackground(Color.WHITE);

		godhpbar = new Label();
		godhpbar.setBounds(WIDTH - 250, HEIGHT / 10 + 50, 200, 30);
		godhpbar.setForeground(Color.BLACK);
		godhpbar.setBackground(Color.GREEN);
		godhpbar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));
		godhpbar.setAlignment(Label.CENTER);

		godHp = new Label();
		godHp.setBounds(WIDTH - 250, HEIGHT / 10 + 50 + 31, 200, 20);
		godHp.setForeground(Color.BLACK);
		godHp.setBackground(Color.RED);
		godHp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		godHp.setAlignment(Label.LEFT);
		godHp.setText("HP: " + 100 + "%");

		buttonpanel.setBackground(Color.DARK_GRAY);// We define the color of the panel centralpanel
		// based on rgb color
		buttonpanel.setLayout(null);
		buttonpanel.setLocation(0, HEIGHT - 130);// We define the location of this panel
		centralpanel.setSize(WIDTH, HEIGHT - 130);// We customize the size of the panel centralpanel, so we can insert
		buttonpanel.setSize(WIDTH, 130);// We define the size of this panel

		// centralpanel.setSize(0,0);
		// the panel buttonpanel

		swordbutton = new JButton("1. Sword");
		modifyMoveButtons(swordbutton, 1, 90, 6);

		spearbutton = new JButton("2. Spear");
		modifyMoveButtons(spearbutton, 2, 70, 4);

		shieldbutton = new JButton("3. Shield");
		modifyMoveButtons(shieldbutton, 3, 0, 5);

		meditatebutton = new JButton("4. Meditate");
		modifyMoveButtons(meditatebutton, 4, 0, 10);

		nomovebutton = new JButton("5. No Move");
		modifyMoveButtons(nomovebutton, 5, 0, 0);

		mes1 = new Label();
		mes1.setBounds(0, HEIGHT * 9 / 10 - 280 + 5 * 40, WIDTH, 40);
		mes1.setBackground(Color.white);
		mes1.setForeground(Color.BLACK);
		mes1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

		addStartWindow();

		mainbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centralpanel.setSize(WIDTH, HEIGHT);
				buttonpanel.setSize(0, 0);
				removeStartWindow();

				createMenuWindow();
			}
		});

		swordbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized (Battle.getLock()) {
					setChosenMove(1);
					Battle.getLock().notify();
				}
			}
		});
		spearbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized (Battle.getLock()) {
					setChosenMove(2);
					Battle.getLock().notify();
				}
			}
		});
		shieldbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized (Battle.getLock()) {
					setChosenMove(3);
					Battle.getLock().notify();
				}
			}
		});
		meditatebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized (Battle.getLock()) {
					setChosenMove(4);
					Battle.getLock().notify();
				}
			}
		});
		nomovebutton.addActionListener(new ActionListener() {
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
		centralpanel.add(heroname);
		centralpanel.add(godname);
		centralpanel.add(herohpbar);
		centralpanel.add(herobackbar);
		centralpanel.add(godhpbar);
		centralpanel.add(godbackbar);
		centralpanel.add(heroHp);
		centralpanel.add(heroEnergy);
		centralpanel.add(godHp);
		centralpanel.add(mes1);

		buttonpanel.add(swordbutton);
		buttonpanel.add(spearbutton);
		buttonpanel.add(meditatebutton);
		buttonpanel.add(shieldbutton);
		buttonpanel.add(nomovebutton);
	}

	private void removeStartWindow() {
		centralpanel.remove(mainbutton);
		centralpanel.remove(heroname);
		centralpanel.remove(godname);
		centralpanel.remove(herohpbar);
		centralpanel.remove(herobackbar);
		centralpanel.remove(godhpbar);
		centralpanel.remove(godbackbar);
		centralpanel.remove(heroHp);
		centralpanel.remove(heroEnergy);
		centralpanel.remove(godHp);
		centralpanel.remove(mes1);

		buttonpanel.remove(swordbutton);
		buttonpanel.remove(spearbutton);
		buttonpanel.remove(meditatebutton);
		buttonpanel.remove(shieldbutton);
		buttonpanel.remove(nomovebutton);
	}

	public static void modifyHpLabels(Character hero, int hp, int initialHp, int herodamage) {
		if (hero.getName().equals(Stages.myHero.getName())) {
			herohpbar.setSize(200 * hp / initialHp, 30);
			heroHp.setText("HP: " + hp);
		} else {
			godhpbar.setSize(200 * hp / initialHp, 30);
			godHp.setText("HP: " + hp * 100 / initialHp + "%");
		}
	}

	public static void modifyEnergyLabel(int energy) {
		heroEnergy.setText("Energy: " + energy);
	}

	public void modifyMes(Label mes, String text) {
		mes.setText(text);
	}

	public void clearMes() {
		/*
		 * mes1.setText(""); mes2.setText(""); mes3.setText(""); mes4.setText("");
		 * mes5.setText("");
		 */
		mes1.setText("");
	}

	public void createLoseWindow() {
		centralpanel.setSize(WIDTH, HEIGHT);
		buttonpanel.setSize(0, 0);
		removeStartWindow();

		loseMes = new Label();
		loseMes.setBounds(WIDTH / 2 - 100, HEIGHT / 2 - 50, 200, 100);
		loseMes.setForeground(Color.RED);
		loseMes.setText("GAME OVER!");
		loseMes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		loseMes.setAlignment(Label.CENTER);

		gameOver = new Button("Return to the main menu");
		gameOver.setBounds(WIDTH / 2 - 150, HEIGHT / 2 + 150, 300, 70);
		gameOver.setForeground(Color.BLACK);
		gameOver.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		centralpanel.add(loseMes);
		centralpanel.add(gameOver);

		gameOver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centralpanel.remove(loseMes);
				centralpanel.remove(gameOver);
				createMenuWindow();
			}
		});

	}

	public void createWinWindow() {
		centralpanel.setSize(WIDTH, HEIGHT);
		buttonpanel.setSize(0, 0);
		removeStartWindow();

		winMes = new Label();
		winMes.setBounds(0, HEIGHT / 2 - 50, WIDTH, 100);
		winMes.setForeground(new Color(255, 215, 0));
		winMes.setText("YOU WON! YOU HAVE CONQUERED OLYMPOUS!");
		winMes.setFont(new Font(Font.SERIF, Font.BOLD, 32));
		winMes.setAlignment(Label.CENTER);

		centralpanel.add(winMes);
	}

	public void createBattleWinWindow(String godName) {
		centralpanel.setSize(WIDTH, HEIGHT);
		buttonpanel.setSize(0, 0);
		removeStartWindow();

		battleWin = new Label();
		battleWin.setBounds(0, HEIGHT / 2 - 150, WIDTH, 200);
		battleWin.setForeground(Color.RED);
		battleWin.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
		battleWin.setAlignment(Label.CENTER);
		battleWin.setText("YOU WON " + godName);

		nextGod = new Button("Upgrade Your Hero's Statistics!");
		nextGod.setBounds((WIDTH - 600) / 2, HEIGHT / 2 - 150 + 250, 600, 75);
		nextGod.setForeground(Color.RED);
		nextGod.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));

		centralpanel.add(battleWin);
		centralpanel.add(nextGod);

		nextGod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centralpanel.remove(battleWin);
				centralpanel.remove(nextGod);
				createStatisticsWindow();
			}
		});

	}

	public void createCheckpointWindow() {
		centralpanel.setSize(WIDTH, HEIGHT);
		buttonpanel.setSize(0, 0);
		removeStartWindow();

		checkpointMes = new Label("You Lost, but you can continuou from the checkpoint!");
		checkpointMes.setBounds((WIDTH - 300) / 2, (HEIGHT - 100) / 2, 300, 100);
		checkpointMes.setForeground(Color.BLACK);
		checkpointMes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		checkpointMes.setAlignment(Label.CENTER);

		checkpoint = new Button("CHECKPOINT");
		checkpoint.setBounds((WIDTH - 150) / 2, (HEIGHT - 100) / 2 + 200, 150, 100);
		checkpoint.setBackground(Color.WHITE);
		checkpoint.setForeground(Color.BLACK);
		checkpoint.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

		centralpanel.add(checkpointMes);
		centralpanel.add(checkpoint);

		checkpoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centralpanel.remove(checkpointMes);
				centralpanel.remove(checkpoint);
				synchronized (Battle.getLock()) {
					Battle.getLock().notify();
				}
				createStartWindow();
			}
		});
	}

	public void createStatisticsWindow() {// We create the window that the play can see and upgrade his statistics

		try {
			File file2 = new File("C:\\java\\Graphics\\GraphicDisplay\\src\\Song2.wav");
			AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(file2);
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

		attackbarlabel = new Label("ATTACK: " + Stages.myHero.getAttack());// We create the label for the statistic bar
		// for attack
		attackbarlabel.setBounds(WIDTH / 10, 110, 325, 75);// We define the location and the size of the label
															// attackbarlabel
		attackbarlabel.setForeground(Color.WHITE);// We define the font color of the label attackbarlabel
		attackbarlabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));// We define the size of the text of the label
		// attackbarlabel and that the text is going
		// to be bold

		attackbar = new JProgressBar();// We create the progress bar for the statistic bar for attack
		attackbar.setValue((int) (double) Stages.myHero.getAttack() * 100
				/ (Stages.myHero.getAttack() + Stages.myHero.getArmour() + Stages.myHero.getHp()));// We set the first
		// value of the
		// progress bar
		// attackbar4
		attackbar.setStringPainted(true);// The progress bar appear on the screen
		attackbar.setBackground(Color.red);// We define the color of the progress bar
		attackbar.setBounds(WIDTH / 10, HEIGHT * 1 / 5, 325, 75);// We define the location and the size of the progress
		// bar

		apattacklabel = new Label(String.valueOf(Stages.getApAttack()));
		apattacklabel.setBounds((WIDTH / 10) + 350, HEIGHT * 1 / 5 + 25 / 2, 75, 50);
		apattacklabel.setBackground(Color.WHITE);
		apattacklabel.setForeground(Color.BLACK);
		apattacklabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		attackplus1 = new Button("+1");
		modigyPlusButtons(attackplus1, 0, 1, 1);

		attackplus5 = new Button("+5");
		modigyPlusButtons(attackplus5, 1, 1, 1);

		attackplus10 = new Button("+10");
		modigyPlusButtons(attackplus10, 2, 1, 1);

		attackremain = new Label(" (MAX: " + Stages.getAttributePoints() / 2 + ")");
		attackremain.setBounds((WIDTH / 10) + 800, HEIGHT * 1 / 5 + 25 / 2, 180, 50);
		attackremain.setForeground(Color.RED);
		attackremain.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		armorbarlabel = new Label("ARMOR: " + Stages.myHero.getArmour());// We create the label for the statistic bar
		// for armor
		armorbarlabel.setBounds(WIDTH / 10, 270, 325, 75);// We define the location and the size of the label
															// armorbarlabel
		armorbarlabel.setForeground(Color.WHITE);// We define the font color of the label armorbarlabel
		armorbarlabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));// We define the size of the text of the label
		// armorbarlabel and that the text is going to
		// be bold
		attackreset = new Button("Reset");
		modigyPlusButtons(attackreset, 3, 1, 2);

		armorbar = new JProgressBar();// We create the progress bar for the statistic bar for armor
		armorbar.setValue((int) (double) Stages.myHero.getArmour() * 100
				/ (Stages.myHero.getAttack() + Stages.myHero.getArmour() + Stages.myHero.getHp()));// We set the first
		// value of the
		// progress bar
		// armorbar
		armorbar.setStringPainted(true);// The progress bar appear on the screen
		armorbar.setBackground(Color.red);// We define the color of the progress bar
		armorbar.setBounds(WIDTH / 10, HEIGHT * 2 / 5, 325, 75);// We define the location and the size of the progress
		// bar

		aparmorlabel = new Label(String.valueOf(Stages.getApArmour()));
		aparmorlabel.setBounds((WIDTH / 10) + 350, HEIGHT * 2 / 5 + 25 / 2, 75, 50);
		aparmorlabel.setBackground(Color.WHITE);
		aparmorlabel.setForeground(Color.BLACK);
		aparmorlabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		armorplus1 = new Button("+1");
		modigyPlusButtons(armorplus1, 0, 2, 1);

		armorplus5 = new Button("+5");
		modigyPlusButtons(armorplus5, 1, 2, 1);

		armorplus10 = new Button("+10");
		modigyPlusButtons(armorplus10, 2, 2, 1);

		armorremain = new Label(" (MAX: " + Stages.getAttributePoints() / 2 + ")");
		armorremain.setBounds((WIDTH / 10) + 800, HEIGHT * 2 / 5 + 25 / 2, 180, 50);

		armorremain.setForeground(Color.RED);
		armorremain.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		armorreset = new Button("Reset");
		modigyPlusButtons(armorreset, 3, 2, 2);

		hpbarlabel = new Label("HEALTH POWER: " + Stages.myHero.getHp());// We create the label for the statistic bar
		// for health power
		hpbarlabel.setBounds(WIDTH / 10, 430, 325, 75);// We define the location and the size of the label hpbarlabel
		hpbarlabel.setForeground(Color.WHITE);// We define the font color of the label hpbarlabel
		hpbarlabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));// We define the size of the text of the label
		// hpbarlabel and that the text is going to be
		// bold

		hpbar = new JProgressBar();// We create the progress bar for the statistic bar for health power
		hpbar.setValue((int) (double) Stages.myHero.getHp() * 100
				/ (Stages.myHero.getAttack() + Stages.myHero.getArmour() + Stages.myHero.getHp()));// We set the first
		// value of the
		// progress bar
		// armorbar
		hpbar.setStringPainted(true);// The progress bar appear on the screen
		hpbar.setBackground(Color.red);// We define the color of the progress bar
		hpbar.setBounds(WIDTH / 10, HEIGHT * 3 / 5, 325, 75);// We define the location and the size of the progress bar

		aphplabel = new Label(String.valueOf(Stages.getApHp()));
		aphplabel.setBounds((WIDTH / 10) + 350, HEIGHT * 3 / 5 + 25 / 2, 75, 50);
		aphplabel.setBackground(Color.WHITE);
		aphplabel.setForeground(Color.BLACK);
		aphplabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		hpplus1 = new Button("+1");
		modigyPlusButtons(hpplus1, 0, 3, 1);

		hpplus5 = new Button("+5");
		modigyPlusButtons(hpplus5, 1, 3, 1);

		hpplus10 = new Button("+10");
		modigyPlusButtons(hpplus10, 2, 3, 1);

		hpremain = new Label(" (MAX: " + Stages.getAttributePoints() / 2 + ")");
		hpremain.setBounds((WIDTH / 10) + 800, HEIGHT * 3 / 5 + 25 / 2, 180, 50);
		hpremain.setForeground(Color.RED);
		hpremain.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		hpreset = new Button("Reset");
		modigyPlusButtons(hpreset, 3, 3, 2);

		attributepoints = new Label("Remaining Attribute Points: " + Stages.getAttributePoints());
		attributepoints.setBounds(WIDTH / 10, HEIGHT * 4 / 5, 375, 75);
		attributepoints.setBackground(Color.WHITE);
		attributepoints.setForeground(Color.BLACK);
		attributepoints.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		donelabel = new Label();
		donelabel.setBounds((WIDTH / 10) + 750, HEIGHT * 4 / 5, 200, 75);
		donelabel.setBackground(Color.BLACK);
		donelabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		donebutton = new Button("DONE");
		donebutton.setBounds((WIDTH / 10) + 755, HEIGHT * 4 / 5 + 5, 190, 65);
		donebutton.setBackground(Color.WHITE);
		donebutton.setForeground(Color.BLACK);
		donebutton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		centralpanel.add(attackbar);// We add the progress bar attackbar at the panel
		centralpanel.add(armorbar);// We add the progress bar armorbar at the panel
		centralpanel.add(hpbar);// We add the progress bar hpbar at the panel

		centralpanel.add(attackbarlabel);// We add the label attackbarlabel at the panel
		centralpanel.add(armorbarlabel);// We add the label armorbarlabel at the panel
		centralpanel.add(hpbarlabel);// We add the label hpbarlabel at the panel
		centralpanel.add(attributepoints);
		centralpanel.add(donebutton);
		centralpanel.add(donelabel);

		centralpanel.add(apattacklabel);
		centralpanel.add(attackplus1);
		centralpanel.add(attackplus5);
		centralpanel.add(attackplus10);
		centralpanel.add(attackremain);
		centralpanel.add(attackreset);

		centralpanel.add(aparmorlabel);
		centralpanel.add(armorplus1);
		centralpanel.add(armorplus5);
		centralpanel.add(armorplus10);
		centralpanel.add(armorremain);
		centralpanel.add(armorreset);

		centralpanel.add(aphplabel);
		centralpanel.add(hpplus1);
		centralpanel.add(hpplus5);
		centralpanel.add(hpplus10);
		centralpanel.add(hpremain);
		centralpanel.add(hpreset);

		donebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Stages.getAttributePoints() == 0) {

					Stages.giveAttributesPoints();

					clip2.stop();

					centralpanel.remove(attackbar);// We remove the progress bar attackbar from the panel
					centralpanel.remove(armorbar);// We remove the progress bar armorbar from the panel
					centralpanel.remove(hpbar);// We remove the progress bar hpbar from the panel

					centralpanel.remove(attackbarlabel);// We remove the progress bar attackbarlabel from the panel
					centralpanel.remove(armorbarlabel);// We remove the progress bar armorbarlabel from the panel
					centralpanel.remove(hpbarlabel);// We remove the progress bar hpbarlabel from the panel
					centralpanel.remove(attributepoints);
					centralpanel.remove(donebutton);
					centralpanel.remove(donelabel);

					centralpanel.remove(apattacklabel);
					centralpanel.remove(attackplus1);
					centralpanel.remove(attackplus5);
					centralpanel.remove(attackplus10);
					centralpanel.remove(attackremain);
					centralpanel.remove(attackreset);

					centralpanel.remove(aparmorlabel);
					centralpanel.remove(armorplus1);
					centralpanel.remove(armorplus5);
					centralpanel.remove(armorplus10);
					centralpanel.remove(armorremain);
					centralpanel.remove(armorreset);

					centralpanel.remove(aphplabel);
					centralpanel.remove(hpplus1);
					centralpanel.remove(hpplus5);
					centralpanel.remove(hpplus10);
					centralpanel.remove(hpremain);
					centralpanel.remove(hpreset);

					centralpanel.remove(mainbutton);// We remove the progress bar mainbutton from the panel
					Stages.setApStatsToZero();

					createStartWindow();
				} else {
					JOptionPane.showMessageDialog(null, "You haven't distributed all the attribute points!");
				}
			}
		});

		attackplus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Stages.getApAttack() + 1 <= maxAp) && (Stages.getAttributePoints() - 1 >= 0)) {
					Stages.setApAttack(1);
					apattacklabel.setText(String.valueOf(Stages.getApAttack()));
					attributepoints.setText("Remaining Attribute Points: " + Stages.getAttributePoints());
				}
			}
		});
		attackplus5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Stages.getApAttack() + 5 <= maxAp) && (Stages.getAttributePoints() - 5 >= 0)) {
					Stages.setApAttack(5);
					apattacklabel.setText(String.valueOf(Stages.getApAttack()));
					attributepoints.setText("Remaining Attribute Points: " + Stages.getAttributePoints());
				}
			}
		});
		attackplus10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Stages.getApAttack() + 10 <= maxAp) && (Stages.getAttributePoints() - 10 >= 0)) {
					Stages.setApAttack(10);
					apattacklabel.setText(String.valueOf(Stages.getApAttack()));
					attributepoints.setText("Remaining Attribute Points: " + Stages.getAttributePoints());
				}
			}
		});
		attackreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stages.setAttackZero();
				apattacklabel.setText(String.valueOf(Stages.getApAttack()));
				attributepoints.setText("Remaining Attribute Points: " + Stages.getAttributePoints());
			}
		});
		armorplus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Stages.getApArmour() + 1 <= maxAp) && (Stages.getAttributePoints() - 1 >= 0)) {
					Stages.setApArmour(1);
					aparmorlabel.setText(String.valueOf(Stages.getApArmour()));
					attributepoints.setText("Remaining Attribute Points: " + Stages.getAttributePoints());
				}
			}
		});
		armorplus5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Stages.getApArmour() + 5 <= maxAp && Stages.getAttributePoints() - 5 >= 0) {
					Stages.setApArmour(5);
					aparmorlabel.setText(String.valueOf(Stages.getApArmour()));
					attributepoints.setText("Remaining Attribute Points: " + Stages.getAttributePoints());
				}
			}
		});
		armorplus10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Stages.getApArmour() + 10 <= maxAp) && (Stages.getAttributePoints() - 10 >= 0)) {
					Stages.setApArmour(10);
					aparmorlabel.setText(String.valueOf(Stages.getApArmour()));
					attributepoints.setText("Remaining Attribute Points: " + Stages.getAttributePoints());
				}
			}
		});
		armorreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stages.setArmorZero();
				aparmorlabel.setText(String.valueOf(Stages.getApArmour()));
				attributepoints.setText("Remaining Attribute Points: " + Stages.getAttributePoints());
			}
		});
		hpplus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Stages.getApHp() + 1 <= maxAp) && (Stages.getAttributePoints() - 1 >= 0)) {
					Stages.setApHp(1);
					aphplabel.setText(String.valueOf(Stages.getApHp()));
					attributepoints.setText("Remaining Attribute Points: " + Stages.getAttributePoints());
				}
			}
		});
		hpplus5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Stages.getApHp() + 5 <= maxAp) && (Stages.getAttributePoints() - 5 >= 0)) {
					Stages.setApHp(5);
					aphplabel.setText(String.valueOf(Stages.getApHp()));
					attributepoints.setText("Remaining Attribute Points: " + Stages.getAttributePoints());
				}
			}
		});
		hpplus10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((Stages.getApHp() + 10 <= maxAp) && (Stages.getAttributePoints() - 10 >= 0)) {
					Stages.setApHp(10);
					aphplabel.setText(String.valueOf(Stages.getApHp()));
					attributepoints.setText("Remaining Attribute Points: " + Stages.getAttributePoints());
				}
			}
		});
		hpreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stages.setHpZero();
				aphplabel.setText(String.valueOf(Stages.getApHp()));
				attributepoints.setText("Remaining Attribute Points: " + Stages.getAttributePoints());
			}
		});
	}

	private void modigyPlusButtons(Button plus, int numOfPlus, int typeOfAttribute, int reset) {
		plus.setBounds((WIDTH / 10) + 450 + numOfPlus * 75, HEIGHT * typeOfAttribute / 5 + 25 / 2, reset * 50, 50);
		plus.setBackground(Color.WHITE);
		plus.setForeground(Color.BLACK);
		plus.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
	}

	public void createSettingsWindow() {
		lightmode = new Button("Light Mode");
		lightmode.setBounds(WIDTH / 10, HEIGHT / 5, 200, 75);
		lightmode.setBackground(Color.WHITE);
		lightmode.setForeground(Color.BLACK);
		lightmode.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		darkmode = new Button("Dark Mode");
		darkmode.setBounds(WIDTH / 10 + 250, HEIGHT / 5, 200, 75);
		darkmode.setBackground(Color.WHITE);
		darkmode.setForeground(Color.BLACK);
		darkmode.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		greekbutton = new Button("Greek");
		greekbutton.setBounds(WIDTH / 10, HEIGHT * 2 / 5, 200, 75);
		greekbutton.setBackground(Color.WHITE);
		greekbutton.setForeground(Color.BLACK);
		greekbutton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		englishbutton = new Button("English");
		englishbutton.setBounds(WIDTH / 10 + 250, HEIGHT * 2 / 5, 200, 75);
		englishbutton.setBackground(Color.WHITE);
		englishbutton.setForeground(Color.BLACK);
		englishbutton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

		centralpanel.add(darkmode);
		centralpanel.add(lightmode);
		centralpanel.add(greekbutton);
		centralpanel.add(englishbutton);

		lightmode.addActionListener(new ActionListener() {// The user has press the button quitbutton
			public void actionPerformed(ActionEvent e) {
				centralpanel.setBackground(Color.GRAY);
			}
		});
		darkmode.addActionListener(new ActionListener() {// The user has press the button quitbutton
			public void actionPerformed(ActionEvent e) {
				centralpanel.setBackground(Color.black);
			}
		});
		mainbutton.addActionListener(new ActionListener() {// If the user clicks the button mainbutton the game returns
			// to the menu window
			public void actionPerformed(ActionEvent e) {
				centralpanel.remove(mainbutton);// We remove the progress bar mainbutton from the panel
				centralpanel.remove(darkmode);
				centralpanel.remove(lightmode);
				centralpanel.remove(greekbutton);
				centralpanel.remove(englishbutton);
				createMenuWindow();
			}
		});
	}
}
