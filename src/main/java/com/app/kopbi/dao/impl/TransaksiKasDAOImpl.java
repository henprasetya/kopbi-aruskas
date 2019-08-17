package com.app.kopbi.dao.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.app.kopbi.dao.TransaksiKasDAO;
import com.app.kopbi.model.ParamTransaksiKas;
import com.app.kopbi.model.TransaksiKas;

@Repository
public class TransaksiKasDAOImpl implements TransaksiKasDAO {

	@Autowired
	MongoTemplate template;

	@Override
	public List<TransaksiKas> list(ParamTransaksiKas filter) {
		Query query = new Query();
		if (filter.getTanggalMulai().trim().length() > 0 && filter.getTanggalAkhir().trim().length() > 0) {
			query.addCriteria(
					Criteria.where("tglTransaksi").gte(filter.getTanggalMulai()).lt(filter.getTanggalAkhir()));
		}
		query.skip((filter.getMulai() - 1));
		query.limit(filter.getAkhir());
		return template.find(query, TransaksiKas.class);
	}

	@Override
	public boolean saveTransaksiKas(TransaksiKas kas) {
		try {
			template.save(kas);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean importFile(String split) {
		try {
			List<TransaksiKas> list = getList(split);
			template.insert(list, TransaksiKas.class);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private List<TransaksiKas> getList(String split) {
		List<TransaksiKas> list = new ArrayList();
		String csvFile = "/home/mti/projectkopbi/data_kas.csv";
		BufferedReader br = null;
		String line = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] a = line.split(split);
				TransaksiKas t = new TransaksiKas();
				try {
					t.setAkun(a[4].trim());
				} catch (Exception e) {
					t.setAkun("");
				}
				try {
					t.setDariKas(a[5].trim());
				} catch (Exception e) {
					t.setDariKas("");

				}
				try {
					t.setJenisTransaksi(a[7].trim());
				} catch (Exception e) {
					t.setJenisTransaksi("");
				}
				try {
					t.setKeterangan(a[3].trim());
				} catch (Exception e) {
					t.setKeterangan("");
				}
				try {
					t.setKetJenisTransaksi("");
				} catch (Exception e) {
					t.setKetJenisTransaksi("");
				}
				try {
					t.setKetKas(a[8].trim());
				} catch (Exception e) {
					t.setKetKas("");
				}
				try {
					t.setNominal(a[2].trim());
				} catch (Exception e) {
					t.setNominal("");
				}
				try {
					t.setTanggalTransaksi(a[1].trim());
				} catch (Exception e) {
					t.setTanggalTransaksi("");
				}
				try {
					t.setTanggalUpdate(sdf.format(new Date()));
				} catch (Exception e) {
					t.setTanggalUpdate("");
				}
				try {
					t.setUntukKas(a[6].trim());
				} catch (Exception e) {
					t.setUntukKas("");
				}
				try {
					t.setUserUpdate(a[10].trim());
				} catch (Exception e) {
					t.setUserUpdate("");
				}
				list.add(t);
			}
		} catch (Exception e) {
		}
		return list;
	}
}
