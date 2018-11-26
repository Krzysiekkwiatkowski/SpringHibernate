package pl.coderslab.controller;


import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entity.PersonDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@Transactional
public class PersonDetailController {

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String form(Model model) {
        model.addAttribute("countries", countries());
        model.addAttribute("programmingSkills", programmingSkills());
        model.addAttribute("hobbies", hobbies());
        model.addAttribute("personDTO", new PersonDTO());
        return "detailsForm";
    }

    @RequestMapping(value = "/details", method = RequestMethod.POST)
    @ResponseBody
    public String info(@ModelAttribute PersonDTO personDTO){
        String programmingSkills = "";
        for (String programmingSkill : personDTO.getProgrammingSkills()) {
            programmingSkills += programmingSkill + " ";
        }
        String hobbies = "";
        for (String hobbie : personDTO.getHobbies()) {
            hobbies += hobbie + " ";
        }
        return personDTO.getLogin() + " " + personDTO.getPassword() + " " + personDTO.getEmail() + " " + personDTO.getFirstName() + " " + personDTO.getLastName() + " " + personDTO.getGender() + " " + personDTO.getCountry() + " " + personDTO.getNotes() + " " + personDTO.isMailingList() + " " + programmingSkills + hobbies;
    }

    @ModelAttribute("countries")
    public List<String> countries(){
        String[] countries = {"Poland", "Germany", "France", "England", "Russia"};
        return Arrays.asList(countries);
    }

    @ModelAttribute("programmingSkills")
    public List<String> programmingSkills(){
        String[] programmingSkills = {"Java", "Ruby", "Javascript", "PHP", "Python"};
        return Arrays.asList(programmingSkills);
    }

    @ModelAttribute("hobbies")
    public List<String> hobbies(){
        List<String> hobbies = new ArrayList<>();
        hobbies.add("Sport");
        hobbies.add("Music");
        hobbies.add("Movies");
        hobbies.add("Cars");
        hobbies.add("Welding");
        return hobbies;
    }
}
