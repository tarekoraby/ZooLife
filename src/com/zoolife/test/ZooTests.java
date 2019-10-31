package com.zoolife.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.zoolife.model.Animal;
import com.zoolife.model.AnimalFriendship;
import com.zoolife.model.Chicken;
import com.zoolife.model.Dog;
import com.zoolife.model.Parrot;
import com.zoolife.model.Zoo;

/**
 * Tests of the {@link com.zoolife.model.Zoo} class
 *
 * @author Tarek Oraby
 * @version 1.0
 *
 */

public class ZooTests {

	@Test(expected = NullPointerException.class)
	public void constructingZooWithBadAnimalSetShouldRaiseNullPointerException() {
		HashSet<Animal> badAnimalsSet = new HashSet<Animal>();
		badAnimalsSet.add(null);
		badAnimalsSet.add(null);
		badAnimalsSet.add(null);
		new Zoo(badAnimalsSet);
	}

	@Test
	public void constructingZooWithGoodAnimalSetShouldNotRaiseException() {
		Dog dog = new Dog("dog", "Food", "Hunting dog");
		Chicken chicken = new Chicken("chicken", "Food", 0.5f, false);
		Parrot parrot = new Parrot("parrot", "Food", 0.5f, true);

		HashSet<Animal> goodAnimalsSet = new HashSet<Animal>();
		goodAnimalsSet.add(dog);
		goodAnimalsSet.add(chicken);
		goodAnimalsSet.add(parrot);
		Zoo goodZoo = new Zoo(goodAnimalsSet);

		Set<Animal> animalSet = goodZoo.getAnimalsSet();
		Assert.assertEquals(animalSet, goodAnimalsSet);
	}

	@Test
	public void ifAnimalOneIsFriendWithAnimalTwoThenAnimalTwoShouldBeFriendWithAnimalOne() {
		Dog dog = new Dog("dog", "Food", "Hunting dog");
		Chicken chicken = new Chicken("name", "Food", 0.5f, false);
		HashSet<Animal> goodAnimalsSet = new HashSet<Animal>();
		goodAnimalsSet.add(dog);
		goodAnimalsSet.add(chicken);
		Zoo goodZoo = new Zoo(goodAnimalsSet);

		goodZoo.incrementDay();
		goodZoo.addFirendship(dog, chicken);

		boolean isOneFriendWithTwo = goodZoo.areFriends(dog, chicken);
		boolean isTwoFriendWithOne = goodZoo.areFriends(chicken, dog);

		Assert.assertTrue(isOneFriendWithTwo);
		Assert.assertTrue(isTwoFriendWithOne);
		Assert.assertEquals(isOneFriendWithTwo, isTwoFriendWithOne);
	}

	@Test
	public void zooShouldBeAbleToAddandRemoveFriends() {
		Dog dog = new Dog("dog", "Food", "Hunting dog");
		Chicken chicken = new Chicken("chicken", "Food", 0.5f, false);
		Parrot parrot = new Parrot("parrot", "Food", 0.5f, true);

		HashSet<Animal> goodAnimalsSet = new HashSet<Animal>();
		goodAnimalsSet.add(dog);
		goodAnimalsSet.add(chicken);
		goodAnimalsSet.add(parrot);
		Zoo goodZoo = new Zoo(goodAnimalsSet);

		goodZoo.incrementDay();
		goodZoo.addFirendship(dog, chicken);
		goodZoo.removeFirendship(dog, chicken);

		goodZoo.addFirendship(parrot, dog);
		goodZoo.addFirendship(dog, chicken);

		int friendshipsNum = goodZoo.getFriendsSet().size();
		Assert.assertEquals(friendshipsNum, 2);
	}

	@Test
	public void zooShouldBeAbleToUpdateDailyFriendsipsSets() {
		Dog dog = new Dog("dog", "Food", "Hunting dog");
		Chicken chicken = new Chicken("chicken", "Food", 0.5f, false);
		Parrot parrot = new Parrot("parrot", "Food", 0.5f, true);

		HashSet<Animal> goodAnimalsSet = new HashSet<Animal>();
		goodAnimalsSet.add(dog);
		goodAnimalsSet.add(chicken);
		goodAnimalsSet.add(parrot);
		Zoo goodZoo = new Zoo(goodAnimalsSet);
		goodZoo.incrementDay();
		goodZoo.addFirendship(parrot, dog);
		goodZoo.addFirendship(dog, chicken);
		goodZoo.addFirendship(parrot, chicken);

		AnimalFriendship friendshipOne = new AnimalFriendship(parrot, dog);
		AnimalFriendship friendshipTwo = new AnimalFriendship(dog, chicken);
		AnimalFriendship friendshipThree = new AnimalFriendship(parrot, chicken);

		Set<AnimalFriendship> dailyFriendsAddedSet = goodZoo.getDailyFriendsAdded();

		Assert.assertTrue(dailyFriendsAddedSet.contains(friendshipOne));
		Assert.assertTrue(dailyFriendsAddedSet.contains(friendshipTwo));
		Assert.assertTrue(dailyFriendsAddedSet.contains(friendshipThree));
	}

}
