package pl.coderslab.charity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CharityInformationService implements ICharityInformationService {
    private final InstitutionRepository institutionRepository;

    @Autowired
    public CharityInformationService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public List<Institution> getInstitutionsList() {
        return institutionRepository.findAll();
    }
}
