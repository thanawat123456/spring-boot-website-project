package net.javaguides.springboot.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(targetEntity=Product.class, mappedBy="category",
    		cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	 private List<Product> products;
    public List<Product> getProducts(){
    	return products;
    };
    public void setProducts(List<Product> p) {
    	this.products=p;
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
	
	
	public Category(){

	}
    public Category(int id){
    	this.id = id;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	
    

}
