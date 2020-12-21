package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.dto.DonationFormDto;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.services.ICharityInformationService;
import pl.coderslab.charity.services.IDonationsService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DonationController {
    private final IDonationsService donationsService;
    private final ICharityInformationService charityInformationService;

    public DonationController(IDonationsService donationsService, ICharityInformationService charityInformationService) {
        this.donationsService = donationsService;
        this.charityInformationService = charityInformationService;
    }

    @GetMapping("/form")
    public ModelAndView showDonationsForm(){
        return new ModelAndView("form", "donationModel", new DonationFormDto());
    }

    @PostMapping("/form")
    public ModelAndView donationsFormHandler(@Valid DonationFormDto donationModel, BindingResult result){
        if (result.hasErrors()){
            return new ModelAndView("form", "donationModel", donationModel);
        }

        return new ModelAndView("redirect:formConfirmation");
    }

    @GetMapping("/formConfirmation")
    public ModelAndView formConfirmation(){
        return new ModelAndView("form-confirmation");
    }

    @ModelAttribute("allCategoriesList")
    public List<Category> allCategoriesList(){
        return donationsService.allCategoriesList();
    }

    @ModelAttribute("institutionsList")
    public List<Institution> getAllInstitutionsList(){
        return charityInformationService.getInstitutionsList();
    }
}
