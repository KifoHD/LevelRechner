package de.kifo.calculator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorFrame extends JFrame implements ActionListener {
	
	public int choosedAnswer = 5;
	public int currentLevel;
	public int wantedLevel;
	
	JPanel chooseCalculatorPanel = new JPanel();
	JPanel getCurrentLevel = new JPanel();
	JPanel getWantedLevel = new JPanel();
	JPanel giveAnswerButton = new JPanel();
	JPanel giveAnswer = new JPanel();

	JLabel chooseLabel = new JLabel("Wähle aus, was du berechnen lassen möchtest:");
	JLabel currentLevelLabel = new JLabel("Welches Level bist du im Moment?");
	JLabel wantedLevelLabel = new JLabel("Welches Level möchtest du erreichen?");
	JLabel giveAnswerLabel = new JLabel();

	JComboBox chooseBox;

	JTextField getCurrentLevelField = new JTextField();
	JTextField getWantedLevelField = new JTextField();

	JButton button = new JButton("Berechnen");

	CalculatorFrame() {
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setSize(1080, 580);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.gray);

		button.setForeground(Color.cyan);
		button.setBackground(Color.lightGray);
		button.addActionListener(this);

		String[] chooseArray = { "Xp", "Geld", "Beides" };
		chooseBox = new JComboBox(chooseArray);
		chooseBox.addActionListener(this);

		chooseLabel.setForeground(Color.black);

		currentLevelLabel.setForeground(Color.black);

		wantedLevelLabel.setForeground(Color.black);

		giveAnswerLabel.setForeground(Color.black);
		
		getCurrentLevelField.setPreferredSize(new Dimension(250, 40));
		getCurrentLevelField.setForeground(Color.black);

		getWantedLevelField.setPreferredSize(new Dimension(250, 40));
		getWantedLevelField.setForeground(Color.black);

		chooseCalculatorPanel.setBackground(Color.gray);
		chooseCalculatorPanel.setBounds(800, 10, 325, 225);
		chooseCalculatorPanel.add(chooseLabel);
		chooseCalculatorPanel.add(chooseBox);

		getCurrentLevel.setBackground(Color.gray);
		getCurrentLevel.setBounds(735, 235, 450, 225);
		getCurrentLevel.add(currentLevelLabel);
		getCurrentLevel.add(getCurrentLevelField);

		getWantedLevel.setBackground(Color.gray);
		getWantedLevel.setBounds(735, 460, 450, 225);
		getWantedLevel.add(wantedLevelLabel);
		getWantedLevel.add(getWantedLevelField);

		giveAnswerButton.setBackground(Color.gray);
		giveAnswerButton.setBounds(735, 685, 450, 50);
		giveAnswerButton.add(button);

		giveAnswer.setBackground(Color.gray);
		giveAnswer.setBounds(735, 735, 450, 225);
		giveAnswer.add(giveAnswerLabel);
		giveAnswer.setVisible(false);

		this.add(chooseCalculatorPanel);
		this.add(getCurrentLevel);
		this.add(getWantedLevel);
		this.add(giveAnswerButton);
		this.add(giveAnswer);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==button) {
				
			choosedAnswer = chooseBox.getSelectedIndex();
			if(choosedAnswer==0) {
				startXpCalculation();
			}
			if(choosedAnswer==1) {
				startMoneyCalculation();
			}
			if(choosedAnswer==2) {
				startBothCalculation();
			}
		}
		
	}
	
	public void startXpCalculation() {

		int answerXp;
		
		String[] responses = {"Okay"};
		
		String currentLevelText = getCurrentLevelField.getText();
		String wantedLevelText = getWantedLevelField.getText();
		
		try {
			currentLevel = Integer.parseInt(currentLevelText);
			wantedLevel = Integer.parseInt(wantedLevelText);
		}
		catch(NumberFormatException e) {
			getCurrentLevelField.setText("1");
			getWantedLevelField.setText("1");
			JOptionPane.showInternalOptionDialog(null, 
					"Du darfst keine Buchstaben oder Zeichen für dein Level eingeben!", 
					"Fehler", 
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE, 
					null, 
					responses, 
					0);
			return;
		}
		
		if(currentLevel >= wantedLevel) {
			getCurrentLevelField.setText("1");
			getWantedLevelField.setText("1");
			JOptionPane.showInternalOptionDialog(null, 
					"Dein Level muss niedriger sein, als das Level, welches du erreichen möchtest!", 
					"Fehler", 
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE, 
					null, 
					responses, 
					0);
		}
		else if(currentLevel == 0) {
			getCurrentLevelField.setText("1");
			JOptionPane.showInternalOptionDialog(null, 
					"Dein jetziges Level muss mindestens Level 1 sein!", 
					"Fehler", 
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE, 
					null, 
					responses, 
					0);
		}
		else {
		LevelUpXp levelUpXp = new LevelUpXp();
		answerXp = levelUpXp.startCalculation(wantedLevel, currentLevel);
		
		giveAnswerLabel.setText("Du benötigst noch " + answerXp + " Xp um Level " + wantedLevel + " zu erreichen.");
		giveAnswer.setVisible(true);
		}
	}
	
	public void startMoneyCalculation() {
		int answerMoney;
		
		String[] responses = {"Okay"};
		
		String currentLevelText = getCurrentLevelField.getText();
		String wantedLevelText = getWantedLevelField.getText();
		
		try {
		currentLevel = Integer.parseInt(currentLevelText);
		wantedLevel = Integer.parseInt(wantedLevelText);
		}
		catch(NumberFormatException e) {
			getCurrentLevelField.setText("1");
			getWantedLevelField.setText("1");
			JOptionPane.showInternalOptionDialog(null, 
					"Du darfst keine Buchstaben oder Zeichen für dein Level eingeben!", 
					"Fehler", 
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE, 
					null, 
					responses, 
					0);
			return;
		}
		
		if(currentLevel >= wantedLevel) {
			getCurrentLevelField.setText("1");
			getWantedLevelField.setText("1");
			JOptionPane.showInternalOptionDialog(null, 
					"Dein Level muss niedriger sein, als das Level, welches du erreichen möchtest!", 
					"Fehler", 
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE, 
					null, 
					responses, 
					0);
		}
		else if(currentLevel == 0) {
			getCurrentLevelField.setText("1");
			JOptionPane.showInternalOptionDialog(null, 
					"Dein jetziges Level muss mindestens Level 1 sein!", 
					"Fehler", 
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE, 
					null, 
					responses, 
					0);
		}
		else {
		LevelUpMoney levelUpMoney = new LevelUpMoney();
		answerMoney = levelUpMoney.startCalculation(wantedLevel, currentLevel);
		
		giveAnswerLabel.setText("Du benötigst noch " + answerMoney + " Dollar um Level " + wantedLevel + " zu erreichen.");
		giveAnswer.setVisible(true);
		}	
	}
	
	public void startBothCalculation() {
		int answerXp;
		int answerMoney;
		
		String[] responses = {"Okay"};
		
		String currentLevelText = getCurrentLevelField.getText();
		String wantedLevelText = getWantedLevelField.getText();
		
		try {
		currentLevel = Integer.parseInt(currentLevelText);
		wantedLevel = Integer.parseInt(wantedLevelText);
		}
		catch(NumberFormatException e) {
			getCurrentLevelField.setText("1");
			getWantedLevelField.setText("1");
			JOptionPane.showInternalOptionDialog(null, 
					"Du darfst keine Buchstaben oder Zeichen für dein Level eingeben!", 
					"Fehler", 
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE, 
					null, 
					responses, 
					0);
			return;
		}
		
		if(currentLevel >= wantedLevel) {
			getCurrentLevelField.setText("1");
			getWantedLevelField.setText("1");
			JOptionPane.showInternalOptionDialog(null, 
					"Dein Level muss niedriger sein, als das Level, welches du erreichen möchtest!", 
					"Fehler", 
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE, 
					null, 
					responses, 
					0);
		}
		else if(currentLevel == 0) {
			getCurrentLevelField.setText("1");
			JOptionPane.showInternalOptionDialog(null, 
					"Dein jetziges Level muss mindestens Level 1 sein!", 
					"Fehler", 
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE, 
					null, 
					responses, 
					0);
		}
		else {
		LevelUpMoney levelUpMoney = new LevelUpMoney();
		LevelUpXp levelUpXp = new LevelUpXp();
		
		answerMoney = levelUpMoney.startCalculation(wantedLevel, currentLevel);
		answerXp = levelUpXp.startCalculation(wantedLevel, currentLevel);
		
		giveAnswerLabel.setText("Du benötigst noch " + answerXp + " Xp und " + answerMoney + " Dollar um Level " + wantedLevel + " zu erreichen.");
		giveAnswer.setVisible(true);
		}
	}
	
}
