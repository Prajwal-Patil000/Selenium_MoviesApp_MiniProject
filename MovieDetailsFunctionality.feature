Feature: Movie Details Page
  As a user of the movies website
  When I click on a movie in the home page
  I should see the movie details page and
  All the UI elements present in it
  Similarly, when I click a movie in the popular page
  I should see the movie details page and all the UI elements

  Scenario: View Movie Details from Home Page
    Given I am on Login page For movie details
    When I enter valid username and password for movie details
    And i click on login button for movie details
    Then I should see the movies details page for Movie Details
    And i should see all the UI elements are present and visible
    And I click on a movies in popular page for Movies Details
