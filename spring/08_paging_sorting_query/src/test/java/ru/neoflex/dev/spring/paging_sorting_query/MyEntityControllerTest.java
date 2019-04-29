package ru.neoflex.dev.spring.paging_sorting_query;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@Sql(value = "/add-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/remove-test-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MyEntityControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGetMyEntities() throws Exception {
		var result = mockMvc.perform(MockMvcRequestBuilders.get("/myEntity")).andExpect(status().isOk()).andDo(print())
				.andReturn();

		var list = objectMapper.readValue(result.getResponse().getContentAsString(), PageWithTotalResponse.class);
		Assert.assertEquals(9, list.data.size());
		Assert.assertEquals(9, list.getTotalElements());
	}

	@Test
	public void testGetMyEntities2OutOf6() throws Exception {
		var result = mockMvc.perform(MockMvcRequestBuilders.get("/myEntity?page=1&size=2")).andExpect(status().isOk())
				.andDo(print()).andReturn();
		// result.getResponse().getContentAsString()
		var list = objectMapper.readValue(result.getResponse().getContentAsString(), PageWithTotalResponse.class);
		Assert.assertEquals(2, list.data.size());
		Assert.assertEquals(9, list.getTotalElements());
	}

	@Test
	public void testGetMyEntitiesSorted_nameAscIdDesc() throws Exception {
		var result = mockMvc.perform(MockMvcRequestBuilders.get("/myEntity?sort=name,ASC&sort=id,DESC"))
				.andExpect(status().isOk()).andDo(print()).andReturn();

		var pageWithTotal = objectMapper.readValue(result.getResponse().getContentAsString(),
				PageWithTotalResponse.class);
		Assert.assertEquals(
				"PageWithTotalResponse{data=[{id=6, name=null}, {id=5, name=null}, {id=4, name=null}, {id=7, name=BIGAndsmall}, {id=2, name=BIGgandsmall}, {id=8, name=BIgandsmall},"
							+ " {id=9, name=Bigandsmall}, {id=1, name=ONLY_BIG}, {id=3, name=only_small}], totalElements=9}",
				pageWithTotal.toString());
	}

	@Test
	public void testGetMyEntitiesSorted_nameDescIdAsc() throws Exception {
		var result = mockMvc.perform(MockMvcRequestBuilders.get("/myEntity?sort=name,DESC&sort=id,ASC"))
				.andExpect(status().isOk()).andDo(print()).andReturn();

		var pageWithTotal = objectMapper.readValue(result.getResponse().getContentAsString(),
				PageWithTotalResponse.class);
		Assert.assertEquals(
				"PageWithTotalResponse{data=[{id=3, name=only_small}, {id=1, name=ONLY_BIG}, {id=9, name=Bigandsmall}, {id=8, name=BIgandsmall}, {id=2, name=BIGgandsmall}, {id=7, name=BIGAndsmall},"
							+ " {id=4, name=null}, {id=5, name=null}, {id=6, name=null}], totalElements=9}",
				pageWithTotal.toString());
	}

	@Test
	public void testGetMyEntitiesSorted_nameDescIdAsc_IgnorCase() throws Exception {
		var result = mockMvc
				.perform(MockMvcRequestBuilders
						.get("/myEntity?sort=name,DESC&sort=id,ASC&ignorecase=true&nullHandling=NATIVE"))
				.andExpect(status().isOk()).andDo(print()).andReturn();

		var pageWithTotal = objectMapper.readValue(result.getResponse().getContentAsString(),
				PageWithTotalResponse.class);
		Assert.assertEquals(
				"PageWithTotalResponse{data=[{id=3, name=only_small}, {id=1, name=ONLY_BIG}, {id=2, name=BIGgandsmall}, {id=7, name=BIGAndsmall}, {id=8, name=BIgandsmall}, {id=9, name=Bigandsmall}, "
							+ "{id=4, name=null}, {id=5, name=null}, {id=6, name=null}], totalElements=9}",
				pageWithTotal.toString());
	}

}
