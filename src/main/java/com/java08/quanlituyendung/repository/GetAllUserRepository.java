package com.java08.quanlituyendung.repository;

import com.java08.quanlituyendung.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GetAllUserRepository extends JpaRepository<UserAccountEntity, Long> {
//    @Query("SELECT UserAccountEntity.id, UserAccountEntity.email, UserAccountEntity.role, UserAccountEntity.username, UserInfoEntity.avatar, UserInfoEntity.fullName " +
//            "FROM UserAccountEntity " +
//            "INNER JOIN UserInfoEntity " +
//            "ON UserAccountEntity.id = UserInfoEntity.id")
//    List<Object[]> findAllUserInfo();

    @Query("SELECT a.id, a.email, a.role, a.username, i.avatar, i.fullName, a.creationTime " +
            "FROM UserAccountEntity a " +
            "INNER JOIN UserInfoEntity i " +
            "ON a.id = i.id")
    List<Object[]> findAllUserInfo();
}
