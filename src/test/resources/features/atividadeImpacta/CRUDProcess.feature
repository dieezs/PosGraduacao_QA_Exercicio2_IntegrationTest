Feature: Crud User

  Background:
    Given User is on Agapito web page
    And User fields the input "numero_processo" with value "31313131"
    And User fields the input "natureza" with value "Patrimonio"
    And User fields the input "partes" with value "Diego x Barbara"
    And User fields the input "urgente" with value "S"
    And User fields the input "arbitramento" with value "S"
    And User fields the input "assistente_social" with value "John Dee"
    And User fields the input "data_entrada" with value "2019-12-25"
    And User fields the input "data_saida" with value "2020-01-01"
    And User fields the input "data_agendamento" with value "2019-05-01"
    And User fields the input "status" with value "Complicado"
    And User fields the input "observacao" with value "Verificar processo."

  # 1 - POST
  Scenario: Create an Process
    Given User fields the input "vara" with value "4"
    When User clicks on button save User
    Then user should see "save with success" message

  # 2 - POST
  Scenario: Create an Process with a different observation
    Given User fields the input "vara" with value "4"
    When User clicks on button save User
    Then user should see "save with success" message

  # POST e TAG IGNORE
  @ignore
  Scenario: Create an User - ignore TAG
    Given User fields the input "vara" with value "53"
    When User clicks on button save User
    Then user should see "save with success" message

  # 3 - POST AND GET
  Scenario: Create an process and verify if was created
    Given User fields the input "vara" with value "435"
    When User clicks on button save User
    Then user should see "save with success" message
    When user search the last saved
    Then User should see the field "vara" with value "435"


  # 4 - POST AND Scenario Outline
  Scenario Outline: : Create a process - <description>
    Given User fields the input "vara" with value "<varaScenario>"
    When User clicks on button save User without ID
    Then user should see "<statusCode>" message

    Examples:
      | description                | statusCode           | varaScenario         |
      | valid process              | save with success    | processo da elite br |
      | process without vara field | unprocessable entity |                      |


  # 5 - POST AND PUT
  Scenario: Create and update vara's process
    Given User fields the input "vara" with value "432"
    And User clicks on button save User
    And the user update "vara" with value "32"
    When the user clicks on update button
    Then user should see "success" message

  # 6 - POST AND PUT
  Scenario: Create and update process natureza process and verify if is correct
    Given User fields the input "vara" with value "39"
    And User clicks on button save User
    And the user update "natureza" with value "Guarda de Menor"
    And the user clicks on update button
    When the user reads the last update
    Then the user should be "natureza" with value "Guarda de Menor"

  # 7 - PUT
  Scenario: update an invalid process
    Given the user update "vara" with value "43"
    When the user clicks on update button with invalid id
    Then user should see "not found" message

  # 8 - POST E DELETE
  Scenario: Create a process and delete it
    Given User fields the input "vara" with value "4342545"
    And User clicks on button save User
    And user clicks on delete button
    Then user should see "no content" message

  # 9 - DELETE
  Scenario: delete a invalid process
    Given the user clicks on delete butto with invalid id
    Then user should see "not found" message