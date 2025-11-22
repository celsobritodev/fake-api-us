package com.javanautas.fake_api_us.business.converter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.javanautas.fake_api_us.apiv1.dto.ProductsDto;
import com.javanautas.fake_api_us.infrastructure.entities.ProdutoEntity;

@Component
public class ProdutoConverter {
	
	
	// converte um dto para entity
	public ProdutoEntity ProductToEntity(ProductsDto ProdutoDto) {
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
	
	
	public ProductsDto ProductToDto(ProdutoEntity produtoEntity) {
		return ProductsDto.builder()
				.entityId(produtoEntity.getId())
				.nome(produtoEntity.getNome())
				.categoria(produtoEntity.getCategoria())
				.descricao(produtoEntity.getDescricao())
				.preco(produtoEntity.getPreco())
				.imagem(produtoEntity.getImagem())
				.build();
		
		
	}
	
	public ProdutoEntity ProductToEntityUpdate(ProdutoEntity produtoEntity, ProductsDto ProdutoDto, String id) {
		return ProdutoEntity.builder()
				.id(id)
				.nome(ProdutoDto.getNome()!=null?ProdutoDto.getNome():produtoEntity.getNome())
				.categoria(ProdutoDto.getCategoria()!=null?ProdutoDto.getCategoria():produtoEntity.getCategoria())
				.descricao(ProdutoDto.getDescricao()!=null?ProdutoDto.getDescricao():produtoEntity.getDescricao())
				.preco(ProdutoDto.getPreco()!=null?ProdutoDto.getPreco():produtoEntity.getPreco())
				.imagem(ProdutoDto.getImagem()!=null?ProdutoDto.getImagem():produtoEntity.getImagem())
				.dataInclusao(produtoEntity.getDataInclusao())
				.dataAtualizacao(LocalDateTime.now())
				.build();
	}
	
	
	public List<ProductsDto> ProductToListDto(List<ProdutoEntity> entityList){
		return entityList.stream().map(this::ProductToDto).toList();
		
	}
	
	

}
