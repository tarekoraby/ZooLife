package com.zoolife.test;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import com.zoolife.model.Animal;
import com.zoolife.model.Chicken;
import com.zoolife.model.Dog;
import com.zoolife.model.Parrot;
import com.zoolife.model.Zoo;
import com.zoolife.model.ZooKeeper;

/**
 * Tests of the {@link com.zoolife.model.ZooKeeper} class
 *
 * @author Tarek Oraby
 * @version 1.0
 *
 */

public class ZooKeeperTests {

	// TODO Improve on the following test since it is possible in principle for
	// different seeds to prduce identical results (especially in a zoo
	// consisting of a small number of animals
	@Test
	public void differentlySeededZooKeeperShouldProduceDifferentResults() {
		Dog dog1 = new Dog("dog1", "Food", "Hunting dog");
		Dog dog2 = new Dog("dog2", "Food", "Hunting dog");
		Chicken chicken1 = new Chicken("chicken1", "Food", 0.5f, false);
		Chicken chicken2 = new Chicken("chicken2", "Food", 0.5f, false);
		Parrot parrot1 = new Parrot("parrot1", "Food", 0.5f, true);
		Parrot parrot2 = new Parrot("parrot2", "Food", 0.5f, true);

		HashSet<Animal> goodAnimalsSet = new HashSet<Animal>();
		goodAnimalsSet.add(dog1);
		goodAnimalsSet.add(dog2);
		goodAnimalsSet.add(chicken1);
		goodAnimalsSet.add(chicken2);
		goodAnimalsSet.add(parrot1);
		goodAnimalsSet.add(parrot2);
		Zoo goodZooOne = new Zoo(goodAnimalsSet);
		Zoo goodZooTwo = new Zoo(goodAnimalsSet);

		ZooKeeper zKeeperOne = new ZooKeeper(1, 1, 1234);
		ZooKeeper zKeeperTwo = new ZooKeeper(1, 1, 4321);

		for (int i = 0; i < 3; i++) {
			zKeeperOne.simulateOneDay(goodZooOne);
			zKeeperTwo.simulateOneDay(goodZooTwo);
		}

		Assert.assertNotEquals(goodZooOne.getFriendsSet(), goodZooTwo.getFriendsSet());
		Assert.assertNotEquals(goodZooOne.getDailyFriendsAdded(), goodZooTwo.getDailyFriendsAdded());
		Assert.assertNotEquals(goodZooOne.getDailyFriendsRemoved(), goodZooTwo.getDailyFriendsRemoved());
	}

	@Test
	public void identicallySeededZooKeeperShouldProduceIdenticalResults() {
		Dog dog = new Dog("dog", "Food", "Hunting dog");
		Chicken chicken = new Chicken("chicken", "Food", 0.5f, false);
		Parrot parrot = new Parrot("parrot", "Food", 0.5f, true);

		HashSet<Animal> goodAnimalsSet = new HashSet<Animal>();
		goodAnimalsSet.add(dog);
		goodAnimalsSet.add(chicken);
		goodAnimalsSet.add(parrot);
		Zoo goodZooOne = new Zoo(goodAnimalsSet);
		Zoo goodZooTwo = new Zoo(goodAnimalsSet);

		ZooKeeper zKeeperOne = new ZooKeeper(1, 1, 1234);
		ZooKeeper zKeeperTwo = new ZooKeeper(1, 1, 1234);

		zKeeperOne.simulateOneDay(goodZooOne);
		zKeeperTwo.simulateOneDay(goodZooTwo);

		Assert.assertEquals(goodZooOne.getFriendsSet(), goodZooTwo.getFriendsSet());
		Assert.assertEquals(goodZooOne.getDailyFriendsAdded(), goodZooTwo.getDailyFriendsAdded());
		Assert.assertEquals(goodZooOne.getDailyFriendsRemoved(), goodZooTwo.getDailyFriendsRemoved());
	}

	@Test
	public void ZooKeeperShouldSimulateZooLife() {
		Dog dog = new Dog("dog", "Food", "Hunting dog");
		Chicken chicken = new Chicken("chicken", "Food", 0.5f, false);
		Parrot parrot = new Parrot("parrot", "Food", 0.5f, true);

		HashSet<Animal> goodAnimalsSet = new HashSet<Animal>();
		goodAnimalsSet.add(dog);
		goodAnimalsSet.add(chicken);
		goodAnimalsSet.add(parrot);
		Zoo goodZoo = new Zoo(goodAnimalsSet);

		ZooKeeper zKeeper = new ZooKeeper(1, 1);

		zKeeper.simulateOneDay(goodZoo);
	}

}
