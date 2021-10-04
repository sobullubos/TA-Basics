package com.example.project.server;

import com.example.project.server.model.CalculationRequest;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSubtraction() throws Exception {
        CalculationRequest calcRequest = new CalculationRequest();
        calcRequest.setParam1(new BigDecimal("3.2"));
        calcRequest.setParam2(new BigDecimal("4.3"));
        calcRequest.setOperation("*");
        String contentToSend = new Gson().toJson(calcRequest);

        mvc.perform(MockMvcRequestBuilders.post("/calculator")
                .content(contentToSend)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result").value(new BigDecimal("13.760000").stripTrailingZeros()))
        ;
    }


}
