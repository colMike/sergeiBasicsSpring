package com.crashcourse.demo.io.repository;

import com.crashcourse.demo.io.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
  UserEntity findByEmail(String email);

  UserEntity findByUserId(String userId);
}
