package com.leverx.capsidian.helpers;

public class AppPatternFileValidator extends PatternFileValidator{
    /**
     * Create an instance of PatternFileValidator for .cds files in 'app'
     * folder.
     */
    public AppPatternFileValidator() {
        super("app", ".cds");
    }
}
