package common;

import java.awt.Image;
import java.io.Serializable;

//import java.awt.Image;

public class Item implements Serializable {
	private String Name;
	private int Price;
	private String Kind;
	private String Color;
	private server.Size Size;
	private String id;
	private Image image;
	private static int count = 0;

	public Item(String _name, int _Price, String _Kind, String _Color, server.Size _Size) {
		// TODO Auto-generated constructor stub
		setName(_name);
		setPrice(_Price);
		setKind(_Kind);
		setColor(_Color);
		setSize(_Size);
		setId(Integer.toString(++count));
	}
	public Item(String _name, int _Price, String _Kind, String _Color, server.Size _Size,String _id) {
		// TODO Auto-generated constructor stub
		setName(_name);
		setPrice(_Price);
		setKind(_Kind);
		setColor(_Color);
		setSize(_Size);
		setId(_id);
	}
	public Item(Item _item)
	{
		setName(_item.getName());
		setPrice(_item.getPrice());
		setKind(_item.getKind());
		setColor(_item.getColor());
		setSize(_item.getSize());
		setId(_item.getId());
	}
	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public String getKind() {
		return Kind;
	}

	public void setKind(String kind) {
		Kind = kind;
	}

	public server.Size getSize() {
		return Size;
	}

	public void setSize(server.Size size) {
		Size = size;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String to_String() {
		return Name + " " + Double.toString(Price) + " " + Kind + " " + Color + " " + Size + " " + id;
	}

	public String toString() {
		return id;
	}
}
