package com.zoolife.control;

import java.util.Scanner;

import com.zoolife.model.Zoo;
import com.zoolife.model.ZooKeeper;
import com.zoolife.view.Viewer;

public class Controller {

	private Scanner scanObj;
	private Viewer viewer;

	public Controller(Viewer viewer) {
		this.scanObj = new Scanner(System.in);
		this.viewer = viewer;
	}

	public void takeControl(Zoo zoo, ZooKeeper zKeeper) {
		this.viewer.displayAppIntro();
		this.viewer.displayVaildInputs();
		String userInput;
		boolean exitConfirmed = false;
		do {
			userInput = this.scanObj.nextLine();
			this.viewer.displayAfterInput();

			if (userInput.equals("d") || userInput.equals("D")) {
				// System.out.println(zoo.getZooProperties() + "\n");
				this.viewer.displayZooProperties(zoo.getZooProperties());
			} else if (userInput.equals("s") || userInput.equals("S")) {
				this.viewer.displaySimulationStartingMessage(zoo);
				zKeeper.simulateOneDay(zoo);
				this.viewer.displaySimulationResults(zoo);
			} else if (userInput.equals("q") || userInput.equals("Q")) {
				exitConfirmed = confirmExit();
			} else {
				this.viewer.displayInvalidInputEnteredMsg(userInput);
				this.viewer.displayVaildInputs();
			}

		} while (!exitConfirmed);
		this.viewer.displayGoodbyeMsg();
	}

	private boolean confirmExit() {
		this.viewer.displayExitWarningMsg();
		do {
			this.viewer.displayExitConfirmationMsg();
			String userInput = this.scanObj.nextLine();
			this.viewer.displayAfterInput();
			if (userInput.equals("y"))
				return true;
			else if (userInput.equals("n"))
				return false;
		} while (true);

	}

}