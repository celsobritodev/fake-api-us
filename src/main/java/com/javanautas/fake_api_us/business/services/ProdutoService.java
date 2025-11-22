package com.javanautas.fake_api_us.business.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javanautas.fake_api_us.apiv1.dto.ProductsDto;
import com.javanautas.fake_api_us.business.converter.ProdutoConverter;
import com.javanautas.fake_api_us.infrastructure.entities.ProdutoEntity;
import com.javanautas.fake_api_us.infrastructure.repositories.ProdutoRepository;

import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	private final ProdutoConverter produtoConverter;

	public ProdutoEntity salvarProdutos(ProdutoEntity produto) {
		try {
			return produtoRepository.save(produto);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar Produtos" + e);
		}

	}
	
	
	public List<ProdutoEntity> buscarTodosProdutos() {
		try {
			return produtoRepository.findAll();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar todos os produtos"+e);
		}
	}
	
	
	public ProdutoEntity buscarProdutoPorNome(String nome) {
		try {
			return produtoRepository.findByNome(nome);
		} catch (Exception e) {
		   throw new RuntimeException(format("Erro ao buscar produto por nome",nome),e);   
		}
	}
	
	
	public void deletaProduto(String nome) {
		try {
			produtoRepository.deleteByNome(nome);
		} catch (Exception e) {
			throw new RuntimeException(format("Erro ao deletar produto por nome",nome),e);   
		}
	}
	
	
	public Boolean existsPorNome(String nome) {
        try {
            return produtoRepository.existsByNome(nome);
        } catch (Exception e) {
            throw new RuntimeException(format("Erro ao buscar produto por nome '%s': %s", nome, e.getMessage()), e);
        }
    }
	
	
	public ProductsDto updateProduto(String id, ProductsDto produtoDto) {
		try {
			ProdutoEntity entity = produtoRepository.findById(id).orElseThrow(()->new RuntimeException("Id não existe no banco"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
