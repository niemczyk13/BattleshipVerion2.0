package com.niemiec.data.check;

import com.niemiec.objects.Board;
import com.niemiec.objects.Coordinates;

public class CheckShotData extends CheckData {
	
	private static Board board;
	
	public static boolean checkIsThereAPlaceWhenShot(Coordinates coordinates, int minimalNumberOfMasts) {
		Coordinates c;
		for (int i = 1; i <= minimalNumberOfMasts; i++) {
			for (int changeXY = (-minimalNumberOfMasts) + i; changeXY <= 0; changeXY++) {
				c = new Coordinates(coordinates, changeXY, 0);
				if (checkTheBoxesNextX(c, minimalNumberOfMasts))
					return true;

				c = new Coordinates(coordinates, 0, changeXY);
				if (checkTheBoxesNextY(c, minimalNumberOfMasts))
					return true;
			}
		}
		return false;
	}

	public static void setVariablesToCheckShotData(Board board) {
		CheckShotData.board = board;
		board.viewBoard();
	}

	private static boolean checkTheBoxesNextX(Coordinates coordinates, int minimalNumberOfMasts) {
		int counter = 0;
		Coordinates c;

		for (int k = 0; k < minimalNumberOfMasts; k++) {
			c = new Coordinates(coordinates, k, 0);
			if (c.checkIfWithinThePlayingField() && checkIfAroundOneIsEmpty(c)
					&& checkIfBoxIsEmpty(coordinates)) {
				counter++;
			} else {
				return false;
			}
		}
		// TODO
		if (counter == minimalNumberOfMasts)
			return true;
		return false;
	}


//
	private static boolean checkTheBoxesNextY(Coordinates coordinates, int minimalNumberOfMasts) {
		int counter = 0;
		Coordinates c;

		for (int k = 0; k < minimalNumberOfMasts; k++) {
			c = new Coordinates(coordinates, 0, k);
			if (checkIfWithinThePlayingField(c) && checkIfAroundOneIsEmpty(c)
					&& checkIfBoxIsEmpty(coordinates)) {
				counter++;
			} else {
				return false;
			}
		}
		// TODO
		if (counter == minimalNumberOfMasts)
			return true;
		return false;
	}
	
}
