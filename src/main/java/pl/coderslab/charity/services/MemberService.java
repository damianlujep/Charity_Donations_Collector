package pl.coderslab.charity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.dto.MemberDto;
import pl.coderslab.charity.entity.Member;
import pl.coderslab.charity.entity.MemberRole;
import pl.coderslab.charity.error.UserAlreadyExistException;
import pl.coderslab.charity.repository.MemberRepository;
import pl.coderslab.charity.repository.MemberRoleRepository;


@Transactional
@Service
public class MemberService implements IMemberService{
    private final MemberRepository memberRepository;
    private final MemberRoleRepository memberRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberRoleRepository memberRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.memberRoleRepository = memberRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Member registerNewMember(MemberDto memberDto) throws UserAlreadyExistException {
        if (emailExists(memberDto.getEmail())){
            throw new UserAlreadyExistException("Exist an account with that email: " + memberDto.getEmail());
        }

        final Member newMember = new Member();
        newMember.setName(memberDto.getFirstName());
        newMember.setLastName(memberDto.getLastName());
        newMember.setEmail(memberDto.getEmail());
        newMember.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
        newMember.setPhone(memberDto.getPhone());

        final MemberRole newRole = new MemberRole();
        newRole.setMember(newMember);
        newRole.setRole("USER");

        memberRoleRepository.save(newRole);
        return memberRepository.save(newMember);
    }

    @Override
    public Member findMemberMyEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    private boolean emailExists(String email){
        return memberRepository.findByEmail(email) != null;
    }
}
