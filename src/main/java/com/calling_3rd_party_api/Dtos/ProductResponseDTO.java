package com.calling_3rd_party_api.Dtos;

import com.calling_3rd_party_api.Models.Product;
import lombok.Data;

@Data
public class ProductResponseDTO {
    Product product;
    ResponseStatusDTO responseStatusDTO;
}
