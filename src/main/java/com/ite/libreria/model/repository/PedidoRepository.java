package com.ite.libreria.model.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ite.libreria.model.beans.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	@Query(
			"select p from Pedido p "
			+ "inner join Usuario u "
			+ "on p.usuario=u.username "
			+ "where p.fechaAlta = ?1")
	List<Pedido> listaPedidosPorFecha(Date fecha);
	
	@Query(
			"select sum(lp.precioVenta) from LineasPedido lp "
			+ "inner join lp.pedido p "
			+ "where p.idPedido = ?1")
	BigDecimal importePedido(int pedido);
}
