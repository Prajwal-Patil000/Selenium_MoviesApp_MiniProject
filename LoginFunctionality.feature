Feature: Login Functionality

  Scenario: Successful login with valid credentials
    Given User is on Login page
    When User enters valid credentials and clicks login
    Then User should be redirected to Home page
