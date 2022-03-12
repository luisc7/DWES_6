package com.ite.libreria.model.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ite.libreria.model.beans.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
	@Query(
			"select usr from Usuario usr "
			+ "inner join usr.perfiles up "
			+ "where up.idPerfil = ?1")
	public List<Usuario> listaPorIdPerfil(int idPerfil);
	
	
	// https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/#jpa.named-parameters
	@Query(
			"select usr from Usuario usr "
			+ "inner join usr.perfiles up "
			+ "where up.idPerfil = ("
												+ "select p.idPerfil from Perfile p "
												+ "where p.descripcion = :descripcionPerfil)")
	public List<Usuario> listaPorPerfil(@Param ("descripcionPerfil") String descripcionPerfil);
	
	
	
	/*@Query(value= 
			"select sum(cantidad) from lineas_pedido lp "
			+ "inner join pedidos p "
			+ "on lp.id_pedido=p.id_pedido "
			+ "where p.username=?1",
			nativeQuery = true)*/
	@Query(
			"select sum(lp.cantidad) from LineasPedido lp "
			+ "inner join lp.pedido p "
			+ "where p.usuario.username = ?1")
	public Integer librosCompradosPorCliente(String username);
	
	/*@Query(value= 
			"select sum(precio_venta) from lineas_pedido lp "
			+ "inner join pedidos p on lp.id_pedido=p.id_pedido "
			+ "where p.username=?1",
			nativeQuery = true)*/
	@Query("select sum(lp.precioVenta) from LineasPedido lp "
			+ "inner join lp.pedido p "
			+ "where p.usuario.username = ?1")
	public BigDecimal importeTotalGastadoCliente(String username);
	
	/*@Query(value= 
			"select count(distinct (libros.id_tema)) from libros "
			+ "inner join lineas_pedido on lineas_pedido.isbn=libros.isbn "
			+ "inner join pedidos on pedidos.id_pedido=lineas_pedido.id_pedido "
			+ "where pedidos.username=?1",
			nativeQuery = true)*/
	@Query(
			"select count(distinct(lib.tema)) from Libro lib "
			+ "inner join LineasPedido lp "
			+ "on lp.libro=lib "
			+ "inner join lp.pedido p "
			+ "where p.usuario.username = ?1")
	public Integer tematicasDiferentesDeCliente(String username);
}
