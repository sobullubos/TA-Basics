package com.example.project.server;

import com.example.project.Calculator;
import com.example.project.server.model.CalculationRequest;
import com.example.project.server.model.CalculationResponse;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class HelloController {

    @GetMapping(value = "/", produces = "application/json")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping(value = "/calculator", consumes = "application/json", produces = "application/json")
    public Object calc(@RequestBody CalculationRequest calculationRequest) {
        Calculator calc = new Calculator();
        BigDecimal r = null;
        if (calculationRequest.getOperation().trim().equals("-")) {
            r = calc.subtract(calculationRequest.getParam1(), calculationRequest.getParam2());
        }
        if (calculationRequest.getOperation().trim().equals("+")) {
            r = calc.add(calculationRequest.getParam1(), calculationRequest.getParam2());
        }
        if (calculationRequest.getOperation().trim().equals("*")) {
            r = calc.multiply(calculationRequest.getParam1(), calculationRequest.getParam2());
        }
        if (calculationRequest.getOperation().trim().equals("/")) {
            try {
                r = calc.divide(calculationRequest.getParam1(), calculationRequest.getParam2());
            } catch (IllegalArgumentException ie) {
                return "{\"errorMessage\": \"" + ie.getMessage() + "\"}";
            }
        }

        return new CalculationResponse(r);
    }

    // TODO fertig machen, falls sinnvoll
//    @GetMapping(value = "/availableOperations", produces = "application/json")
//    public CalculationResponse getAvailableOperations() {
//        return new CalculationResponse("Supports addition +, substraction -, multiplication * and division /");
//    }

}
