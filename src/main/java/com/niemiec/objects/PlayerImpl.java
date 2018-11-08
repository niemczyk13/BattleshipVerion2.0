package com.niemiec.objects;

import com.niemiec.logic.ShipsCreator;

public class PlayerImpl implements Player {
	private Board board;
	private Board opponentBoard;
	private int sunkenShips;
	private CollectionShips collectionShips;
	private int typeOflPlayer;

	public PlayerImpl(int typeOfPlayer) {
		this.board = new Board();
		this.opponentBoard = new Board();
		this.collectionShips = new CollectionShips();
		this.typeOflPlayer = typeOfPlayer;
		this.sunkenShips = 0;
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Board getOpponentBoard() {
		return opponentBoard;
	}

	@Override
	public int getSunkenShips() {
		return sunkenShips;
	}

	@Override
	public void increaseSunkenShips() {
		sunkenShips++;
	}

	@Override
	public CollectionShips getCollectionShips() {
		return collectionShips;
	}

	@Override
	public void addShips() {
		ShipsCreator shipsCreator = new ShipsCreator(isVirtualPlayer(), collectionShips);
		shipsCreator.addShips(isVirtualPlayer(), collectionShips);
	}

	@Override
	public boolean isVirtualPlayer() {
		return (typeOflPlayer == Player.VIRTUAL_PLAYER) ? true : false;
	}

}
