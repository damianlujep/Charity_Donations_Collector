package pl.coderslab.charity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    private String zipCode;
    @NotBlank
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
