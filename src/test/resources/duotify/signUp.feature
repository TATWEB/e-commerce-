Feature: Sign up feature involving DB layer


  Scenario: New User Sign Up from UI to DB flow
    Given I am on the sign up page and I am connected to the DB
    When  I sign up with the following info
      | username    | first | last   | email                | password     |
      | mark.zucker | Mark  | Zucker | markzucker@gmail.com | markzucker66 |
    Then I should land on the homepage
    And The data base should also have the correct info


  Scenario: New User Creation from DB to UI flow
    Given I am connected to the DB
    When I add a new user to the database with the following info
      | username  | first | last  | email               | password    |
      | marksuger | Mark  | Suger | marksuger@gmail.com | marksuger66 |
    Then I should be able to log in with the "marksuger" as uasername and "marksuger66" as password on the UI



  Scenario: Verify Songs Table Column Names
    Given I am connected to the DB
    When I retrieve the column names for the Songs table
    Then It should be the following
      | id         |
      | title      |
      | artist     |
      | album      |
      | genre      |
      | duration   |
      | path       |
      | albumOrder |
      | plays      |



  Scenario: Verify Email update on the UI
    Given I am connected to the DB
    And I am logged in as a valid user
    When I update the user email
    Then The user email update should be correctly within the database




  Scenario: Test if input field leading and trailing spaces are truncated before committing data to the database
    Given I am on the sign up page and I am connected to the DB
    When  I sign up with the following info "    mark.zucker  " "   Mark    "  "   Zucker" "          markzucker@gmail.com    " "markzucker66"
    Then I should land on the homepage
    And The data base should also have the correct info without spaces


  @duotify
  Scenario: Check for duplicate values in the username column
    Given I am on the sign up page and I am connected to the DB
    When I send a query to check the usernames
    Then The returned result list should be empty