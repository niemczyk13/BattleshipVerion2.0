package com.niemiec.logic;

import com.niemiec.objects.Coordinates;
import com.niemiec.objects.Player;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BorderManagement {
	
	private VBox myBorder;
	private VBox opponentBorder;

	public BorderManagement(VBox myBorder, VBox opponentBorder) {
		this.myBorder = myBorder;
		this.opponentBorder = opponentBorder;
	}

	public void startNewGameWithVirtualPlayer() {
		cleanBorders();
		myBorder.setDisable(false);
		opponentBorder.setDisable(true);
	}

	private void cleanBorders() {
		drawInBorderButton(myBorder.getChildren(), null);
		drawInBorderButton(opponentBorder.getChildren(), null);
	}
	
	public void drawInMyBorder(Player player) {
		drawInBorderButton(myBorder.getChildren(), player);
	}
	
	public void drawInOpponentBorder(Player player) {
		drawInBorderButton(opponentBorder.getChildren(), player);
	}
	
	private void drawInBorderButton(ObservableList<Node> obervableList, Player player) {
		for (int y = 0; y < 10; y++) {	
			HBox hbox = (HBox) obervableList.get(y);
			drawInBorderXAxis(y, hbox, player);		
		}
	}

	private void drawInBorderXAxis(int y, HBox hbox, Player player) {
		for (int x = 0; x < 10; x++) {
			Coordinates coordinates = new Coordinates(x + 1, y + 1);
			Button button = (Button) hbox.getChildren().get(x);
			drawInButton(button, player, coordinates);		
		}
	}

	private void drawInButton(Button button, Player player, Coordinates coordinates) {
		if (player == null) {
			button.setText("");
		} else {
			int box = player.getBoard().getBox(coordinates);
			button.setText((box != 0) ? Integer.toString(box) : "");
		}
	}

	public void setBordersToStartShot() {
		myBorder.setDisable(true);
		opponentBorder.setDisable(false);
	}

	public void realPlayerAddedShipsAutomatically() {
		myBorder.setDisable(true);
	}
	
}
