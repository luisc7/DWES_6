package com.ite.libreria.model.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ite.libreria.model.beans.LineasPedido;

public interface LineasPedidoRepository extends JpaRepository<LineasPedido, Integer>{
	
	@Query(
			"select sum(lp.precioVenta) from LineasPedido lp "
			+ "where lp.pedido.idPedido = ?1")
	public BigDecimal totalPedido(int idPedido);

}
