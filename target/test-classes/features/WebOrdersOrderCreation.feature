@regression @ui @orderCreation
Feature: Validating Order Creation

  Background: Logging in
    Given User navigates to application
    When User logs in with username "Tester" and password "test"
    And User clicks on Order tab

  Scenario Outline: Validating discount calculator
    When User selects product "<Product>", enters quantity <quantity>
    Then User validates the price is correctly calculated according to quantity <quantity>
    Examples:
      | Product     | quantity |
      | MyMoney     | 5        |
      | FamilyAlbum | 10       |
      | ScreenSaver | 15       |

    @placeOrder
    Scenario: Validating Order Creating functionality
      And User creates order with data
        | PRODUCT | QUANTITY | CUSTOMER NAME | STREET     | CITY   | STATE         | ZIP   | CARD NUM  | EXP DATE |
        | MyMoney | 5        | Jane Doe      | 123 Lee st | Boston | Massachusetts | 12345 | 123456789 | 01/26    |
        | MyMoney | 10       | John Doe      | 123 Lee st | Boston | Massachusetts | 12345 | 987654321 | 02/26    |
      Then User validate success message "New order has been successfully added."
      And User validates created order is in the list of all orders