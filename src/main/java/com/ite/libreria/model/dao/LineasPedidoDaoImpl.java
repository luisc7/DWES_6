package com.ite.libreria.model.dao;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ite.libreria.model.repository.LineasPedidoRepository;

@Repository
public class LineasPedidoDaoImpl implements LineasPedidoDao {

	@Autowired
	LineasPedidoRepository lprepo;

	@Override
	public BigDecimal importePedido(int idPedido) {
		return lprepo.totalPedido(idPedido);
	}
}
