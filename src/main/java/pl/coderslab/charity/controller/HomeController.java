package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.services.ICharityInformationService;

import java.util.List;


@Controller
public class HomeController {
    private final ICharityInformationService charityInformationService;

    @Autowired
    public HomeController(ICharityInformationService charityInformationService) {
        this.charityInformationService = charityInformationService;
    }

    @RequestMapping("/")
    public ModelAndView homeAction(){
        ModelAndView homePage = new ModelAndView("index");
        List<Institution> institutionsList = charityInformationService.getInstitutionsList();
        homePage.addObject("institutionsList", institutionsList);

        return homePage;
    }
}
