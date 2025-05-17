package com.graphQL.SpringBootGraphQL.service;

import com.graphQL.SpringBootGraphQL.entity.ShipmentDetails;
import com.graphQL.SpringBootGraphQL.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {

    @Autowired
    ShipmentRepository shipmentRepository;

    public ShipmentDetails createShipment(ShipmentDetails shipmentDetails)
    {
        System.out.println(shipmentDetails.getReferenceNo());
       return shipmentRepository.save(shipmentDetails);

    }

    public List<ShipmentDetails> getAllDetails(){
       return shipmentRepository.findAll();
    }

    public Optional<ShipmentDetails> findById(int id)
    {
        return shipmentRepository.findById(id);
    }
}
