Feature: Validating Yard Api calls

  @MB8-123 @smoke @regression @api
  Scenario: Validating POST Yard Api call
    Given user creates yard with post yard call with data
      | location | random     |
      | status   | active     |
      | address  | 123 MY ave |
    When user connects to database
    Then user validates created yard is persisted in DB
