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
		this.addShips = new AddShips(borderManagement, realPlayer, virtualPlayer);
	}

	public void startNewGameWithVirtualPlayer() {
		createPlayers();
		borderManagement.startNewGameWithVirtualPlayer();
		addShips.addShips();
	}

	private void createPlayers() {
		realPlayer = new PlayerImpl(Player.REAL_PLAYER);
		virtualPlayer = new PlayerImpl(Player.VIRTUAL_PLAYER);
	}

	public void addShips(ActionEvent event) {
		if (addShips.addShips(Player.REAL_PLAYER, event)) {
			borderManagement.setBordersToStartShot();
		}
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
