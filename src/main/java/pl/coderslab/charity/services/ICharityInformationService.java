package pl.coderslab.charity.services;

import pl.coderslab.charity.entity.Institution;

import java.util.List;

public interface ICharityInformationService {
    List<Institution> getInstitutionsList();
    int getSumAllBagsDonated();
    int countAllDonationsRealized();
}
