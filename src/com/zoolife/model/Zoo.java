package com.zoolife.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * This class holds information about (1) the set of animals in the Zoo, (2) the
 * set of friendships among these animals, and (3) the set of friendships that
 * were established/broken up during the last simulated day.
 * <p>
 * Other than maintaining and retrieving these records, the main function of
 * this class is to ensure the structural integrity of the friendship network in
 * the zoo. For example, it ensures that if animal A becomes friends with animal
 * B, then B becomes also friend with A. Alternatively, to take another example,
 * the “Zoo” class ensures that friendships are established only among animals
 * that are already in the zoo.
 *
 * @see com.zoolife.model.Animal
 * @see com.zoolife.model.AnimalFriendship
 * @see com.zoolife.model.ZooKeeper
 *
 * @author Tarek Oraby
 * @version 1.0
 *
 */

public class Zoo {

	private Set<Animal> animalsSet; // The set of all animals in the zoo
	private Set<AnimalFriendship> dailyFriendsAdded; // The set of friendships
														// added daily
	private Set<AnimalFriendship> dailyFriendsRemoved; // The set of friendships
														// removed daily
	private int dayCount;
	private Set<AnimalFriendship> friendsSet; // The set of current friendships

	private Set<AnimalFriendship> prevDayFriendsSet; // The set of yesterday's
														// friendships

	/**
	 * The constructor
	 *
	 * @param newAnimalsSet
	 *            The animal set of the zoo
	 *
	 * @throws NullPointerException
	 *             if a null object was passed as the zoo's animal set or if a
	 *             null object was passed as one of the zoo's animals
	 * @throws IllegalArgumentException
	 *             if the animal set is empty
	 */
	public Zoo(Set<Animal> newAnimalsSet) {
		setAnimalsSet(newAnimalsSet);
		this.friendsSet = new HashSet<AnimalFriendship>();
		this.dayCount = 0;
	}

	/**
	 * Adds two animals as friends and updates the sets that reflect the daily
	 * changes in the zoo's friendship network. Note that the incrementDay()
	 * method must be called once before Friendships can be added
	 *
	 * @param anim1
	 *            animal friend number one
	 * @param anim2
	 *            animal friend number two
	 *
	 * @throws NullPointerException
	 *             if either of the passed two Animal objects are null
	 * @throws IllegalArgumentException
	 *             if either of the two animals doesn't belong to the zoo or if
	 *             the two animals are already friends
	 * @throws RuntimeException
	 *             if incrementDay() was not called at least once before the
	 *             call to this method
	 *
	 */
	public void addFirendship(Animal anim1, Animal anim2) {
		if ((anim1 == null) || (anim2 == null))
			throw new NullPointerException("Cannot update the friendship of a null Animal object!");
		if (!this.animalsSet.contains(anim1))
			throw new IllegalArgumentException("Animal 1 is not in this zoo. cannot set its friendship!");
		if (!this.animalsSet.contains(anim2))
			throw new IllegalArgumentException("Animal 2 is not in this zoo. cannot set its friendship!");
		if (this.dayCount == 0)
			throw new RuntimeException("Call incrementDay() before editing frindships!");
		if (areFriends(anim1, anim2))
			throw new IllegalArgumentException("These animals are already friends!");

		AnimalFriendship newFriendship = new AnimalFriendship(anim1, anim2);
		this.friendsSet.add(newFriendship);

		// In case this friendship was removed today,
		// remove it from the daily lost friendships
		this.dailyFriendsRemoved.remove(newFriendship);

		// Only add to daily gained friendships set if
		// this friendship didn't exist yesterday
		if (!this.prevDayFriendsSet.contains(newFriendship)) {
			this.dailyFriendsAdded.add(newFriendship);
		}
	}

	/**
	 * @param anim1
	 *            animal friend number one
	 * @param anim2
	 *            animal friend number two
	 *
	 * @return return true if anim1 and anim2 are friends
	 *
	 * @throws IllegalArgumentException
	 *             if the two animals don't belong to the zoo
	 */
	public boolean areFriends(Animal anim1, Animal anim2) {
		if (!this.animalsSet.contains(anim1))
			throw new IllegalArgumentException("Animal 1 is not in this zoo. cannot check its friendship status!");
		if (!this.animalsSet.contains(anim2))
			throw new IllegalArgumentException("Animal 2 is not in this zoo. cannot check its friendship status!");

		AnimalFriendship possibleFriendship = new AnimalFriendship(anim1, anim2);
		if (this.friendsSet.contains(possibleFriendship))
			return true;
		else
			return false;
	}

	/**
	 * @param anim
	 *            the animal whose friends' Animal objects are retrieved
	 * @return the set of anim friends
	 */
	public Set<Animal> getAnimalFirends(Animal anim) {
		return getAnimalFirendsInSet(anim, this.friendsSet);
	}

	/**
	 * @param anim
	 *            the animal whose today's gained friends are retrieved
	 * @return the set of animals with which anim has established a new
	 *         friendship today
	 */
	public Set<Animal> getAnimalFriendsGained(Animal anim) {
		return getAnimalFirendsInSet(anim, this.dailyFriendsAdded);
	}

	/**
	 * @param anim
	 *            the animal whose today's lost friends are retrieved
	 * @return the set of animals with which anim has broken up its friendship
	 *         today
	 */
	public Set<Animal> getAnimalFriendsLost(Animal anim) {
		return getAnimalFirendsInSet(anim, this.dailyFriendsRemoved);
	}

	/**
	 * @return the set of zoo animals
	 */
	public Set<Animal> getAnimalsSet() {
		return new HashSet<>(this.animalsSet);
	}

	/**
	 * @return the set of animal friendships that were established today (i.e.
	 *         the ones that didn't exist in the previous day)
	 */
	public Set<AnimalFriendship> getDailyFriendsAdded() {
		return new HashSet<>(this.dailyFriendsAdded);
	}

	/**
	 * @return the set of animal friendships that don't exist today (but which
	 *         existed in the previous day)
	 */
	public Set<AnimalFriendship> getDailyFriendsRemoved() {
		return new HashSet<>(this.dailyFriendsRemoved);
	}

	/**
	 * @return the current day in the zoo (initialized with 0 and increments by
	 *         one per ZooKeeper's simulation)
	 */
	public int getDayCount() {
		return this.dayCount;
	}

	/**
	 * @param anim
	 *            the animal whose friends' names are retrieved
	 * @return a comma delimited string of anim's friend names (ordered
	 *         alphabetically)
	 */
	public String getFriendsNames(Animal anim) {
		if (anim == null)
			throw new NullPointerException("Cannot check the friendship of a null Animal object!");
		if (!this.animalsSet.contains(anim))
			throw new IllegalArgumentException("Animal is not in this zoo. don't know its friends!");
		Set<Animal> friends = getAnimalFirends(anim);
		ArrayList<String> friendsNames = new ArrayList<String>();
		for (Animal animFriend : friends) {
			friendsNames.add(animFriend.getName());
		}
		Collections.sort(friendsNames);
		return String.join(", ", friendsNames);
	}

	/**
	 * @param anim
	 *            the animal whose number of friends are calculated
	 * @return the number of anim current friends in the zoo
	 */
	public int getFriendsNumber(Animal anim) {
		if (anim == null)
			throw new NullPointerException("Cannot check the friendship of a null Animal object!");
		if (!this.animalsSet.contains(anim))
			throw new IllegalArgumentException("Animal is not in this zoo. don't know its friends number!");
		return getAnimalFirends(anim).size();

	}

	/**
	 * @return the set of the zoo's current animal friendships
	 */
	public Set<AnimalFriendship> getFriendsSet() {
		return new HashSet<>(this.friendsSet);
	}

	/**
	 * @return The properties of the Zoo animals both individually (their names,
	 *         favorite food, etc.) as well as in terms of Friendships (who's
	 *         friend with whom).
	 */
	public TreeMap<String, TreeMap<String, String>> getZooProperties() {
		Set<Animal> animalSet = getAnimalsSet();
		TreeMap<String, TreeMap<String, String>> zooProperties = new TreeMap<String, TreeMap<String, String>>();
		for (Animal anim : animalSet) {
			TreeMap<String, String> currAnimProp = anim.getProperties();
			currAnimProp.put("friends", getFriendsNames(anim));
			currAnimProp.put("friendsNumber", String.valueOf(getFriendsNumber(anim)));
			zooProperties.put(currAnimProp.get("name"), currAnimProp);
		}
		return zooProperties;
	}

	/**
	 * Increments the day count by one. This method also resets the
	 * AnimalFreindship sets that are necessary to keep track of the daily
	 * changes in of the zoo's social network
	 */
	public void incrementDay() {
		this.dayCount++;
		this.prevDayFriendsSet = new HashSet<AnimalFriendship>(this.friendsSet);
		this.dailyFriendsAdded = new HashSet<AnimalFriendship>();
		this.dailyFriendsRemoved = new HashSet<AnimalFriendship>();
	}

	/**
	 * Breaks-up the friendship of the specified two animals and updates the
	 * sets that reflect the daily changes in the zoo's friendship network.
	 *
	 * @param anim1
	 *            animal friend number one
	 * @param anim2
	 *            animal friend number two
	 *
	 * @exception NullPointerException
	 *                if anim1 or anim2 is null
	 *
	 * @exception IllegalArgumentException
	 *                if either of the two animals are not part of this zoo or
	 *                if anim1 and anim2 are not friends in the first place
	 *
	 */
	public void removeFirendship(Animal anim1, Animal anim2) {
		if ((anim1 == null) || (anim2 == null))
			throw new NullPointerException("Cannot update the friendship of a null Animal object!");
		if (!this.animalsSet.contains(anim1))
			throw new IllegalArgumentException("Animal 1 is not in this zoo. cannot set its friendship!");
		if (!this.animalsSet.contains(anim2))
			throw new IllegalArgumentException("Animal 2 is not in this zoo. cannot set its friendship!");
		if (!areFriends(anim1, anim2))
			throw new IllegalArgumentException("These animals are already non-friends!");

		AnimalFriendship lostFriendship = new AnimalFriendship(anim1, anim2);
		this.friendsSet.remove(lostFriendship);

		// In case this friendship was added today,
		// remove it from the daily new friendships
		this.dailyFriendsAdded.remove(lostFriendship);

		// Only adjust the daily lost friendships sets if
		// this friendship existed yesterday
		if (this.prevDayFriendsSet.contains(lostFriendship)) {
			this.dailyFriendsRemoved.add(lostFriendship);
		}

	}

	private Set<Animal> getAnimalFirendsInSet(Animal anim, Set<AnimalFriendship> friendshipSet) {
		if (anim == null)
			throw new NullPointerException("Cannot check the friendship of a null Animal object!");
		if (!this.animalsSet.contains(anim))
			throw new IllegalArgumentException("Animal is not in this friendshipSet. Cannot know its friends!");

		Set<Animal> animFriends = new HashSet<Animal>();

		for (AnimalFriendship friendship : friendshipSet) {
			if (friendship.contains(anim)) {
				animFriends.add(friendship.getFriendOf(anim));
			}
		}

		return animFriends;
	}

	private void setAnimalsSet(Set<Animal> newAnimalsSet) {
		if (newAnimalsSet == null)
			throw new NullPointerException("a null object was passed as the zoo's animal set!");
		if (newAnimalsSet.contains(null))
			throw new NullPointerException("a null object was passed as one of the zoo's animals!");
		if (newAnimalsSet.size() == 0)
			throw new IllegalArgumentException("The animal set cannot be empty!");

		this.animalsSet = new HashSet<>(newAnimalsSet);
	}
}