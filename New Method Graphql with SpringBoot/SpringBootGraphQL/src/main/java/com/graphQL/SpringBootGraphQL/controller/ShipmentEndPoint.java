package com.graphQL.SpringBootGraphQL.controller;


import com.graphQL.SpringBootGraphQL.Pojo.ShipmentDetail;
import com.graphQL.SpringBootGraphQL.entity.ShipmentDetails;
import com.graphQL.SpringBootGraphQL.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.List;
import java.util.Optional;


@Controller
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping(value = "/graphql")
public class ShipmentEndPoint {

    @Autowired
    ShipmentService shipmentService;

    //getmapping to get all the details which is normal springboot annotation
    @GetMapping("/getDetails")
    public List<ShipmentDetails> getAllDetails()
    {
        return shipmentService.getAllDetails();
    }

    //querymapping is the graphql annotation which is used to retrieve the same details but it all happens in a single request , we only change the query not the entire request url

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @QueryMapping("getAllDetails")
    public List<ShipmentDetails> getAllDetails1()
    {
        return shipmentService.getAllDetails();
    }

    //getmapping to get the details of particular ID which is normal springboot annotation
    @GetMapping("/getByShipmentId/{id}")
    public Optional<ShipmentDetails> getByShipmentId(@PathVariable int id)
    {
        return shipmentService.findById(id);
    }

    //querymapping to get the details of particular ID which is graphql annotation
    @QueryMapping("getByShipmentId")
    public Optional<ShipmentDetails> getByShipmentId1(@Argument int referenceNo)
    {
        return shipmentService.findById(referenceNo);
    }

    //querymapping is the graphql technique , in which the getmapping done in normal springboot is altered with querymapping
    //querymapping is only to get/fetch the data .




//    @PostMapping("/createShipment")
//    public String createShipment(@RequestBody ShipmentDetails shipmentDetails)
//    {
//        return shipmentService.createShipment(shipmentDetails);
//    }

//    @MutationMapping
//    public String addShipment(@Valid @Argument ShipmentDetail shipmentDetail)
//    {
//        ShipmentDetails shipmentDetails = new ShipmentDetails();
//        shipmentDetails.setReferenceNo(shipmentDetail.getReferenceNo());
//        shipmentDetails.setUserName(shipmentDetail.getUserName());
//        shipmentDetails.setBookingStatus(shipmentDetail.getBookingStatus());
//        shipmentService.createShipment(shipmentDetails);
//        return "success";
//
//    }
    @MutationMapping
    public ResponseEntity<ShipmentDetails> addShipment(@Valid @Argument ShipmentDetail shipmentDetail)
    {
        ShipmentDetails shipmentDetails = new ShipmentDetails();
        shipmentDetails.setReferenceNo(shipmentDetail.getReferenceNo());
        shipmentDetails.setUserName(shipmentDetail.getUserName());
        shipmentDetails.setBookingStatus(shipmentDetail.getBookingStatus());
        ShipmentDetails saveShipment =  shipmentService.createShipment(shipmentDetails);
            return new ResponseEntity<ShipmentDetails>(saveShipment, HttpStatus.CREATED);

    }

//    @MutationMapping
//    public Object addShipment(@Valid @Argument ShipmentDetail shipmentDetail)
//    {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//
//        Set<ConstraintViolation<ShipmentDetail>> constraintViolations = validator.validate(shipmentDetail);
//
//        if (constraintViolations.size() > 0 ) {
//            System.out.println("Constraint Violations occurred..");
//            for (ConstraintViolation<ShipmentDetail> contraints : constraintViolations) {
//                System.out.println(contraints.getRootBeanClass().getSimpleName()+
//                        "." + contraints.getPropertyPath() + " " + contraints.getMessage());
//            }
//        }
//        System.out.println("hell0");
//        ShipmentDetails shipmentDetails = new ShipmentDetails();
//        shipmentDetails.setReferenceNo(shipmentDetail.getReferenceNo());
//        System.out.println("hell1");
//        shipmentDetails.setUserName(shipmentDetail.getUserName());
//        System.out.println("heel2");
//        shipmentDetails.setBookingStatus(shipmentDetail.getBookingStatus());
//
//        try{
//            System.out.println("hell3");
//            ShipmentDetails saveShipment =  shipmentService.createShipment(shipmentDetails);
//            return new ResponseEntity<ShipmentDetails>(saveShipment, HttpStatus.CREATED);
//        }
//        catch (ConstraintViolationException ex)
//        {
//            System.out.println(ex);
//            return ex.getMessage();
//          ///  return ex.getConstraintViolations();
//        }
//    }


}
