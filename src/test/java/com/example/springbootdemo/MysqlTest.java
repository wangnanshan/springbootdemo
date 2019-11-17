package com.example.springbootdemo;

import com.example.springbootdemo.entity.Department;
import com.example.springbootdemo.entity.Role;
import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.repository.DepartmentRepository;
import com.example.springbootdemo.repository.RoleRepository;
import com.example.springbootdemo.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
public class MysqlTest {

    private static Logger logger = LoggerFactory.getLogger(MysqlTest.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    RoleRepository roleRepository;


    @Before
    public void initData(){
        userRepository.deleteAll();
        roleRepository.deleteAll();
        departmentRepository.deleteAll();

        Department department = new Department();
        department.setName("开发部");
        departmentRepository.save(department);
        Assert.notNull(department.getId());


        Role role = new Role();
        role.setName("admin");
        roleRepository.save(role);
        Assert.notNull(role.getId());

        User user = new User();
        user.setName("user");
        user.setCreatedate(new Date());
        user.setDepartment(department);
        List<Role> roles = roleRepository.findAll();
        Assert.notNull(roles);
        user.setRoles(roles);

        userRepository.save(user);
        Assert.notNull(user.getId());
    }


    @Test
    public void findPage(){
        //Pageable pageable = new PageRequest(0,10,new Sort(Sort.Direction.ASC,"id"));
        List<User> page = userRepository.findAll();
        Assert.notNull(page);
        for (User user:page) {
            System.out.println("====user name:{}====,department name:{},role name:{}"+user.getName()+user.getDepartment().getName()+user.getRoles().get(0).getName());
        }
    }

}
