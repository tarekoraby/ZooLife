/**
 *
 */
package com.zoolife.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class represents a friendship between two animals (it is essentially a
 * HashSet of two animals). Having this class simplifies working with the set of
 * friendships (especially in the {@link com.zoolife.model.Zoo} class).
 * Specifically, instead of working with two layers of sets (a set of set of
 * animals), this class enables other classes to work only with one set of
 * animal friendships.
 *
 *
 * @see com.zoolife.model.Zoo
 *
 * @author Tarek Oraby
 * @version 1.0
 *
 */

public class AnimalFriendship {

	private Set<Animal> animalFriendshipSet;

	/**
	 * Constructor
	 *
	 * @param friendOne
	 *            an Animal object that forms one part of the friendship
	 * @param friendTwo
	 *            an Animal object that forms the other part of the friendship
	 *
	 * @throws NullPointerException
	 *             if friendOne or friendTrue is null
	 * @throws IllegalArgumentException
	 *             if friendOne and friendTrue are identical
	 *
	 */
	public AnimalFriendship(Animal friendOne, Animal friendTwo) {
		if ((friendOne == null) || (friendTwo == null))
			throw new NullPointerException("Cannot set AnimalFriendship with a null Animal object!");
		if (friendOne.equals(friendTwo))
			throw new IllegalArgumentException("Both animals are identical. Cannot be friend with oneself!");

		this.animalFriendshipSet = new HashSet<Animal>();
		this.animalFriendshipSet.add(friendOne);
		this.animalFriendshipSet.add(friendTwo);
	}

	/**
	 * Checks if an animal is a member of the friendship
	 *
	 * @param anim
	 *            the Animal object whose membership in this friendship is to be
	 *            checked
	 *
	 * @return <code>true</code> if anim is a member of this friendship,
	 *         otherwise <code>false</code>
	 */
	public boolean contains(Animal anim) {
		return this.animalFriendshipSet.contains(anim);
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
		if (!(obj instanceof AnimalFriendship))
			return false;
		AnimalFriendship other = (AnimalFriendship) obj;
		if (this.animalFriendshipSet == null) {
			if (other.animalFriendshipSet != null)
				return false;
		} else if (!this.animalFriendshipSet.equals(other.animalFriendshipSet))
			return false;
		return true;
	}

	/**
	 * @param anim
	 *            the Animal object whose friend's Animal object is to be
	 *            returned
	 * @throws IllegalArgumentException
	 *             if the passed Animal object is not a part of the
	 *             AnimalFriendship
	 * @return the friend of anim
	 */
	public Animal getFriendOf(Animal anim) {
		if (!this.animalFriendshipSet.contains(anim))
			throw new IllegalArgumentException("The passed Animal object is not a part of this AnimalFriendship");

		for (Animal a : this.animalFriendshipSet) {
			if (a != anim)
				return (a);
		}
		return null;
	}

	/**
	 * @return a java.util.Iterator object of the friendship
	 */
	public Iterator<Animal> getIterator() {
		return this.animalFriendshipSet.iterator();
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
		result = (prime * result) + ((this.animalFriendshipSet == null) ? 0 : this.animalFriendshipSet.hashCode());
		return result;
	}

}
