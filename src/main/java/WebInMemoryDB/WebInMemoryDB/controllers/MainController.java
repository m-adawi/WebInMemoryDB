package WebInMemoryDB.WebInMemoryDB.controllers;

import DB.CommandGenerators.CommandsGenerator;
import DB.Commands.Command;
import DB.Commands.SelectCommand;
import DB.Database;
import DB.InvalidDatabaseOperationException;
import WebInMemoryDB.WebInMemoryDB.DAO.DatabaseDAO;
import WebInMemoryDB.WebInMemoryDB.DAO.UsersDAO;
import org.gibello.zql.ParseException;
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
    String serveQuery(@RequestParam String query, Model model) {
        return dao.executeSQLCommand(query);
    }

    @GetMapping("/test")
    String test(Model model) {
        CommandsGenerator commandsGenerator = CommandsGenerator.getCommandGenerator();
        Database database = Database.getDatabase();
        try {
            Command command = commandsGenerator.generateFromSqlQuery("select * from students;");
            String[][] table = database.executeQuery((SelectCommand) command);
            model.addAttribute("rows", table);
        } catch (ParseException | InvalidDatabaseOperationException e) {
            System.out.println(e.getMessage());
        }
        return "testTable";
    }
}
