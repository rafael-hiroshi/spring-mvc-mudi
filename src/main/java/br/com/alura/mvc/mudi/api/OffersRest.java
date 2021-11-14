package br.com.alura.mvc.mudi.api;

import br.com.alura.mvc.mudi.dto.RequestNewOffer;
import br.com.alura.mvc.mudi.model.Offer;
import br.com.alura.mvc.mudi.model.Order;
import br.com.alura.mvc.mudi.repository.OrderRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/offers")
public class OffersRest {

    private OrderRepository orderRepository;

    public OffersRest(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public Offer store(@Valid @RequestBody RequestNewOffer request) {
        Optional<Order> orderFound = orderRepository.findById(request.getOrderId());
        if (!orderFound.isPresent()) {
            return null;
        }
        Order order = orderFound.get();
        Offer newOffer = request.toOffer();

        newOffer.setOrder(order);
        order.getOffers().add(newOffer);
        orderRepository.save(order);

        return newOffer;
    }
}
