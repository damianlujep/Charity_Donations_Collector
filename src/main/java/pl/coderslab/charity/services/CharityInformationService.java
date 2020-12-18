package pl.coderslab.charity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CharityInformationService implements ICharityInformationService {
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    @Autowired
    public CharityInformationService(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @Override
    public List<Institution> getInstitutionsList() {
        return institutionRepository.findAll();
    }

    @Override
    public int getSumAllBagsDonated() {
        return donationRepository.sumAllBagsDonated();
    }

    @Override
    public int countAllDonationsRealized() {
        return donationRepository.countAllDonations();
    }
}
