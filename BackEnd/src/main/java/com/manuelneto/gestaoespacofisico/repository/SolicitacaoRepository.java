package com.manuelneto.gestaoespacofisico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.manuelneto.gestaoespacofisico.entity.Solicitacao;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>{
	List<Solicitacao> findByStatus(String status);
	List<Solicitacao> findBySolicitanteId(Long usuarioId);
	
	@Query("SELECT COUNT(s) From Solicitacao s WHERE s.status = 'PENDENTE'")
	Long countPendentes();

}
