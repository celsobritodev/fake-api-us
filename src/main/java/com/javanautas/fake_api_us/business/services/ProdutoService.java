package com.javanautas.fake_api_us.business.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javanautas.fake_api_us.business.converter.ProdutoConverter;
import com.javanautas.fake_api_us.dto.ProdutoDto;
import com.javanautas.fake_api_us.infrastructure.entities.ProdutoEntity;
import com.javanautas.fake_api_us.infrastructure.repositories.ProdutoRepository;

import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	private final ProdutoConverter produtoConverter;

	public ProdutoEntity salvarProduto(ProdutoEntity produtoEntity) {
		try {
			return produtoRepository.save(produtoEntity);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar Produtos" + e);
		}

	}
	
	
	public ProdutoDto salvarProdutoDto(ProdutoDto produtoDto) {
		try {
			ProdutoEntity produtoEntity = produtoConverter.ProductToEntity(produtoDto);
			return produtoConverter.ProductToDto(produtoRepository.save(produtoEntity));
		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar Produtos" + e);
		}

	}
	
	
//	public List<ProdutoEntity> buscarTodosProdutos() {
//		try {
//			return produtoRepository.findAll();
//		} catch (Exception e) {
//			throw new RuntimeException("Erro ao buscar todos os produtos"+e);
//		}
//	}
	
	
	public ProdutoDto buscarProdutoPorNome(String produtoNome) {
		try {
			return produtoConverter.ProductToDto(produtoRepository.findByNome(produtoNome));
		} catch (Exception e) {
		   throw new RuntimeException(format("Erro ao buscar produto por nome",produtoNome),e);   
		}
	}
	
	
	public List<ProdutoDto> buscarTodosProdutos() {
		try {
			return produtoConverter.ProductToListDto(produtoRepository.findAll());
		} catch (Exception e) {
		   throw new RuntimeException(format("Erro ao buscar todos os produtos"),e);   
		}
	}
	
	
	@Transactional
	public void deletarProduto(String produtoNome) {
		try {
			if (existsPorNome(produtoNome)) { 
			 produtoRepository.deleteByNome(produtoNome);
			}else {
	            throw new RuntimeException("Produto não encontrado: " + produtoNome);
	        }
					
		} catch (Exception e) {
			throw new RuntimeException(format("Erro ao deletar produto por nome",produtoNome),e);   
		}
	}
	
	
	public Boolean existsPorNome(String produtoNome) {
        try {
            return produtoRepository.existsByNome(produtoNome);
        } catch (Exception e) {
            throw new RuntimeException(format("Erro ao buscar produto por nome '%s': %s", produtoNome, e.getMessage()), e);
        }
    }
	
	
	public ProdutoDto atualizarProduto(String id, ProdutoDto produtoDto) {
		try {
			ProdutoEntity produtoEntity = produtoRepository.findById(id).orElseThrow(()->new RuntimeException("Id não existe no banco"));
			salvarProduto(produtoConverter.ProductToEntityUpdate(produtoEntity,produtoDto,id));
			return produtoConverter.ProductToDto(produtoRepository.findByNome(produtoEntity.getNome()));
		} catch (Exception e) {
			 throw new RuntimeException(format("Erro ao atualizar produto"));
		}
		
	}

}
