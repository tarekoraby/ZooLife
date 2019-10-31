package com.zoolife.model;

import java.util.TreeMap;

/**
 * A subclass of {@link com.zoolife.model.Bird} that holds information specific
 * to chickens (i.e. whether or not they are broiler chicken)
 *
 * @see com.zoolife.model.Animal
 * @see com.zoolife.model.Bird
 *
 * @author Tarek Oraby
 * @version 1.0
 *
 */
public class Chicken extends Bird {

	private boolean isBroiler;

	private final String SPECIES = "chicken";

	/**
	 * @param name
	 *            The chicken's name
	 * @param favFood
	 *            The chicken's favorite food
	 * @param wingspan
	 *            The chicken's wingspan
	 * @param isBroiler
	 *            whether or not the chicken is broiler
	 *
	 * @throws NullPointerException
	 *             if a null object was passed as the chicken's name or favorite
	 *             food
	 * @throws IllegalArgumentException
	 *             if a negative wingspan value is passed or if the string
	 *             representing the chicken name or its favorite food is empty
	 *             or if it is too long (larger than 20 characters)
	 */
	public Chicken(String name, String favFood, float wingspan, boolean isBroiler) {
		super(name, favFood, wingspan);
		this.isBroiler = isBroiler;
	}

	/**
	 * @return a TreeMap of the animal's properties
	 */
	@Override
	public TreeMap<String, String> getProperties() {
		TreeMap<String, String> properties = super.getProperties();
		properties.put("species", getSpecies());
		properties.put("isBroiler", String.valueOf(isBroiler()));
		return properties;
	}

	public String getSpecies() {
		return this.SPECIES;
	}

	public boolean isBroiler() {
		return this.isBroiler;
	}
}
