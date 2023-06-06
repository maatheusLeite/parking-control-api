package com.matheuslt.parkingcontrol.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheuslt.parkingcontrol.models.ParkingSpotModel;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {
	
	boolean existsByLicensePlateCar(String licensePlateCar);
	boolean existsByParkingSpotLabel(String parkingSpotLabel);
	boolean existsByApartmentAndBlock(String apartment, String block);
}
