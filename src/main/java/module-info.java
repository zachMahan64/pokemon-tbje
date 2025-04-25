module pokemonTextBased {
    exports pokemonTextBased;
    requires javafx.controls;
    requires javafx.swing;
    requires javafx.media;
    requires com.google.gson;
    opens pokemonTextBased to javafx.graphics, com.google.gson;
}