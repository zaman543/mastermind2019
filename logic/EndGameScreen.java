package logic;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndGameScreen extends JFrame {

	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	public EndGameScreen(CodePegs[] cpgArr) 
  	 {
 		super("Mastermind Cheat Sheet");		 
		setSize(WIDTH, HEIGHT);
       
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setLayout(new GridLayout(2, 1));
              
       JLabel textBox = new JLabel("Here are the answers!");
       add(textBox);
       
       JPanel codeSequencePanel = new JPanel();
       add(codeSequencePanel);
       
       codeSequencePanel.setLayout(new GridLayout(2, 6));
       for(int i = 0; i < 2; i++)
    	   for(int j = 0; j < 6; j++)
    		   if(i == 0) {
    			   JLabel colorLabel = new JLabel(cpgArr[j].getColor());
    			   codeSequencePanel.add(colorLabel);
    		   }
    		   else {
    			   JPanel colorPanel = new JPanel();
    			   colorPanel.setBackground(cpgArr[j].getColorObj());
    			   codeSequencePanel.add(colorPanel);
    		   }
           //set the background color to the code pegs array
   }
}
