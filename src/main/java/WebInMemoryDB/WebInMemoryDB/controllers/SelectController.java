package WebInMemoryDB.WebInMemoryDB.controllers;

import DB.Attributes.StudentAttributeType;
import DB.Commands.SelectCommand;
import WebInMemoryDB.WebInMemoryDB.DAO.DatabaseDAO;
import WebInMemoryDB.WebInMemoryDB.pojo.SelectedAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/read/select")
public class SelectController {
    @Autowired
    DatabaseDAO databaseDAO;

    @GetMapping
    public ModelAndView select() {
        SelectedAttributes selectedAttributes = new SelectedAttributes();
        selectedAttributes.setSelectedAttributes(StudentAttributeType.values());
        return new ModelAndView("db/select", "command", selectedAttributes);
    }

    @PostMapping
    ModelAndView selectRecords(@ModelAttribute SelectedAttributes selectedAttributes,
                         Model model) {
        try {
            String sql = generateSqlCommand(selectedAttributes);
            model.addAttribute("rows", databaseDAO.executeQuery(sql));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return new ModelAndView("db/select", "command", selectedAttributes);
    }

    String generateSqlCommand(SelectedAttributes selectedAttributes) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select ");
        StudentAttributeType[] selected = selectedAttributes.getSelectedAttributes();
        stringBuilder.append(selected[0].name());
        for(int i = 1; i < selected.length; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(selected[i].name());
        }
        stringBuilder.append(" from students ");
        if(!selectedAttributes.getConditionAttribute().equalsIgnoreCase("none")) {
            stringBuilder.append("where ");
            stringBuilder.append(selectedAttributes.getConditionAttribute());
            stringBuilder.append(selectedAttributes.getOperator());
            stringBuilder.append(selectedAttributes.getAttributeValue());
        }
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    @ModelAttribute("attributes")
    public StudentAttributeType[] getAllAttributes() {
        return StudentAttributeType.values();
    }

    @ModelAttribute("operators")
    public String[] getOperators() {
        String[] operators = {"=", "<", ">", "<=", ">="};
        return operators;
    }
}
