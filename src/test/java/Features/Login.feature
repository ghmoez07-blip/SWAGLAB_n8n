Feature: Tester la connexion au site "Swaglab"
Background: Test Login avec les identifiants valides
Given je suis sur la page Login
  When je saisi l'username "standard_user"
  And je saisi le mot de passe "secret_sauce"
  And je click sur le bouton Login
  Then redirection vers la page Home

  Scenario Outline: Test login avec identifiants invalides
    Given je suis sur la page Login
    When je saisi l'username "<username>"
    And je saisi le mot de passe "<password>"
    And je click sur le bouton Login
    Then un msg derreur doit safficher "<msgErreur>"
    Examples:
      | username |  password|  msgErreur|
      | standard_use |  secret_sauce|Username and password do not match any user in this service|
      | standard_user |  |  Epic sadface: Password is required                                  |
      |   |secret_sauce|  Epic sadface: Username is required                                    |
      |  standard_use |secret_sauc| Username and password do not match any user in this service  |
      |  locked_out_user |secret_sauce| Sorry, this user has been locked out.                   |

    @logout
  Scenario: test logout
    When je click sur menuBtn
    And je click sur logoutSidebarBtn
    Then redirection vers la page login

