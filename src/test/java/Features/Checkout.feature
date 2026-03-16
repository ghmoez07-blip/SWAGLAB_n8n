Feature: tester le checkout
  Background: Test Login avec les identifiants valides
    Given je suis sur la page dacceuil
    And je click sur le bouton AddToCart
    And je click sur le bouton AddToCart DeuxiemeProduit
    And je click sur le panier

    Scenario: test valide de la fonction checkout
      When je click checkout
      And je saisi Firstname "hela"
      And je saisi Lastname "SD"
      And je saisi Codepostal "5060"
      And je click Continue
      And je click Finish
      Then successful checkout

      Scenario Outline: test invalide de la fonction Checkout
        When je click checkout
        And je saisi Firstname "<firstname>"
        And je saisi Lastname "<lastname>"
        And je saisi Codepostal "<codepostal>"
        And je click Continue
        Then cehckout failed affichage msg "<msg>"
        Examples:
          |firstname|lastname|codepostal|msg|
        |hela|  |5060|Error: Last Name is required|
          |    |sd|5060|Error: First Name is required|
          |    |  |    |Error: First Name is required|
        |hela|sd|    |Error: Postal Code is required|


  Scenario: Vérification du prix total pour deux produits

    When je click checkout
    And je saisi Firstname "Amina"
    And je saisi Lastname "Test"
    And je saisi Codepostal "4000"
    And je click Continue
    Then le prix total des deux produits doit être correct



