package com.calling_3rd_party_api.Services;

import com.calling_3rd_party_api.Models.Product;

import java.util.ArrayList;

public interface ProductService {
    public Product getProduct(long id);

    public ArrayList<Product> getProducts();
}
