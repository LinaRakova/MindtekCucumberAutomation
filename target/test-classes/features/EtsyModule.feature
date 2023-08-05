@regression @outlineTest
Feature: Validating Modules functionality

  Scenario: Validating module functionality
    Given User navigates to Etsy application
    When User clicks on Module
      | Module                | Title                                |
      | Fashion Favorites     | Fashion favorites                    |
      | Jewelry & Accessories | Jewelry & Accessories                |
      | Clothing & Shoes      | Clothing & Shoes                     |
      | Home & Living         | Home & Living                        |
      | Wedding & Party       | Wedding & Party                      |
      | Toys & Entertainment  | Toys & Entertainment                 |
      | Art & Collectibles    | Art & Collectibles                   |
      | Craft Supplies        | Craft Supplies                       |
      | Gifts & Gift Cards    | The Etsy Gift Guide for 2023 \| Etsy |
    Then User validates Title of the page is correct

