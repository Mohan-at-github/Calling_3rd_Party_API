package com.calling_3rd_party_api.Dtos;

import com.calling_3rd_party_api.Models.Product;
import lombok.Data;

import java.util.List;
@Data
public class ProductsResponseDTO {
    List<Product> productList;
    ResponseStatusDTO responseStatus;
}
