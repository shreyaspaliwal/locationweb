package com.shreyas.location.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shreyas.location.entities.Location;
import com.shreyas.location.repos.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository repo;
	
	@Override
	public Location saveLocation(Location location) {
		// TODO Auto-generated method stub
		return repo.save(location);
	}

	@Override
	public Location updateLocation(Location location) {
		// TODO Auto-generated method stub
		return repo.save(location);
	}

	@Override
	public void deleteLocation(Location location) {
		// TODO Auto-generated method stub  
		repo.delete(location);
	}

	@Override
	public Location getLocationById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	public List<Location> getAllLocations() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
