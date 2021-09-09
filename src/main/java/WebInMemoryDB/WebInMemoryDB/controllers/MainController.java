package WebInMemoryDB.WebInMemoryDB.controllers;

import WebInMemoryDB.WebInMemoryDB.DAO.DatabaseDAO;
import WebInMemoryDB.WebInMemoryDB.DAO.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Service;

@Controller
public class MainController {
    @Autowired
    DatabaseDAO dao;


    @GetMapping("/")
    String in(Model model, Authentication auth) {
        model.addAttribute("isAdmin", auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
        return "home";
    }

    @ResponseBody
    @PostMapping("/")
    String serveQuery(HttpServletRequest request, Model model) {
        String query = request.getParameter("query");
        return dao.executeDatabaseQuery(query);
    }
}
