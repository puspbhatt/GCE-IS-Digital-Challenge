package com.gcesolutions.shopmanager.controller;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.gcesolutions.shopmanager.model.Shop;
import com.gcesolutions.shopmanager.service.ShopService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShopControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ShopService shopService;

	@Test
	public void findAllTest_success() throws Exception {
		Shop mockShop = createDummyShop(1, "Supermarket", 2, 2, "XXX", "Nand Joshi");

		List<Shop> mockShops = new ArrayList<>();
		mockShops.add(mockShop);

		Mockito.doReturn(mockShops).when(shopService).findAll();

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/shops").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"id\":1,\"name\":\"XXX\",\"category\":\"Supermarket\",\"address\":{\"address\":\"Not Found\",\"city\":null,\"state\":null,\"zipCode\":0,\"country\":null},\"ownerName\":\"Nand Joshi\",\"longitude\":\"2\",\"latitude\":\"2\",\"createdAt\":null,\"updatedAt\":null}]";
		assertEquals(result.getResponse().getContentAsString(), expected);

	}

	private Shop createDummyShop(int id, String category, double latitude, double longitude, String name,
			String owner) {
		Shop shop = new Shop();
		shop.setId(id);
		shop.setCategory(category);
		shop.setLatitude(new BigDecimal(latitude).toPlainString());
		shop.setLongitude(new BigDecimal(longitude).toPlainString());
		shop.setName(name);
		shop.setOwnerName(owner);
		return shop;
	}

}
