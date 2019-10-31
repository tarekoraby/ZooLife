/**
 *
 */
package com.zoolife.model;

import java.util.TreeMap;

/**
 * A subclass of {@link com.zoolife.model.Bird} that holds information specific
 * to parrots (i.e. whether or not they can speak)
 *
 * @see com.zoolife.model.Animal
 * @see com.zoolife.model.Bird
 *
 * @author Tarek Oraby
 * @version 1.0
 *
 */

public class Parrot extends Bird {

	private boolean canSpeak;
	private final String SPECIES = "parrot";

	/**
	 * @param name
	 *            The parrot's name
	 * @param favFood
	 *            The parrot's favorite food
	 * @param wingspan
	 *            The parrot's wingspan
	 * @param canSpeak
	 *            whether or not the parrot can speak
	 *
	 * @throws NullPointerException
	 *             if a null object was passed as the animal's name or favorite
	 *             food
	 * @throws IllegalArgumentException
	 *             if a negative wingspan value is passed or if the string
	 *             representing the animal name or its favorite food is empty or
	 *             if it is too long (larger than 20 characters)
	 */
	public Parrot(String name, String favFood, float wingspan, boolean canSpeak) {
		super(name, favFood, wingspan);
		this.canSpeak = canSpeak;
	}

	public boolean canSpeak() {
		return this.canSpeak;
	}

	/**
	 * @return a TreeMap of the animal's properties
	 */
	@Override
	public TreeMap<String, String> getProperties() {
		TreeMap<String, String> properties = super.getProperties();
		properties.put("species", getSpecies());
		properties.put("canSpeak", String.valueOf(canSpeak()));
		return properties;
	}

	public String getSpecies() {
		return this.SPECIES;
	}

}
