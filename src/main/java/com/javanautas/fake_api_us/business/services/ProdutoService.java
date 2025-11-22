package com.javanautas.fake_api_us.business.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javanautas.fake_api_us.infrastructure.entities.ProdutoEntity;
import com.javanautas.fake_api_us.infrastructure.repositories.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

	private ProdutoRepository repository;

	public ProdutoEntity salvarProdutos(ProdutoEntity produto) {
		try {
			return repository.save(produto);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar Produtos" + e);
		}

	}
	
	
	public List<ProdutoEntity> buscarTodosProdutos() {
		try {
			return repository.findAll();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar todos os produtos"+e);
		}
	}

}
