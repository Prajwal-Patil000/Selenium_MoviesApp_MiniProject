Feature: Popular Page Functionality
  As a user
  I want to verify the Popular page and its movies
  So that I can ensure the Popular section works properly

  Scenario: Verify Popular Page Navigation and Movies
    Given I am on the Movies App homepage
    When I click on the Popular button
    Then I should be navigated to the Popular page
    And available movies should be displayed
    Then I close the Popular page browser
