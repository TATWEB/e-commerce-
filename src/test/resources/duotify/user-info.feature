Feature: User info feature involving DB layer


  Scenario: Verify Email update on the UI
    Given I am connected to the DB
    And I am logged in as a valid user
    When I update the user email
    Then The user email update should be correctly within the database


  Scenario: Verify Email update in the DB
    Given I am connected to the DB
    When I update username "minnie" email to "minnie2022@gmail.com"
    Then I should see the updated email on the URL



  Scenario: Verify the expected genres in genres table in DB
    Given I am connected to the DB
    When I retrieve the genres from the genres table
    Then It should contain the following

      | jazz       |
      | electronic |
      | rap        |
      | pop        |
      | techno     |
      | rnb        |
      | house      |
      | classical  |
      | dance      |
      | reggae     |
      | reggaeton  |