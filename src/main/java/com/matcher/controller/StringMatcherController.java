package com.matcher.controller;

import com.matcher.domain.StringMatcherRequest;
import com.matcher.domain.StringMatcherResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StringMatcherController {

    @PostMapping(value = "/match", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public StringMatcherResponse matchString(@Valid @RequestBody StringMatcherRequest request) {
        String text = request.getText().toLowerCase();
        String subText = request.getSubText().toLowerCase();
        List<Integer> indexes = new ArrayList<>();
        for (int i = -1; (i = text.indexOf(subText, i + 1)) != -1; i++) {
            indexes.add(i);
        }
        return new StringMatcherResponse(indexes);
    }
}
