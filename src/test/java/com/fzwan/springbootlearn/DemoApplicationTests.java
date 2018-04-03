package com.fzwan.springbootlearn;

import java.util.Random;

import com.fzwan.springbootlearn.dao.pojo.Product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) 
public class DemoApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void contextLoads() {
		long productId = 1;
		Product product = restTemplate.getForObject("http://localhost:8080/product/" + productId, Product.class);
		Product newProduct = new Product();
        long newPrice = new Random().nextLong();
        newProduct.setName("new name");
        newProduct.setPrice(newPrice);
		restTemplate.put("http://localhost:8080/product/" + productId, newProduct);
	}

}
