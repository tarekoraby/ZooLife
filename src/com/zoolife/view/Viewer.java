/**
 *
 */
package com.zoolife.view;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import com.zoolife.model.Animal;
import com.zoolife.model.AnimalFriendship;
import com.zoolife.model.Zoo;

/**
 * The Viewer class
 *
 * @author Tarek Oraby
 * @version 1.0
 *
 */
public final class Viewer {

	public void displayAfterInput() {
		System.out.println();

	}

	public void displayAppIntro() {
		System.out.println("\n\n***** ZOOLIFE: A simulator of life in the zoo *****");
		System.out.println("Version: 1.0\n");
		System.out.println("--> Description:");
		System.out.println("This application simulates life among a number of zoo animals.");
		System.out.println("Each animal can have several friends among other animals from the zoo."
				+ "\nEvery day each animal randomly loses one friend (if there are any) and gets from zero to one new friend."
				+ "\nIf A is a friend of B then B is a friend of A automatically. At the beginning: nobody has friends.\n");
	}

	public void displayExitConfirmationMsg() {
		System.out.println("Are you sure you want to quit? [y/n]");

	}

	public void displayExitWarningMsg() {
		System.out.println("Any simulated zoo life will be lost forever ... ");

	}

	public void displayGoodbyeMsg() {
		System.out.println("\nZooLife is now Terminated. Adios!");

	}

	public void displayInvalidInputEnteredMsg(String userInput) {
		System.out.println("Sorry! I don't understand ... \n" + "You entered \"" + userInput
				+ "\", which is not a valid input.\n");

	}

	public void displayRepeatedString(String strToRepeat, int numRepetitions) {
		System.out.println(new String(new char[numRepetitions]).replace("\0", strToRepeat));

	}

	public void displaySimulationResults(Zoo zoo) {
		System.out.print("and its done!\n");
		displayRandomIntroMessage();

		Set<AnimalFriendship> dailyFriendsAdded = zoo.getDailyFriendsAdded();
		if ((dailyFriendsAdded.size() == 0) || (dailyFriendsAdded == null)) {
			System.out.println("\nThere were no new friendships today!");
		} else {
			System.out.println("\nThe following animals became friends:");
			for (AnimalFriendship newFriendship : dailyFriendsAdded) {
				Iterator<Animal> itr = newFriendship.getIterator();
				System.out.println("-> " + itr.next().getName() + " and " + itr.next().getName());
			}
		}

		Set<AnimalFriendship> dailyFriendsRemoved = zoo.getDailyFriendsRemoved();
		if (dailyFriendsRemoved.size() == 0) {
			System.out.println("\nAnd happily, no animal lost a friend toady!");
		} else {
			System.out.println("\nSadly, the following animals broke up:");
			for (AnimalFriendship lostFriendship : zoo.getDailyFriendsRemoved()) {
				Iterator<Animal> itr = lostFriendship.getIterator();
				System.out.println("-> " + itr.next().getName() + " and " + itr.next().getName());
			}
		}
	}

	public void displaySimulationStartingMessage(Zoo zoo) {
		displayRepeatedString("-", 60);
		System.out.print("\nSimulation of day no. " + (zoo.getDayCount() + 1) + " is now beginnig ... ");
	}

	public void displayVaildInputs() {
		System.out.println("--> Usage:");
		System.out.println("\t.D or d: \tDisplays all zoo animals with their properties and friends' names.");
		System.out.println("\t.S or s: \tSimulates one day in the zoo and displays what happened on that day");
		System.out.println("\t.Q or q: \tExits ZooLife\n");
	}

	public void displayZooProperties(TreeMap<String, TreeMap<String, String>> treeMap) {
		displayRepeatedString("-", 80);
		System.out.print("\nThe zoo has " + treeMap.size() + " animals:\n\n");

		for (Map.Entry<String, TreeMap<String, String>> entry : treeMap.entrySet()) {
			entry.getKey();
			TreeMap<String, String> animalEntryValue = entry.getValue();

			String animalName = animalEntryValue.get("name");
			String animalSpecies = animalEntryValue.get("species");
			String animalFavfood = animalEntryValue.get("favFood");
			String animalFriends = animalEntryValue.get("friends");
			int animalFriendsNumber = Integer.parseInt(animalEntryValue.get("friendsNumber"));

			System.out.print("-> " + animalName + " ");
			switch (animalSpecies) {
			case "dog":
				String dogType = animalEntryValue.get("dogType");
				System.out.print("is " + (checkIfBeginsWithVowel(dogType) ? "an" : "a") + " " + dogType.toLowerCase());
				System.out.print(" whose favorite food is " + animalFavfood.toLowerCase() + ".\n");
				System.out.print(animalName + " ");
				displayAnimalsFriendships(animalFriendsNumber, animalFriends);
				System.out.print("\n\n");
				break;
			case "chicken":
				boolean isBroiler = Boolean.parseBoolean(animalEntryValue.get("isBroiler"));
				System.out.print("is a " + (isBroiler ? "broiler" : "non-broiler") + " chicken.");
				System.out.print(" Its favorite food is " + animalFavfood.toLowerCase());
				System.out.print(", and its wingspan is " + animalEntryValue.get("wingspan") + ".\n");
				System.out.print(animalName + " ");
				displayAnimalsFriendships(animalFriendsNumber, animalFriends);
				System.out.print("\n\n");
				break;
			case "parrot":
				boolean canSpeak = Boolean.parseBoolean(animalEntryValue.get("canSpeak"));
				System.out.print("is a " + (canSpeak ? "speaking" : "non-speaking") + " parrot.");
				System.out.print(" Its favorite food is " + animalFavfood.toLowerCase());
				System.out.print(", and its wingspan is " + animalEntryValue.get("wingspan") + ".\n");
				System.out.print(animalName + " ");
				displayAnimalsFriendships(animalFriendsNumber, animalFriends);
				System.out.print("\n\n");
				break;
			}
		}
	}

	private boolean checkIfBeginsWithVowel(String str) {
		if ("aeiouAEIOU".indexOf(str.charAt(0)) > 0)
			return true;
		return false;
	}

	private void displayAnimalsFriendships(int animalFriendsNumber, String animalFriends) {

		if (animalFriendsNumber == 0) {
			System.out.print("has no friends.");
			return;
		}

		System.out.print("is friends with ");
		switch (animalFriendsNumber) {
		case 1:
			System.out.print(animalFriends + ".");
			break;
		case 2:
			System.out.print(animalFriends.replace(",", " and"));
			break;
		default:
			int lastCommaInd = animalFriends.lastIndexOf(",");
			String animalFriendsEdited = animalFriends.substring(0, lastCommaInd) + ", and"
					+ animalFriends.substring(lastCommaInd + 1);
			System.out.print(animalFriendsEdited + ".");
			break;
		}

	}

	private void displayRandomIntroMessage() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(4);
		switch (randomInt) {
		case 0:
			System.out.println("A lot has happened today.");
			break;
		case 1:
			System.out.println("What a day!");
			break;
		case 2:
			System.out.println("The animals were busy today.");
			break;
		case 3:
			System.out.println("The zoo keeper was busy toady.");
			break;
		}

	}

}
