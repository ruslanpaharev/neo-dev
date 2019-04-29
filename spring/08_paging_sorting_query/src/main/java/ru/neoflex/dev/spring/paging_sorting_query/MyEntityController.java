package ru.neoflex.dev.spring.paging_sorting_query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.NullHandling;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class MyEntityController {

	private final MyEntityRepository myEntityRepository;

	public MyEntityController(MyEntityRepository myEntityRepository) {
		this.myEntityRepository = myEntityRepository;
	}
	
	@GetMapping("/myEntity")
	@Transactional
	public PageWithTotalResponse<MyEntity> myGetMyEntities( Pageable pageable, @RequestParam(required=false) boolean ignorecase,
																	@RequestParam(defaultValue="NATIVE") NullHandling nullHandling) {
		
	
		Pageable mypageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(SortManipulating.toOrderStream(pageable.getSort(), ignorecase, nullHandling)));
	
		
	//	Order orderIC = new Sort.Order(Sort.Direction.ASC, "name").ignoreCase();
		
		
		Page<MyEntity> entities = this.myEntityRepository.findAll(mypageable);
		PageWithTotalResponse<MyEntity> PageWithTotalResponseImpl = new PageWithTotalResponse<MyEntity>(
				entities.getContent(), entities.getTotalElements());


		return PageWithTotalResponseImpl;
	}
	

	@GetMapping("/myEntity1")
	@Transactional
	public PageWithTotalResponse<MyEntity> getMyEntities( Pageable pageable) {
		
		Page<MyEntity> entities = this.myEntityRepository.findAll(pageable);
		PageWithTotalResponse<MyEntity> PageWithTotalResponseImpl = new PageWithTotalResponse<MyEntity>(
				entities.getContent(), entities.getTotalElements());
		// var dtos = StreamSupport.stream(entities.spliterator(),
		// false).map(MyEntityDto::ofMyEntity)
		// .collect(Collectors.toList());

		return PageWithTotalResponseImpl;
	}

	
}
