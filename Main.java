import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
   static DatabaseHelper database=new DatabaseHelper();


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Main obj=new Main();
        boolean run=true;
        database.connectDatabase();
        while(run){
            System.out.println("Press 1: Enroll Your_Self");
            System.out.println("Press 2: Login Your_Self");
            System.out.println("Press 3: Exist Application");
            int choice=sc.nextInt();
            switch (choice){
                case 1: obj.enrollStudent();
                break;
                case 2: obj.loginStudent();
                 break;
                case 3:
                    run=obj.existApplication();
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }
    private void enrollStudent() {


        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter Name : ");
            String name=reader.readLine();
            System.out.println("Enter Roll Number : ");
            String rollNumber=reader.readLine();
            System.out.println("Are you a Regular Student "+"\n"+" true if 'YES'");
            String temp=reader.readLine();
            boolean regular=Boolean.parseBoolean(temp);
            // System.out.println(regular);
            System.out.println("Enter Age : ");
            int age=reader.read();
            Student student=new Student(name,rollNumber,age,regular);
            student.enrollIfNotExist(database);

        } catch (Exception e) {
            System.out.println("Enrolling Student"+e.toString());
        }

    }
    private boolean existApplication() {
        return false;
    }

    public void loginStudent() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter RollNumber : ");
        String rollNumber=sc.nextLine();
        boolean check=database.hasRollNumber(rollNumber);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        if(check){
            System.out.println("SuccessFully LogIn");
            Student student=new Student(""," ",0,true);
            database.intialzeData(student);
            Services service=new Services(student,database);
            service.provideServices();
        } else {
            System.out.println("Please Enroll yourself First");
        }



    }


}
