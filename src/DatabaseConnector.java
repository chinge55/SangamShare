import java.sql.*;
public class DatabaseConnector
{
    private String username;
    private String password;
    private boolean passwordMatched;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public DatabaseConnector(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    boolean Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db?characterEncoding=latin1&useConfigs=maxPerformance", "root", "iamumanga"
            );
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from users");
            while(rs.next()) {
                String db_username = rs.getString(1);

                String db_password = rs.getString(2);
                if(username.equals(db_username) && password.equals(db_password)) {
                    passwordMatched = true;
                    break;
                }
                else
                    passwordMatched = false;
                //System.out.println(rs.getString(1)+"  "+rs.getString(2));
            }
            con.close();
            return passwordMatched;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;

        }
    }
}
