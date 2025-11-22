package com.javanautas.fake_api_us.business.converter;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.javanautas.fake_api_us.apiv1.dto.ProductsDto;
import com.javanautas.fake_api_us.infrastructure.entities.ProdutoEntity;

@Component
public class ProdutoConverter {
	
	
	// converte um dto para entity
	public ProdutoEntity toEntity(ProductsDto ProdutoDto) {
		return ProdutoEntity.builder()
				.id(String.valueOf(UUID.randomUUID()))
				.nome(ProdutoDto.getNome())
				.categoria(ProdutoDto.getCategoria())
				.descricao(ProdutoDto.getDescricao())
				.preco(ProdutoDto.getPreco())
				.imagem(ProdutoDto.getImagem())
				.dataInclusao(LocalDateTime.now())
				.build();
	}

}
