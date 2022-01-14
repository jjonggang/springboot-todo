package com.example.todospring.persistence;

import com.example.todospring.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// JpaRepository<T, ID>
// T: 테이블에 매핑될 엔티티 클래스
// ID: ID의 타입
@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
//    @Query("select * from Todo t where t.userId = ?1")
    List<TodoEntity> findByUserId(String userId);
}
