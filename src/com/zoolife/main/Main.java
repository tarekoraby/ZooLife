package com.zoolife.main;

import java.util.Date;

import com.zoolife.control.Controller;
import com.zoolife.model.Loader;
import com.zoolife.model.Zoo;
import com.zoolife.model.ZooKeeper;
import com.zoolife.view.Viewer;

/**
 *
 * <h1>ZooLife: A simulator of life in the zoo</h1> ZooLife is a java
 * application that simulates life among a number of zoo animals. This is the
 * entry class to Zoolife.
 *
 * <p>
 * This class performs the following tasks: (1) It initiates the "Loader" class
 * (which loads the animals and the simulation policy). (2) It initiates a "Zoo"
 * class with the loaded animals. (3) It initiates a "ZooKeeper" class using the
 * loaded simulation policy. (3) It initiates a "Viewer" class (which is later
 * used to display the zoo status to the user). (4) It initiates a "Controller"
 * class (passing it the instantiated classes: "ZooKeeper", "ZooKeeper", and
 * "Viewer". The "Controller" class then takes control of the interaction with
 * the user and the working of the different parts.
 *
 * @see com.zoolife.model.Loader
 * @see com.zoolife.model.Zoo
 * @see com.zoolife.model.ZooKeeper
 * @see com.zoolife.view.Viewer
 * @see com.zoolife.control.Controller
 *
 * @author Tarek Oraby
 * @version 1.0
 *
 */

public class Main {

	/**
	 * @param args
	 *            accepts zero or one (optional) argument to be passed: an
	 *            integer of type long that serves as the seed of the random
	 *            number generator used to simulate life in the zoo
	 */
	public static void main(String[] args) {
		if (args.length > 1) {
			System.err.println("You entered an invalid number of arguments");
			System.out.println("This program accepts one optional argument: "
					+ "an integer that serves as the random seed of the zoo simulation");
			System.exit(1);
		}

		long randomSeed = 0;
		if (args.length == 0) {
			randomSeed = (new Date()).getTime();
		} else {
			assert args.length == 1 : "Unexpected code redirection";
			try {
				randomSeed = Long.parseLong(args[0]);
			} catch (NumberFormatException e) {
				System.err.println("You entered an invalid number as the seed of the random numbers generator");
				System.out.println("A valid seed is an integer between " + 
						Long.MIN_VALUE + " and " + Long.MAX_VALUE);
				System.exit(1);
			}
		}

		Loader loader = new Loader();
		Zoo zoo = new Zoo(loader.loadAnimals());
		ZooKeeper zKeeper = new ZooKeeper(loader.loadMaxLosableFriends(), loader.maxGainableFriends(), randomSeed);
		Viewer viewer = new Viewer();
		Controller controller = new Controller(viewer);
		controller.takeControl(zoo, zKeeper);

		System.exit(0);
	}
}
