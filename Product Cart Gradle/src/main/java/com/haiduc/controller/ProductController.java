package com.haiduc.controller;

import com.haiduc.model.Product;
import com.haiduc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



@Controller
public class ProductController {
@Autowired
    private ProductService productService;

    // List All
    @GetMapping("/home")
    public ModelAndView listProduct(Pageable pageable){
        Page<Product> products = productService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
    // End List All

    //Create
    @GetMapping("/create-product")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @PostMapping("/create-product")
    public ModelAndView saveProduct(@ModelAttribute Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());//post dem ra html
        modelAndView.addObject("message", "New post created successfully");
        return modelAndView;
    }
    //End Create

    // Edit
    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Product product = productService.findById(id);
        if(product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("editProduct", product);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/product/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-product")
    public ModelAndView updateProduct(@ModelAttribute Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("editProduct", product); //editpost dua qua html
        modelAndView.addObject("message", "product updated successfully");
        return modelAndView;
    }

    //End Edit

    // Delete
    @PostMapping("/delete-product")
    public String deleteProduct(@ModelAttribute("post") Product product){
        productService.remove(product.getId());
        return "redirect:/home";
    }

    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Product product = productService.findById(id);
        if(product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("deleteProduct", product);//deletepost de dem qua html
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/product/error.404");
            return modelAndView;
        }
    }
    // End Delete
}
