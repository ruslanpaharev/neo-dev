package ru.neoflex.dev.spring.paging_sorting_query;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyEntityRepository extends PagingAndSortingRepository <MyEntity, Long> {


}
