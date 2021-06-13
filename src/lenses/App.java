package lenses;

import java.util.List;
import java.util.Scanner;

import lenses.dao.LensDao;
import lenses.entity.Lens;

public class App {
	private Scanner scanner = new Scanner(System.in);
	private LensDao lensDao = new LensDao();

	public static void main(String[] args) {
		new App().runMenu();
	}

	private void runMenu() {
		boolean done = false;
		
		while(!done) {
			printMenu();
			try {
				int number = readIntInput("Input a menu number");
				
				switch(number) {
					case 1:
						listLenses();
						break;
						
					case 2:
						createLens();
						break;
						
					case 3:
						updateLens();
						break;
						
					case 4:
						deleteLens();
						break;
						
					case 5:
						System.out.println("Till next time!");
						done = true;
						break;
						
					default:
						System.out.println(number + " isn't valid. Please enter a proper number:");
						System.out.println();
						break;
				}
			} catch(Exception e) {
				System.out.println("Oh heck, exception: " + e.toString());
				done = true;
			}
		}
		
	}

	private void deleteLens() {
		System.out.println("You selected to delete a lens :(");
		int lensId = readIntInput("Enter the lens number");
		
		lensDao.deleteLens(lensId);
		System.out.println("You deleted lens with number " + lensId);
	}

	private void updateLens() {
		System.out.println("You selected to update a lens");
		int lensId = readIntInput("Enter the lenses number");
		String nickname = readStringInput("Enter the new nickname");
		
		lensDao.updateLensNickname(lensId, nickname);
		System.out.println("Lens nickname changed to " + nickname);
	}

	private void createLens() {
		System.out.println("You selected to create a lens");
		String nickname = readStringInput("Enter the lenses nickname");	
		
		lensDao.createLens(nickname);
		System.out.println("Congrats on creating the " + nickname);
	}

	private String readStringInput(String message) {
		System.out.println();
		System.out.print(message + ": ");
		return scanner.nextLine();
	}

	private void listLenses() {
		System.out.println("You selected to list all your lenses");
		List<Lens> lenses = lensDao.findLenses();
		
		System.out.println();
		System.out.println("Here are all the lenses:");
		
		if(lenses.isEmpty()) {
			System.out.println("There is nothing here / cricket noises /");
		} else {
			for (Lens lens : lenses) {
				System.out.println("    " + lens.getLensId() + ": " + lens.getNickname());
			}
		}
	}

	private void printMenu() {
		System.out.println();
		System.out.println("1. List all the lenses");
		System.out.println("2. Create a lens");
		System.out.println("3. Update a lens");
		System.out.println("4. Delete a lens");
		System.out.println("5. Exit the app");
	}

	private int readIntInput(String message) {
		try {
			System.out.println();
			System.out.print(message + ": ");
			return Integer.parseInt(scanner.nextLine());
		} catch(Exception e) {
			System.out.println("One or more errors occurred: " + e.toString() + " ... Also Hello!");
			return 0;
		}
	}
}