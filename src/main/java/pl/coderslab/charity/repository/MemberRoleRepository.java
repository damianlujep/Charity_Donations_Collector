package pl.coderslab.charity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.MemberRole;

import java.util.List;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRole, Integer> {
    List<MemberRole> findAllByMemberEmail(String email);
}
