package com.ite.libreria.model.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ite.libreria.model.beans.Pedido;
import com.ite.libreria.model.repository.PedidoRepository;

@Repository
public class PedidoDaoImpl implements PedidoDao {
	
	@Autowired
	private PedidoRepository prepo;

	@Override
	public boolean nuevoPedido(Pedido pedidoWeb) {
		if(pedidoWeb == null)
			return false;
		else {
			prepo.save(pedidoWeb);
			return true;
		}
	}

	@Override
	public List<Pedido> listaPedidosDia(Date fecha) {
		return prepo.listaPedidosPorFecha(fecha);
	}

	@Override
	public Pedido pedidoPorId(int idPedido) {
		return prepo.findById(idPedido).orElse(null);
	}

	@Override
	public BigDecimal sumaPedido(int idPedido) {
		BigDecimal importe = prepo.importePedido(idPedido);
		if (importe != null)
			return importe;
		else
			return BigDecimal.ZERO;
	}

}
