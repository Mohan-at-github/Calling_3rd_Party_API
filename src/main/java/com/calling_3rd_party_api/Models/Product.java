package com.calling_3rd_party_api.Models;

import lombok.Data;
@Data
public class Product {
    long id;
    String title;
    double price;
    String description;
   Category category; //category -> as class so that we can have Id and name of the category
   String image;//we will get image url so an image type is string

}
