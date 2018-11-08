package com.niemiec.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ship {
	public static final int SHIP_WAY_NO_SPACE = 0;
	public static final int SHIP_WAY_X = 1;
	public static final int SHIP_WAY_Y = 2;
	public static final int SHIP_WAY_XY = 3;
	
	private List<Integer> pointOnTheAxisX;
	private List<Integer> pointOnTheAxisY;

	// private int x[];
	// private int y[];
	private int numberOfMasts; // maksymalna ilo�� maszt�w
	private int currentNumberOfMasts; // aktualna ilo�� zbudowanych maszt�w
	private int currentNumberOfHitMasts; // aktualna ilo�� trafionych maszt�w
	private int way; // 0 - brak, 1 - wzd�� x, 2 - wzd�� y, 3 - w obie strony
	private boolean sunk; // true, gdy zatopiony

	public Ship(int numberOfMasts) {
		this.pointOnTheAxisX = new ArrayList<Integer>();
		this.pointOnTheAxisY = new ArrayList<Integer>();

		// this.x = new int[numberOfMasts];
		// this.y = new int[numberOfMasts];
		this.currentNumberOfMasts = 0;
		this.currentNumberOfHitMasts = 0;
		this.numberOfMasts = numberOfMasts;
		this.way = 0;
		this.sunk = false;
	}

	public Coordinates getCoordinates(int numberOfMasts) {
		return new Coordinates(pointOnTheAxisX.get(numberOfMasts -1),  pointOnTheAxisY.get(numberOfMasts -1));
	}

	public void setMast(Coordinates box, int currentNumberOfMasts) {
		this.pointOnTheAxisX.add(box.getX());
		this.pointOnTheAxisY.add(box.getY());

		// this.x[currentNumberOfMasts - 1] = x;
		// this.y[currentNumberOfMasts - 1] = y;
		this.currentNumberOfMasts++;

		if (this.currentNumberOfMasts > 1)
			sortMasts();
	}

	public boolean getSunk() {
		return this.sunk;
	}

	public void setSunkTrue() {
		this.sunk = true;
	}

	public int getNumberOfMasts() {
		return this.numberOfMasts;
	}

	public int getWay() {
		return this.way;
	}

	public void setWay(int way) {
		this.way = way;
	}

	public int getCurrentNumberOfMasts() {
		return currentNumberOfMasts;
	}

	public void setCurrentNumberOfMasts(int currentNumberOfMasts) {
		this.currentNumberOfMasts = currentNumberOfMasts;
	}

	public void sortMasts() {
		if ((pointOnTheAxisX.get(1) - pointOnTheAxisX.get(0)) == 0)
			Collections.sort(pointOnTheAxisY);
		else if ((pointOnTheAxisY.get(1) - pointOnTheAxisY.get(0)) == 0)
			Collections.sort(pointOnTheAxisX);
	}

	public int getCurrentNumberOfHitMasts() {
		return currentNumberOfHitMasts;
	}
	
	public void increaseCurrentNumberOfHitMasts() {
		this.currentNumberOfHitMasts++;
	}
}
