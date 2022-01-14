package com.example.todospring.dto;

import com.example.todospring.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
//사용자는 이 클래스를 활용해 아이템을 생서앟고 수정하고 삭제한다.
public class TodoDTO {
    private String id;
    private String title;
    private boolean done;
    public TodoDTO(final TodoEntity entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone();
    }

    // HTTP 응답을 반환할 때 비즈니스 로직을 캡슐화하거나 추가적인 정보를 함께 반환하려고 DTO를 사용한다.
    // 따라서 컨트롤러는 사용자에게서 TodoDTO를 요청 바디로 넘겨받고 이를 TodoEntity로 변환해 저장해야한다. 또 TodoService의 create()가 리턴하는 TodoEntity를
    // TodoDTO로 변환해 리턴해햐한다.
    // toEntity 함수를 이용해 DTO를 Entity로 변환시킨다.
    public static TodoEntity toEntity(final TodoDTO dto){
        return TodoEntity.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .done(dto.isDone())
                .build();
    }
}
