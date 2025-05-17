package com.graphQL.SpringBootGraphQL.Pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShipmentDetail {

    @Min(value = 1 ,message = "value must be greater than 1")
    @Max(value = 20 , message = "value must be lesser than 20")
    private int referenceNo;

    @NotEmpty(message = "reference name should not be empty")
    @Pattern(regexp="[0-9a-zA-Z.+_',/-]+",message="only accepted characters in regex will be accepted")
    private String userName;

    private String bookingStatus;
}
