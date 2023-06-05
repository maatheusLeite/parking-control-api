package com.matheuslt.parkingcontrol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheuslt.parkingcontrol.repositories.ParkingSpotRepository;

@Service
public class ParkingSpotService {

	@Autowired
	ParkingSpotRepository parkingSpotRepository;
	
}
