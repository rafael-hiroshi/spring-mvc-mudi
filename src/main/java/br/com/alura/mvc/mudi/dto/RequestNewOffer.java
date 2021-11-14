package br.com.alura.mvc.mudi.dto;

import br.com.alura.mvc.mudi.model.Offer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequestNewOffer {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @NotNull
    private Long orderId;
    @NotNull @Pattern(regexp = "^\\d+(\\.\\d+{2})?$")
    private String transactionValue;
    @NotNull @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}")
    private String deliveryDate;
    private String comment;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(String transactionValue) {
        this.transactionValue = transactionValue;
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
        offer.setValue(new BigDecimal(transactionValue));

        return offer;
    }
}
