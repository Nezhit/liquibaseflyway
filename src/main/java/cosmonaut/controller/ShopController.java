package cosmonaut.controller;

import cosmonaut.entity.Product;
import cosmonaut.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShopController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/shop")
    public String toShop(Model model, Pageable pageable) {
        model.addAttribute("products", productService.getAllProducts(pageable));
        return "shop";
    }

    @GetMapping("/shop/sort/name")
    public String shopSortedByName(Model model, Pageable pageable) {
        model.addAttribute("products", productService.getAllProducts(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("title"))));
        return "shop";
    }

    @GetMapping("/shop/sort/price")
    public String shopSortedByPrice(Model model, Pageable pageable) {
        model.addAttribute("products", productService.getAllProducts(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("price"))));
        return "shop";
    }

    @GetMapping("/shop/sort/date-of-arrival")
    public String shopSortedByDate(Model model, Pageable pageable) {
        model.addAttribute("products", productService.getAllProducts(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("arrivalTime"))));
        return "shop";
    }

    @GetMapping("/shop/sort/category")
    public String shopSortedByCategory(Model model, Pageable pageable) {
        model.addAttribute("products", productService.getAllProducts(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("productType"))));
        return "shop";
    }

    @GetMapping("/remove/{id}")
    public String deleteByProductId(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/shop";
    }
}
