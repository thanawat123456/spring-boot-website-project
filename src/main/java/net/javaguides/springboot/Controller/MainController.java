package net.javaguides.springboot.Controller;

import net.javaguides.springboot.Config.CartConfig;
import net.javaguides.springboot.DTO.CommentDTO;
import net.javaguides.springboot.Model.Comment;
import net.javaguides.springboot.Model.Product;
import net.javaguides.springboot.Service.CategoryService;
import net.javaguides.springboot.Service.CommentService;
import net.javaguides.springboot.Service.ProductService;
import net.javaguides.springboot.Service.UserRegisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	UserRegisService userRegisService;

	@Autowired
	CommentService commentService;

	@GetMapping("/login")
	public String login() {
		CartConfig.cart.clear();
		return "login2";
	}

	@GetMapping({"/","/home"})
	public String home(Model model){
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("cartCount", CartConfig.cart.size());
		model.addAttribute("products",productService.getAllProduct());
		return "index2";
	}

	@GetMapping("/admin/users")
	public String getUser(Model model) {
		model.addAttribute("user",userRegisService.getAllUser());
		return "users";
	}

	@GetMapping("/admin/user/delete/{id}")
	public String deleteUser(@PathVariable int id){
		userRegisService.removeUser(id);
		return "redirect:/admin/users";
	}

	@PostMapping("/contact")
	public String addcomment(@ModelAttribute("commentsDTO") CommentDTO commentDTO){
		Comment comment = new Comment();
		comment.setFirstName(commentDTO.getFirstName());
		comment.setLastName(commentDTO.getLastName());
		comment.setEmail(commentDTO.getEmail());
		comment.setPhone(commentDTO.getPhone());
		comment.setComment(commentDTO.getComment());
		commentService.adComment(comment);
		return "redirect:/contact";
	}
	@GetMapping("/contact")
	public String contactPage(Model model){
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("cartCount", CartConfig.cart.size());
		model.addAttribute("commentsDTO", new CommentDTO());
		model.addAttribute("products",productService.getAllProduct());return "contact";
	}

	@GetMapping("/admin/comments")
	public String getComments(Model model) {
		model.addAttribute("comment",commentService.getAllComment());
		return "comments";
	}

	@GetMapping("/admin/comments/delete/{id}")
	public String deleteComments(@PathVariable int id){
		commentService.removeCommentID(id);
		return "redirect:/admin/comments";
	}

	@GetMapping("/about")
	public String aboutPage(Model model){

		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("cartCount", CartConfig.cart.size());
		model.addAttribute("products",productService.getAllProduct());return "about";
	}

	@GetMapping("/blog")
	public String blogPage(Model model){

		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("cartCount", CartConfig.cart.size());
		model.addAttribute("products",productService.getAllProduct());return "blog";
	}

	@GetMapping("/our_story")
	public String our_storyPage(Model model){

		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("cartCount", CartConfig.cart.size());
		model.addAttribute("products",productService.getAllProduct());return "our_story";
	}



	@GetMapping("/faq")
	public String faqPage(Model model){
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("cartCount", CartConfig.cart.size());
		model.addAttribute("products",productService.getAllProduct());
		return "faq";
	}


	@GetMapping("/shop")
	public String shop(Model model){
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("cartCount", CartConfig.cart.size());
		model.addAttribute("products",productService.getAllProduct());
		return "living_room";
	}


	@GetMapping("/shop/category/{id}")
	public String shopCat(Model model,@PathVariable int id){
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("cartCount", CartConfig.cart.size());
		model.addAttribute("products",productService.getAllProductByCategoryId(id));
		return "living_room";
	}

	

	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model,@PathVariable int id){
		model.addAttribute("product",productService.getProductID(id).get());
		model.addAttribute("cartCount", CartConfig.cart.size());

		return "product_detail";
	}

	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id){
		CartConfig.cart.add(productService.getProductID(id).get());
		return "redirect:/shop";
	}

	@GetMapping("/cart")
	public String cartGet(Model model){
		model.addAttribute("cartCount", CartConfig.cart.size());
		model.addAttribute("total", CartConfig.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart", CartConfig.cart);
		model.addAttribute("categories",categoryService.getAllCategory());
		return "mycart";
	}

	@GetMapping("/cart/removeItem/{index}")
	public String cartItemRemove(@PathVariable int index){
		CartConfig.cart.remove(index);
		return "redirect:/cart";
	}

	@GetMapping("/checkout")
	public String checkout(Model model){
		model.addAttribute("total", CartConfig.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("cartCount", CartConfig.cart.size());
		return "checkout";
	}







}
