package com.example.zh.clms.database;

import java.util.List;
import java.util.Map;

//该接口实现对数据库中教师表增删改查
public interface AdminService {
    //添加数据
    public boolean addAdmin(Admin admin);

    //删除数据
    public boolean deleteAdmin(Admin admin);

    //修改数据
    public boolean updateAdmin(Admin admin, Object[] params);

    //查询单个数据，返回map键值对
    public Map<String, String> viewAdmin(Admin admin);

    //查询多个数据
    public List<Map<String, String>> listAdminMaps();
}
