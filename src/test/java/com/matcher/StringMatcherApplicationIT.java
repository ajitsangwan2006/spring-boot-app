package com.matcher;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StringMatcherApplicationIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindPositionForSubTextInText_Case_Insensitive() throws Exception {
        String input = "{\"text\":\"abcdxyzabcd\",\"subText\":\"aBcD\"}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/match")
                .contentType(MediaType.APPLICATION_JSON)
                .content(input)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        List<Integer> positions = (ArrayList<Integer>)new BasicJsonParser().parseMap(result.getResponse().getContentAsString()).get("positions");
        Assert.assertTrue(positions.containsAll(Arrays.asList(0l,7l)));
    }
}
