package com.App.questionservice.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {
    private Integer id;
    private String response;

    public Integer getId() {
        return id;
    }

    public String getResponse() {
        return response;
    }
}
