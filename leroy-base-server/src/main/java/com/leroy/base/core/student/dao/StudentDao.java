package com.leroy.base.core.student.dao;


import com.leroy.base.core.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//泛型中第一个参数是实体类，第二个是id类型
@Repository
public interface StudentDao extends JpaRepository<Student, Long> {
    //根据学生姓名查询数据
    public List<Student> findByName(String name);
}
