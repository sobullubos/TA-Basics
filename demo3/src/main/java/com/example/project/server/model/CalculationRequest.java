package com.example.project.server.model;

import java.math.BigDecimal;

public class CalculationRequest {

    private BigDecimal param1;
    private BigDecimal param2;
    private String operation;

    public BigDecimal getParam1() {
        return param1;
    }

    public void setParam1(BigDecimal param1) {
        this.param1 = param1;
    }

    public BigDecimal getParam2() {
        return param2;
    }

    public void setParam2(BigDecimal param2) {
        this.param2 = param2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
