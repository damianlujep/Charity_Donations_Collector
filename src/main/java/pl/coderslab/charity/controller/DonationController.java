package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.dto.DonationFormDto;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.services.IDonationsService;

import java.util.List;

@Controller
public class DonationController {
    private final IDonationsService donationsService;

    public DonationController(IDonationsService donationsService) {
        this.donationsService = donationsService;
    }

    @GetMapping("form")
    public ModelAndView showDonationsForm(){
        return new ModelAndView("form", "donationModel", new DonationFormDto());
    }

    @PostMapping("form")
    public ModelAndView donationsFormHandler(){
        return new ModelAndView("form", "donationModel", new DonationFormDto());
    }

    @ModelAttribute("allCategoriesList")
    public List<Category> allCategoriesList(){
        return donationsService.allCategoriesList();
    }
}
