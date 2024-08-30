package com.leverx.capsidian.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

public class PatternFileFilter implements IOFileFilter {

    /** Regex pattern. */
    private Pattern regex;

    /** File extension. */
    private String extension;

    /**
     * Create the instance of PatternFileFilter for received regex and file
     * extension.
     * @param regex  the pattern
     * @param extension  file extension
     */
    public PatternFileFilter(final Pattern regex, final String extension) {
        this.regex = regex;
        this.extension = extension;
    }

    /**
     * Overridden IOFileFilter method.
     * @return  true if any match is found otherwise false
     */
    @Override
    public boolean accept(final File file) {
        if (file.getName().endsWith(this.extension)) {
            return this.isMatchExist(file);
        }
        return false;
    }

    /**
     * Overridden IOFileFilter method.
     * @return false
     */
    @Override
    public boolean accept(final File dir, final String name) {
        return false;
    }

    private boolean isMatchExist(final File file) {
        try {
            String content = FileUtils.readFileToString(file,
                    StandardCharsets.UTF_8.name());
            Matcher matcher = this.regex.matcher(content);

            return matcher.find();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
