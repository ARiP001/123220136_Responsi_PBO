package DAOdataperpus;

import java.sql.*;
import java.util.*;
import connection.Connector;
import model.*;
import DAOimplement.dataperpusimplement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dataperpusDAO implements dataperpusimplement {
    Connection connection;

    final String select = "SELECT * FROM buku;";
    final String insert = "INSERT INTO buku (judul, penulis, rating, harga) VALUES ( ?, ?, ?, ?);";
    final String update = "UPDATE buku SET judul = ?, penulis = ?, rating = ?, harga = ?  WHERE id = ?;";
    final String delete = "DELETE FROM buku WHERE id = ?;";
    
    public dataperpusDAO() {
        connection = Connector.connection();
    }

    @Override
    public void insert(dataPerpus p) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, p.getJudul()); //urutin sesuai query
            statement.setString(2, p.getPenulis());
            statement.setFloat(3, p.getRating());
            statement.setFloat(4, p.getHarga());
            
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                p.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
   
    @Override
    public void update(dataPerpus p) {
       PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, p.getJudul()); //urutin sesuai query
            statement.setString(2, p.getPenulis());
            statement.setFloat(3, p.getRating());
            statement.setFloat(4, p.getHarga());
            statement.setInt(5, p.getId());
         

            statement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
           }
        }
    }

    
    @Override
    public List<dataPerpus> getAll() {
        List<dataPerpus> dp = null;
        try {
            dp = new ArrayList<dataPerpus>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                dataPerpus perpus = new dataPerpus();
                perpus.setId(rs.getInt("id")); //WAJIB SAMA KAYAK DI DATABASE !!!
                perpus.setJudul(rs.getString("judul"));
                perpus.setPenulis(rs.getString("penulis"));
                perpus.setRating(rs.getFloat("rating"));
                perpus.setHarga(rs.getFloat("harga"));
                dp.add(perpus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dataPerpus.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dp;
    }
}