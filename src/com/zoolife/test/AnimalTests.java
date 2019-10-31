package com.zoolife.test;

import org.junit.Assert;
import org.junit.Test;

import com.zoolife.model.Chicken;
import com.zoolife.model.Dog;
import com.zoolife.model.Parrot;

/**
 * Tests of the Animal, Bird, Chicken, Parrot, and Dog classes
 *
 * @author Tarek Oraby
 * @version 1.0
 *
 */

public class AnimalTests {

	@Test
	public void chickensWithDifferentNameShouldBeConsideredDiffernt() {
		Chicken chickenOne = new Chicken("name", "Food", 0.5f, false);
		Chicken chickenTwoSameName = new Chicken("name_different", "Food", 0.5f, false);
		Assert.assertNotEquals(chickenOne, chickenTwoSameName);
	}

	@Test
	public void chickensWithSameNameShouldBeConsideredEqual() {
		Chicken chickenOne = new Chicken("name", "Food", 0.5f, false);
		Chicken chickenTwoSameName = new Chicken("name", "Food", 2f, true);
		Assert.assertEquals(chickenOne, chickenTwoSameName);
	}

	@Test
	public void constructingAnimalWithProperFieldsShouldRaiseNoException() {
		new Dog("name", "Food", "Hunting dog");
		new Chicken("name", "Food", 0.5f, false);
		new Parrot("name", "Food", 0.5f, true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructingChickenWithEmptyFavFoodShouldRaiseIllegalArgumentException() {
		new Chicken("name", "", 0.5f, false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructingChickenWithEmptyNameShouldRaiseIllegalArgumentException() {
		new Chicken("", "Food", 0.5f, false);
	}

	@Test(expected = NullPointerException.class)
	public void constructingChickenWithNullFavFoodShouldRaiseNullPointerException() {
		new Chicken("name", null, 0.5f, false);
	}

	@Test(expected = NullPointerException.class)
	public void constructingChickenWithNullNameShouldRaiseNullPointerException() {
		new Chicken(null, "Food", 0.5f, false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructingDogWithEmptyFavFoodShouldRaiseIllegalArgumentException() {
		new Dog("name", "", "Hunting dog");
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructingDogWithEmptyNameShouldRaiseIllegalArgumentException() {
		new Dog("", "Food", "Hunting dog");
	}

	@Test(expected = NullPointerException.class)
	public void constructingDogWithNullFavFoodShouldRaiseNullPointerException() {
		new Dog("name", null, "Hunting dog");
	}

	@Test(expected = NullPointerException.class)
	public void constructingDogWithNullNameShouldRaiseNullPointerException() {
		new Dog(null, "Food", "Hunting dog");
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructingParrotWithEmptyFavFoodShouldRaiseIllegalArgumentException() {
		new Parrot("name", "", 0.5f, false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructingParrotWithEmptyNameShouldRaiseIllegalArgumentException() {
		new Parrot("", "Food", 0.5f, false);
	}

	@Test(expected = NullPointerException.class)
	public void constructingParrotWithNullFavFoodShouldRaiseNullPointerException() {
		new Parrot("name", null, 0.5f, false);
	}

	@Test(expected = NullPointerException.class)
	public void constructingParrotWithNullNameShouldRaiseNullPointerException() {
		new Parrot(null, "Food", 0.5f, false);
	}

	@Test
	public void dogsWithDifferentNameShouldBeConsideredDiffernt() {
		Dog dogOne = new Dog("name", "Food", "Hunting dog");
		Dog dogTwoSameName = new Dog("name_different", "Food", "Hunting dog");
		Assert.assertNotEquals(dogOne, dogTwoSameName);
	}

	@Test
	public void dogsWithSameNameShouldBeConsideredEqual() {
		Dog dogOne = new Dog("name", "Food", "Hunting dog");
		Dog dogTwoSameName = new Dog("name", "Food_different", "Hunting dog_different");
		Assert.assertEquals(dogOne, dogTwoSameName);
	}

	@Test
	public void parrotsWithDifferentNameShouldBeConsideredDiffernt() {
		Parrot parrotOne = new Parrot("name", "Food", 0.5f, false);
		Parrot parrotTwoSameName = new Parrot("name_different", "Food", 0.5f, false);
		Assert.assertNotEquals(parrotOne, parrotTwoSameName);
	}

	@Test
	public void parrotsWithSameNameShouldBeConsideredEqual() {
		Parrot parrotOne = new Parrot("name", "Food", 0.5f, false);
		Parrot parrotTwoSameName = new Parrot("name", "Food", 2f, true);
		Assert.assertEquals(parrotOne, parrotTwoSameName);
	}
}
