package com.alphagnfss.etr3.frontend.factory;


import javafx.scene.shape.SVGPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SVGPaths {
    public static final String ADD_SVG_PATH = "M4 0 H6 V4 H10 V6 H6 V10 H4 V6 H0 V4 H4 Z";
    private static final Logger logger = LogManager.getLogger(SVGPaths.class);

    public static SVGPath createSVGPath(String svg) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svg);
        return svgPath;
    }
}
