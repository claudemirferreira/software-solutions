package br.com.ss.academico.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ss.academico.dominio.Mensalidade;
import br.com.ss.academico.enumerated.StatusPagamento;

@Repository
public interface MensalidadeRepositorio extends
		JpaRepository<Mensalidade, Long> {

	@Query("select r from Mensalidade r where r.statusPagamento = :statusPagamento "
			+ "and r.dataVencimento between :dataInicio and :datafim")
	public List<Mensalidade> findByStatusAndDataPagamento(	// TODO statusPagamento com erro
			@Param("statusPagamento") StatusPagamento statusPagamento,
			@Param("dataInicio") Date dataInicio, @Param("datafim") Date datafim);
	
	@Query("select r from Mensalidade r where r.statusPagamento = :statusPagamento "
			+ "and r.dataVencimento between :dataInicio and :datafim")
	public List<Mensalidade> findByStatusAndDataVencimento(	// TODO statusPagamento com erro
			@Param("statusPagamento") StatusPagamento statusPagamento,
			@Param("dataInicio") Date dataInicio, @Param("datafim") Date datafim);

}