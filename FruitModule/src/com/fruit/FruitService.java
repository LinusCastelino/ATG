package com.fruit;

import atg.nucleus.GenericService;

public class FruitService extends GenericService {
	
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
