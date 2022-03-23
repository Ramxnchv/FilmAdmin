Feature: Compra de Entradas

Scenario: Comprar Entrada con 4 asientos para Sesion 1
    Given call read('login.feature@login_a')
    And driver baseUrl + '/entradas/compra-entradas/1'
    And click("a[id=nav-peliculas]")
    And click("a[id=sesion1]")
    And click("#botonmas")
    And click("#botonmas")
    And click("#botonmas")
    And click("#botonmas")
    When submit().click("#botoncomprar")
    Then waitForUrl(baseUrl + '/entradas/2')
    