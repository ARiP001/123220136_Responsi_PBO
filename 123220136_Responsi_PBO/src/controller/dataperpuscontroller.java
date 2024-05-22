package controller;

import java.util.List;
import DAOimplement.dataperpusimplement;
import DAOdataperpus.dataperpusDAO;
import model.*;
import view.viewPerpus;
import javax.swing.JOptionPane;

public class dataperpuscontroller {

    viewPerpus frame;
    dataperpusimplement impdataperpus;
    List<dataPerpus> dp;

    public dataperpuscontroller(viewPerpus frame) {
        this.frame = frame;
        impdataperpus = new dataperpusDAO();
        dp = impdataperpus.getAll();
    }

    public void isitable() {
        dp = impdataperpus.getAll();
        modeltabelDataperpus mp = new modeltabelDataperpus(dp);
        frame.getjTableIsiBuku().setModel(mp);
        frame.getjTableIsiBuku().getColumnModel().getColumn(0).setMaxWidth(30);
        frame.getjTableIsiBuku().getColumnModel().getColumn(3).setMaxWidth(50);
    }

    public void insert() {
        dataPerpus dp = new dataPerpus();

        try {
            dp.setJudul(frame.getjTextFieldJudul().getText());
            dp.setPenulis(frame.getjTextFieldPenulis().getText());

            Float rating = Float.parseFloat(frame.getjTextFieldRating().getText());
            Float harga = Float.parseFloat(frame.getjTextFieldHarga().getText());

            if (isValidRating(rating) && harga >= 0) {
                dp.setRating(rating);
                float tempHarga = harga + 500 + (rating * 100);
                dp.setHarga(tempHarga);

                impdataperpus.insert(dp);

                JOptionPane.showMessageDialog(null, "Data Buku Berhasil Ditambahkan!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Nilai Rating / Harga tidak sesuai", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Input tidak valid: " + e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean isValidRating(Float value) { //cek validasi rating
        return value >= 1 && value <= 5;
    }

    public void update() {

        dataPerpus dp = new dataPerpus();
        try {
            dp.setJudul(frame.getjTextFieldJudul().getText());
            dp.setPenulis(frame.getjTextFieldPenulis().getText());

            int id = Integer.parseInt(frame.getjTextFieldId().getText());
            Float rating = Float.parseFloat(frame.getjTextFieldRating().getText());
            Float harga = Float.parseFloat(frame.getjTextFieldHarga().getText());

            dp.setId(id);

            if (isValidRating(rating) && harga >= 0) {
                dp.setRating(rating);
                float tempHarga = harga + 500 + (rating * 100);
                dp.setHarga(tempHarga);

                impdataperpus.update(dp);

                JOptionPane.showMessageDialog(null, "Data Buku Berhasil Diupdate!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Nilai Rating / Harga tidak sesuai", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Input tidak valid: " + e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void delete() {
        int selectedRow = frame.getjTableIsiBuku().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Silakan pilih yang ingin dihapus terlebih dahulu", "No row selected", JOptionPane.WARNING_MESSAGE);
        } else {
            int id = Integer.parseInt(frame.getjTextFieldId().getText());
            impdataperpus.delete(id);
            JOptionPane.showMessageDialog(null, "Data Buku Berhasil Dihapus!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
