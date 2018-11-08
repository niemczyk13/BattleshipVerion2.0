package com.niemiec.objects;

//Zarz�dzanie tablic� statk�w
public class CollectionShips {
	Ship[] ships;
	int currentlyNumberOfShips;
	//w tej tablicy przechwywane b�d� indeksy statk�w - numer w tablicy ships
	//z tablicy boardWithShipsIndex pobieramy indeks i od razu dokonujemy zmiany w konkretnym statku
	//bo znamy jego numer w tablicy
	Board boardWithShipsIndex;

	public CollectionShips() {
		this.ships = new Ship[10];
		this.currentlyNumberOfShips = 0;
		this.boardWithShipsIndex = new Board();
	}
	
	public void addShip(Ship ship) {
		ships[currentlyNumberOfShips++] = ship;
		for (int i = 1; i <= ship.getNumberOfMasts(); i++) {
			boardWithShipsIndex.setBox(ship.getCoordinates(i), currentlyNumberOfShips);
		}
//		System.out.println("FUNKCJA COLLECTIONSHIPS, STATEK DODANO! currentlyNumberOfShips: " + currentlyNumberOfShips);
	}
	
	public void viewStatistic() {
		for (int i = 0; i < 10; i++) {
			Ship ship = ships[i];
			System.out.print("Statek " + ship.getNumberOfMasts() + ", posiada wsp�rz�dne: ");
			for (int j = 1; j <= ship.getNumberOfMasts(); j++) {
				System.out.print("x" + j + ": " + ship.getCoordinates(j).getX() + ", y" + j + ": " + ship.getCoordinates(j).getY() +"; ");
			}
			System.out.println("");	
		}
		boardWithShipsIndex.viewBoard();
	}

	public boolean shipIsSunk(Coordinates box) {
		Ship ship = ships[getShipIndexFromBoard(box) - 1];
		return ship.getSunk();
	}
	
	public void shipWasHit(Coordinates box) {
		Ship ship = ships[getShipIndexFromBoard(box) - 1];
		ship.hit();
	}
	
	private int getShipIndexFromBoard(Coordinates box) {
		return boardWithShipsIndex.getBox(box);
	}

	public Ship getShip(Coordinates box) {
		int index = boardWithShipsIndex.getBox(box);
		return ships[index - 1];
	}
	
	public Ship getShip(int index) {
		return ships[index];
	}
}
