package com.ite.libreria.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ite.libreria.model.beans.Pedido;
import com.ite.libreria.model.repository.PedidoRepository;

@Repository
public class PedidoDaoImpl implements PedidoDao {
	
	@Autowired
	private PedidoRepository pdao;

	@Override
	public boolean nuevoPedido(Pedido pedidoWeb) {
		if(pedidoWeb == null)
			return false;
		else {
			pdao.save(pedidoWeb);
			return true;
		}
	}

}
