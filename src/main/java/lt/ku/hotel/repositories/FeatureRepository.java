package lt.ku.hotel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ku.hotel.entities.Feature;
import lt.ku.hotel.entities.Room;

public interface FeatureRepository extends JpaRepository<Feature, Integer>{
	List<Feature> findFeaturesByRoomsId(Integer id);
}
