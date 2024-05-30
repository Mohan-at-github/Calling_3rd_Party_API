package com.calling_3rd_party_api.Dtos;

import com.calling_3rd_party_api.Models.Category;
import lombok.Data;

@Data
public class FakeProductServiceDto {
    private long id;
    private String title;
    private double price;
    private String description;
    private String category;
}
