package br.com.alura.mvc.mudi.dto;

import br.com.alura.mvc.mudi.model.Offer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequestNewOffer {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Long orderId;
    private String value;
    private String deliveryDate;
    private String comment;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Offer toOffer() {
        Offer offer = new Offer();
        offer.setComment(comment);
        offer.setDeliveryDate(LocalDate.parse(deliveryDate, formatter));
        offer.setValue(new BigDecimal(value));

        return offer;
    }
}
