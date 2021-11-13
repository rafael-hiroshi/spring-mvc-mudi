package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.model.Order;
import br.com.alura.mvc.mudi.model.OrderStatus;
import br.com.alura.mvc.mudi.repository.OrderRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public UserController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("orders")
    public String home(Model model, Principal principal) {
        List<Order> orders = orderRepository.findAllByUser(principal.getName());
        model.addAttribute("orders", orders);
        return "user/home";
    }

    @GetMapping("orders/{status}")
    public String byStatus(@PathVariable("status") String status, Model model, Principal principal) {
        List<Order> orders = orderRepository.findByStatusAndUser(OrderStatus.valueOf(status.toUpperCase()), principal.getName());
        model.addAttribute("orders", orders);
        model.addAttribute("status", status);
        return "user/home";
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public String onError() {
        return "redirect:/user/home";
    }

}
