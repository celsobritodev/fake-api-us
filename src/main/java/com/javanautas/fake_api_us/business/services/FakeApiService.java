package com.javanautas.fake_api_us.business.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javanautas.fake_api_us.business.converter.ProdutoConverter;
import com.javanautas.fake_api_us.dto.ProdutoDto;
import com.javanautas.fake_api_us.infrastructure.client.FakeApiClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FakeApiService { // ✅ Corrigir acento no nome

	private final FakeApiClient fakeApiCliente;
	private final ProdutoConverter produtoConverter;
	private final ProdutoService produtoService;

	public List<ProdutoDto> salvarListarProdutosDaApi() {
		try {
			List<ProdutoDto> produtosDto = fakeApiCliente.buscarListaProdutosDaApi();
			
			log.info("Encontrados {} produtos na API externa", produtosDto.size());

			produtosDto.forEach(produtoDto -> {
				Boolean existe = produtoService.existsPorNome(produtoDto.getNome());
				if (!existe) {
                    log.info("✅ Produto salvo: {}", produtoDto.getNome());
					produtoService.salvarProduto(produtoConverter.ProductToEntity(produtoDto));
				} else {
					 // ✅ MENSAGEM NO CONSOLE em vez de exceção
                    log.warn("⚠️ Produto já cadastrado: {}", produtoDto.getNome());
				}
			});
			return produtoService.buscarTodosProdutos();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar produtos: " + e.getMessage(), e);
		}
	}
}