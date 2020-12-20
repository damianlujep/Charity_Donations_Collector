package pl.coderslab.charity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class DonationFormDto {
    private List<Category> categories = new ArrayList<>();
    private int bagsQuantity;
    private Institution institution;

    //Pick up address
    private String street;
    private String city;
    private String zipCode;
    private String phoneNumber;

    //Pick up date
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    private LocalTime pickUpTime;
    private String pickUpComment;








}
