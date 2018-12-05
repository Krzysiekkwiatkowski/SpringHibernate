package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.PersonDTO;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/form")
@Controller
public class PersonDetailController {
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String formGet(Model model){
        model.addAttribute("personDTO", new PersonDTO());
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    @ResponseBody
    public String formPost(@ModelAttribute PersonDTO personDTO){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(personDTO.getLogin() + " | " + personDTO.getPassword() + " | " + personDTO.getEmail() + " | " + personDTO.getFirstName() + " | " + personDTO.getLastName() + " | " + personDTO.getGender() + " | " + personDTO.getCountry() + " | " + personDTO.getNotes() + " | " + personDTO.isMailingList());
        for (String skill : personDTO.getProgrammingSkills()) {
            stringBuilder.append(" | " + skill);
        }
        for (String hobbie : personDTO.getHobbies()) {
            stringBuilder.append(" | " + hobbie);
        }
        return stringBuilder.toString();
    }

    @ModelAttribute("countries")
    public List<String> countries(){
        String[] countries = {"Poland", "Germany", "France", "Russia", "Denmark"};
        return Arrays.asList(countries);
    }

    @ModelAttribute("programmingSkills")
    public List<String> programmingSkills(){
        String[] programmingSkills = {"PHP", "Java", "Ruby", "Python", "C#"};
        return Arrays.asList(programmingSkills);
    }

    @ModelAttribute("hobbies")
    public List<String> hobbies(){
        String[] hobbies = {"Sport", "Music", "Programming", "Games", "Races"};
        return Arrays.asList(hobbies);
    }
}
