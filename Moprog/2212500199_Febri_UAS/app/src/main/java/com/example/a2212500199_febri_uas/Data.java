package com.example.a2212500199_febri_uas;

public class Data {
    private String id, nama, alamat;

    public Data() {
    }

    public Data(String id, String nama, String alamat){
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
