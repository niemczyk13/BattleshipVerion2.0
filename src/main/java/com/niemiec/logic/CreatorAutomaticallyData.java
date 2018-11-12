package com.niemiec.logic;

import java.util.Random;

import com.niemiec.objects.Coordinates;
import com.niemiec.objects.Ship;

public class CreatorAutomaticallyData {
	private Random random;
	private final int firstMast = 1;
	private final int XValue = 0;
	private final int YValue = 1;

	public CreatorAutomaticallyData() {
		random = new Random();
	}

	public Coordinates downloadCoordinatesWhenAddShip(Ship ship, int currentMast) {
		Coordinates coordinates;

		while (true) {
			switch (currentMast) {
			case 0:
				coordinates = randomTheFirstMast();
				break;
			case 1:
				// TODO Konieczne dopracowanie metody
				coordinates = randomTheSecondMast(ship);
				break;
			default:
				coordinates = randomTheReamainingMasts(ship, currentMast);
				break;
			}

			if (CheckData.checkIfWithinThePlayingField(coordinates))
				return coordinates;
		}
	}

	private Coordinates randomTheReamainingMasts(Ship ship, int currentMast) {
		// TODO Auto-generated method stub
		return null;
	}

	private Coordinates randomTheSecondMast(Ship ship) {
		int direction = random.nextInt(2);
		return assignCoordinates(direction, ship);
	}

//	private Coordinates assignSide(int direction) {
//		int side = random.nextInt(2);
//		return assignCoordinates(direction, side);
//	}

	private Coordinates assignCoordinates(int direction, Ship ship) {
//		if (direction == 0) {
//			return findCoordinates(XValue, YValue, ship);
//		} else {
//			return findCoordinates(YValue, XValue, ship);
//		}
		int x = ship.getCoordinates(firstMast).getX();
		int y = ship.getCoordinates(firstMast).getY();
		Coordinates coordinates = new Coordinates();
		int side = random.nextInt(2);

		if (direction == 0) {
			coordinates.setX(x);
			if (side == 1 && (y - 1) > 0)
				coordinates.setY(y - 1);
			else if (side == 0 && (y + 1) <= 10)
				coordinates.setY(y + 1);
		} else if (direction == 1) {
			coordinates.setY(y);
			if (side == 1 && (x - 1) > 0)
				coordinates.setX(x - 1);
			else if (side == 0 && (x + 1) <= 10)
				coordinates.setX(x + 1);
		}

		return coordinates;
	}

//	private Coordinates findCoordinates(int constantValue, int variable, Ship ship) {
//		int x = ship.getCoordinates(firstMast).getX();
//		int y = ship.getCoordinates(firstMast).getY();
//		Coordinates coordinates = new Coordinates();
//		
//		int side = random.nextInt(2);
//		
//		if (side == 1 && (y - 1) > 0)
//			box[1] = y - 1;
//		else if (direction == 0 && (y + 1) <= 10)
//			box[1] = y + 1;
//	}

	private Coordinates randomTheFirstMast() {
		int x = random.nextInt(10) + 1;
		int y = random.nextInt(10) + 1;
		return new Coordinates(x, y);
	}

}
