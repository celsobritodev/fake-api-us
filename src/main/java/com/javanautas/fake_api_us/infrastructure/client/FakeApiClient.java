package com.javanautas.fake_api_us.infrastructure.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.javanautas.fake_api_us.dto.ProdutoDto;



//Feign Client ≠ Controller tradicional
//@FeignClient é uma anotação do Spring Cloud OpenFeign
//Ela não precisa de @Controller ou @RestController
//O Feign cria um client HTTP que faz chamadas para APIs externas

//O @GetMapping no Feign Client não cria um endpoint, mas sim define como será a requisição
// HTTP que o client fará para a API externa.


@FeignClient(value="fake-api", url="${fake-api.url:#{null}}")
public interface FakeApiClient {
	
	@GetMapping("/products")
	List<ProdutoDto> buscarListaProdutosDaApi();
	

}
