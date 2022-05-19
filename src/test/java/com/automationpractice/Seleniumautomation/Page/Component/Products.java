package com.automationpractice.Seleniumautomation.Page.Component;

import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class Products {
    List<WebElement> products;

    public Products(List<WebElement> products) {
        this.products = products;
    }
    public List<WebElement> getNames(){

        return products.stream().map(product->product.findElement(By.className("product-name")))
                .collect(Collectors.toList());
    }


}
