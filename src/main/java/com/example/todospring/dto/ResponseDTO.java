package com.example.todospring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO<T> {
    private String error;
    private List<T> data;// Todo를 하나만 반환하는 경우보다 리스트를 반환하는 경우가 많으므로 리스트로 데이터를 반환하도록 한다.
}
