package pl.coderslab.charity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.coderslab.charity.entity.Member;
import pl.coderslab.charity.entity.MemberRole;
import pl.coderslab.charity.repository.MemberRepository;
import pl.coderslab.charity.repository.MemberRoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyMemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;

    @Autowired
    public MyMemberDetailsService(MemberRepository memberRepository, MemberRoleRepository memberRoleRepository) {
        this.memberRepository = memberRepository;
        this.memberRoleRepository = memberRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member currentMember = memberRepository.findByEmail(email);
        if (currentMember == null){
            throw new UsernameNotFoundException("Member not found");
        }

        List<String> roles = new ArrayList<>();
        List<MemberRole> currentMemberRoles = memberRoleRepository.findAllByMemberEmail(email);
        for (MemberRole a : currentMemberRoles){
            roles.add(a.getRole());
        }

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roles.size() > 0){
            for (String role : roles){
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
                grantList.add(authority);
            }
        }

        return new User(currentMember.getEmail(), currentMember.getPassword(), grantList);
    }
}
