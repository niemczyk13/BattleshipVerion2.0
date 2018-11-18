package com.niemiec.data.create.automatically;

import com.niemiec.data.check.CheckData;
import com.niemiec.data.check.CheckShotData;
import com.niemiec.objects.Board;
import com.niemiec.objects.Coordinates;
import com.niemiec.objects.Player;
import com.niemiec.objects.PlayerImpl;

public class CreatorAutomaticallyShotData extends CreatorAutomaticallyData {
	private final int directionX = 0;
	private final int directionY = 1;
	private PlayerImpl players[];
//	private Board board;
	
	
	
	public CreatorAutomaticallyShotData(PlayerImpl[] players) {
		super();
		this.players = players;
	}

	public Coordinates downloadShotFromVirtualPlayer(int activePlayer) {
//		CheckShotData.setVariablesToCheckShotData(players[activePlayer].getOpponentBoard());
		CheckData.setVariablesToCheckData(players[activePlayer].getOpponentBoard());
		if (players[activePlayer].isOnHit()) {
			return nextMoveVirtualPlayer(activePlayer);
		}
		return firstMoveVirtualPlayer(activePlayer);
	}

	private Coordinates nextMoveVirtualPlayer(int activePlayer) {
		PlayerImpl vp = players[activePlayer];
		// TODO dokończyć
//		if (vp.getDirectionOnHit() == )
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
