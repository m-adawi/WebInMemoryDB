package WebInMemoryDB.WebInMemoryDB.controllers;

import WebInMemoryDB.WebInMemoryDB.DAO.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    @Autowired
    UsersDAO usersDAO;

    @GetMapping("/")
    String in(Model model, Authentication auth) {
        model.addAttribute("isAdmin", usersDAO.isAdmin(auth));
        model.addAttribute("isWriter", usersDAO.isAllowedToWrite(auth));
        return "home";
    }
}
