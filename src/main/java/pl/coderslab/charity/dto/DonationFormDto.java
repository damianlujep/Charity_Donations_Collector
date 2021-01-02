package pl.coderslab.charity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class DonationFormDto {
    @NotNull()
    private List<Category> categories = new ArrayList<>();
    @NotNull
    @Min(value = 1, message = "{donationFormDto.bagsQuantity}")
    private int bagsQuantity;
    @NotNull
    private Institution institution;

    //Pick up address
    @NotBlank()
    private String street;
    @NotBlank
    private String city;
    @NotBlank
//    @Pattern(regexp = " [0-9]{2}-[0-9]{3}")
    private String zipCode;
    @NotBlank
    @Pattern(regexp = "(?<!\\w)(\\(?(\\+|00)?48\\)?)?[ -]?\\d{3}[ -]?\\d{3}[ -]?\\d{3}(?!\\w)")
    private String phoneNumber;

    //Pick up date
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Future(message = "Enter a valid pick up date")
    private LocalDate pickUpDate;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime pickUpTime;
    private String pickUpComment;
}
