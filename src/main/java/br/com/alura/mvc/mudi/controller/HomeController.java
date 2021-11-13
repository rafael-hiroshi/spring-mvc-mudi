package br.com.alura.mvc.mudi.controller;

import br.com.alura.mvc.mudi.model.Order;
import br.com.alura.mvc.mudi.model.OrderStatus;
import br.com.alura.mvc.mudi.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
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
        List<Order> orders = repository.findAll();
        model.addAttribute("orders", orders);
        return "home";
    }

    @GetMapping("/{status}")
    public String byStatus(@PathVariable("status") String status, Model model) {
        List<Order> orders = repository.findByStatus(OrderStatus.valueOf(status.toUpperCase()));
        model.addAttribute("orders", orders);
        model.addAttribute("status", status);
        return "home";
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public String onError() {
        return "redirect:/home";
    }
}
