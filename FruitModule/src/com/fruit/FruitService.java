package com.fruit;

public class FruitService {
	
	Fruit fruit;

	public void printFruit()
	{
		System.out.println("Fruit : "+getFruit().getFruitName());
	}

	public Fruit getFruit() {
		return fruit;
	}

	public void setFruit(Fruit fruit) {
		this.fruit = fruit;
	}
	
}
