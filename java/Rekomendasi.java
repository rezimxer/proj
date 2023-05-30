package com.apps.frontendsiswa.fitursiswa.rekomendasi;

import com.google.gson.annotations.SerializedName;

public class Rekomendasi {
    @SerializedName("id_rekomendasi")
    private String id_rekomendasi;
    @SerializedName("nama_tempat")
    private String nama_tempat;

    @SerializedName("jarak")
    private String jarak;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;

    @SerializedName("alamat_lat")
    private String alamat_lat;
    @SerializedName("alamat_lng")
    private String alamat_lng;

    @SerializedName("kategori")
    private String kategori;


    @SerializedName("address_line")
    private String address_line;

    public String getAddress_line() {
        return address_line;
    }

    public void setAddress_line(String address_line) {
        this.address_line = address_line;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }


    public String getAlamat_lng() {
        return alamat_lng;
    }

    public void setAlamat_lng(String alamat_lng) {
        this.alamat_lng = alamat_lng;
    }


    public String getAlamat_lat() {
        return alamat_lat;
    }

    public void setAlamat_lat(String alamat_lat) {
        this.alamat_lat = alamat_lat;
    }

    public String getId_rekomendasi() {
        return id_rekomendasi;
    }

    public void setId_rekomendasi(String id_rekomendasi) {
        this.id_rekomendasi = id_rekomendasi;
    }

    public String getNama_tempat() {
        return nama_tempat;
    }

    public void setNama_tempat(String nama_tempat) {
        this.nama_tempat = nama_tempat;
    }

    public String getJarak() {
        return jarak;
    }

    public void setJarak(String jarak) {
        this.jarak = jarak;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }


    //tambahan
    private String kode;
    private String pesan;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}
