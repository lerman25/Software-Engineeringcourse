

import java.awt.Image;

//import java.awt.Image;

class Item {
		private	String	Name;
		private double  Price;
	    private  String	Kind;
		private	 String	Color;
		private	 String  Size;	
		private	 String     id  ;
		private Image    image;
	    
	
	    public Item(String _name,double  _Price, String	_Kind, String	_Color, String  _Size,String     _id ) {
			// TODO Auto-generated constructor stub
	    	setName(_name);
	    	setPrice(_Price);
	    	setKind(_Kind);
	    	setColor(_Color);
	    	setSize(_Size);
	    	setId(_id);
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
		public double getPrice() {
			return Price;
		}
		public void setPrice(double price) {
			Price = price;
		}
		public String getKind() {
			return Kind;
		}
		public void setKind(String kind) {
			Kind = kind;
		}
		public String getSize() {
			return Size;
		}
		public void setSize(String size) {
			Size = size;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String to_String()
		{
			return Name+" "+Double.toString(Price)+" "+Kind+" "+Color+" "+Size+" "+id;
		}
}

