package io.github.glaz_egy;

public interface BotTemplate {
	ReversiCell nextPos(ReversiCell[][] cell);
	String description();
}
