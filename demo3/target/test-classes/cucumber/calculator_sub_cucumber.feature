Feature: Is my calculator working 2

  Scenario Outline: I want to test the subtraction
    Given I am using my calculator
    When I subtract <a> and <b>
    Then I get the result <r>

    Examples:
      | a    | b   | r   |
      | 10.3 | 2.4 | 7.9 |
      | 5    | 5   | 0   |
