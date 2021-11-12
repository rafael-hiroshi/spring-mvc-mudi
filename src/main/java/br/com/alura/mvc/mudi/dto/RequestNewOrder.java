package br.com.alura.mvc.mudi.dto;


import br.com.alura.mvc.mudi.model.Order;
import br.com.alura.mvc.mudi.model.OrderStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RequestNewOrder {

    @NotBlank @Size(max = 300)
    private String productName;
    @NotBlank @Size(max = 1000)
    private String productUrl;
    @NotBlank @Size(max = 1000)
    private String imageUrl;
    @Size(max = 255)
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
        order.setStatus(OrderStatus.WAITING);
        return order;
    }
}
