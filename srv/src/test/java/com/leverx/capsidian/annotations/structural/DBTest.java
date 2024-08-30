package com.leverx.capsidian.annotations.structural;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import com.leverx.capsidian.helpers.DBPatternFileValidator;

public class DBTest {

    /**
     * Validator of db folder.
     */
    private DBPatternFileValidator validator = new DBPatternFileValidator();

    /**
     * Test if any db file has @mandatory annotation.
     */
    @Test
    public void testMandatory() {
        Pattern regex = Pattern.compile("\\@mandatory", Pattern.DOTALL);
        assertEquals("@mandatory has been used", true,
                validator.testMatches(regex));
    };

    /**
     * Test if any db file has @assert.range annotation.
     */
    @Test
    public void testRange() {
        Pattern regex = Pattern.compile("\\@assert.range", Pattern.DOTALL);
        assertEquals("@assert.range has been used", true,
                validator.testMatches(regex));
    };

    /**
     * Test if any db file has @assert.integrity annotation.
     */
    @Test
    public void testIntegrity() {
        Pattern regex = Pattern.compile("\\@assert.integrity", Pattern.DOTALL);
        assertEquals("@assert.integrity has been used", true,
                validator.testMatches(regex));
    };

    /**
     * Test if any db file has @assert.format annotation.
     */
    @Test
    public void testFormat() {
        Pattern regex = Pattern.compile("\\@assert.format", Pattern.DOTALL);
        assertEquals("@assert.format has been used", true,
                validator.testMatches(regex));
    };

}
