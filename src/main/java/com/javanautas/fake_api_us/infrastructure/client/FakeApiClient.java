package com.javanautas.fake_api_us.infrastructure.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.javanautas.fake_api_us.apiv1.dto.ProductsDto;

@FeignClient(value="fake-api", url="${fake-api.url:#{null}}")
public interface FakeApiClient {
	
	@GetMapping("/products")
	List<ProductsDto> buscaListaProdutos();
	

}
