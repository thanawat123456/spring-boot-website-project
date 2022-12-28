package net.javaguides.springboot.Controller;

import net.javaguides.springboot.Config.CartConfig;
import net.javaguides.springboot.DTO.CommentDTO;
import net.javaguides.springboot.DTO.ProductDTO;
import net.javaguides.springboot.Model.Comment;
import net.javaguides.springboot.Model.Product;
import net.javaguides.springboot.Service.CategoryService;
import net.javaguides.springboot.Service.CommentService;
import net.javaguides.springboot.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class ProductController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;


    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    //public String uploadDir = "src/main/resources/static/productImages";

    @GetMapping({"/all_products"})
    public String allProduct(Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("cartCount", CartConfig.cart.size());
        model.addAttribute("products",productService.getAllProduct());
        model.addAttribute("cartCount", CartConfig.cart.size());
        return "all_products";
    }



    @GetMapping("/admin/products")
    public String product(Model model){
        model.addAttribute("products",productService.getAllProduct());

        return "products";
    }



    @GetMapping("/admin/products/add")
    public String productAddGet(Model model){
        model.addAttribute("productDTO",new ProductDTO());
        model.addAttribute("categories",categoryService.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String productAddPost(@ModelAttribute("productDTO")ProductDTO products,
                                 @RequestParam("productImage") MultipartFile file,
                                 @RequestParam("imgName")String imgName) throws IOException {
        Product product = new Product();
        product.setId(products.getId());
        product.setName(products.getName());
        product.setCategory(categoryService.getCategoryById(products.getCatID()).get());
        product.setPrice(products.getPrice());
        product.setQuantity(products.getQuantity());
        product.setDes(products.getDes());
        StringBuilder fileNames = new StringBuilder();
        if (!file.isEmpty()) {
            java.nio.file.Path fileNameAndPath = Paths.get(uploadDir, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
        }
        else {
            System.out.println("No errors, and file empty");
        }
        product.setImageName(fileNames.toString());


        productService.addProduct(product);

        return "redirect:/admin/products";
    }

//	@PostMapping("/admin/products/add")
//	public String productAddPost(@Validated Product product, BindingResult result, Model model,
//								 @RequestParam("productImage")MultipartFile file,
//								 @RequestParam("imgName")String imgName) throws IOException {
//		if (result.hasErrors()) {
//			List<Category> categoryList = (List<Category>) categoryRepository.findAll();
//			model.addAttribute("categories", categoryList);
//			return "productsAdd";
//		}
//		String imageUUID;
//		if (!file.isEmpty()){
//			imageUUID = file.getOriginalFilename();
//			Path fileNameAndPath = (Path) Paths.get(uploadDir,imageUUID);
//			Files.write((java.nio.file.Path) fileNameAndPath,file.getBytes());
//		}else{
//			imageUUID = imgName;
//		}
//		product.setImageName((imageUUID));
//		productRepository.save(product);
//		return "redirect:/admin/products";
//	}

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        productService.removeProductID(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable int id,Model model){
        Product pd = productService.getProductID(id).get();
        ProductDTO products = new ProductDTO();
        products.setId(pd.getId());
        products.setName(pd.getName());
        products.setCatID(pd.getCategory().getId());
        products.setPrice(pd.getPrice());
        products.setQuantity(pd.getQuantity());
        products.setDes(pd.getDes());
        products.setImageName(pd.getImageName());

        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("productDTO",products);
        return "productsAdd";

    }




}
