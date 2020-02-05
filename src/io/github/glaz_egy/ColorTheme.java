package io.github.glaz_egy;

import java.awt.Color;

public class ColorTheme {
	public static final int NORMAL = 0;
	public static final int DARK = 1;
	public static final int LIGTH = 2;
	public static final int CUSTOM = 3;

	protected Color board, stone1, stone2, back, turnLogString, turnLogBack
	, statusString, statusBack, buttonString, buttonBack
	, turnString, turnBack, menuString, menuBack;

	public final void setSettingColor(ReversiSetting set){
		set.boardColorButton.setBackground(board);
		set.stoneColorButton1.setBackground(stone1);
		set.stoneColorButton2.setBackground(stone2);
		set.backColorButton.setBackground(back);
		set.turnLogColorStringButton.setBackground(turnLogString);
		set.turnLogColorBackButton.setBackground(turnLogBack);
		set.statusColorStringButton.setBackground(statusString);
		set.statusColorBackButton.setBackground(statusBack);
		set.buttonColorStringButton.setBackground(buttonString);
		set.buttonColorBackButton.setBackground(buttonBack);
		set.turnColorStringButton.setBackground(turnString);
		set.turnColorBackButton.setBackground(turnBack);
		set.menuColorStringButton.setBackground(menuString);
		set.menuColorBackButton.setBackground(menuBack);
	}

	public final void setMainColor(ReversiMain main){
		main.stone1 = stone1;
		main.stone2 = stone2;
		main.board = board;
		main.frame.backPanel.setBackground(back);
		main.frame.MainPanel.setBackground(back);
		main.frame.panel_3.setBackground(back);
		main.frame.mainBoard.setBackground(back);
		main.frame.getContentPane().setBackground(back);
		main.frame.logArea.setBackground(turnLogBack);
		main.frame.logArea.setForeground(turnLogString);
		main.frame.status.setForeground(statusString);
		main.frame.status.setBackground(statusBack);
		main.frame.StatusBar.setBackground(statusBack);
		main.frame.reset.setForeground(buttonString);
		main.frame.backLog.setForeground(buttonString);
		main.frame.nextLog.setForeground(buttonString);
		main.frame.reset.setBackground(buttonBack);
		main.frame.backLog.setBackground(buttonBack);
		main.frame.nextLog.setBackground(buttonBack);
		main.frame.turnColorBox.setForeground(turnString);
		main.frame.turnColorBox.setBackground(turnBack);
		main.frame.menuBar.setForeground(menuString);
		main.frame.editMenu.setForeground(menuString);
		main.frame.fileMenu.setForeground(menuString);
		main.frame.Star.setForeground(menuString);
		main.frame.Stone.setForeground(menuString);
		main.frame.setting.setForeground(menuString);
		main.frame.newGame.setForeground(menuString);
		main.frame.getStoneNum.setForeground(menuString);
		main.frame.maxSelect.setForeground(menuString);
		main.frame.botNothing.setForeground(menuString);
		main.frame.botPlugin.setForeground(menuString);
		main.frame.offlineStatus.setForeground(menuString);
		main.frame.onlineStatus.setForeground(menuString);
		main.frame.groupIndication.setForeground(menuString);
		main.frame.attackStart.setForeground(menuString);
		main.frame.menuBar.setBackground(menuBack);
		main.frame.editMenu.setBackground(menuBack);
		main.frame.fileMenu.setBackground(menuBack);
		main.frame.Star.setBackground(menuBack);
		main.frame.Stone.setBackground(menuBack);
		main.frame.setting.setBackground(menuBack);
		main.frame.newGame.setBackground(menuBack);
		main.frame.getStoneNum.setBackground(menuBack);
		main.frame.maxSelect.setBackground(menuBack);
		main.frame.botNothing.setBackground(menuBack);
		main.frame.botPlugin.setBackground(menuBack);
		main.frame.offlineStatus.setBackground(menuBack);
		main.frame.onlineStatus.setBackground(menuBack);
		main.frame.groupIndication.setBackground(menuBack);
		main.frame.attackStart.setBackground(menuBack);
	}

	public void setBoardColor(Color col){
		board = col;
	}

	public void setStone1Color(Color col){
		stone1 = col;
	}

	public void setStone2Color(Color col){
		stone2 = col;
	}

	public void setBackColor(Color col){
		back = col;
	}

	public void setTurnLogStringColor(Color col){
		turnLogString = col;
	}

	public void setTurnLogBackColor(Color col){
		turnLogBack = col;
	}

	public void setStatusStringColor(Color col){
		statusString = col;
	}

	public void setStatusBackColor(Color col){
		statusBack = col;
	}

	public void setButtonStringColor(Color col){
		buttonString = col;
	}

	public void setButtonBackColor(Color col){
		buttonBack = col;
	}

	public void setTurnStringColor(Color col){
		turnString = col;
	}

	public void setTurnBackColor(Color col){
		turnBack = col;
	}

	public void setMenuStringColor(Color col){
		menuString = col;
	}

	public void setMenuBackColor(Color col){
		menuBack = col;
	}


	public Color getBoardColor(){
		return board;
	}

	public Color getStone1Color(){
		return stone1;
	}

	public Color getStone2Color(){
		return stone2;
	}

	public Color getBackColor(){
		return back;
	}

	public Color getTurnLogStringColor(){
		return turnLogString;
	}

	public Color getTurnLogBackColor(){
		return turnLogBack;
	}

	public Color getStatusStringColor(){
		return statusString;
	}

	public Color getStatusBackColor(){
		return statusBack;
	}

	public Color getButtonStringColor(){
		return buttonString;
	}

	public Color getButtonBackColor(){
		return buttonBack;
	}

	public Color getTurnStringColor(){
		return turnString;
	}

	public Color getTurnBackColor(){
		return turnBack;
	}

	public Color getMenuStringColor(){
		return menuString;
	}

	public Color getMenuBackColor(){
		return menuBack;
	}


}
