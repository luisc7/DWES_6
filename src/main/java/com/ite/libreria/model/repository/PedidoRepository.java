package com.ite.libreria.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ite.libreria.model.beans.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
