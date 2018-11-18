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
	private PlayerImpl realPlayer;
	private PlayerImpl virtualPlayer;
	private AddShips addShips;
	private ShotShip shotShip;

	public GameLogic(VBox myBorder, VBox opponentBorder) {
		this.borderManagement = new BorderManagement(myBorder, opponentBorder);
		this.automaticallySpacingOfShips = false;
		
	}

	public void startNewGameWithVirtualPlayer() {
		this.addShips = new AddShips();
		this.shotShip = new ShotShip();
		createPlayers();
		addShips.addPlayers(realPlayer, virtualPlayer);
		borderManagement.startNewGameWithVirtualPlayer();
		shotShip.setInitialData(borderManagement, realPlayer, virtualPlayer);
		addShips.addShipsAutomatically(Player.VIRTUAL_PLAYER);
		borderManagement.drawBoardInOpponentBorder(virtualPlayer);
		if (automaticallySpacingOfShips) {
			addShips.addShipsAutomatically(Player.REAL_PLAYER);
			borderManagement.setBordersToStartShot();
			borderManagement.drawBoardInMyBorder(realPlayer);
		}
		shotShip.firstShotInTheGame();
	}

	private void createPlayers() {
		realPlayer = new PlayerImpl(Player.REAL_PLAYER);
		virtualPlayer = new PlayerImpl(Player.VIRTUAL_PLAYER);
	}

	public void addShips(ActionEvent event) {
		if (addShips.addShipsManually(Player.REAL_PLAYER, event)) {
			borderManagement.setBordersToStartShot();
		}
		borderManagement.drawBoardInMyBorder(realPlayer);
	}

	public void shot(ActionEvent event) {
		if (shotShip.shot(event)) {
			String winner = shotShip.getWinnerName();
			System.out.println("Wygrywa gracz: " + winner);
			borderManagement.setBordersToEndGame();
		}
	}

	public void setAutomaticallySpacingOfShips(boolean b) {
		this.automaticallySpacingOfShips = b;
	}

	public boolean getAutomaticallySpacingOfShips() {
		return automaticallySpacingOfShips;
	}
}
