/**
 *
 */
package com.zoolife.model;

import java.util.TreeMap;

/**
 * This class is a subclass of {@link com.zoolife.model.Animal} and the super
 * class of {@link com.zoolife.model.Chicken} and
 * {@link com.zoolife.model.Parrot}. It holds information common to all birds
 * (i.e. their name wingspan)
 *
 * @see com.zoolife.model.Animal
 * @see com.zoolife.model.Chicken
 * @see com.zoolife.model.Parrot
 *
 * @author Tarek Oraby
 * @version 1.0
 *
 */

abstract class Bird extends Animal {

	private float wingspan;

	/**
	 * @param name
	 *            The bird's name
	 * @param favFood
	 *            The bird's favorite food
	 * @param wingspan
	 *            The bird's wingspan
	 *
	 * @throws NullPointerException
	 *             if a null object was passed as the animal's name or favorite
	 *             food
	 * @throws IllegalArgumentException
	 *             if a negative wingspan value is passed or if the string
	 *             representing the animal name or its favorite food is empty or
	 *             if it is too long (larger than 20 characters)
	 */
	public Bird(String name, String favFood, float wingspan) {
		super(name, favFood);
		setWingspan(wingspan);
	}

	/**
	 * @return a TreeMap of the animal's properties
	 */
	@Override
	public TreeMap<String, String> getProperties() {
		TreeMap<String, String> properties = super.getProperties();
		properties.put("wingspan", String.valueOf(getWingspan()));
		return properties;
	}

	public float getWingspan() {
		return this.wingspan;
	}

	private void setWingspan(float wingspan) {
		if (wingspan < 0)
			throw new IllegalArgumentException("A bird cannot have a negative wingspan!");
		this.wingspan = wingspan;
	}

}
