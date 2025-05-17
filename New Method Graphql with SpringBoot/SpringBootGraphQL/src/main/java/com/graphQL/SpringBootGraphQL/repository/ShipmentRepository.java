package com.graphQL.SpringBootGraphQL.repository;

import com.graphQL.SpringBootGraphQL.entity.ShipmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<ShipmentDetails,Integer> {
}
