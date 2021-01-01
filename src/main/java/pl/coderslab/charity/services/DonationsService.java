package pl.coderslab.charity.services;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.dto.DonationFormDto;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DonationsService implements IDonationsService{
    private final CategoryRepository categoryRepository;
    private final DonationRepository donationRepository;

    public DonationsService(CategoryRepository categoryRepository, DonationRepository donationRepository) {
        this.categoryRepository = categoryRepository;
        this.donationRepository = donationRepository;
    }

    @Override
    public List<Category> allCategoriesList() {
        return categoryRepository.findAll();
    }

    @Override
    public void saveCompletedDonation(DonationFormDto donationFormDto) {
        Donation newDonation = new Donation();
        newDonation.setCategories(donationFormDto.getCategories());
        newDonation.setBagsQuantity(donationFormDto.getBagsQuantity());
        newDonation.setCity(donationFormDto.getCity());
        newDonation.setInstitution(donationFormDto.getInstitution());
        newDonation.setStreet(donationFormDto.getStreet());
        newDonation.setPickUpdate(donationFormDto.getPickUpDate());
        newDonation.setPickUpTime(donationFormDto.getPickUpTime());
        newDonation.setPickUpComment(donationFormDto.getPickUpComment());
        newDonation.setZipCode(donationFormDto.getZipCode());

        donationRepository.save(newDonation);
    }
}
