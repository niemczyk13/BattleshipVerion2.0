package com.niemiec.logic;

import com.niemiec.objects.Board;
import com.niemiec.objects.Coordinates;
import com.niemiec.objects.Ship;

public class CheckData {
	private static final int checkWayX = 1;
	private static final int checkWayY = 2;
	private static Board board;
	private static Ship ship;
	private static int sumCheckWay;

	public static boolean checkIfBoxIsEmpty(Coordinates coordinates) {
		if (board.getBox(coordinates) == Board.BOX_EMPTY)
			return true;
		return false;
	}

	public static boolean checkIfAroundOneIsEmpty(Coordinates coordinates) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (itsEmptyNextTo(coordinates, i, j))
					return false;
			}
		}
		return true;
	}

	public static boolean checkIsThereAPlace(Coordinates coordinates) {
		if (shipNotOneMastedAndWithoutWay()) {
			return checkPlaceStart(coordinates);
		} else {
			return true;
		}
		
	}

	private static boolean shipNotOneMastedAndWithoutWay() {
		return (ship.getNumberOfMasts() != 1 && ship.getWay() == Ship.SHIP_WAY_NO_SPACE);
	}
	
	private static boolean checkPlaceStart(Coordinates coordinates) {
		sumCheckWay = 0;
		loopToCheckThePlace(coordinates, CheckData.checkWayX);
		loopToCheckThePlace(coordinates, CheckData.checkWayY);
		return checkWay();
	}

	private static void loopToCheckThePlace(Coordinates coordinates, int checkNextXorY) {
		for (int i = 1; i <= ship.getNumberOfMasts(); i++) {
			if (startCheckingFromTheExtremePoint(coordinates, checkNextXorY, i))
				break;
		}
	}

	private static boolean startCheckingFromTheExtremePoint(Coordinates coordinates, int checkNextXorY, int i) {
		int extremePoint = (-ship.getNumberOfMasts()) + i;
		for (; extremePoint <= 0; extremePoint++) {
			if (checkWillTheShipFitFromExtremePoint(coordinates, checkNextXorY, extremePoint)) {
				sumCheckWay += checkNextXorY;
				return true;
			}
		}

		return false;
	}

	private static boolean checkWillTheShipFitFromExtremePoint(Coordinates coordinates, int checkNextXorY, int extremePoint) {
		int counterMasts = 0;
		for (int k = 0; k < ship.getNumberOfMasts(); k++) {
			int mast = k + extremePoint;
			if (checkCanYouPutAMast(coordinates, checkNextXorY, mast))
				counterMasts++;	
			else
				break;
		}
		return counterMasts == ship.getNumberOfMasts();
	}

	private static boolean checkCanYouPutAMast(Coordinates coordinates, int checkNextXorY, int shiftOnTheAxis) {
		int addedToX = 0;
		int addedToY = 0;

		if (checkNextXorY == CheckData.checkWayX) {
			addedToX = shiftOnTheAxis;
		} else {
			addedToY = shiftOnTheAxis;
		}

		Coordinates toCheck = new Coordinates(coordinates.getX() + addedToX, coordinates.getY() + addedToY);
		return checkTheBoxesNextIsGood(toCheck);
	}
	
	private static boolean checkTheBoxesNextIsGood(Coordinates coordinates) {
		return (checkIfWithinThePlayingField(coordinates) && checkIfAroundOneIsEmpty(coordinates)
				&& checkIfTheFieldIsEquals(coordinates, Board.BOX_EMPTY));
	}

	private static boolean checkWay() {
		switch (sumCheckWay) {
		case 0:
			ship.setWay(Ship.SHIP_WAY_NO_SPACE);
			return false;
		case 1:
			ship.setWay(Ship.SHIP_WAY_X);
			return true;
		case 2:
			ship.setWay(Ship.SHIP_WAY_Y);
			return true;
		case 3:
			ship.setWay(Ship.SHIP_WAY_XY);
			return true;
		default:
			return false;
		}
	}

	private static boolean itsEmptyNextTo(Coordinates coordinates, int i, int j) {
		if (!ijEqualsZero(i, j) && (checkIfWithinThePlayingField(coordinates, i, j) && itsEqualsArgNextTo(coordinates, i, j, Board.BOX_SHIP)))
			return true;
		else
			return false;
	}

	private static boolean ijEqualsZero(int i, int j) {
		return (i == 0 && j == 0);
	}

	private static boolean itsEqualsArgNextTo(Coordinates coordinates, int i, int j, int boxForCheck) {
		Coordinates c = new Coordinates(coordinates.getX() + i, coordinates.getY() + j);
		return checkIfTheFieldIsEquals(c, boxForCheck);
	}

	private static boolean checkIfTheFieldIsEquals(Coordinates coordinates, int boxForCheck) {
		return (board.getBox(coordinates) == boxForCheck);
	}
	
	private static boolean checkIfWithinThePlayingField(Coordinates coordinates) {
		int x = coordinates.getX();
		int y = coordinates.getY();

		if (x >= 1 && x <= 10 && y >= 1 && y <= 10) {
			return true;
		}
		return false;
	}

	private static boolean checkIfWithinThePlayingField(Coordinates coordinates, int addedToX, int addedToY) {
		int xPlus = coordinates.getX() + addedToX;
		int yPlus = coordinates.getY() + addedToY;

		if (xPlus >= 1 && xPlus <= 10 && yPlus >= 1 && yPlus <= 10) {
			return true;
		}
		return false;
	}

	public static void setVariablesToCheckData(Board board, Ship ship) {
		CheckData.board = board;
		CheckData.ship = ship;
	}
}
