package com.manuelneto.gestaoespacofisico.repository;

import java.time.LocalDate;
import java.time.LocalTime;
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
	List<Solicitacao> findByEspacoFisicoId(Long espacoId);

	@Query("SELECT s FROM Solicitacao s WHERE " +
			"s.espacoFisico.id = :espacoId AND " +
			"s.dataReserva = :data AND " +
			"((:horaInicio < s.horaFim AND :horaFim > s.horaInicio))")
	List<Solicitacao> findConflitosDeHorario(
			@Param("espacoId") Long espacoId,
			@Param("data") LocalDate data,
			@Param("horaInicio") LocalTime horaInicio,
			@Param("horaFim") LocalTime horaFim
	);

	@Query("SELECT COUNT(s) From Solicitacao s WHERE s.status = 'PENDENTE'")
	Long countPendentes();

}
