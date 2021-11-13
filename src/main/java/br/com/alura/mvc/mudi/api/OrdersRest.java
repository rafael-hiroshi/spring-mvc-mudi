package br.com.alura.mvc.mudi.api;

import br.com.alura.mvc.mudi.model.Order;
import br.com.alura.mvc.mudi.model.OrderStatus;
import br.com.alura.mvc.mudi.repository.OrderRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersRest {

    private OrderRepository orderRepository;

    public OrdersRest(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("waiting")
    public List<Order> getOrdersWaitingForOffers() {
        Sort sort = Sort.by("id").descending();
        PageRequest paging = PageRequest.of(0, 10, sort);

        return orderRepository.findByStatus(OrderStatus.WAITING, paging);
    }
}
