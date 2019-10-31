package com.zoolife.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * This class loads the initial ZooLife application properties.
 *
 * <b>Note:</b> These properties are currently hard-coded in this class, but
 * could in the future be extended to load from an external source (e.g. from
 * file)
 *
 * @author Tarek Oraby
 * @version 1.0
 *
 */

public class Loader {

	private boolean animalsLoaded;
	private Set<Animal> loadedAnimals;
	private HashMap<String, Integer> loadedZooKeeperSettings;
	private boolean zooKeeperSettingsLoaded;

	/**
	 * Default constructor.
	 */
	public Loader() {

	}

	/**
	 *
	 * Loads and returns the set of {@link com.zoolife.model.Animal} objects
	 * that the zoo should contain
	 *
	 * <b>Note:</b> The set of animals are currently hard-coded in this method.
	 * In the future, this method could be extended to load the set of zoo
	 * animals from file
	 *
	 * @return a HashSet of {@link com.zoolife.model.Animal} objects that the
	 *         zoo should contain
	 *
	 */
	public Set<Animal> loadAnimals() {
		if (!this.animalsLoaded) {
			this.loadedAnimals = new HashSet<Animal>();
			this.loadedAnimals.add(new Dog("Dog one", "Meat", "Hunting dog"));
			this.loadedAnimals.add(new Dog("Dog two", "Fresh meat", "Assistance dog"));
			this.loadedAnimals.add(new Dog("Dog three", "Pedigree", "Racing dog"));
			this.loadedAnimals.add(new Parrot("Parrot one", "Grain", 0.25f, false));
			this.loadedAnimals.add(new Parrot("Parrot two", "Corn", 0.5f, true));
			this.loadedAnimals.add(new Chicken("Chicken one", "Corn", 0.75f, true));
			this.loadedAnimals.add(new Chicken("Chicken two", "Corn", 0.75f, false));
			this.animalsLoaded = true;
		}
		return this.loadedAnimals;
	}

	/**
	 *
	 * Loads and returns the maximum number of friends that each zoo animal can
	 * loose per round of simulation (day)
	 *
	 * <b>Note:</b> this maximum number is currently hard-coded in this method.
	 * In the future, this method could be extended to load the number from file
	 *
	 * @return maximum number of friends each animal can loose per day
	 *
	 */
	public int loadMaxLosableFriends() {
		if (!this.zooKeeperSettingsLoaded) {
			loadZooKeeperSettings();
		}
		return this.loadedZooKeeperSettings.get("maxLosableFriends");
	}

	/**
	 *
	 * Loads and returns the maximum number of friends that each zoo animal can
	 * gain per round of simulation (day)
	 *
	 * <b>Note:</b> this maximum number is currently hard-coded in this method.
	 * In the future, this method could be extended to load the number from file
	 *
	 * @return maximum number of friends each animal can gain per day
	 *
	 */
	public int maxGainableFriends() {
		if (!this.zooKeeperSettingsLoaded) {
			loadZooKeeperSettings();
		}
		return this.loadedZooKeeperSettings.get("maxGainableFriends");
	}

	// called once
	private void loadZooKeeperSettings() {
		this.loadedZooKeeperSettings = new HashMap<String, Integer>();
		this.loadedZooKeeperSettings.put("maxLosableFriends", 1);
		this.loadedZooKeeperSettings.put("maxGainableFriends", 1);
		this.zooKeeperSettingsLoaded = true;
	}
}
