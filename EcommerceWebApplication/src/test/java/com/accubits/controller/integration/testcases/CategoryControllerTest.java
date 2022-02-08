package com.accubits.controller.integration.testcases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.accubits.controller.CategoryController;
import com.accubits.model.Category;
import com.accubits.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value=CategoryController.class,secure = false)
public class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CategoryService categoryService;
	
	@Test
	public void testSaveCategory() throws JsonProcessingException {
		try {
			Category category = new Category();
			category.setId(3);
			category.setCategoryName("Homeo Medicines");
			category.setProducts(null);
			String inputInJson = this.mapToJson(category);
			String URI = "/api/category/addCategory";
			Mockito.when(categoryService.saveCategory(Mockito.any(Category.class))).thenReturn(category);
			RequestBuilder builder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(builder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			String outputInJson = response.getContentAsString();
			assertThat(outputInJson).isEqualTo(inputInJson);
			assertEquals(HttpStatus.OK.value(), response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private String mapToJson(Object object) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}
