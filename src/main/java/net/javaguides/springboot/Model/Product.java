package net.javaguides.springboot.Model;

import javax.persistence.*;


@Entity
@Table(name = "product")
public class Product {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    
    @Column(name="name")
    private String name;
    private double price;
    private double quantity;
    private String des;
    private String imageName;




	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    private Category category;
    
    public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Product() {
		super();
		setQuantity(0);
		setPrice(0);
	}
	public Product(String name) {
		this.name = name;
	}

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public Product(String name, double price, double quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public Product(String name, double price, double quantity, String des) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.des = des;
	}

	public Product(String name, double price, double quantity, String des, String imageName) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.des = des;
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", des=" + des
				+ ", imageName=" + imageName + ", category=" + category.getName() + "]";
	}


	
	



}
