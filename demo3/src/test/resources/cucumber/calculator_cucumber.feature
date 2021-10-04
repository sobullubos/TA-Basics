Feature: Is my calculator working

  Scenario Outline: I want to test the addition
    Given I am using my calculator
    When I add <a> and <b>
    Then I get the result <r>

    Examples:
      | a | b | r  |
      | 1 | 2 | 3  |
      | 5 | 5 | 10 |
      | 7 | 6 | 13 |
      | 0 | 0 | 0  |


