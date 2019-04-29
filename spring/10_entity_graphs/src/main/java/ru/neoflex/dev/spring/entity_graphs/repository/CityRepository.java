package ru.neoflex.dev.spring.entity_graphs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.dev.spring.entity_graphs.entity.City;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Set;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	@EntityGraph(value = "city.departments.employees", type = EntityGraph.EntityGraphType.FETCH)
    Set<City> findAllBy();
	
 
}
