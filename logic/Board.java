package logic;

import java.util.ArrayList;
//Board.java stores the state of the board: getters and setters
//generates starting code
//compares guess sequences w/ code sequence

public class Board {
	CodePegs[] codeSequence;
	CodePegs[][] guessSequences;
	ArrayList<ArrayList<KeyPegs>> keySequences;
	
	public Board() { 
		guessSequences = new CodePegs[20][6];
		keySequences = new ArrayList<ArrayList<KeyPegs>>();
		codeSequence = new CodePegs[6];
		
		generateCodeSequence();

	}
	
	private void generateCodeSequence(){
		CodePegs[] codeSequence = new CodePegs[6];
		int[] diffIndices = removeDuplicates(6); //creates an int array of 6 distinct numbers from 0-9
		
		for(int i = 0; i < 6; i++) {
			codeSequence[i] = new CodePegs(diffIndices[i]);
		}
		setCodeSequence(codeSequence);
	}
	
	private int[] removeDuplicates(int size) {
		int randIndex;
		int[] diffNums = new int[size];	
			for(int i = 0; i < 6; i++) {
				randIndex = (int)(Math.random()*10);
				diffNums[i] = randIndex;
				for(int j = 0; j <= i; j++) {
					if(diffNums[j] == diffNums[i] && j!=i) {
						randIndex = (int)(Math.random()*10);
						diffNums[i] = randIndex;
						j = 0;
					}
				}
			}
		return diffNums;
	}
	
	private void setCodeSequence(CodePegs[] cSeq) {
		codeSequence = cSeq;
	}
	
	public CodePegs[] getCodeSequence() {
		return codeSequence;
	}
	
	public CodePegs[][] getGuessSequences() {
		return guessSequences;
	}
	
	public ArrayList<KeyPegs> getKeySequences(int row){
		return keySequences.get(row);
	}
	
	public void updateGuessSequences(int row, int col, CodePegs cpg) {
		guessSequences[row][col] = cpg;
		
		if(col == 5) {
			compareCodes(row);
		}
	}
	
	public void compareCodes(int row) {
		int numB = 0;
		int numW = 0;
		ArrayList<CodePegs> alreadyCounted = new ArrayList<CodePegs>();
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(guessSequences[row][i].getColor() == getCodeSequence()[j].getColor() && i ==j) {
					if(alreadyCounted.contains(guessSequences[row][i]))
						continue;
					numB++;
					alreadyCounted.add(guessSequences[row][i]);
				}
			}
		}
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(guessSequences[row][i].getColor() == getCodeSequence()[j].getColor() && i != j) {
					if(alreadyCounted.contains(guessSequences[row][i]))
						continue;
					numW++;
					alreadyCounted.add(guessSequences[row][i]);
					}
				}
			}		
		updateKeySequences(numB, numW);
	}

	
	public boolean updateKeySequences(int blackPegs, int whitePegs) {
		if(blackPegs == 6) {
			return true;
		}
		ArrayList<KeyPegs> addKeys = new ArrayList<KeyPegs>();
		for(int i = 0; i < blackPegs + whitePegs; i++) {
			if(i < blackPegs)
				addKeys.add(new KeyPegs(false));
			else
				addKeys.add(new KeyPegs(true));
		}
		keySequences.add(addKeys);
		return false;
	}
}
