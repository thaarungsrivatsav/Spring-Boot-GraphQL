package com.graphQL.SpringBootGraphQL.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Data
@Getter
@Setter
@Table(name = "shipment_details")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ShipmentDetails {

    @Id
    @Column(name = "reference_no")
//    @Min(value = 1 ,message = "value must be greated than 1")
//    @Max(value = 20 , message = "value must be lesser than 20")
    private int referenceNo;

    @Column(name = "customer_name")
//    @NotEmpty(message = "reference name should not be empty")
//    @Pattern(regexp="^[a-zA-Z]",message="only alphabets allowed")
    private String userName;

    @Column(name = "booking_status")
    private String bookingStatus;
}
