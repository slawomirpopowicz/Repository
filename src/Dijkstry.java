import java.util.Scanner;

public class Dijkstry {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int[][] coordinates = new int[5][3];
		for (int i = 0; i < 5; i++) {
			System.out.printf("Podaj wspó³rzêdn¹ x punktu nr " + (i + 1) + ". " + ((char) (65 + i)) + ": ");
			Scanner odczyt = new Scanner(System.in);
			coordinates[i][0] = odczyt.nextInt();
			System.out.printf("Podaj wspó³rzêdn¹ y punktu nr " + (i + 1) + ". " + ((char) (65 + i)) + ": ");
			coordinates[i][1] = odczyt.nextInt();
			coordinates[i][2] = 65 + i;
		}

		// tablica robocza
		int[][] coordinates_kopia = new int[5][3];
		for (int i = 0; i < 5; i++) 
		{
			coordinates_kopia[i][0] = coordinates[i][0];
			coordinates_kopia[i][1] = coordinates[i][1];
			coordinates_kopia[i][2] = coordinates[i][2];
		}

		// punkt startowy
		System.out.printf("Wybierz punkt startowy: ");
		Scanner odczyt = new Scanner(System.in);
		int startowy = odczyt.nextInt() - 1;
		System.out.println("Punkt startowy to: " + ((char) (65 + startowy)) + ": "
				+ coordinates[startowy][0] + "," + coordinates[startowy][1]);

		/* nadpisanie punktu startowego w tablicy roboczej przez zero oraz
		 wpisanie punktu statowego w tablice*/
		char kolejnosc[] = new char[6];
		coordinates_kopia[startowy][0] = 0;
		coordinates_kopia[startowy][1] = 0;
		coordinates_kopia[startowy][2] = 0;
		kolejnosc[0] = (char) (startowy + 65);
		kolejnosc[5] = (char) (startowy + 65);

		// liczenie odleglosci miedzy punktami
		int aktualny_punkt= 0;
		int minimalna_odl = 0;
		double suma_odl = 0;
		double min_odl = 0;
		double tablica_odleglosci[][] = new double[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == 0) {
					aktualny_punkt= startowy;
				} else
					aktualny_punkt= minimalna_odl;
				kolejnosc[i] = (char) (aktualny_punkt+ 65);
				if (coordinates_kopia[j][2] != 0) {
					double odl = Math.sqrt(Math.pow((coordinates[aktualny_punkt][0]) - (coordinates_kopia[j][0]), 2)
							+ Math.pow((coordinates[aktualny_punkt][1]) - (coordinates_kopia[j][1]), 2));
					tablica_odleglosci[i][j] = odl;
				}

			}
			/* algorytm wyszukuje w tabeli odleglosci pierwszej wartosci która nie jest zerem i przerywa
			petle*/
			for (int k = 0; k < 5; k++) {
				if (tablica_odleglosci[i][k] != 0) {
					min_odl = tablica_odleglosci[i][k];
					break;
				}

			}
			// przyrownuje powyzsza wartosc i szukam minimum
			for (int k = 0; k < 5; k++) {
				if (min_odl >= tablica_odleglosci[i][k] && tablica_odleglosci[i][k] != 0) {
					min_odl = tablica_odleglosci[i][k];
					minimalna_odl = k;

				}
			}
			if (i != 4)
				suma_odl = suma_odl + min_odl;
			else {
				double odl = Math.sqrt(Math.pow((coordinates[aktualny_punkt][0]) - (coordinates[startowy][0]), 2)
						+ Math.pow((coordinates[aktualny_punkt][1]) - (coordinates[startowy][1]), 2));
				tablica_odleglosci[i][4] = odl;
				suma_odl = suma_odl + odl;
			}
			coordinates_kopia[minimalna_odl][0] = 0;
			coordinates_kopia[minimalna_odl][1] = 0;
			coordinates_kopia[minimalna_odl][2] = 0;
		}
				
		//Wyœwietla kolejnoœæ przejœcia oraz ³¹czna d³ugoœæ drogi
		System.out.println("£¹czna d³ugoœæ przebytej drogi wynosi: " + suma_odl);
		System.out.print("Kolejnoœæ drogi po punktach: ");
		for (int i = 0; i < 6; i++) {
			System.out.print( kolejnosc[i]+ "=>");
		}
	}
}
