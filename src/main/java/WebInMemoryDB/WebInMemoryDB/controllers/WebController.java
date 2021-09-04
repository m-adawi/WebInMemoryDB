package WebInMemoryDB.WebInMemoryDB.controllers;

import WebInMemoryDB.WebInMemoryDB.DAO.DatabaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Service;

@Controller
public class WebController {
    @Autowired
    DatabaseDAO dao;

    @GetMapping("/")
    String in() {
        return "home";
    }

    @ResponseBody
    @PostMapping("/")
    String serveQuery(HttpServletRequest request, Model model) {
        String query = request.getParameter("query");
        return dao.executeDatabaseQuery(query);
    }
}
