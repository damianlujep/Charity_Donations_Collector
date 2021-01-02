package pl.coderslab.charity.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.dto.DonationFormDto;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.Member;
import pl.coderslab.charity.services.ICharityInformationService;
import pl.coderslab.charity.services.IDonationsService;
import pl.coderslab.charity.services.IMemberService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/donations")
public class DonationController {
    private final IDonationsService donationsService;
    private final ICharityInformationService charityInformationService;
    private final IMemberService memberService;

    public DonationController(IDonationsService donationsService, ICharityInformationService charityInformationService, IMemberService memberService) {
        this.donationsService = donationsService;
        this.charityInformationService = charityInformationService;
        this.memberService = memberService;
    }

    @GetMapping("/form")
    public ModelAndView showDonationsForm(HttpSession session, Authentication auth){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            String email = auth.getName();
            Member currentMember = memberService.findMemberMyEmail(email);
            session.setAttribute("currentAdminLogged", currentMember.getName());
        }

        return new ModelAndView("form", "donationModel", new DonationFormDto());
    }

    @PostMapping("/form")
    public ModelAndView donationsFormHandler(@Valid DonationFormDto donationModel, BindingResult result){
        if (result.hasErrors()){
            return new ModelAndView("form", "donationModel", donationModel);
        }

        donationsService.saveCompletedDonation(donationModel);

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
