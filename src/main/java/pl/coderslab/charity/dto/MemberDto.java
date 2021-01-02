package pl.coderslab.charity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.coderslab.charity.validator.PasswordMatches;
import pl.coderslab.charity.validator.ValidEmail;
import pl.coderslab.charity.validator.ValidPassword;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@ToString
@PasswordMatches
public class MemberDto {
    @NotBlank
    @Size(min = 2, message = "{Size.memberDto.firstName}")
    private String firstName;

    @NotBlank
    @Size(min = 2, message = "{Size.memberDto.lastName}")
    private String lastName;

    @NotBlank
    @ValidEmail
    private String email;

    @NotBlank
    @ValidPassword
    private String password;

    @NotBlank
    private String rePassword;

    @NotBlank
    @Pattern(regexp = "(?<!\\w)(\\(?(\\+|00)?48\\)?)?[ -]?\\d{3}[ -]?\\d{3}[ -]?\\d{3}(?!\\w)")
    @Size(min = 7, max = 20, message = "{ValidPhoneNumber}")
    private String phone;
}
