package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Integer> {
    @Query("SELECT COALESCE(SUM(d.bagsQuantity), 0) FROM Donation d")
    int sumAllBagsDonated();

    @Query("SELECT COUNT(d) FROM Donation d")
    int countAllDonations();

}
