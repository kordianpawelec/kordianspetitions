package net.javaguides.kordianspetitions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index"; // Refers to index.html in src/main/resources/templates
    }
}
