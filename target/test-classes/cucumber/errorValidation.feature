
@tag
Feature: Purchase the product from ecommerce website
  I want to use this template for my feature file

 
  @ErrorValidation
  Scenario Outline: Positive test of submitting the order
    Given I landed on Ecommerce page
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password." is displayed

    Examples: 
      | username                  | password    | 
      | kamathveena28@gmail.com   | Sudheer@1   | 