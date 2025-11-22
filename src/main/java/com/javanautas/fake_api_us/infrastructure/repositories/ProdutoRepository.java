package com.javanautas.fake_api_us.infrastructure.repositories;

import org.springframework.stereotype.Repository;

import com.javanautas.fake_api_us.infrastructure.entities.ProdutoEntity;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity,String> {
	
	Boolean existsByNome(String nome);
	
	ProdutoEntity findByNome(String nome);
	
	void deleteByNome(String nome);

}
