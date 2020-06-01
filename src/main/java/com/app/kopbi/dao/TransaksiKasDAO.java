package com.app.kopbi.dao;

import java.util.Map;

import com.app.kopbi.model.ResponseData;
import com.app.kopbi.model.TransaksiKas;

public interface TransaksiKasDAO {

	ResponseData list(Map<String, Object> transaksiKas);
	boolean saveTransaksiKas(TransaksiKas kas);
	boolean importFile(String split);
}
