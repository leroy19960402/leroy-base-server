package com.leroy.base.core.student.service;


import com.leroy.base.core.student.vo.StudentVo;

import java.util.List;

public interface StudentService {
    //插入一个学生
    public void addStudent(StudentVo studentVo);
    //修改一个学生(jpa是根据id来修改的)
    public void updateStudent(StudentVo studentVo);
    //根据id删除一条数据
    public void deleteStudentById(Long id);
    //查询所有
    public List<StudentVo> findAll();
    //根据id查询一条数据(2.0后不能使用findOne了)
    public StudentVo findStudentById(Long id);
    //根据学生姓名查询多条数据
    public List<StudentVo> findStudentByName(String name);
}
