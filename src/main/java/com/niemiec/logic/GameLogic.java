package com.niemiec.logic;

import com.niemiec.objects.Player;
import com.niemiec.objects.PlayerImpl;

import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;

//przechowuje cały stan o aktualej grze
//gdy zaczynamy grę na nowo to MainScreenController tworzy nowy obiekt
public class GameLogic {
	private BorderManagement borderManagement;
	private boolean automaticallySpacingOfShips;
	private Player realPlayer;
	private Player virtualPlayer;
	private AddShips addShips;

	public GameLogic(VBox myBorder, VBox opponentBorder) {
		this.borderManagement = new BorderManagement(myBorder, opponentBorder);
		this.automaticallySpacingOfShips = false;
		this.addShips = new AddShips();
	}

	public void startNewGameWithVirtualPlayer() {
		managePlayers();
		borderManagement.startNewGameWithVirtualPlayer();
		addShips.addShipsAutomatically(Player.VIRTUAL_PLAYER);
		borderManagement.drawInOpponentBorder(virtualPlayer);
		if (automaticallySpacingOfShips) {
			addShips.addShipsAutomatically(Player.REAL_PLAYER);
			borderManagement.realPlayerAddedShipsAutomatically();
		}
	}
	
	private void managePlayers() {
		createPlayers();
		addShips.addPlayers(realPlayer, virtualPlayer);
	}

	private void createPlayers() {
		realPlayer = new PlayerImpl(Player.REAL_PLAYER);
		virtualPlayer = new PlayerImpl(Player.VIRTUAL_PLAYER);
	}

	public void addShips(ActionEvent event) {
		if (addShips.addShipsManually(Player.REAL_PLAYER, event)) {
			borderManagement.setBordersToStartShot();
		}
		borderManagement.drawInMyBorder(realPlayer);
	}

	public boolean shot(ActionEvent source) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setAutomaticallySpacingOfShips(boolean b) {
		this.automaticallySpacingOfShips = b;
	}

	public boolean getAutomaticallySpacingOfShips() {
		return automaticallySpacingOfShips;
	}
}
