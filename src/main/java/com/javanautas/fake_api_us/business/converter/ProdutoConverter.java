package com.javanautas.fake_api_us.business.converter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.javanautas.fake_api_us.dto.ProdutoDto;
import com.javanautas.fake_api_us.infrastructure.entities.ProdutoEntity;

@Component
public class ProdutoConverter {
	
	
	// converte um ProdutoDto para ProdutoEntity
	public ProdutoEntity ProductToEntity(ProdutoDto produtoDto) {
		return ProdutoEntity.builder()
				.id(String.valueOf(UUID.randomUUID()))
				.nome(produtoDto.getNome())
				.categoria(produtoDto.getCategoria())
				.descricao(produtoDto.getDescricao())
				.preco(produtoDto.getPreco())
				.imagem(produtoDto.getImagem())
				.dataInclusao(LocalDateTime.now())
				.build();
	}
	
	// converte um ProdutoEntity para um ProdutoDto
	public ProdutoDto ProductToDto(ProdutoEntity produtoEntity) {
		return ProdutoDto.builder()
				.entityId(produtoEntity.getId())
				.nome(produtoEntity.getNome())
				.categoria(produtoEntity.getCategoria())
				.descricao(produtoEntity.getDescricao())
				.preco(produtoEntity.getPreco())
				.imagem(produtoEntity.getImagem())
				.build();
		
		
	}
	
	
	
	// recebe um ProdutoDto e um ProdutoEntity
	// atualiza ProdutoEntity com os camos de ProdutoDto que nao estao nulos
	public ProdutoEntity ProductToEntityUpdate(ProdutoEntity produtoEntity, ProdutoDto produtoDto, String id) {
		return ProdutoEntity.builder()
				.id(id)
				.nome(produtoDto.getNome()!=null?produtoDto.getNome():produtoEntity.getNome())
				.categoria(produtoDto.getCategoria()!=null?produtoDto.getCategoria():produtoEntity.getCategoria())
				.descricao(produtoDto.getDescricao()!=null?produtoDto.getDescricao():produtoEntity.getDescricao())
				.preco(produtoDto.getPreco()!=null?produtoDto.getPreco():produtoEntity.getPreco())
				.imagem(produtoDto.getImagem()!=null?produtoDto.getImagem():produtoEntity.getImagem())
				.dataInclusao(produtoEntity.getDataInclusao())
				.dataAtualizacao(LocalDateTime.now())
				.build();
	}
	
	
	// converte uma lista de ProdutoEntity em lista de ProdutoDto
	public List<ProdutoDto> ProductToListDto(List<ProdutoEntity> entityList){
		return entityList.stream()       // 🎯 Converte lista em stream
				.map(this::ProductToDto)// 🔄 Aplica converter em cada elemento
				.toList();              // 📦 Converte de volta para lista
		
	}
	
	

}
