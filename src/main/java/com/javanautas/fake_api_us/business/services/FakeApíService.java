package com.javanautas.fake_api_us.business.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javanautas.fake_api_us.apiv1.dto.ProductsDto;
import com.javanautas.fake_api_us.business.converter.ProdutoConverter;
import com.javanautas.fake_api_us.infrastructure.client.FakeApiClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FakeApíService {
	
	private final FakeApiClient cliente;
	private final ProdutoConverter ProdutoConverter;
	private final ProdutoService produtoService;
	
	public List<ProductsDto> buscarProdutos() {
		List<ProductsDto> produtosDto = cliente.buscaListaProdutos();
		produtosDto.forEach(produto->produtoService.salvarProdutos(ProdutoConverter.toEntity(produto)));
		return produtosDto;
		
	}
	
	
	

}
