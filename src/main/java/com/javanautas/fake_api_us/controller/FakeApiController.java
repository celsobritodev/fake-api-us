package com.javanautas.fake_api_us.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javanautas.fake_api_us.apiv1.dto.ProductsDto;
import com.javanautas.fake_api_us.business.services.FakeApiService;
import com.javanautas.fake_api_us.business.services.ProdutoService;

// ✅ IMPORTS CORRETOS PARA SPRINGDOC (Swagger v3)
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor // ✅ MUDAR PARA RequiredArgsConstructor

@Tag(name = "fake-api", description = "API para gerenciamento de produtos")
public class FakeApiController {

	private final FakeApiService fakeApiService;
	private final ProdutoService produtoService;

	@Operation(summary = "Busca todos os produtos da fake api e salva no postgre", method = "POST", description = "Retorna uma lista com todos os produtos disponíveis da Fake Store API")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados") })
	// busca produtos de uma api e salva no banco de dados
	@PostMapping("/api")
	public ResponseEntity<List<ProductsDto>> salvaProdutosApi() {
		return ResponseEntity.ok(fakeApiService.buscarProdutos());
	}

	
	@Operation(summary = "Salva novos produtos", method = "POST", description = "Salva um produto no banco de dados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produto salvo com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao salvar o produto") })
	// busca produtos de uma api e salva no banco de dados
	@PostMapping("/")
	public ResponseEntity<ProductsDto> salvaProdutos(@RequestBody ProductsDto produtoDto) {
		return ResponseEntity.ok(produtoService.salvarProdutoDto(produtoDto));
	}

	
	@Operation(summary = "Fazer update de novos produtos", method = "PUT", description = "Atualiza um produto no banco de dados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao atualizar o produto") })
	// busca produtos de uma api e salva no banco de dados
	@PostMapping("/")
	public ResponseEntity<ProductsDto> updateProdutos(@RequestParam("id") String id,
			@RequestBody ProductsDto produtoDto) {
		return ResponseEntity.ok(produtoService.updateProduto(id, produtoDto));
	}

	
	@Operation(summary = "Deleta produtos", method = "DELETE", description = "Deleta um produto no banco de dados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao deletar o produto") })
	// busca produtos de uma api e salva no banco de dados
	@DeleteMapping("/")
	public ResponseEntity<Void> deletaProduto(@RequestParam("nome") String nome) {
		produtoService.deletaProduto(nome);
		return ResponseEntity.accepted().build();
	}
	
	@Operation(summary = "Busca todos os produtos", method = "GET", description = "Busca todos os produtos no banco de dados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produtos buscados com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao buscar produtos") })
	// busca produtos de uma api e salva no banco de dados
	@GetMapping("/")
	public ResponseEntity<List<ProductsDto>> buscaTodosProdutos() {
		return ResponseEntity.ok(produtoService.buscaTodosProdutos());
	}
	
	
	@Operation(summary = "Busca produto por nome", method = "GET", description = "Busca produto por nome no banco de dados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produto buscado com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao buscar o produto") })
	// busca produtos de uma api e salva no banco de dados
	@GetMapping("/{nome}")
	public ResponseEntity<ProductsDto> buscaProdutoPorNome(@PathVariable("nome") String nome) {
		return ResponseEntity.ok(produtoService.buscarProdutoPorNome(nome));
	}
	

	
	



}