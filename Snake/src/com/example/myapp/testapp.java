package com.example.myapp;

import javax.swing.*;
public class testapp {
	public static void main(String[] args) throws Exception {
	int boardWidth = 600;
	int boardHeight = boardWidth;
	
	JFrame frame = new JFrame("Snake");
	frame.setVisible(true);
	frame.setSize(boardWidth, boardHeight);
	frame.setLocationRelativeTo(null);
	frame.setResizable(false);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	Game game = new Game(boardWidth, boardHeight);
	// Add the game panel to the JFrame
    frame.add(game);
    
    // Pack the frame (optional, ensures the frame fits the preferred size of its components)
    frame.pack();
    
    // Make the frame visible after all components are added
    frame.setVisible(true);
    game.requestFocus();
}
}