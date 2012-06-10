#Simple json mapper for android

##What is it?
Simple json mapper allows you to map json strings directly to java objects. Although there are many great libs with the same purpose, I find them too cumbersome for the kind of apps I'm constantly working on.

**THIS IS NOT A FULL JSON-TO-OBJECT MAPPER**. If you're looking for a full featured mapper i suggest you take a look at Jackson or Gson. Use simple json mapper if you're working with simple or small pieces of json data  that you have control over (Almost all the json APIs I use are created by me).

##Usage
Let's say we have a `Product` class that looks like this:

 	public class Product {
	
		private String name;
		private int amount;
		private double price;
	}

And our json looks like this:

	{"name": "potato", "amount": 10, "price": 2.5}

To map our json the a `Product` object we can simply:

	ObjectMapper objectMapper = new ObjectMapper();
	Product product = objectMapper.map(jsonString, Product.class);

