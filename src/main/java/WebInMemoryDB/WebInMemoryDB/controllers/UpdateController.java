package WebInMemoryDB.WebInMemoryDB.controllers;

import DB.Attributes.StudentAttributeType;
import DB.InvalidDatabaseOperationException;
import DB.Record;
import WebInMemoryDB.WebInMemoryDB.DAO.DatabaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;

@Controller
@RequestMapping("/write/update")
public class UpdateController {
    @Autowired
    DatabaseDAO databaseDAO;

    @GetMapping
    String getPage(@RequestParam(value = "id", required = false) String id,
                   Model model) {
        if(id == null) {
            model.addAttribute("noID", true);
        } else if(!databaseDAO.containsID(id)) {
            model.addAttribute("error", "There is no record with ID: " + id);
            model.addAttribute("noID", true);
        } else {
            Record record = databaseDAO.selectRecord(id);
            model.addAttribute("attributes", StudentAttributeType.values());
            model.addAttribute("record", record);
        }
        return "db/update";
    }

    @PostMapping
    String updateRecord(@RequestParam String id,
                        @RequestParam HashMap<String, String> formData,
                        Model model){
        try {
            databaseDAO.updateRecord(id, databaseDAO.generateRecord(formData));
            model.addAttribute("success", "Updated record successfully");
        } catch (InvalidDatabaseOperationException e) {
            model.addAttribute("error", e.getMessage());
        }
        return getPage(id, model);
    }
}
