package WebInMemoryDB.WebInMemoryDB.controllers;

import DB.Attributes.StudentAttributeType;
import DB.Attributes.StudentID;
import DB.InvalidDatabaseOperationException;
import DB.Record;
import WebInMemoryDB.WebInMemoryDB.DAO.DatabaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/write/insert")
public class InsertController {
    @Autowired
    DatabaseDAO databaseDAO;

    @GetMapping
    String getPage(Model model) {
        model.addAttribute("attributes", StudentAttributeType.values());
        return "db/insert";
    }

    @PostMapping
    String insertRecord(@RequestParam HashMap<String, String> formData, Model model) {
        String id = formData.get(StudentAttributeType.ID.name());
        if(id == null || id.equals("")) {
            model.addAttribute("error", "You must provide an ID");
        } else {
            try {
                databaseDAO.insertRecord(databaseDAO.generateRecord(formData));
                model.addAttribute("success", "Inserted a record successfully");
            } catch (InvalidDatabaseOperationException e) {
                model.addAttribute("error", e.getMessage());
            }
        }
        return getPage(model);
    }
}
