package WebInMemoryDB.WebInMemoryDB.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/in")
    String in() {
        return "welcome";
    }
}
