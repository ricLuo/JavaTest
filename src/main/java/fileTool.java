/**
 * Created by Owner on 2017/5/29.
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

/*File tool for Reading in Student Information From Text File */

public class fileTool {

    String filePath;
    File file;

    public fileTool(){}

    public fileTool(String path){
        this.filePath = path;
        this.file = new File(filePath);
    }

    public Student[] readStuInfo(String path) throws FileNotFoundException{
        File file = new File(path);
        Scanner reader = new Scanner(file);
        reader.useDelimiter(",+|\\s+|\n+|\t+");
        List<Student> list = new ArrayList<>();
        while(reader.hasNext()){
            String firstName = reader.next();
            String lastName = reader.next();
            String ageStr = reader.next().trim();
            int age = Integer.valueOf(ageStr);
            Student st = new Student(firstName,lastName,age);
            list.add(st);
//            System.out.println("firstName: "+firstName+"\tlastName: "+lastName+"\t"+"age: "+ageStr);
        }
        Student[] students = new Student[list.size()];
        int index=0;
        System.out.println("\t\t\t---Read in Student Info---");
        for(Student stu:list){
            students[index++] = stu;
            System.out.println("firstName: "+stu.firstName+"\tlastName: "+stu.lastName+"\t"+"age: "+stu.age);
        }
        System.out.println();
        return students;
    }


//    public static void main(String[] args) throws FileNotFoundException{
//        fileTool t = new fileTool();
//        Student[] stuts = t.readStuInfo(filePath);
//        for(Student st:stuts) {
//            System.out.println(st.firstName+"\t"+st.lastName+"\t"+st.age);
//        }
//    }
}
