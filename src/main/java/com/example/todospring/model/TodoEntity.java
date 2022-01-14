package com.example.todospring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder // 오브젝트를 생성하기 위한 디자인 패턴 중 하나. 롬복에서 제공하는 Builder를 사용하면,
// Builder 클래스를 따로 개발하지 않고도 Builder 패턴을 사용해 오브젝트를 생성할 수 있다.
@NoArgsConstructor // 매개변수가 없는 생성자를 구현해준다.
@AllArgsConstructor // 클래스의 모든 멤버 변수를 매개변수로 받는 생성자를 구현해준다.
@Data // Getter, Setter 메서드를 구현해준다.
@Entity
@Table(name="todo") // 이 엔티티는 데이터베이스의 Todo 테이블에 매핑된다. name이 없다면, @Entity의 이름을 테이블 이름으로 간주한다.
public class TodoEntity {
    @Id // 기본키가 될 필드에 지정한다.
    @GeneratedValue(generator="system-uuid") // ID를 자동으로 생성한다.
    @GenericGenerator(name="system-uuid", strategy="uuid") // 어떤 방식으로 ID를 생성할지.
    private String id; // 이 오브젝트의 아이디
    private String userId; // 이 오브젝트를 생성한 사용자의 아이디
    private String title; // Todo 타이틀
    private boolean done; // true - todo를 완료한 경우

}
