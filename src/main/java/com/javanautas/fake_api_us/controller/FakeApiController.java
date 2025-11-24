package com.javanautas.fake_api_us.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javanautas.fake_api_us.business.services.FakeApiService;
import com.javanautas.fake_api_us.business.services.ProdutoService;
import com.javanautas.fake_api_us.dto.ProdutoDto;

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
	@PostMapping("/savefromapi")
	public ResponseEntity<List<ProdutoDto>> salvarListarProdutosDaApi() {
		return ResponseEntity.ok(fakeApiService.salvarListarProdutosDaApi());
	}

	
	@Operation(summary = "Salva novos produtos", method = "POST", description = "Salva um produto no banco de dados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produto salvo com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao salvar o produto") })
	// busca produtos de uma api e salva no banco de dados
	@PostMapping("/insert")
	public ResponseEntity<ProdutoDto> salvarProduto(@RequestBody ProdutoDto produtoDto) {
		return ResponseEntity.ok(produtoService.salvarProdutoDto(produtoDto));
	}

	
	@Operation(summary = "Fazer update de novos produtos", method = "PUT", description = "Atualiza um produto no banco de dados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao atualizar o produto") })
	// busca produtos de uma api e salva no banco de dados
	@PutMapping("/update")
	public ResponseEntity<ProdutoDto> atualizarProduto(@RequestParam("id") String id,
			@RequestBody ProdutoDto produtoDto) {
		return ResponseEntity.ok(produtoService.atualizarProduto(id, produtoDto));
	}

	
	@Operation(summary = "Deleta produtos", method = "DELETE", description = "Deleta um produto no banco de dados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao deletar o produto") })
	// busca produtos de uma api e salva no banco de dados
	@DeleteMapping("/deletebyname")
	public ResponseEntity<Void> deletarProduto(@RequestParam("nome") String nome) {
		produtoService.deletarProduto(nome);
		return ResponseEntity.accepted().build();
	}
	
	@Operation(summary = "Busca todos os produtos", method = "GET", description = "Busca todos os produtos no banco de dados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produtos buscados com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao buscar produtos") })
	// busca produtos de uma api e salva no banco de dados
	@GetMapping("/listall")
	public ResponseEntity<List<ProdutoDto>> buscarTodosProdutos() {
		return ResponseEntity.ok(produtoService.buscarTodosProdutos());
	}
	
	
	@Operation(summary = "Busca produto por nome", method = "GET", description = "Busca produto por nome no banco de dados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produto buscado com sucesso"),
			@ApiResponse(responseCode = "500", description = "Erro ao buscar o produto") })
	// busca produtos de uma api e salva no banco de dados
	@GetMapping("findbyname/{nome}")
	public ResponseEntity<ProdutoDto> buscarProdutoPorNome(@PathVariable("nome") String nome) {
		return ResponseEntity.ok(produtoService.buscarProdutoPorNome(nome));
	}
	

	
	



}