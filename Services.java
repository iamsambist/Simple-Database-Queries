import practice.Database;

import java.util.Scanner;

public class Services {
    Scanner scanner=new Scanner(System.in);
    Student abc=null;
    DatabaseHelper xyz=null;
    public Services(Student student,DatabaseHelper database){
        abc=student;
        xyz=database;
    }
    public void provideServices(){
        boolean serviceCheck=true;
        while(serviceCheck){
            System.out.println("Press 1 : To get your details ");
            System.out.println("Press 2 : pay Semester Fee");
            System.out.println("Press 3 : Log out ");
            int choice=scanner.nextInt();
            switch (choice){
                case 1:
                    this.printStudentDetails();
                    break;
                case 2:
                    this.getPaymentStatus();
                    break;
                case 3:
                    serviceCheck=false;
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }

    private void getPaymentStatus() {
        int remainingAmount=xyz.checkPayment(abc.rollNumber);
        if(remainingAmount!=0 && remainingAmount > 0 ){
            System.out.println("You have "+remainingAmount+" to Pay");
        }else if(remainingAmount!=0 && remainingAmount < 0){
            System.out.println("You have "+remainingAmount+" to receive from college");
        }else{
            System.out.println("You have Paid your Bills");
        }
    }

    private void printStudentDetails() {
        System.out.println("Name = "+abc.name);
        System.out.println("Roll Number = "+abc.rollNumber);
        System.out.println("Age = "+abc.age);
        System.out.println("Regular = "+abc.regular);
    }
}
