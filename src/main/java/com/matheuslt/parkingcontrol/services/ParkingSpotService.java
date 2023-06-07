package com.matheuslt.parkingcontrol.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheuslt.parkingcontrol.models.ParkingSpotModel;
import com.matheuslt.parkingcontrol.repositories.ParkingSpotRepository;

import jakarta.transaction.Transactional;

@Service
public class ParkingSpotService {

	@Autowired
	ParkingSpotRepository repository;
	
	public List<ParkingSpotModel> findAll() {
		return repository.findAll();
	}
	
	public Optional<ParkingSpotModel> findById(UUID id) {
		return repository.findById(id);
	}

	@Transactional
	public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
		return repository.save(parkingSpotModel);
	}
	
	@Transactional
	public void delete(ParkingSpotModel parkingSpotModel) {
		repository.delete(parkingSpotModel);
	}

	public boolean existsByLicensePlateCar(String licensePlateCar) {
		return repository.existsByLicensePlateCar(licensePlateCar);
	}

	public boolean existsByParkingSpotLabel(String parkingSpotLabel) {
		return repository.existsByParkingSpotLabel(parkingSpotLabel);
	}

	public boolean existsByApartmentAndBlock(String apartment, String block) {
		return repository.existsByApartmentAndBlock(apartment, block);
	}
}
