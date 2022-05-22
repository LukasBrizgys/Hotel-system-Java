package lt.ku.hotel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.ku.hotel.entities.Feature;
import lt.ku.hotel.repositories.FeatureRepository;

@Service
public class FeatureService {
	@Autowired
	FeatureRepository featureRepository;
	
	public List<Feature> getAllFeatures(){
		return featureRepository.findAll();
	}
	public Feature getFeature(Integer id) {
		return featureRepository.getById(id);
	}
	public void deleteFeature(Integer id) {
		featureRepository.deleteById(id);
	}
	public Feature addFeature(Feature feature) {
		return featureRepository.save(feature);
	}
}
