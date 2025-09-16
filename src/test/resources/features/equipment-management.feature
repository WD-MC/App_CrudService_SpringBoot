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
    Then La reponse du get doit avoir le code 200
    And la réponse doit contenir 2 équipements

  Scenario: Mise à jour d’un équipement
    Given un équipement ayant les données suivantes:
      | name   | brand | quantity | employee | status     |
      | Laptop | Dell  | 5        | Alice    | disponible |
    When Via l'API je crée l'équipement
    And je mets à jour l'équipement avec les données suivantes:
      | name     | brand | quantity | employee | status  |
      | Laptop X | Dell  | 10       | Alice    | attribue |
    Then la réponse du put doit avoir le code 200
    And le corps de la réponse doit avoir "Laptop X"

  Scenario: Suppression d’un équipement
    Given un équipement a les données suivantes:
      | name   | brand | quantity | employee | status     |
      | Mouse  | Lenovo    | 3        | Bob      | disponible |
    When je crée l'équipement
    When je supprime l'équipement par son ID
    Then la réponse du delete a pour code 204
    And l'équipement ne doit plus exister en base

  Scenario: Récupération d’un équipement par son ID
    Given un équipement possede les données suivantes:
      | name   | brand | quantity | employee | status     |
      | Keyboard | Logitech | 2 | Carol | disponible |
    When je crée un nouveau équipement
    And je récupère l'équipement par son ID via l'API
    Then la réponse du get par ID doit avoir le code 200
    And le corps de la réponse doit contenir l'équipement "Keyboard"