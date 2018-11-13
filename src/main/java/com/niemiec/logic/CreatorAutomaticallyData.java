package com.niemiec.logic;

import java.util.Random;

import com.niemiec.objects.Coordinates;
import com.niemiec.objects.Ship;

public class CreatorAutomaticallyData {
	private Random random;
	private final int firstMast = 1;
	private final int directionY = 0;
	private final int directionX = 1;
	
	private final int rightAndDown = 0;
	private final int leftAndTop = 1;

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
			
		if (direction == directionY) {
			coordinates = followWhenDirectionItIsY(side, x, y);
		} else if (direction == directionX) {
			coordinates = followWhenDirectionItIsX(side, x, y);
		}

		return coordinates;
	}	

	private Coordinates followWhenDirectionItIsY(int side, int x, int y) {
		Coordinates coordinates = new Coordinates();
		coordinates.setX(x);
		if (side == leftAndTop && CheckData.checkIfWithinThePlayingField(new Coordinates(x, y - 1)))
			coordinates.setY(y - 1);
		else if (side == rightAndDown && CheckData.checkIfWithinThePlayingField(new Coordinates(x, y + 1)))
			coordinates.setY(y + 1);
		
		return coordinates;
	}
	
	private Coordinates followWhenDirectionItIsX(int side, int x, int y) {
		Coordinates coordinates = new Coordinates();
		coordinates.setY(y);
		if (side == leftAndTop && CheckData.checkIfWithinThePlayingField(new Coordinates(x - 1, y)))
			coordinates.setX(x - 1);
		else if (side == rightAndDown && CheckData.checkIfWithinThePlayingField(new Coordinates(x + 1, y)))
			coordinates.setX(x + 1);
		
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
