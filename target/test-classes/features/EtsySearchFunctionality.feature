@regression @etsy
Feature: Validating Search functionality

    # Background will run steps below once for each Scenario.
  Background: Repeated Steps
    Given User navigates to Etsy application
    When User searches for "carpet"

  Scenario: Validating search result matches the searched item
    Then User validates search result contains
      | carpet |
#        | turkish | rersian | rug     |
  # The values above

  Scenario: Validating price range functionality for searched item
    And User selects price range over 1000
    Then User validates price range for items over 1000

