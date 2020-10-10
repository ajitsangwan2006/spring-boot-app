package com.matcher.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StringMatcherResponse {
    private List<Integer> positions;
    public StringMatcherResponse(List<Integer> positions){
        this.positions = positions;
    }
}
