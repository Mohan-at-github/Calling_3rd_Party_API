package com.calling_3rd_party_api.Services;

import com.calling_3rd_party_api.Dtos.FakeProductServiceDto;
import com.calling_3rd_party_api.Models.Category;
import com.calling_3rd_party_api.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService{
//   private RestTemplate restTemplate;
//
//   @Autowired
//   public FakeStoreProductServiceImpl(RestTemplate restTemplate) {
//       this.restTemplate = restTemplate;
//   }
    private WebClient webClient;
    public FakeStoreProductServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    //fake-product-Dto -> product
    private Product convertDtoToProduct(FakeProductServiceDto dto)
    {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        Category category=new Category();
        category.setName(String.valueOf(dto.getCategory()));
        product.setCategory(category);
        return product;

    }
    //getting productDTO by ID
    @Override
    public Product getProduct(long id) {
        //using rest template
        //FakeProductServiceDto productDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeProductServiceDto.class);

        //using Webclient
        FakeProductServiceDto productDto = webClient.get()
                .uri("https://fakestoreapi.com/products/"+id)
                .retrieve()
                .bodyToMono(FakeProductServiceDto.class).block();
        assert productDto!=null;
        return convertDtoToProduct(productDto);
    }

    //Getting all the product
    @Override
   public ArrayList<Product> getProducts() {
        /*
        using rest template
        FakeProductServiceDto[] productDto = restTemplate.getForObject("https://fakestoreapi.com/products", FakeProductServiceDto[].class);
        ArrayList<Product> products=new ArrayList<>();
        for(FakeProductServiceDto dto:productDto){
            Product product =convertDtoToProduct(dto);
            products.add(product);
        }
         */

        //Using webclient
        FakeProductServiceDto[] productDto = webClient.get()
                .uri("https://fakestoreapi.com/products")
                .retrieve()
                .bodyToMono(FakeProductServiceDto[].class).block();
        assert productDto!=null;

        ArrayList<Product> products=new ArrayList<>();
        for(FakeProductServiceDto dto:productDto) {
            Product product = convertDtoToProduct(dto);
            products.add(product);
        }
        return products;

  }

}
