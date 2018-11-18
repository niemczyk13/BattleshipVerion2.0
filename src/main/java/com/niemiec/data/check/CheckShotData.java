package com.niemiec.data.check;

import com.niemiec.objects.Coordinates;

public class CheckShotData extends CheckData {
	public static boolean checkIsThereAPlaceWhenShot(Coordinates coordinates, int minimalNumberOfMasts) {
//		Coordinates c;
//		for (int i = 1; i <= minimalNumberOfMasts; i++) {
//			for (int changeXY = (-minimalNumberOfMasts) + i; changeXY <= 0; changeXY++) {
//				c = new Coordinates(coordinates, changeXY, 0);
//				if (checkTheBoxesNextX(c, minimalNumberOfMasts))
//					return true;
//
//				c = new Coordinates(coordinates, 0, changeXY);
//				if (checkTheBoxesNextY(c, minimalNumberOfMasts))
//					return true;
//			}
//		}
//		return false;
		return true;
	}

	private static boolean checkTheBoxesNextX(Coordinates coordinates, int minimalNumberOfMasts) {
		int counter = 0;
		Coordinates c;

		for (int k = 0; k < minimalNumberOfMasts; k++) {
			c = new Coordinates(coordinates, k, 0);
			// TODO sprawdzić dokładnie checkIfAroundOneIsEmpty, czy działa zgodnie z
			// potrzebami tej funkcji
			if (c.checkIfWithinThePlayingField() && checkIfAroundOneIsEmptyWhenShot(c)
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

	private static boolean checkTheBoxesNextY(Coordinates coordinates, int minimalNumberOfMasts) {
		int counter = 0;
		Coordinates c;

		for (int k = 0; k < minimalNumberOfMasts; k++) {
			c = new Coordinates(coordinates, 0, k);
			// TODO sprawdzić dokładnie checkIfAroundOneIsEmpty, czy działa zgodnie z
			// potrzebami tej funkcji
			if (checkIfWithinThePlayingField(c) && checkIfAroundOneIsEmptyWhenShot(c)
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
