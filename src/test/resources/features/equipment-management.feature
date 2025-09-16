Feature: API de Gestion des équipements

  Scenario: Création d'un équipement
    Given un équipement avec les données suivantes:
      | name    | brand | quantity | employee | status     |
      | Laptop  | Dell  | 5        | Michele  | disponible |
    When je crée l'équipement via l'API
    Then la réponse doit avoir le code 201
    And le corps de la réponse doit contenir "Laptop"