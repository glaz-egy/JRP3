package io.github.glaz_egy;

import java.awt.Color;

public class NormalTheme extends ColorTheme {
	public NormalTheme(){
		setBoardColor(Color.GREEN);
		setStone1Color(Color.BLACK);
		setStone2Color(Color.WHITE);
		setBackColor(new Color(240, 240, 240));
		setTurnLogStringColor(new Color(0, 0, 0));
		setTurnLogBackColor(new Color(255, 255, 255));
		setStatusStringColor(new Color(0, 0, 0));
		setStatusBackColor(new Color(240, 240, 240));
		setButtonStringColor(new Color(0, 0, 0));
		setButtonBackColor(new Color(240, 240, 240));
		setTurnStringColor(new Color(0, 0, 0));
		setTurnBackColor(new Color(255, 255, 255));
		setMenuStringColor(new Color(0, 0, 0));
		setMenuBackColor(new Color(240, 240, 240));
	}
}
