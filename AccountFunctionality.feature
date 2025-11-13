Feature: Account Page and Logout

  Scenario: Verify Account details and logout
    Given User navigates to Account page
    Then User details and logout option should be visible
    When User logs out from account
    Then User should be redirected to Login page again
