package com.satyendra.location.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satyendra.location.entities.Location;
import com.satyendra.location.repos.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository repository;
	@Override
	public Location saveLocation(Location location) {
		return repository.save(location);
	}

	@Override
	public Location updateLocation(Location location) {
		// TODO Auto-generated method stub
		return repository.save(location);
	}

	@Override
	public void deleteLocation(Location location) {
		// TODO Auto-generated method stub
		repository.delete(location);

	}

	@Override
	public Location getLocationById(int id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

	@Override
	public List<Location> getAllLocations() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
