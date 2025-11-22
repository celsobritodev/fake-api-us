package com.javanautas.fake_api_us.business.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javanautas.fake_api_us.apiv1.dto.ProductsDto;
import com.javanautas.fake_api_us.business.converter.ProdutoConverter;
import com.javanautas.fake_api_us.infrastructure.client.FakeApiClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FakeApiService { // ✅ Corrigir acento no nome

	private final FakeApiClient cliente;
	private final ProdutoConverter produtoConverter;
	private final ProdutoService produtoService;

	public List<ProductsDto> buscarProdutos() {
		try {
			List<ProductsDto> produtosDto = cliente.buscaListaProdutos();
			
			log.info("Encontrados {} produtos na API externa", produtosDto.size());

			produtosDto.forEach(produto -> {
				Boolean existe = produtoService.existsPorNome(produto.getNome());
				if (!existe) {
                    log.info("✅ Produto salvo: {}", produto.getNome());
					produtoService.salvarProdutos(produtoConverter.ProductToEntity(produto));
				} else {
					 // ✅ MENSAGEM NO CONSOLE em vez de exceção
                    log.warn("⚠️ Produto já cadastrado: {}", produto.getNome());

				}
				// ✅ Remover o throw RuntimeException - estava impedindo o loop
			});

			// ✅ Mover o return para fora do forEach
			return produtoConverter.ProductToListDto(produtoService.buscarTodosProdutos());

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar produtos: " + e.getMessage(), e);
		}
	}
}