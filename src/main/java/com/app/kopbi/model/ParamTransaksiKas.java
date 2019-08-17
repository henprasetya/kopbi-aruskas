package com.app.kopbi.model;

public class ParamTransaksiKas {

	String tanggalMulai = "";
    String tanggalAkhir = "";
    String dariKas = "";
	String untukKas = "";
	String jenisTransaksi = "";
    int mulai = 1;
    int akhir = 10;
    
	public String getTanggalMulai() {
		return tanggalMulai;
	}
	public void setTanggalMulai(String tanggalMulai) {
		this.tanggalMulai = tanggalMulai;
	}
	public String getTanggalAkhir() {
		return tanggalAkhir;
	}
	public void setTanggalAkhir(String tanggalAkhir) {
		this.tanggalAkhir = tanggalAkhir;
	}
	public String getDariKas() {
		return dariKas;
	}
	public void setDariKas(String dariKas) {
		this.dariKas = dariKas;
	}
	public String getUntukKas() {
		return untukKas;
	}
	public void setUntukKas(String untukKas) {
		this.untukKas = untukKas;
	}
	public String getJenisTransaksi() {
		return jenisTransaksi;
	}
	public void setJenisTransaksi(String jenisTransaksi) {
		this.jenisTransaksi = jenisTransaksi;
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
}
