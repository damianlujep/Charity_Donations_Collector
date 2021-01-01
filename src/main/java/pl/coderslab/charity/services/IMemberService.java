package pl.coderslab.charity.services;

import pl.coderslab.charity.dto.MemberDto;
import pl.coderslab.charity.entity.Member;
import pl.coderslab.charity.error.UserAlreadyExistException;

public interface IMemberService {
    Member registerNewMember(MemberDto memberDto) throws UserAlreadyExistException;
    Member findMemberMyEmail(String email);
}
