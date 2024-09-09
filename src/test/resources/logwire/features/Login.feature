@regression
Feature: Login sur le site OrangeHRM
    en tant qu admin
    j aimerai me connecter a mon compte avec mon username et password

  Scenario: Login avec mot de passe correct et non d utilisateur correct
    Given je me rends sur le lien "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    When je saisis le nom d uilisateur "Admin"
    And je saisis le mot de passe "admin123"
    And je clique sur le bouton Login
    Then j accede a mon espace admin

  Scenario: Login avec mot de passe correct et non d utilisateur incorrect
    Given je me rends sur le lien "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    When je saisis le nom d uilisateur "Admin_incorrect"
    And je saisis le mot de passe "admin123"
    And je clique sur le bouton Login
    Then une erreur s affiche

  Scenario: Login avec mot de passe incorrect et non d utilisateur correct
    Given je me rends sur le lien "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    When je saisis le nom d uilisateur "Admin"
    And je saisis le mot de passe "admin123_incorrect"
    And je clique sur le bouton Login
    Then une erreur s affiche
