import java.sql.*;
import java.util.ArrayList;

public class DatabaseHelper {
    ResultSet result=null;
    Connection con=null;
    String name="",roll="";
    int age;
    boolean regular;

/*    public static void main(String[] args) {
        DatabaseHelper db=new DatabaseHelper();
        db.connectDatabase();
        System.out.println( db.hasRollNumber("86"));
    }*/

    public boolean hasRollNumber(String rollNumber) {

        String query="SELECT *FROM student WHERE rollNumber ="+"\""+rollNumber+"\"";
        try {
            Statement statement= con.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                     name= resultSet.getString(1);
                     roll=resultSet.getString(2);
                     age= resultSet.getInt(3);
                    regular= resultSet.getBoolean(4);
                }
            return rollNumber.equals(roll);


        } catch (Exception e) {
            System.out.println("DatabaseHelper hasRollNumber : "+e.toString());
            return false;
        }

    }
    public void connectDatabase(){
        String userName="root";
        String password="love1u2mom";
        String driverType="com.mysql.cj.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/sms";

        try {
            Class.forName(driverType);
            con= DriverManager.getConnection(url,userName,password);
            if(con.isClosed()){
                System.out.println("Connection Unsuccessful");
            } else{
                System.out.println("Database Connected");
            }
        } catch (Exception e) {
            System.out.println("Error : "+ e.toString());
        }
    }

    public  boolean insertStudent(String name, String rollNumber,
                              int age, boolean regular) {
        PreparedStatement pst=null;
        String query="insert into student values ( ?,?,?,?)";

        try {
            pst=con.prepareStatement(query);
            pst.setString(1,name);
            pst.setString(2,rollNumber);
            pst.setInt(3,age);
            pst.setBoolean(4,regular);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("insertStudent : " +e.toString());
            return false;
        }

    }

    public ArrayList getStudentDetails() {
        ArrayList array=new ArrayList();
        array.add(name);
        array.add(roll);
        array.add(age);
        array.add(regular);
     return array;
    }

    public void intialzeData(Student student) {
        student.name=name;
        student.rollNumber=roll;
        student.age=age;
        student.regular=regular;
    }

    public int checkPayment(String rollNumber) {
        String query="SELECT *FROM student WHERE rollNumber ="+"\""+rollNumber+"\"";
     int paidStatus=0;
        try {
            Statement statement= con.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
               paidStatus=resultSet.getInt(4);
            }
            return paidStatus;



        } catch (Exception e) {
            System.out.println("DatabaseHelper hasRollNumber : "+e.toString());
            return 0;
        }
    }
}
