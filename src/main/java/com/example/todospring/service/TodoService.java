package com.example.todospring.service;

import com.example.todospring.model.TodoEntity;
import com.example.todospring.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 서비스 레이어는 컨트롤러와 퍼시스턴스 사이에서 비즈니스 로직을 수행하는 역할을 한다.
// 서비스 레이어는 HTTP와 긴밀히 연관된 컨트롤러에서 분리돼있고, 또 데이터베이스와 긴밀히 연관된 퍼시스턴스와도 분리돼있다.

@Slf4j // 로그 라이브러리 사용을 위한 어노테이션
@Service // 스테레오타입 어노테이션. 내부에 @Component 어노테이션을 갖고 있다.
// @Component와 비교했을 때 큰 차이는 없지만, 기능적으로 비즈니스 로직을 수행하는 서비스 레이어임을 알려준다.
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public String testService(){
        //TodoEntity 생성
        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
        //TodoEntity 저장
        repository.save(entity);
        //TodoEntity 검색
        TodoEntity savedEntity = repository.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }

    public List<TodoEntity> create(final TodoEntity entity){

        validate(entity);

        repository.save(entity);

        log.info("Entity Id: {} is saved.", entity.getId());

        return repository.findByUserId(entity.getUserId());

    }

    public List<TodoEntity> retrieve(final String userId){
        return repository.findByUserId(userId);
    }


    private void validate(final TodoEntity entity){
        // Validations
        if(entity == null){
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if(entity.getUserId() == null){
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }

//    public List<TodoEntity> update(final TodoEntity entity){
//        // (1) 저장할 엔티티가 유효한지 확인한다.
//        validate(entity);
//
//        // (2) 넘겨받은 엔티티를 id를 이용해 TodoEntity를 가져온다. 존재하지 않는 엔티티는 업데이트 할 수 없기 때문이다.
//        final Optional<TodoEntity> original = repository.findById(entity.getId());
//
//        original.ifPresent(todo -> {
//            // (3) 반환된 TodoEntity가 존재하면, 값을 새 entity 값으로 덮어씌운다.
//            todo.setTitle(entity.getTitle());
//            todo.setDone(entity.isDone());
//
//            // (4) 데이터베이스에 새 값을 저장한다.
//            repository.save(todo);
//        });
//
//        // Retrieve Todo에서 만든 메서드를 이용해 사용자의 모든 Todo 리스트를 반환한다.
//        return retrieve(entity.getUserId());
//    }

    //람다 미사용 버전
    public List<TodoEntity> update(final TodoEntity entity){
        // (1) 저장할 엔티티가 유효한지 확인한다.
        validate(entity);

        // (2) 넘겨받은 엔티티 id를 이용해 TodoEntity를 가져온다. 존재하지 않는 엔티티는 업데이트할 수 없기 때문이다.
        final Optional<TodoEntity> original = repository.findById(entity.getId());

        if(original.isPresent()){
            // (3) 반환된 TodoEntity가 존재한다면, 값을 새 entity값으로 덮어씌운다.
            final TodoEntity todo = original.get();
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            // (4) 데이터베이스에 새 값을 저장한다.
            repository.save(todo);


        }
        return retrieve(entity.getUserId());
    }

    public List<TodoEntity> delete(final TodoEntity entity){
        // (1) 유효성 검증
        validate(entity);

        try{
            // (2) 엔티티 삭제
            repository.delete(entity);
        }catch (Exception e){
            // (3) exception 발생 시 id와 exception을 로깅한다.
            log.error("error deleting entity", entity.getId(), e);
        }

        // (5) 새 Todo 리스트를 가져와 리턴한다.
        return retrieve(entity.getUserId());
    }
}
