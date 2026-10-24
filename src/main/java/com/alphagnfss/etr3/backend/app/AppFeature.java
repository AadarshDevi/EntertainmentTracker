package com.alphagnfss.etr3.backend.app;

public enum AppFeature {

    /**
     * This is a feature that everyone has access to.
     */
    PUBLIC,

    /**
     * The final step in the creation process of a feature. The final testing aka Polishing.
     */
    RELEASE,

    /**
     * Improving the feature
     */
    BETA_TEST,

    /**
     * The feature is being pushed aside for now.
     */
    HIBERNATE,

    /**
     * The feature will not be coming to the app. It is dead.
     * <p>
     * or it is currently not in a good state to continuing to be developed
     */
    SLEEP,

    /**
     * Creating and Implementing a new feature
     */
    ALPHA_TEST
}
