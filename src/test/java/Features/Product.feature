Feature: Gestion produit
  @Product
Scenario: Ajouter un produit de la page dacceuil au panier avec succés
  Given je suis sur la page dacceuil
  When je click sur le bouton AddToCart
  And je click sur le panier
  Then produit ajouté au panier avec succés

  Scenario: Ajouter un produit de la page produit au panier avec succés
    Given je suis sur la page dacceuil
    When je click sur le produit
    And je click sur le bouton AddToCart du produit
    And je click sur le panier du produit
    Then produit ajouté au panier du produit avec succés

    Scenario: notification ajout produit
      Given je suis sur la page dacceuil
      When je click sur le bouton AddToCart
      Then notification dajout saffiche sur le panier

      Scenario: incrementation Notification ajout produit
        Given je suis sur la page dacceuil
        When je click sur le bouton AddToCart
        Then incremetation panier

        Scenario: ajout differents produits au panier
          Given je suis sur la page dacceuil
          When je click sur le bouton AddToCart
          And je click sur le bouton AddToCart DeuxiemeProduit
          Then incremetation panier


          Scenario: tester le bouton remove
            Given je suis sur la page dacceuil
            When je click sur le bouton AddToCart
            And je click sur Remove
            And je click sur le panier
            Then produit supprimé


            Scenario: tester le tri des produits (Z to A)
              Given je suis sur la page dacceuil
              When je click sur filtreicon je choisi ZTOA
              Then produits filtrés selon Name Z to A





