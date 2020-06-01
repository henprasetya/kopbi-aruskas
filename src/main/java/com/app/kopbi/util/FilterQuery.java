package com.app.kopbi.util;

import java.util.Map;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class FilterQuery {

	Map<String, Object> filter;
	
	public FilterQuery(Map<String, Object> filter) {
		this.filter = filter;
	}
	
	public Query getFilter() {
		Query query = new Query();
		for(Map.Entry<String, Object> f : filter.entrySet()) {
			if(f.getKey().equalsIgnoreCase("tanggalMulai")) {
				if(f.getValue().toString().trim().length()>0) {
					query.addCriteria(Criteria.where("tanggalTransaksi").gte(f.getValue()).lt(filter.get("tanggalAkhir")));
				} 
			}else
			if(!f.getKey().equalsIgnoreCase("mulai") && !f.getKey().equalsIgnoreCase("akhir") && !f.getKey().equalsIgnoreCase("tanggalAkhir")) {
				if(f.getValue().toString().trim().length()>0) {
					query.addCriteria(Criteria.where(f.getKey()).regex(f.getValue().toString()));
				}
			}
			
		}
		return query;
	}
}
