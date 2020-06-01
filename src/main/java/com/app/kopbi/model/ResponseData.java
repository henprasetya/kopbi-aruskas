package com.app.kopbi.model;

public class ResponseData {

	Long totalRow = 0l;
	int mulai = 1;
    int akhir = 10;
    Object data;
    
	public Long getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(Long totalRow) {
		this.totalRow = totalRow;
	}
	public int getMulai() {
		return mulai;
	}
	public void setMulai(int mulai) {
		this.mulai = mulai;
	}
	public int getAkhir() {
		return akhir;
	}
	public void setAkhir(int akhir) {
		this.akhir = akhir;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
    
}
