@regression @ui
Feature: Validating Login Functionality

  @smoke @loginPositive
  Scenario: Validating Login Functionality with valid credentials
    Given User navigates to application
    When User logs in with username "Tester" and password "test"
    Then User is successfully logged in and lands on the homepage

  @smoke @loginNegative
  Scenario: Validating login functionality with invalid credentials
    Given User navigates to application
    When User logs in with username "Tester" and password "Tester"
    Then User validates error message "Invalid Login or Password."

