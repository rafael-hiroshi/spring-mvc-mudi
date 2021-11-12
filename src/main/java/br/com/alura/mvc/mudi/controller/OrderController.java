package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.dto.RequestNewOrder;
import br.com.alura.mvc.mudi.model.Order;
import br.com.alura.mvc.mudi.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("order")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("form")
    public String form(RequestNewOrder request) {
        return "order/form";
    }

    @PostMapping("register")
    public String register(@Valid RequestNewOrder request, BindingResult result) {
        if (result.hasErrors()) {
            return "order/form";
        }
        Order order = request.toOrder();
        orderRepository.save(order);

        return "redirect:/home";
    }
}
