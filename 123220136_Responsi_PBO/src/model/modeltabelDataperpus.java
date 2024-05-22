package model;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class modeltabelDataperpus extends AbstractTableModel{
    List <dataPerpus> dp;

    public modeltabelDataperpus (List<dataPerpus> dp) {
        this.dp = dp;
    }

    @Override
    public int getRowCount() {
        return dp.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override 
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Id"; 
            case 1:
                return "Judul";
            case 2:
                return "Penulis";
            case 3:
                return "Rating";
            case 4:
                return "Harga";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return dp.get(row).getId();
            case 1:
                return dp.get(row).getJudul();
            case 2:
                return dp.get(row).getPenulis();
            case 3:
                return dp.get(row).getRating();
            case 4:
                return dp.get(row).getHarga();
            default:
                return null;
        }
   }
}

