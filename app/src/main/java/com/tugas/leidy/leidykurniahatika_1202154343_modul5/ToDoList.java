package com.tugas.leidy.leidykurniahatika_1202154343_modul5;

public class ToDoList {
    private int id;
    private String nama, deskripsi, prioritas;

    public ToDoList(int id, String nama, String deskripsi, String prioritas) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.prioritas = prioritas;
    }

    public int getId() {
        return id;
    }

    public String getDekripsi() {
        return deskripsi;
    }

    public String getNama() {
        return nama;
    }

    public String getPrioritas() {
        return prioritas;
    }
}
