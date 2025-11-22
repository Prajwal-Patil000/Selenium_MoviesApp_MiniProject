Feature: Login Functionality
  As a user of Movie application
  I want to be able to login into my account
  So that i can access personalized featured and watch a movie
  After logged in Home page i should verify UI elements
  Scenario: Successful Login
    Given I am on the login page
    When I entered valid username and password
    And I click on a login button
    Then I shoud be redirected to home page
    Then I Should check heading text of each section is presented
    And Verifying weather play button is presented
    And Verifying weather movies are displayed in trending section
    And Verifying weather movies are displayed in origional section
    Then I should check weather contact section is presented
