package com.app.kopbi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TransaksiKas extends DateAudit {
	@Id
	String id;
	String tanggalTransaksi;
	String nominal;
	String keterangan;
	String akun;
	String kodeDariKas;
	String dariKas;
	String kodeUntukKas;
	String untukKas;
	String jenisTransaksi;
	String ketJenisTransaksi;
	String ketKas;
	String tanggalUpdate;
	String userUpdate;
	String statusAktif = "AKTIF";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTanggalTransaksi() {
		return tanggalTransaksi;
	}
	public void setTanggalTransaksi(String tanggalTransaksi) {
		this.tanggalTransaksi = tanggalTransaksi;
	}
	public String getNominal() {
		return nominal;
	}
	public void setNominal(String nominal) {
		this.nominal = nominal;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	public String getAkun() {
		return akun;
	}
	public void setAkun(String akun) {
		this.akun = akun;
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
	public String getKetJenisTransaksi() {
		return ketJenisTransaksi;
	}
	public void setKetJenisTransaksi(String ketJenisTransaksi) {
		this.ketJenisTransaksi = ketJenisTransaksi;
	}
	public String getKetKas() {
		return ketKas;
	}
	public void setKetKas(String ketKas) {
		this.ketKas = ketKas;
	}
	public String getTanggalUpdate() {
		return tanggalUpdate;
	}
	public void setTanggalUpdate(String tanggalUpdate) {
		this.tanggalUpdate = tanggalUpdate;
	}
	public String getUserUpdate() {
		return userUpdate;
	}
	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}
	public String getStatusAktif() {
		return statusAktif;
	}
	public void setStatusAktif(String statusAktif) {
		this.statusAktif = statusAktif;
	}
	public String getKodeDariKas() {
		return kodeDariKas;
	}
	public void setKodeDariKas(String kodeDariKas) {
		this.kodeDariKas = kodeDariKas;
	}
	public String getKodeUntukKas() {
		return kodeUntukKas;
	}
	public void setKodeUntukKas(String kodeUntukKas) {
		this.kodeUntukKas = kodeUntukKas;
	}
}
