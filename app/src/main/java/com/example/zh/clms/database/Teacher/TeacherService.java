package com.example.zh.clms.database.Teacher;

import com.example.zh.clms.database.Teacher.Teacher;

import java.util.List;
import java.util.Map;

//该接口实现对数据库中教师表增删改查
public interface TeacherService {
    //添加数据
    public boolean addTeacher(Teacher teacher);

    //删除数据
    public boolean deleteTeacher(Teacher teacher);

    //修改数据
    public boolean updateTeacher(Teacher teacher,Object[] params );

    //查询单个数据，返回map键值对
    public Map<String, String> viewTeacher(Teacher teacher);

    //查询多个数据
    public List<Map<String, String>> listTeacherMaps();
}
