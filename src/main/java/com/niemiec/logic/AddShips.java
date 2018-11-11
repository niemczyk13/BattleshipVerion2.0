package com.niemiec.logic;

import com.niemiec.objects.Coordinates;
import com.niemiec.objects.Player;
import com.niemiec.objects.Ship;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class AddShips {
	
	private BorderManagement borderManagement;
	private Player realPlayer;
	private Player virtualPlayer;
	private int actualPlayer;
	
	int[] quantityShipsAndMasts;
	int currentQuantityShipsOfGivenType;
	int currentShip;
	int currentMast;
	Ship ship;

	public AddShips(BorderManagement borderManagement, Player realPlayer, Player virtualPlayer) {
		this.borderManagement = borderManagement;
		this.realPlayer = realPlayer;
		this.virtualPlayer = virtualPlayer;
		setTheInitialConditions();
	}

	public boolean addShips(int typeOfPlayer, ActionEvent event) {
		setTheInitionalConditionsBeforeVirtualPlayerAddedShips(typeOfPlayer);
		addNewMast(getCoordinatesFromButton((Button) event.getSource()));
		if (conditionOfTheEndOfAddingShips())
			return true;
		return false;
	}

	public boolean addShips() {
		while (!conditionOfTheEndOfAddingShips()) {
			//addNewMast(WylosowaneCoordinates)
//			return true;
		}
		
		return true;
	}
	
	private void addNewMast(Coordinates coordinates) {
		
	}

	private void setTheInitionalConditionsBeforeVirtualPlayerAddedShips(int typeOfPlayer) {
		if (actualPlayer != typeOfPlayer) {
			setTheInitialConditions();
			actualPlayer = typeOfPlayer;
		}
	}

	private boolean conditionOfTheEndOfAddingShips() {
		if (currentQuantityShipsOfGivenType == quantityShipsAndMasts.length && currentShip == 0)
			return true;
		return false;
	}
	
	private void setTheInitialConditions() {
		this.quantityShipsAndMasts = new int[] { 4, 3, 2, 1 };
		this.currentQuantityShipsOfGivenType = 0;
		updateCurrentShip();
		this.ship = new Ship(quantityShipsAndMasts[currentQuantityShipsOfGivenType]);
		this.currentMast = 0;
		this.actualPlayer = Player.VIRTUAL_PLAYER;
	}
	
	private void updateCurrentShip() {
		currentShip = quantityShipsAndMasts[quantityShipsAndMasts.length - (currentQuantityShipsOfGivenType + 1)];
	}

	private Coordinates getCoordinatesFromButton(Button button) {
		String id = button.getId();
		char[] chars = new char[2];
		id.getChars(2, 4, chars, 0);
		return new Coordinates(Character.getNumericValue(chars[0]) + 1, Character.getNumericValue(chars[1]) + 1);
	}
}
