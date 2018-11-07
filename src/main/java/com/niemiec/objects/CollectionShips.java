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
			boardWithShipsIndex.setBox(ship.getBox(i), currentlyNumberOfShips);
		}
//		System.out.println("FUNKCJA COLLECTIONSHIPS, STATEK DODANO! currentlyNumberOfShips: " + currentlyNumberOfShips);
	}
	
	public void viewStatistic() {
		for (int i = 0; i < 10; i++) {
			Ship ship = ships[i];
			System.out.print("Statek " + ship.getNumberOfMasts() + ", posiada wsp�rz�dne: ");
			for (int j = 1; j <= ship.getNumberOfMasts(); j++) {
				System.out.print("x" + j + ": " + ship.getBox(j).getX() + ", y" + j + ": " + ship.getBox(j).getY() +"; ");
			}
			System.out.println("");	
		}
		boardWithShipsIndex.viewBoard();
	}

	public boolean checkShip(Box box) {
		int index = boardWithShipsIndex.getBox(box);
		Ship ship = ships[index - 1];
		ship.increaseCurrentNumberOfHitMasts();
		if (ship.getCurrentNumberOfHitMasts() == ship.getNumberOfMasts()) {
			ship.setSunkTrue();
			return true;
		}
		return false;
	}

	public Ship getShip(Box box) {
		int index = boardWithShipsIndex.getBox(box);
		return ships[index - 1];
	}
	
	public Ship getShip(int index) {
		return ships[index];
	}
}
