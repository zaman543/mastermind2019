package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Gui extends JFrame implements ActionListener {
		
	public static final int WIDTH = 500;
	public static final int HEIGHT = 1000;
	private GridLayout gameResponseGrid;
	private JPanel[][] gameResponseArr; 
	private GridLayout userGuessGrid;
	private JPanel[][] userGuessArr;
	private int row, col;
	private Board currentBoard;

	public Gui() {
		super("Mastermind");		                                       
	 	setSize(WIDTH, HEIGHT);
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	setLayout(new BorderLayout());
    
    row = 0;
    col = 0;
    currentBoard = new Board();
    
    JPanel colorOptions = new JPanel();
    colorOptions.setLayout(new GridLayout(2, 5));
    add(colorOptions, BorderLayout.NORTH);
    
    for(int i = 0; i < 10; i++){
    	CodePegs coloredPeg = new CodePegs(i);
    	
      JButton aColor = new JButton(coloredPeg.getColor()); 
      aColor.addActionListener(this);
      
      Color colorObj = coloredPeg.getColorObj();
      aColor.setBackground(colorObj);
      aColor.setForeground(Color.magenta);
      
      aColor.setActionCommand(i + " peg");
      colorOptions.add(aColor);
    }
   
    JPanel gameResponse = new JPanel();
	gameResponse.setBorder(BorderFactory.createLineBorder(Color.black));
    gameResponseGrid = new GridLayout(20, 6, 1, 1);
    gameResponse.setLayout(gameResponseGrid);
    add(gameResponse, BorderLayout.WEST);
    
    gameResponseArr = new JPanel[20][6];
    for(int i = 0; i < 20; i++) {
  	  for(int j = 0; j < 6; j++) {
  		  gameResponseArr[i][j] = new JPanel();
  		  gameResponseArr[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
  		  gameResponse.add(gameResponseArr[i][j]);
  	  }
    }
    
    JPanel userGuess = new JPanel();
	userGuess.setBorder(BorderFactory.createLineBorder(Color.black));
    userGuessGrid = new GridLayout(20, 6, 1, 1);
    userGuess.setLayout(userGuessGrid);
    add(userGuess, BorderLayout.EAST);
    userGuessArr = new JPanel[20][6];
    for(int i = 0; i < 20; i++) {
  	  for(int j = 0; j < 6; j++) {
  		  userGuessArr[i][j] = new JPanel();
  		  userGuessArr[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
  		  userGuess.add(userGuessArr[i][j]);
  	  }
    }
    
    
    JPanel giveUpPanel = new JPanel();
    giveUpPanel.setLayout(new FlowLayout());
    add(giveUpPanel, BorderLayout.SOUTH);
    
    JButton giveup = new JButton("Give Up");
    giveup.addActionListener(this);
    giveUpPanel.add(giveup);

	} 

	private boolean updateGameResponse(ArrayList<KeyPegs> kpgList) {
		int numCorrect = 0;
		for(int i = 0; i < kpgList.size(); i++) {
			if(kpgList.get(i).getColor().equals("black"))
				numCorrect++;
			gameResponseArr[row][i].setBackground((kpgList.get(i).getColorObj()));
		}
		return numCorrect == 6;
	}
  
  public void actionPerformed(ActionEvent e){
		String buttonName = e.getActionCommand();
    
		  if(buttonName == "Give Up"){
			  gameOver();
		  } else if (buttonName.contains("peg")) {
			  String colorIndex = buttonName.charAt(0) + "";
			  int cIndex = Integer.parseInt(colorIndex);
			  
			  CodePegs panelBgColor = new CodePegs(cIndex);
			  userGuessArr[row][col].setBackground(panelBgColor.getColorObj());
			  
			  CodePegs[] answers = currentBoard.getCodeSequence();
			  for(int i = 0; i < 6; i++) {
				  System.out.println(answers[i].getColor());
			  }
			  
			  currentBoard.updateGuessSequences(row, col, panelBgColor);
			  if(col == 5) {
				  if(updateGameResponse(currentBoard.getKeySequences(row))) {
					  gameOver();
				  }
				  row++;
				  col = 0;
			  } else 
				  col++;
			  if(row == 20)
				  gameOver();
		  }
	}
private void gameOver() {
	this.dispose();
	EndGameScreen gameOver = new EndGameScreen(currentBoard.getCodeSequence());
	gameOver.setVisible(true);
}


public static void main(String[] args)
{
	Gui gameBoard = new Gui();
  gameBoard.setVisible(true);

}

}
