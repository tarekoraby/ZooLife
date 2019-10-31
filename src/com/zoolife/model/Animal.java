package com.zoolife.model;

import java.util.TreeMap;

/**
 * This class is the super class of all animals, and it holds information about
 * common animal features (i.e. their name and favorite food)
 *
 * @see com.zoolife.model.Bird
 * @see com.zoolife.model.Dog
 * @see com.zoolife.model.Chicken
 * @see com.zoolife.model.Parrot
 *
 * @author Tarek Oraby
 * @version 1.0
 *
 */

public abstract class Animal {

	private String favFood;
	private String name;

	/**
	 * @param name
	 *            The animal's name
	 * @param favFood
	 *            The animal's favorite food
	 *
	 * @throws NullPointerException
	 *             if a null object was passed as the animal's name or favorite
	 *             food
	 * @throws IllegalArgumentException
	 *             if the string representing the animal name or its favorite
	 *             food is empty or if it is too long (larger than 20
	 *             characters)
	 */
	public Animal(String name, String favFood) {
		setName(name);
		setFavFood(favFood);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Animal))
			return false;
		Animal other = (Animal) obj;
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name))
			return false;
		return true;
	}

	public String getFavFood() {
		return this.favFood;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * @return a TreeMap of the animal's properties
	 */
	public TreeMap<String, String> getProperties() {
		TreeMap<String, String> properties = new TreeMap<String, String>();
		properties.put("name", getName());
		properties.put("favFood", getFavFood());
		return properties;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());
		return result;
	}

	private void setFavFood(String favFood) {
		if (favFood == null)
			throw new NullPointerException("A null object was passed as the animal's favorite food");
		if (favFood.isEmpty())
			throw new IllegalArgumentException("An empty String was passed as the animal's favorite food."
					+ " All animals must prefer some food!");
		if (favFood.length() > 20)
			throw new IllegalArgumentException("Too long animal name.");
		this.favFood = favFood;
	}

	private void setName(String name) {
		if (name == null)
			throw new NullPointerException("A null object was passed as the animal's name");
		if (name.isEmpty())
			throw new IllegalArgumentException(
					"An empty String was passed as an animal name. All animals need a name!");
		if (name.length() > 20)
			throw new IllegalArgumentException("Too long animal name.");
		this.name = name;
	}
}
