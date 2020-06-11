package com.leroy.base.core.student.api;

import com.leroy.base.core.common.vo.CommonResult;
import com.leroy.base.core.student.service.StudentService;
import com.leroy.base.core.student.vo.StudentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(StudentController.PATH)
public class StudentController {

    public static final String PATH = "/student";

    private static Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    /**
     * @param studentVo
     * @return CommonResult
     * @description 添加一个学生
     * @author lideyou
     * @date 2020年6月8日15:27:14
     */
    @PostMapping(value = "/addStudent")
    public CommonResult addStudent(@RequestBody StudentVo studentVo) {

        CommonResult result = new CommonResult();
        try {
            studentService.addStudent(studentVo);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }

    /**
     * @param studentVo
     * @return CommonResult
     * @description 修改一个学生(jpa是根据id来修改的)
     * @author lideyou
     * @date 2020年6月8日15:27:14
     */
    @PostMapping(value = "/updateStudent")
    public CommonResult updateStudentById(@RequestBody StudentVo studentVo) {
        CommonResult result = new CommonResult();
        try {
            studentService.updateStudent(studentVo);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }

    /**
     * @param id
     * @return CommonResult
     * @description 根据id删除一条数据
     * @author lideyou
     * @date 2020年6月8日15:27:14
     */
    @PostMapping(value = "/deleteStudent")
    public CommonResult deleteStudentById(@RequestParam(value = "id") Long id) {
        CommonResult result = new CommonResult();
        try {
            studentService.deleteStudentById(id);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }

    //查询所有
    @PostMapping(value = "/findAll")
    public CommonResult findAll() {
        CommonResult result = new CommonResult();
        try {
            List<StudentVo> list = studentService.findAll();
            //将查询结果封装到CommonResult中
            result.setData(list);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }

    //根据id查询一条数据(2.0后不能使用findOne了)
    @PostMapping(value = "/findStudentById")
    public CommonResult findStudentById(@RequestParam(value = "id") Long id) {
        CommonResult result = new CommonResult();
        try {
            StudentVo studentVo = studentService.findStudentById(id);
            //将查询结果封装到CommonResult中
            result.setData(studentVo);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }

    //根据学生姓名查询多条数据
    @PostMapping(value = "/findStudentByName")
    public CommonResult findStudentByName(@RequestParam(value = "name") String name) {
        CommonResult result = new CommonResult();
        try {
            List<StudentVo> studentList = studentService.findStudentByName(name);
            //将查询结果封装到CommonResult中
            result.setData(studentList);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(500);
            result.setMsg("失败");
            return result;
        }
    }
}
