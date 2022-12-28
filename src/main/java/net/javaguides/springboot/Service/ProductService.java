package net.javaguides.springboot.Service;

import net.javaguides.springboot.Model.Product;
import net.javaguides.springboot.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    
    public List<Product> getAllProduct(){
        return (List<Product>) productRepository.findAll();
    }
    public void addProduct(Product product){
        productRepository.save(product);
    }
    public void removeProductID(int id){
        productRepository.deleteById(id);
    }
    public Optional<Product> getProductID(int id){
        return productRepository.findById(id);
    }
    public  List<Product> getAllProductByCategoryId(int id){
        return productRepository.findAllByCategory_id(id);
    }





}
