package com.app.kopbi.dao;

import java.util.List;

import com.app.kopbi.model.ParamTransaksiKas;
import com.app.kopbi.model.TransaksiKas;

public interface TransaksiKasDAO {

	List<TransaksiKas> list(ParamTransaksiKas transaksiKas);
	boolean saveTransaksiKas(TransaksiKas kas);
	boolean importFile(String split);
}
