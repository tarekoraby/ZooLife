package com.zoolife.model;

import java.util.Random;
import java.util.Set;

/**
 * This class controls the methods of the {@link com.zoolife.model.Zoo} class
 * and initiates the daily simulations. The main purpose of this class is to
 * ensure that friendships are established or broken-up according to specific
 * rules (which it takes as inputs). As such, while the Zoo class ensures the
 * structural integrity of all edits to the zoo records, the ZooKeeper class is
 * where the application ensures that the number of established or removed
 * friendships per simulated day is within the desired limits.
 *
 * <p>
 * Note that although this class specifies the maximum number of friends that
 * each animal can gain and loose per simulation round, the actual number of
 * gained/lost friends for some animals can be more than those maximum values.
 * To explain why this is so consider how the ZooKeeper class might deal with
 * the following scenario. Imagine that the zoo consists of three animals (a
 * dog, a chicken, and a parrot) and that these three are all friends with each
 * other. the ZooKeeper class will take each of these three animals in turn
 * applying the maximum values to them (imagine that the maximum loosable
 * friendships is 1). So if it takes the dog first, it might randomly make it
 * loose its friendship with the parrot, then when it comes to the chicken it
 * might also make it loose its friendship with the parrot. This means that the
 * parrot might be left loosing two freinds even if the maximum number of
 * loosable friends is 1.
 *
 *
 * @see com.zoolife.model.Zoo
 *
 * @author Tarek Oraby
 * @version 1.0
 *
 */

public class ZooKeeper {

	private int maxGainableFriends;
	private int maxLosableFriends;

	private Random randomGenerator;

	/**
	 * The constructor with no seed
	 *
	 * @param maxLosableFriends
	 *            The maximum number of friends that each animal can loose (Note
	 *            that animals can loose more than this number. See the note in
	 *            the class description)
	 * @param maxGainableFriends
	 *            The maximum number of friends that each animal can gain (Note
	 *            that animals can gain more than this number. See the note in
	 *            the class description)
	 */
	public ZooKeeper(int maxLosableFriends, int maxGainableFriends) {
		this.setMaxLosableFriends(maxLosableFriends);
		this.setMaxGainableFriends(maxGainableFriends);
		this.randomGenerator = new Random();
	}

	/**
	 * The constructor with a seed to the random generator
	 *
	 * @param maxLosableFriends
	 *            The maximum number of friends that each animal can loose (Note
	 *            that animals can loose more than this number. See the note in
	 *            the class description)
	 * @param maxGainableFriends
	 *            The maximum number of friends that each animal can gain (Note
	 *            that animals can gain more than this number. See the note in
	 *            the class description)
	 * @param randomSeed
	 *            the seed to the random numbers generator that is used to
	 *            simulate the creation or loss of friendships in the zoo
	 */
	public ZooKeeper(int maxLosableFriends, int maxGainableFriends, long randomSeed) {
		this.setMaxLosableFriends(maxLosableFriends);
		this.setMaxGainableFriends(maxGainableFriends);
		this.randomGenerator = new Random(randomSeed);
	}

	/**
	 * @return the maxGainableFriends
	 */
	public int getmaxGainableFriends() {
		return this.maxGainableFriends;
	}

	/**
	 * @return the maxLosableFriends
	 */
	public int getMaxLosableFriends() {
		return this.maxLosableFriends;
	}

	/**
	 * @param maxGainableFriends
	 *            The maximum number of friends that each animal can gain (Note
	 *            that animals can gain more than this number. See the note in
	 *            the class description)
	 * @throws IllegalArgumentException
	 *             if maxGainableFriends less than 0
	 */
	public void setMaxGainableFriends(int maxGainableFriends) {
		if (maxGainableFriends < 0)
			throw new IllegalArgumentException("The maximum number of gainable friends must be >= 0!");
		this.maxGainableFriends = maxGainableFriends;
	}

	/**
	 * @param maxLosableFriends
	 *            The maximum number of friends that each animal can loose (Note
	 *            that animals can loose more than this number. See the note in
	 *            the class description)
	 *
	 * @throws IllegalArgumentException
	 *             if maxLosableFriends less than 0
	 */
	public void setMaxLosableFriends(int maxLosableFriends) {
		if (maxLosableFriends < 0)
			throw new IllegalArgumentException("The maximum number of losable friends must be >= 0!");
		this.maxLosableFriends = maxLosableFriends;
	}

	/**
	 * Simulates one day in the zoo, establishing and breaking up friendships
	 * randomly within the constraints of the supplied policy which determines
	 * the maximum number of friends that each animal can gain or loose per
	 * round
	 *
	 * @param zoo
	 *            the zoo whose friendship network is to simulated
	 *
	 */
	public void simulateOneDay(Zoo zoo) {
		zoo.incrementDay();

		Set<Animal> zooAnimalsSet = zoo.getAnimalsSet();
		for (Animal currAnimal : zooAnimalsSet) {
			// Loose a random friend and update the new friends' list
			looseRandomFriend(zoo, currAnimal, this.maxLosableFriends);
			// Gain a random friend and update the new friends' list
			gainRandomFriend(zoo, currAnimal, this.maxGainableFriends);
		}
	}

	private void gainRandomFriend(Zoo zoo, Animal anim, int maxGainable) {
		/*
		 * For the current animal get the set of gainable friends, which is
		 * equal to the set of all animals minus the set of friends that the
		 * animal already have (and the animal itself).
		 */
		Set<Animal> gainableFriendsSet = zoo.getAnimalsSet();
		gainableFriendsSet.removeAll(zoo.getAnimalFirends(anim));
		gainableFriendsSet.remove(anim);

		int i = 0;
		while (!gainableFriendsSet.isEmpty() && (i < maxGainable)) {
			Animal friendToGain = pickRandomFromAnimalSet(gainableFriendsSet);
			zoo.addFirendship(anim, friendToGain);
			gainableFriendsSet.remove(gainableFriendsSet);
			i++;
		}
	}

	private void looseRandomFriend(Zoo zoo, Animal anim, int maxLosable) {
		/*
		 * For the current animal get the set of loosable friends, which is
		 * equal to the set of all the animal's current friends
		 */
		Set<Animal> losableFriendsSet = zoo.getAnimalFirends(anim);

		int i = 0;
		while (!losableFriendsSet.isEmpty() && (i < maxLosable)) {
			Animal friendToLose = pickRandomFromAnimalSet(losableFriendsSet);
			zoo.removeFirendship(anim, friendToLose);
			losableFriendsSet.remove(losableFriendsSet);
			i++;
		}
	}

	private Animal pickRandomFromAnimalSet(Set<Animal> animalSet) {
		if (animalSet.size() == 0)
			throw new IllegalArgumentException(
					"The animal set from which a random element is to be picked cannot be empty!");

		int randNum = this.randomGenerator.nextInt(animalSet.size());
		int i = 0;
		for (Animal currAnim : animalSet) {
			if (i == randNum)
				return currAnim;
			i++;
		}
		return null;
	}

}
