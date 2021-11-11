package br.com.alura.mvc.mudi.dto;


import br.com.alura.mvc.mudi.model.Order;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

public class RequestNewOrder {

    @NotBlank @Min(5) @Max(30)
    private String productName;
    @NotBlank
    private String productUrl;
    @NotBlank
    private String imageUrl;
    private String description;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Order toOrder() {
        Order order = new Order();
        order.setDescription(description);
        order.setProductName(productName);
        order.setImageUrl(imageUrl);
        order.setProductUrl(productUrl);
        return order;
    }
}
