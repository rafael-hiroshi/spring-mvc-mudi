package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.dto.RequestNewOrder;
import br.com.alura.mvc.mudi.model.Order;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.OrderRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserRepository userRepository;

    public OrderController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
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

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        Order order = request.toOrder();
        order.setUser(user);
        orderRepository.save(order);
        return "redirect:/home";
    }
}
