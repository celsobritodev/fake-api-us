package com.javanautas.fake_api_us.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javanautas.fake_api_us.apiv1.dto.ProductsDto;
import com.javanautas.fake_api_us.business.services.FakeApiService;

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
    
    private final FakeApiService service;
    
    @Operation(
        summary = "Busca todos os produtos", 
        description = "Retorna uma lista com todos os produtos disponíveis da Fake Store API"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
        @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados")
    })
    @GetMapping("")
    public ResponseEntity<List<ProductsDto>> buscaProdutos() {
        return ResponseEntity.ok(service.buscarProdutos());
    }
}