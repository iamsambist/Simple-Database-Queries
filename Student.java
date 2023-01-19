public class Student {
    public String name;
    public String rollNumber;
    public int age;
    public boolean regular;

    public Student( String name, String rollNumber, int age, boolean regular) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.age = age;
        this.regular = regular;
    }


    public void enrollIfNotExist(DatabaseHelper database) {
        // checking whether database have a student whose id is all ready exist or not
        if(!database.hasRollNumber(this.rollNumber)){
          boolean check = database.insertStudent(
                  this.name,this.rollNumber,this.age,this.regular);
          if(check){
              System.out.println("added Successfully");
              Main obj=new Main();
              obj.loginStudent();
          }else{
              System.out.println("Something is Wrong please re-enter data carefully");
          }
        }
    }
}
