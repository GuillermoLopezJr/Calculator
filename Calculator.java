import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

public class Calculator extends JPanel implements ActionListener{

	private JButton[] numButtons;
	private final int numOfButtons = 10;
	private JButton decButton;
	private JButton addButton;
	private JButton subButton;
	private JButton multButton;
	private JButton divButton;
	private JButton equalButton;
	private JButton powButton;
	private JButton modButton;
	private JButton clearButton;
	private JButton leftParenButton;
	private JButton rightParenButton;

	private final Dimension BUTTON_DIM = new Dimension(90,90);
	private final Font buttonFont = new Font("Serif", Font.BOLD, 24);
	private JPanel buttonPanel, screenPanel;
	private JLabel screen;
	private String text = "";

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
		screenPanel.setBackground(Color.GRAY);
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

		clearButton = new JButton("clear");
		clearButton.addActionListener(this);
		clearButton.setPreferredSize(BUTTON_DIM);
		clearButton.setFont(buttonFont);
		gbc.gridx = 3;
		gbc.gridy = 0;
		buttonPanel.add(clearButton, gbc);

		leftParenButton = new JButton("(");
		leftParenButton.addActionListener(this);
		leftParenButton.setPreferredSize(BUTTON_DIM);
		leftParenButton.setFont(buttonFont);
		gbc.gridx = 1;
		gbc.gridy = 0;
		buttonPanel.add(leftParenButton, gbc);

		rightParenButton = new JButton(")");
		rightParenButton.addActionListener(this);
		rightParenButton.setPreferredSize(BUTTON_DIM);
		rightParenButton.setFont(buttonFont);
		gbc.gridx = 2;
		gbc.gridy = 0;
		buttonPanel.add(rightParenButton, gbc);

		decButton = new JButton(".");
		decButton.addActionListener(this);
		decButton.setPreferredSize(BUTTON_DIM);
		decButton.setFont(buttonFont);
		gbc.gridx = 2;
		gbc.gridy = 4;
		buttonPanel.add(decButton, gbc);

		equalButton = new JButton("=");
		equalButton.addActionListener(this);
		equalButton.setPreferredSize(BUTTON_DIM);
		equalButton.setFont(buttonFont);
		gbc.gridx = 4;
		gbc.gridy = 4;
		buttonPanel.add(equalButton, gbc);
	}

	public void addNumberButtons(GridBagConstraints gbc)
	{
		numButtons = new JButton[numOfButtons];
		int xPos = 3;
		int yPos = 1;

		for(int i = 0; i < numOfButtons; i++)
		{
			numButtons[i] = new JButton(""+i);
			numButtons[i].addActionListener(this);
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
		addButton.addActionListener(this);
		addButton.setPreferredSize(BUTTON_DIM);
		addButton.setFont(buttonFont);
		gbc.gridx = 4;
		gbc.gridy = 3;
		buttonPanel.add(addButton, gbc);

		subButton = new JButton("-");
		subButton.addActionListener(this);
		subButton.setPreferredSize(BUTTON_DIM);
		subButton.setFont(buttonFont);
		gbc.gridx = 4;
		gbc.gridy = 2;
		buttonPanel.add(subButton, gbc);

		multButton = new JButton("*");
		multButton.setPreferredSize(BUTTON_DIM);
		multButton.addActionListener(this);
		multButton.setFont(buttonFont);
		gbc.gridx = 4;
		gbc.gridy = 1;
		buttonPanel.add(multButton, gbc);

		divButton = new JButton("/");
		divButton.addActionListener(this);
		divButton.setPreferredSize(BUTTON_DIM);
		divButton.setFont(buttonFont);
		gbc.gridx = 4;
		gbc.gridy = 0;
		buttonPanel.add(divButton, gbc);

		powButton = new JButton("^");
		powButton.addActionListener(this);
		powButton.setPreferredSize(BUTTON_DIM);
		powButton.setFont(buttonFont);
		gbc.gridx = 0;
		gbc.gridy = 3;
		buttonPanel.add(powButton, gbc);

		modButton = new JButton("%");
		modButton.addActionListener(this);
		modButton.setPreferredSize(BUTTON_DIM);
		modButton.setFont(buttonFont);
		gbc.gridx = 0;
		gbc.gridy = 2;
		buttonPanel.add(modButton, gbc);
	}

	public void actionPerformed(ActionEvent event) 
	{  
		for(int i = 0; i < numOfButtons; i++)
		{
			if(event.getSource() == numButtons[i])
			{
				text += numButtons[i].getText();	
			}
		}

		if(event.getSource() == addButton)
		{
			text += "+";
		}
		else if (event.getSource() == subButton)
		{
			text += "-";
		}
		else if (event.getSource() == multButton)
		{
			text += "*";
		}
		else if(event.getSource() == divButton)
		{
			text += "/";
		}
		else if (event.getSource() == powButton)
		{
			text += "^";
		}
		else if (event.getSource() == modButton)
		{
			text += "%";
		}
		else if (event.getSource() == clearButton)
		{
			text = " ";
		}
		else if (event.getSource() == decButton)
		{
			text += ".";
		}
		else if (event.getSource() == leftParenButton)
		{
			text += "(";
		}
		else if (event.getSource() == rightParenButton)
		{
			text += ")";
		}
		else if (event.getSource() == equalButton)
		{
			try{
				double result = evaluate(text);
				text = ""+result;
			}
			catch(Exception ex)
			{
				text = "ERROR";
			}
		}
		screen.setText(text);
	} 

	public double evaluate(String text) throws Exception
	{
		Evaluater obj = new Evaluater();

		try{
			double result = obj.calculate(text);
			return result;
		}
		catch(Exception ex){
			throw new IllegalArgumentException("An error occurred");
		}	
	}
}



