
@tag
Feature: Purchase the product from ecommerce website
  I want to use this template for my feature file

  Background: 
  Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <username> and password <password>
    When I add product <productName> to cart 
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed in confirmation page

    Examples: 
      | username                  | password    | productName  |
      | kamathveena28@gmail.com   | Sudheer@18  | WROGN |
     
