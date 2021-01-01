package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.dto.MemberDto;
import pl.coderslab.charity.entity.Member;
import pl.coderslab.charity.error.UserAlreadyExistException;
import pl.coderslab.charity.services.ICharityInformationService;
import pl.coderslab.charity.services.IMemberService;

import javax.validation.Valid;

@Controller
public class HomeController {
    private final ICharityInformationService charityInformationService;
    private final IMemberService memberService;

    @Autowired
    public HomeController(ICharityInformationService charityInformationService, IMemberService memberService) {
        this.charityInformationService = charityInformationService;
        this.memberService = memberService;
    }

    @GetMapping("/")
    public ModelAndView homeAction(){
        ModelAndView homePage = new ModelAndView("index");
        homePage.addObject("institutionsList", charityInformationService.getInstitutionsList());
        homePage.addObject("sumAllBagsDonated",charityInformationService.getSumAllBagsDonated());
        homePage.addObject("donationsNumber", charityInformationService.countAllDonationsRealized());

        return homePage;
    }

    @GetMapping("/login")
    public ModelAndView showLoginForm(){
      return new ModelAndView("login");
    }

    @GetMapping("/registration")
    public ModelAndView showRegistrationForm(){
        return new ModelAndView("register", "newMember", new MemberDto());
    }

    @PostMapping("/registration")
    public ModelAndView registrationFormHandler(@ModelAttribute("newMember") @Valid MemberDto memberDto, BindingResult result){
        Member memberRegistered;

        if (result.hasErrors()){
            return new ModelAndView("register", "newMember", memberDto);
        }

        try{
            memberRegistered = memberService.registerNewMember(memberDto);
        } catch (UserAlreadyExistException e){
            ModelAndView mav = new ModelAndView("/register", "newMember", memberDto);
            mav.addObject("message", "Istnieje już członek z adresem e-mail " + memberDto.getEmail());
            return mav;
        }

        return new ModelAndView("redirect: login", "memberRegistered", memberRegistered);

    }

}
