package com.calling_3rd_party_api.Controllers;

import com.calling_3rd_party_api.Dtos.ProductResponseDTO;
import com.calling_3rd_party_api.Dtos.ProductsResponseDTO;
import com.calling_3rd_party_api.Dtos.ResponseStatusDTO;
import com.calling_3rd_party_api.Models.Product;
import com.calling_3rd_party_api.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/product")
public class ProductController {
    // localhost:8080
    //localhost -> this the Ip address where my application is hosted (Address)
    //8080 -> port on the machine where the application is hosted (road to reach the address)
    //In our machine we have total 65,536 ports out of which only one port 8080 hosting my application

    //localhost:8080/Product -> Product is a path of our api and returns all the product
    //localhost:8080/Product/1
    //@GetMapping("/id") -> here id will be part of my path and its static end point
    //@GetMapping("/{id}") -> now id will act as placeholder or a variable which is used to filer get only
    //product whose Id is 1

    ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable("id") long id)
    {
        ProductResponseDTO productResponseDTO=new ProductResponseDTO();
        try{
            Product product= this.productService.getProduct(id);
            productResponseDTO.setProduct(product);
            productResponseDTO.setResponseStatusDTO(ResponseStatusDTO.SUCCESS);
        }catch(Exception e){
            System.out.println("Invalid Id please enter valid ID");
            productResponseDTO.setResponseStatusDTO(ResponseStatusDTO.FAILED);
        }
        return productResponseDTO;
    }

    @GetMapping
    public ProductsResponseDTO getAllProducts()
    {
        ProductsResponseDTO productsResponseDTO=new ProductsResponseDTO();
        try{
            List<Product> productList=this.productService.getProducts();
            productsResponseDTO.setProductList(productList);
            productsResponseDTO.setResponseStatus(ResponseStatusDTO.SUCCESS);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            productsResponseDTO.setResponseStatus(ResponseStatusDTO.FAILED);
        }
        return productsResponseDTO;
    }
}
