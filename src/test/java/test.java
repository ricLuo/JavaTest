/**
 * Created by Owner on 2017/5/29.
 */

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test {

    private Student[] students_1;
    private Student[] students_2;
    private Solution sol;
    List<Student> studentList = new ArrayList<>();

    @Before
    public void init() throws FileNotFoundException{
        fileTool t = new fileTool();
        sol = new Solution();
        studentList = new ArrayList<>();
        readStuInfo();
        students_1 = new Student[studentList.size()];
        int index=0;
//        store it in array so we can use Arrays.sort(arr, comparator) in comparison with our quick sort
        for(Student stu:studentList){
            students_1[index++] = stu;
            System.out.println("firstName: "+stu.firstName+"\tlastName: "+stu.lastName+"\t"+"age: "+stu.age);
        }
    }

    public void readStuInfo() throws FileNotFoundException {
//        Read Student Info from text files.
        String path = new File("").getAbsolutePath()+"\\src\\main\\resources\\studentInfo.txt";
        File file = new File(path);
        Scanner reader = new Scanner(file);
        reader.useDelimiter(",+|\\s+|\n+|\t+");
        while(reader.hasNext()){
            String firstName = reader.next();
            String lastName = reader.next();
            String ageStr = reader.next().trim();
            int age = Integer.valueOf(ageStr);
            Student st = new Student(firstName,lastName,age);
            studentList.add(st);
//            System.out.println("firstName: "+firstName+"\tlastName: "+lastName+"\t"+"age: "+ageStr);
        }
        reader.close();
    }

    @Test
    public void testSortNull(){
        sol.quickSort(null,0,10);
    }

    public boolean validate(Student st1, Student st2){
        System.out.println("Compare:"+sol.compare(st1, st2));
        System.out.println("firstName:"+st1.firstName.compareTo(st2.firstName));
        System.out.println("lastName:"+st1.lastName.compareTo(st2.lastName));
        System.out.println("age:"+st1.firstName.compareTo(st2.firstName));
        if(sol.compare(st1, st2)>0)    return false;
        return true;
    }

    @Test
    public void testQuickSort(){
        Student[] copyArray = students_1.clone();
        sol.quickSort(copyArray, 0 , copyArray.length-1);
        System.out.println("Sorted Array:");
        printResult(copyArray);
        System.out.println();
        for(int i=0;i<copyArray.length-1;i++) {
            for(int j=i+1;j<copyArray.length;j++) {
                System.out.println("firstName: " + copyArray[i].firstName + "\tlastName: " + copyArray[i].lastName + "\t" + "age: " + copyArray[i].age);
                System.out.println("firstName: " + copyArray[i+1].firstName + "\tlastName: " + copyArray[i+1].lastName + "\t" + "age: " + copyArray[i+1].age);
                if (!validate(copyArray[i], copyArray[j])) fail("Sort Failure");
            }
        }
        assertTrue(true);
    }

    @Test
    public void testSortTime(){
        long startTime_std = System.nanoTime();
        Student[] test_std = students_1.clone();
        Student[] test_quickSort = students_1.clone();
        for(int i=0;i<students_1.length-1;i++){
            for(int j=i+1;j<test_std.length;j++) {
                if (sol.compare(test_std[i], test_std[j]) > 0) {
                    sol.swap(test_std, i, j);
                }
            }
        }
        long stopTime_std = System.nanoTime();
        long startTime_quickSort = System.nanoTime();
        sol.quickSort(test_quickSort, 0 , test_quickSort.length-1);
        long stopTime_quickSort = System.nanoTime();
        long std_time = stopTime_std-startTime_std;
        long quickSort_time = stopTime_quickSort-startTime_quickSort;
        System.out.println("Brute Force Sort Time: "+ std_time);
        System.out.println("Quick Sort Time: " + quickSort_time);
        System.out.println("Difference is not large, since sample number is small");
        assertTrue(std_time>quickSort_time);
    }

    @Test
    public void TestResult() throws FileNotFoundException{
        Solution solution = new Solution();
        solution.loadFile();
        Student[] students_1 = solution.class_1.enrolled;
        Student[] students_2 = solution.class_2.enrolled;
        int numGetManually = findSame(students_1,students_2);
        System.out.println("Test Result:  "+numGetManually);
        int numQuickSortAndWindowSolve = solution.solve(solution.class_1,solution.class_2);
        System.out.println("Solution Result:  "+numQuickSortAndWindowSolve);
        assertTrue(numGetManually==numQuickSortAndWindowSolve);
    }

    public int findSame(Student[] st_1, Student[] st_2){
        int count=0;
        for(int i=0;i<st_1.length;i++){
            for(int j=0;j<st_2.length;j++){
                if(sol.isSameStudent(st_1[i], st_2[j])) count++;
            }
        }
        return count;
    }

    public void printResult(Student[] stud){
        for(Student st:stud){
            System.out.println("firstName: "+st.firstName+"\tlastName: "+st.lastName+"\t"+"age: "+st.age);
        }
    }


}
