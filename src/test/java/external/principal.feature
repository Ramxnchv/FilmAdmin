Feature: Compra de Entradas

Background:
    Given url baseUrl

Scenario: Generar Página de Compra de Entradas para Sesion 1
    
    Given path '/entradas/compra-entradas'
    And param sesion = '1'
    When method GET
    Then status 200

Scenario: Obtener Mapa de Asientos para Sesion 1
    
    Given path '/entradas/asientos/1'
    When method GET
    Then status 200
    And header Accept = 'application/json'
    And match response.id == 1

Scenario: Comprar Entrada con Asientos 37, 38, 39 y 40 para Sesion 1
    
    Given path '/entradas/compra-entradas'
    And param idSesion = '1'
    And request {"asientos": [37,38,39,40], "numeroasientos": 4}
    And header Accept = 'application/json'
    When method POST
    Then status 200
    And match response == {"id":2}