package net.javaguides.springboot.Config;

import net.javaguides.springboot.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartConfig {
    public  static List<Product> cart;
    static {
        cart = new ArrayList<Product>();
    }
}
