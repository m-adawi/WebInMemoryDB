package WebInMemoryDB.WebInMemoryDB.controllers;

import DB.InvalidDatabaseOperationException;
import DB.Storages.RecordNotFoundException;
import WebInMemoryDB.WebInMemoryDB.DAO.DatabaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("write/delete")
public class DeleteController {
    @Autowired
    DatabaseDAO databaseDAO;

    @GetMapping
    String getPage(){
        return "db/delete";
    }

    @PostMapping
    String deleteRecord(Model model, @RequestParam String id) {
        try {
            databaseDAO.deleteRecord(id);
            model.addAttribute("success", "deleted record with ID: " + id + " successfully");
        } catch (InvalidDatabaseOperationException | RecordNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "db/delete";
    }
}
