package com.niemiec.data.create.automatically;

import com.niemiec.data.check.CheckData;
import com.niemiec.data.check.CheckShotData;
import com.niemiec.objects.Board;
import com.niemiec.objects.Coordinates;
import com.niemiec.objects.Player;
import com.niemiec.objects.PlayerImpl;

public class CreatorAutomaticallyShotData extends CreatorAutomaticallyData {
	
	private PlayerImpl players[];
//	private Board board;
	
	
	
	public CreatorAutomaticallyShotData(PlayerImpl[] players) {
		super();
		this.players = players;
	}

	public Coordinates downloadShotFromVirtualPlayer(int activePlayer) {
		CheckData.setVariablesToCheckData(players[activePlayer].getOpponentBoard());
		if (players[activePlayer].isOnHit()) {
			return nextMoveVirtualPlayer(players, activePlayer);
		}
		return firstMoveVirtualPlayer(activePlayer);
	}

	private Coordinates nextMoveVirtualPlayer(PlayerImpl[] players2, int activePlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	private Coordinates firstMoveVirtualPlayer(int activePlayer) {
		Coordinates coordinates = null;
		while (true) {
			coordinates = randomTheFirstMast();
			int box = getBoxFromOpponentBoard(coordinates, activePlayer);
			if (box == Board.BOX_EMPTY && willTheShipFit(coordinates, activePlayer))
				return coordinates;
		}
	}

	private boolean willTheShipFit(Coordinates coordinates, int activePlayer) {
		int opponentPlayer = getIndexOpponentPlayer(activePlayer);
		int minimalNumberOfMasts = getMinimalNmberOfMastsFromNotHitShips(opponentPlayer);
		System.out.println(minimalNumberOfMasts);
		
		return CheckShotData.checkIsThereAPlaceWhenShot(coordinates, minimalNumberOfMasts);
	}

	private int getMinimalNmberOfMastsFromNotHitShips(int opponentPlayer) {
		return players[opponentPlayer].getCollectionShips().getMinimalNumberMastsOfNoHitShips();
	}

	private int getIndexOpponentPlayer(int activePlayer) {
		return (activePlayer == Player.REAL_PLAYER) ? Player.VIRTUAL_PLAYER : Player.REAL_PLAYER;
	}

	private int getBoxFromPlayerBoard(Coordinates coordinates, int typeOfPlayer) {
		return players[typeOfPlayer].getBoard().getBox(coordinates);
	}

	private int getBoxFromOpponentBoard(Coordinates coordinates, int typeOfPlayer) {
		return players[typeOfPlayer].getOpponentBoard().getBox(coordinates);
	}
}
