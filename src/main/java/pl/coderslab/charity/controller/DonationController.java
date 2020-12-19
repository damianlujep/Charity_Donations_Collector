package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DonationController {
    @GetMapping("form")
    public ModelAndView showDonationsForm(){
        return new ModelAndView("form");
    }
}
