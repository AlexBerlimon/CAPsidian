package com.leverx.capsidian.helpers;

public class DBPatternFileValidator extends PatternFileValidator {
    
    /**
     * Create an instance of PatternFileValidator for .cds files in 'db'
     * folder.
     */
    public DBPatternFileValidator() {
        super("db", ".cds");
    }
}
