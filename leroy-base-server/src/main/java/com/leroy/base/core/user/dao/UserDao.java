package com.leroy.base.core.user.dao;


import com.leroy.base.core.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//泛型中第一个参数是实体类，第二个是id类型
@Repository
public interface UserDao extends JpaRepository<User, Long> {

    public User findByUserNameAndPassWord(String userName, String passWord);
}
