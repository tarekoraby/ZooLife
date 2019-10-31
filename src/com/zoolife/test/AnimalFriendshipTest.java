package com.zoolife.test;

import org.junit.Assert;
import org.junit.Test;

import com.zoolife.model.Animal;
import com.zoolife.model.AnimalFriendship;
import com.zoolife.model.Chicken;
import com.zoolife.model.Dog;
import com.zoolife.model.Parrot;

/**
 * Tests of the {@link com.zoolife.model.AnimalFriendship} class
 *
 * @author Tarek Oraby
 * @version 1.0
 *
 */

public class AnimalFriendshipTest {

	@Test(expected = IllegalArgumentException.class)
	public void addingIdenticalAnimalsAsFriendsShouldRaiseIllegalArgumentException() {
		Dog dog = new Dog("dog", "Food", "Hunting dog");

		new AnimalFriendship(dog, dog);
	}

	@Test
	public void FriendshipObjectsWithDifferentSetOfAnimalsShouldBeConsideredDifferent() {
		Dog dog = new Dog("dog", "Food", "Hunting dog");
		Chicken chicken = new Chicken("chicken", "Food", 0.5f, false);
		Parrot parrot = new Parrot("parrot", "Food", 0.5f, true);

		AnimalFriendship friendshipOne = new AnimalFriendship(dog, chicken);
		AnimalFriendship friendshipTwo = new AnimalFriendship(dog, parrot);

		Assert.assertNotEquals(friendshipOne, friendshipTwo);
	}

	@Test
	public void FriendshipObjectsWithTheSameSetOfAnimalsShouldBeConsideredEqual() {
		Dog dog = new Dog("dog", "Food", "Hunting dog");
		Chicken chicken = new Chicken("chicken", "Food", 0.5f, false);

		AnimalFriendship friendshipOne = new AnimalFriendship(dog, chicken);
		AnimalFriendship friendshipTwo = new AnimalFriendship(chicken, dog);

		Assert.assertEquals(friendshipOne, friendshipTwo);
	}

	@Test
	public void ifAnimalOneIsFriendWithAnimalTwoThenAnimalTwoShouldBeFriendWithAnimalOne() {
		Dog dog = new Dog("dog", "Food", "Hunting dog");
		Chicken chicken = new Chicken("name", "Food", 0.5f, false);

		AnimalFriendship friendship = new AnimalFriendship(dog, chicken);
		Animal friendOfDog = friendship.getFriendOf(dog);
		Animal friendOfChicken = friendship.getFriendOf(chicken);
		Assert.assertEquals(friendOfDog, chicken);
		Assert.assertEquals(friendOfChicken, dog);
	}
}
