package logic;

public class KeyPegs extends CodePegs {
	//can be white or black
	//white = correct color, incorrect slot
	//black color, correct position
	
	public KeyPegs(boolean isWhite) {
		super(0);
		if(!isWhite)
			super.setColor(1);
	}
}
