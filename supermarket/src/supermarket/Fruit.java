package supermarket;

public class Fruit {
	private String name;
	private int price;
	private int weight;
	private String id;
	private String unit;
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Fruit(String id, String name, int price, String unit) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.unit = unit;
	}

	public int getMoney() {
		return price*weight;
	}
}
