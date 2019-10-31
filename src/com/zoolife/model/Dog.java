package com.zoolife.model;

import java.util.TreeMap;

/**
 * A subclass of {@link com.zoolife.model.Animal} that holds information
 * specific to dogs (i.e. their type)
 *
 * @see com.zoolife.model.Animal
 *
 * @author Tarek Oraby
 * @version 1.0
 *
 */
public class Dog extends Animal {

	private String dogType;
	private final String SPECIES = "dog";

	/**
	 * @param name
	 *            The dog's name
	 * @param favFood
	 *            The dog's favorite food
	 * @param dogType
	 *            The dog's type
	 *
	 * @throws NullPointerException
	 *             if a null object was passed as the dog's name, favorite food,
	 *             or type
	 * @throws IllegalArgumentException
	 *             if the string representing the dog name, its favorite food,
	 *             or its type is empty or if it is too long (larger than 20
	 *             characters)
	 */
	public Dog(String name, String favFood, String dogType) {
		super(name, favFood);
		setDogType(dogType);
	}

	public String getDogType() {
		return this.dogType;
	}

	/**
	 * @return a TreeMap of the animal's properties
	 */
	@Override
	public TreeMap<String, String> getProperties() {
		TreeMap<String, String> properties = super.getProperties();
		properties.put("species", getSpecies());
		properties.put("dogType", getDogType());
		return properties;
	}

	public String getSpecies() {
		return this.SPECIES;
	}

	private void setDogType(String dogType) {
		if (dogType == null)
			throw new NullPointerException("a null object was passed as the dog type");
		if (dogType.isEmpty())
			throw new IllegalArgumentException(
					"An empty String was passed as the dog type." + " All dogs must have a type!");
		this.dogType = dogType;
	}

}
