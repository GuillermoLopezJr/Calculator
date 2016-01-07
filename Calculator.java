import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class Calculator extends JPanel{

	private JButton[] numButtons;
	private JButton decButton;
	private JButton addButton;
	private JButton subButton;
	private JButton multButton;
	private JButton divButton;
	private JButton equalButton;
	private JButton powButton;
	private JButton modButton;

	private final Dimension BUTTON_DIM = new Dimension(70,70);
	private final Font buttonFont = new Font("Serif", Font.BOLD, 24);
	private JPanel buttonPanel, screenPanel;
	private JLabel screen;

	public Calculator()
	{	
		setLayout(new BorderLayout());
		initComponents();
	}

	public void initComponents()
	{
		initPanels();
		initButtons();
		initScreen();
	}

	public void initPanels()
	{
		Border screenPanelBorder = BorderFactory.createLineBorder(Color.BLACK, 3);
		Border buttPanelBorder = BorderFactory.createLoweredBevelBorder();

		screenPanel = new JPanel();
		screenPanel.setBorder(screenPanelBorder);
		screenPanel.setBackground(Color.WHITE);
		add(screenPanel, BorderLayout.NORTH);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout() );
		buttonPanel.setBorder(buttPanelBorder);
		buttonPanel.setBackground(Color.GRAY);
		add(buttonPanel, BorderLayout.CENTER);
	}

	public void initScreen()
	{
		screen = new JLabel(" ");
		screen.setFont(new Font("Serif", Font.PLAIN, 45) );
		screenPanel.add(screen);
	}

	public void initButtons()
	{
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(3,3,3,3);
		
		addNumberButtons(gbc);
		addOperatorButtons(gbc);

		
		decButton = new JButton(".");
		decButton.setPreferredSize(BUTTON_DIM);
		decButton.setFont(buttonFont);
		gbc.gridx = 2;
		gbc.gridy = 4;
		buttonPanel.add(decButton, gbc);

		equalButton = new JButton("=");
		equalButton.setPreferredSize(BUTTON_DIM);
		equalButton.setFont(buttonFont);
		gbc.gridx = 4;
		gbc.gridy = 4;
		buttonPanel.add(equalButton, gbc);
	}

	public void addNumberButtons(GridBagConstraints gbc)
	{
		int numOfButtons = 10;
		numButtons = new JButton[numOfButtons];
		int xPos = 3;
		int yPos = 1;

		for(int i = 0; i < numOfButtons; i++)
		{
			numButtons[i] = new JButton(""+i);
			numButtons[i].setFont(buttonFont);
			numButtons[i].setPreferredSize(BUTTON_DIM);
		}

		for(int i = numOfButtons-1; i >= 0; i--)
		{
			gbc.gridx = xPos;
			gbc.gridy = yPos;
			buttonPanel.add(numButtons[i], gbc);

			if(i == 1)
			{
				xPos = 1;
				yPos++;
			}
			else if(xPos == 1)
			{	
				xPos = 3;
				yPos++;
			}
			else
				xPos--;
		}

	}

	public void addOperatorButtons(GridBagConstraints gbc)
	{
		addButton = new JButton("+");
		addButton.setPreferredSize(BUTTON_DIM);
		addButton.setFont(buttonFont);
		gbc.gridx = 4;
		gbc.gridy = 3;
		buttonPanel.add(addButton, gbc);

		subButton = new JButton("-");
		subButton.setPreferredSize(BUTTON_DIM);
		subButton.setFont(buttonFont);
		gbc.gridx = 4;
		gbc.gridy = 2;
		buttonPanel.add(subButton, gbc);

		multButton = new JButton("*");
		multButton.setPreferredSize(BUTTON_DIM);
		multButton.setFont(buttonFont);
		gbc.gridx = 4;
		gbc.gridy = 1;
		buttonPanel.add(multButton, gbc);

		divButton = new JButton("/");
		divButton.setPreferredSize(BUTTON_DIM);
		divButton.setFont(buttonFont);
		gbc.gridx = 4;
		gbc.gridy = 0;
		buttonPanel.add(divButton, gbc);

		powButton = new JButton("^");
		powButton.setPreferredSize(BUTTON_DIM);
		powButton.setFont(buttonFont);
		gbc.gridx = 0;
		gbc.gridy = 3;
		buttonPanel.add(powButton, gbc);

		modButton = new JButton("%");
		modButton.setPreferredSize(BUTTON_DIM);
		modButton.setFont(buttonFont);
		gbc.gridx = 0;
		gbc.gridy = 2;
		buttonPanel.add(modButton, gbc);
	}
}
