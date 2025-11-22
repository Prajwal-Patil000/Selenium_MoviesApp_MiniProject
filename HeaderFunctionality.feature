Feature: Header Navigation Functionality
  Verify that header elements navigate to the correct pages

  Scenario: Test header navigation
    Given I am on the enter in login
    When I enter valid username and password
    And I click on the login button
    Then I should be redirected to the home page
    When I navigate to the Popular page through header
    Then I should be redirected to the Popular page
    When I navigate to the Account page through header
    Then I should be redirected to the Account page
    When I navigate back to the Home page through header
    Then I should be redirected back to the Home page
