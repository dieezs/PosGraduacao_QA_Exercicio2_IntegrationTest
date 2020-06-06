Feature: Crud User

  Background:
    Given User is on Agapito web page
    And User fields the input "full_name" with value "Diego Souza"
    And User fields the input "email" with value "diegosouza@gmail.com"
    And User fields the input "age" with value "20"

  # 1 - POST
  Scenario: Create an User
    Given User fields the input "login" with value "dieezs"
    When User clicks on button save User
    Then user should see "save with success" message

  # 2 - POST
  Scenario: Create an User with different login
    Given User fields the input "login" with value "diegosouzamaga"
    When User clicks on button save User
    Then user should see "save with success" message

  # POST e TAG IGNORE
  @ignore
  Scenario: Create an User ignore
    Given User fields the input "login" with value "dieezs"
    When User clicks on button save User
    Then user should see "save with success" message

  # 3 - POST AND GET
  Scenario: Create an User and verify if was created
    Given User fields the input "login" with value "dieezs"
    When User clicks on button save User
    Then user should see "save with success" message
    When user search the last saved
    Then User should see the field "email" with value "diegosouza@gmail.com"


  # 4 - POST AND Scenario Outline
  Scenario Outline: : Create an User - <description>
    Given User fields the input "login" with value "<login>"
    When User clicks on button save User without ID
    Then user should see "<statusCode>" message

    Examples:
      | description        | statusCode           | login  |
      | user with login    | save with success    | dieezs |
      | user without login | unprocessable entity |        |


  # 5 - POST AND PUT
  Scenario: Create and update age of the user
    Given User fields the input "login" with value "dieezs"
    And User clicks on button save User
    And the user update "age" with value "38"
    When the user clicks on update button
    Then user should see "success" message

  # 6 - POST AND PUT
  Scenario: Create and update email of the user and verify if is correct
    Given User fields the input "login" with value "dieezs"
    And User clicks on button save User
    And the user update "email" with value "qualqueremail@gmail.com"
    And the user clicks on update button
    When the user reads the last update
    Then the user should be "email" with value "qualqueremail@gmail.com"

  # 7 - PUT
  Scenario: update a invalid user
    Given the user update "age" with value "67"
    When the user clicks on update button with invalid id
    Then user should see "not found" message

  # 8 - POST E DELETE
  Scenario: Create an user and delete
    Given User fields the input "login" with value "dieezs"
    And User clicks on button save User
    And user clicks on delete button
    Then user should see "no content" message

  # 9 - DELETE
  Scenario: delete an invalid user
    Given the user clicks on delete butto with invalid id
    Then user should see "not found" message