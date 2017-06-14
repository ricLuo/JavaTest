import java.io.FileNotFoundException;

/**
 * Created by Owner on 2017/5/29.
 */

import java.io.File;


public class Solution {

    Class class_1;
    Class class_2;

    public Solution(){

    }

    public void loadFile()  throws FileNotFoundException{
        String directory = new File("").getAbsolutePath();
        String path_1=directory+"\\src\\main\\resources\\studentInfo.txt";
        String path_2=directory+"\\src\\main\\resources\\studentInfo_2.txt";
        fileTool ft = new fileTool();
        Student[] StuArray_1 = ft.readStuInfo(path_1);
        Student[] StuArray_2 = ft.readStuInfo(path_2);
        this.class_1 = new Class(StuArray_1);
        this.class_2 = new Class(StuArray_2);
    }


    public static void main(String[] args) throws FileNotFoundException{
        Solution sl = new Solution();
        sl.loadFile();
        int result = sl.solve(sl.class_1, sl.class_2);
        System.out.println("Number of Same Student in Both Classes: "+result);
    }

    public int solve(Class class1, Class class2){
        if(class1==null || class2==null)    return -1;
        Student[] students_1 = class1.enrolled;
        Student[] students_2 = class2.enrolled;
        quickSort(students_1, 0, students_1.length-1);
        quickSort(students_2, 0, students_2.length-1);
        int index_1=0;
        int index_2=0;
        int numBothStu = 0;
        while(index_1<students_1.length && index_2<students_2.length){
            Student stu_1 = students_1[index_1];
            Student stu_2 = students_2[index_2];
            if(isSameStudent(stu_1, stu_2)){
                numBothStu++;
                index_1++;
                index_2++;
            }
            else if(compare(stu_1, stu_2)<0){
                index_1++;
            }
            else {
                index_2++;
            }
        }

        return numBothStu;
    }


    public void quickSort(Student[] st, int low, int high){
        if (st == null || st.length == 0) return;
        int i = low;
        int j = high;
        Student pivot = st[i + (j - i) / 2];
        while (i <= j) {
            while (compare(st[i], pivot) < 0) {
                i++;
            }
            while (compare(st[j], pivot) > 0) {
                j--;
            }
            if (i <= j) {
                swap(st, i, j);
                i++;
                j--;
            }
        }
        if (low < i-1) {
            quickSort(st, low, i-1);
        }
        if (i < high){
            quickSort(st, i, high);
        }
    }

//    String.compareTo has constant complexity as we suppose name length is limited
    public int compare(Student st1, Student st2){
        if(st1.firstName.compareTo(st2.firstName)<0)    return -1;
        else if(st1.firstName.compareTo(st2.firstName)>0)    return 1;
        else if(st1.lastName.compareTo(st2.lastName)<0) return -1;
        else if(st1.lastName.compareTo(st2.lastName)>0) return 1;
        else if(st1.age<st2.age)    return -1;
        else if(st1.age>st2.age)    return 1;
        else return 0;
    }

    public void swap(Student[] st, int x, int y){
        Student temp = new Student(st[x].firstName,st[x].lastName,st[x].age);
        st[x] = st[y];
        st[y] = temp;
    }


    public boolean isSameStudent(Student st1, Student st2){
        if(  st1.firstName.compareTo(st2.firstName)==0
                && st1.lastName.compareTo(st2.lastName)==0
                && st1.age==st2.age)
            return true;
        return false;
    }

}
















