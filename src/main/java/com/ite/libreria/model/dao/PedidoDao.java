package com.ite.libreria.model.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ite.libreria.model.beans.Pedido;

public interface PedidoDao {
	
	boolean nuevoPedido(Pedido pedidoWeb);
	List<Pedido> listaPedidosDia(Date fecha);
	Pedido pedidoPorId(int idPedido);
	BigDecimal sumaPedido(int idPedido);
}
