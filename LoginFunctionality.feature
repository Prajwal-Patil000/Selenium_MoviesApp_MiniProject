Feature: Login Functionality
  As a user of the movies website
  I want to be able to log in to my account
  So that I can access my personalized features and watch movies

  Scenario: Successful Login
    Given I am on the Login page
    When I enter valid username and password
    And I click on the login button
    Then I should be redirected to the home page

  Scenario: Invalid Login
    Given I am on the Login page
    When I enter invalid username and password
    And I click on the login button
    Then I should see an error message for invalid login
