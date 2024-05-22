package model;

public class dataPerpus {
    private int id;
    private String judul;
    private String penulis;
    private Float rating;
    private Float harga;

    public Float getHarga() {
        return harga;
    }

    public void setHarga(Float harga) {
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
