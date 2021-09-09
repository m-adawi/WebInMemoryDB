package WebInMemoryDB.WebInMemoryDB.controllers;

import WebInMemoryDB.WebInMemoryDB.DAO.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    UsersDAO usersDAO;

    @GetMapping
    String getAccountPage(){
        return "account";
    }

    @PostMapping
    String changePassword(@RequestParam String oldpass,
                          @RequestParam String newpass,
                          @RequestParam String renewpass,
                          Authentication auth,
                          Model model){
        String username = auth.getName();
        if(!newpass.equals(renewpass)) {
            model.addAttribute("match", "passwords don't match");
        } else if(!usersDAO.areValidCredentials(username, oldpass)){
            model.addAttribute("fail", "incorrect password");
        } else {
            usersDAO.changePassword(username, newpass);
            return "redirect:/logout";
        }
        return "account";
    }
}
