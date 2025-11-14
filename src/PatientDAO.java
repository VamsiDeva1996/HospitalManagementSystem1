import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class PatientDAO {
    public void addPatient(String name, int age, String gender)
    {
        try(Connection con = DBConnection.getConnection())
        {
            String query = "INSERT INTO patient(name,age,gender) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,name);
            ps.setInt(2,age);
            ps.setString(3,gender);
            ps.executeUpdate();
            System.out.println( "Patient added successfully!" );
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void viewPatient()
    {
        try (Connection con = DBConnection.getConnection()){
            Statement stmt = con.createStatement( );
            ResultSet rs = stmt.executeQuery("SELECT * FROM patient");
            while (rs.next())
            {
                System.out.println( rs.getInt("patient_id") + " - "
                        + rs.getString("name") + " - "
                        + rs.getInt("age") + " - "
                        + rs.getString("gender")) ;
            }

        }catch (Exception e)
        {
          e.printStackTrace();
        }
    }
    public void deletePatient(int id)
    {
        try(Connection con = DBConnection.getConnection()) {
            String sql = "DELETE FROM patient WHERE patient_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch (Exception e)
        {
          e.printStackTrace();
        }
    }
    public DefaultTableModel getAllPatient()
    {
        String[] columns ={"ID","NAME","AGE","GENDER"};
        DefaultTableModel model = new DefaultTableModel(columns,0);
        try(Connection con = DBConnection.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM patient");
            while (rs.next())
            {
                Object[] row ={
                        rs.getInt("patient_id"),rs.getString("name"),rs.getInt("age"),
                        rs.getString("gender")};
                model.addRow(row);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return model;
    }

}

