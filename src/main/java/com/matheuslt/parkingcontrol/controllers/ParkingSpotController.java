package com.matheuslt.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheuslt.parkingcontrol.controllers.dtos.ParkingSpotDTO;
import com.matheuslt.parkingcontrol.models.ParkingSpotModel;
import com.matheuslt.parkingcontrol.services.ParkingSpotService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

	@Autowired
	ParkingSpotService service;
	
	@GetMapping
	public ResponseEntity<List<ParkingSpotModel>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
		Optional<ParkingSpotModel> parkingSpotModelOptional = service.findById(id);
		
		if(!parkingSpotModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
	}
	
	@PostMapping
	public ResponseEntity<Object> save(@RequestBody @Valid ParkingSpotDTO parkingSpotDTO) {
		if (service.existsByLicensePlateCar(parkingSpotDTO.getLicensePlateCar())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!");
		}
		if (service.existsByParkingSpotLabel(parkingSpotDTO.getParkingSpotLabel())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");
		}
		if (service.existsByApartmentAndBlock(parkingSpotDTO.getApartment(), parkingSpotDTO.getBlock())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registered for this apartment/block!");
		}
		
		ParkingSpotModel parkingSpotModel = new ParkingSpotModel();
		BeanUtils.copyProperties(parkingSpotDTO, parkingSpotModel);
		parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(parkingSpotModel));
	}
	
	
}
