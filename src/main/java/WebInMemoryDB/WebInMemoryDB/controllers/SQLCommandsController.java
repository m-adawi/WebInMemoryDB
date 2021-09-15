package WebInMemoryDB.WebInMemoryDB.controllers;

import DB.CommandGenerators.CommandsGenerator;
import DB.CommandGenerators.UnsupportedSQLStatementException;
import DB.Commands.Command;
import DB.Commands.SelectCommand;
import DB.Database;
import DB.InvalidDatabaseOperationException;
import WebInMemoryDB.WebInMemoryDB.DAO.UsersDAO;
import org.gibello.zql.ParseException;
import org.gibello.zql.TokenMgrError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sql")
public class SQLCommandsController {
    @Autowired
    CommandsGenerator commandsGenerator;
    @Autowired
    Database database;
    @Autowired
    UsersDAO usersDAO;

    @GetMapping
    String getPage() {
        return "sql";
    }

    @PostMapping
    String executeSQLCommand(@RequestParam String query,
                             Model model,
                             Authentication auth) {
        try {
            Command command = commandsGenerator.generateFromSqlQuery(query+";");
            if(command instanceof SelectCommand) {
                String[][] table = database.executeQuery((SelectCommand) command);
                model.addAttribute("rows", table);
            } else {
                String result;
                if(usersDAO.isAllowedToWrite(auth.getName()))
                    result = database.execute(command);
                else
                    result = "Not permitted";
                model.addAttribute("message", result);
            }
        } catch (ParseException | TokenMgrError | InvalidDatabaseOperationException | UnsupportedSQLStatementException e) {
            model.addAttribute("message", e.getMessage());
        }
        model.addAttribute("sqlcommand", query);
        return "sql";
    }
}
