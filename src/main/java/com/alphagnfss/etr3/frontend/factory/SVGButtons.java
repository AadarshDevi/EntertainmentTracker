package com.alphagnfss.etr3.frontend.factory;


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SVGButtons {
    private static final Logger logger = LogManager.getLogger(SVGButtons.class);

    private static final CornerRadii cornerRadii = new CornerRadii(5);
    private static final Insets insets = new Insets(5);

    private static Button getButton(String text, String path, Paint textColor, Background background) {
        Button button = new Button(text, SVGPaths.createSVGPath(path));
        button.setTextFill(textColor);
        button.setPadding(insets);
        button.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.CORAL,
                                cornerRadii,
                                Insets.EMPTY
                        )
                )
        );
        return button;
    }
}
