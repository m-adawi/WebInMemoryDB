package WebInMemoryDB.WebInMemoryDB.controllers;

import WebInMemoryDB.WebInMemoryDB.DAO.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/management")
public class ManagementController {
    @Autowired
    UsersDAO usersDAO;

    @GetMapping
    String managementPage() {
        return "management/main";
    }

    @GetMapping("/add")
    String getAddUserPage() {
        return "management/addUser";
    }

    @PostMapping("/add")
    String addUser(@RequestParam String userType,
                   @RequestParam String username,
                   @RequestParam String password,
                   Model model
                   ) {
        try {
            usersDAO.addUser(username, password, userType);
            model.addAttribute("success", "user " + username + " was added successfully");
        } catch (DuplicateKeyException e) {
            model.addAttribute("used", "username " + username + " is used");
        }
        return getAddUserPage();
    }

    @GetMapping("/remove")
    String getRemoveUserPage() {
        return "management/removeUser";
    }

    @PostMapping("/remove")
    String removeUser(@RequestParam String username, Model model) {
        boolean deleted = usersDAO.removeUser(username);
        if(deleted)
            model.addAttribute("success", "user " + username + " was removed successfully");
        else
            model.addAttribute("fail", "No such user: "+ username);
        return getRemoveUserPage();
    }
}
