package com.leroy.base.core.student.service.impl;

import com.leroy.base.core.student.dao.StudentDao;
import com.leroy.base.core.student.entity.Student;
import com.leroy.base.core.student.service.StudentService;
import com.leroy.base.core.student.vo.StudentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    private static Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    //插入一个学生
    @Override
    public void addStudent(StudentVo studentVo) {
        Student student = new Student();
        BeanUtils.copyProperties(studentVo, student);
        studentDao.save(student);
    }

    //修改一个学生(jpa是根据id来修改的)
    @Override
    public void updateStudent(StudentVo studentVo) {
        Student student = new Student();
        BeanUtils.copyProperties(studentVo, student);
        studentDao.save(student);
    }

    //根据id删除一条数据
    @Override
    public void deleteStudentById(Long id) {
        studentDao.deleteById(id);
    }

    //查询所有
    @Override
    public List<StudentVo> findAll() {
        List<Student> studentList = studentDao.findAll();
        List<StudentVo> studentVoList = new ArrayList<>();
        BeanUtils.copyProperties(studentList, studentVoList);
        return studentVoList;
    }

    //根据id查询一条数据(2.0后不能使用findOne了)
    @Override
    public StudentVo findStudentById(Long id) {
        Student student = studentDao.findById(id).get();
        StudentVo studentVo = new StudentVo();
        BeanUtils.copyProperties(student, studentVo);
        return studentVo;
    }

    //根据学生姓名查询多条数据
    @Override
    public List<StudentVo> findStudentByName(String name) {
        List<Student> studentList = studentDao.findByName(name);
        List<StudentVo> studentVoList = new ArrayList<>();
        BeanUtils.copyProperties(studentList, studentVoList);
        return studentVoList;
    }
}
