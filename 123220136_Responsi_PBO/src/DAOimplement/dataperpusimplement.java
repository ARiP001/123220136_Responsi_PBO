package DAOimplement;
import java.util.List;
import model.*;

public interface dataperpusimplement {
    public void insert(dataPerpus p);
    public void update(dataPerpus p);
    public void delete(int p);
    public List<dataPerpus>getAll();
}
