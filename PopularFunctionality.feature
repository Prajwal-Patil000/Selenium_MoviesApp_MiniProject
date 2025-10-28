Feature: Popular page test
  As a user of the movies website
  I should see all the popular movies in the popular section
  and be able to redirect to the movie details page.

  Scenario: Test the popular page UI
    Given I am on login page for Popular page
    When I enter valid username and password for popular movies page
    And i click on login button for popular movies
    And I navigate to the popular page and click on a movies
    Then I should see the Popular page loaded Successfully
