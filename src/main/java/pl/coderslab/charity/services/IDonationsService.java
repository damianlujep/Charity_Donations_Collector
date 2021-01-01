package pl.coderslab.charity.services;

import pl.coderslab.charity.dto.DonationFormDto;
import pl.coderslab.charity.entity.Category;

import java.util.List;

public interface IDonationsService {
    List<Category> allCategoriesList();
    void saveCompletedDonation(DonationFormDto donationFormDto);
}
