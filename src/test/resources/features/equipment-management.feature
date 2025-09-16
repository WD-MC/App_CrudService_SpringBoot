Feature: API de Gestion des équipements

  Scenario: Création d'un équipement
    Given un équipement avec les données suivantes:
      | name    | brand | quantity | employee | status     |
      | Laptop  | Dell  | 5        | Michele  | disponible |
    When je crée l'équipement via l'API
    Then la réponse doit avoir le code 201
    And le corps de la réponse doit contenir "Laptop"

  Scenario: Récupération de tous les équipements
    Given Les équipements suivants existent :
      | name    | brand | quantity | employee | status     |
      | Laptop  | Dell  | 5        | Michele  | disponible |
      | Monitor | Dell  | 6        | John     | attribue   |
    When J'envoie une requête GET via l’API
    Then La reponse doit avoir le code 200
    And Le nombre d'équipements retournés doit etre de 2

