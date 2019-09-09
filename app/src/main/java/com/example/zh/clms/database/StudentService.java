package com.example.zh.clms.database;

import java.util.List;
import java.util.Map;

//该接口实现对数据库增删改查
public interface StudentService {

    //添加数据
    public boolean addStudent(Student student);

    //删除数据
    public boolean deleteStudent(Student student);

    //修改数据
    public boolean updateStudent(Student student);

    //查询单个数据，返回map键值对
    public Map<String, String> viewStudent(Student student);

    //查询多个数据
    public List<Map<String, String>> listStudentMaps();


}
