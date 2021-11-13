package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.model.Order;
import br.com.alura.mvc.mudi.model.OrderStatus;
import br.com.alura.mvc.mudi.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final OrderRepository repository;

    public HomeController(OrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String home(Model model) {
        List<Order> orders = repository.findByStatus(OrderStatus.DELIVERED);
        model.addAttribute("orders", orders);
        return "home";
    }
}
