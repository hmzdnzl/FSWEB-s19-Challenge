package com.workintech.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.workintech.twitter.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
@Query("select r from Role r where r.authority = :authority")
Role getRoleByAuthority(@Param("authority") String authority);
}
