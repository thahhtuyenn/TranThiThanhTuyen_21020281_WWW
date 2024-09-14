package com.iuh.student.thanhtuyen.thuchanh_tuan03.business;

import com.iuh.student.thanhtuyen.thuchanh_tuan03.models.Student;

import java.util.ArrayList;
import java.util.List;

public class BaseProcess {
    private final List<Student> students = new ArrayList<>();

    public List<Student> findAll(){
        students.add(new Student(1, "Nguyen Van A", "nguyenvana@gmail.com", "0123456789"));
        students.add(new Student(2, "Nguyen Van B", "nguyenvanb@gmail.com", "0123456789"));
        students.add(new Student(3, "Nguyen Van C", "nguyenvanc@gmail.com", "0123456789"));
        students.add(new Student(4, "Nguyen Van D", "nguyenvand@gmail.com", "0123456789"));
        return students;
    }

    public Student findById(int id){
        return students.stream().filter(student -> student.getId() == id).findFirst().orElse(null);
    }

}
