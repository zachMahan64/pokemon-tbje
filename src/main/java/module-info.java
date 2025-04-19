module pokemonTextBased {
    requires javafx.controls;
    requires javafx.swing;
    requires javafx.media;

    opens pokemonTextBased to javafx.graphics;
}