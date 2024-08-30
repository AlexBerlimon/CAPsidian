package com.leverx.capsidian.helpers;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import java.util.regex.Pattern;

public class PatternFileValidator {
    /** Name of discovered folder. */
    private String folderName;

    /** File extension. */
    private String extension;

    /**
     * Create the instanse of PatternFileValidator for recived folder and file
     * extension.
     * 
     * @param folderName the name of the discovered folder
     * @param extension  file extension
     */
    public PatternFileValidator(final String folderName,
            final String extension) {
        this.folderName = folderName;
        this.extension = extension;
    }

    /**
     * Check if there are any files in the specified folder
     * that match the pattern.
     *
     * @param regex the pattern to check
     * @return true if at least one file matches the pattern
     */
    public boolean testMatches(final Pattern regex) {
        String path = this.getPath();
        Collection<File> listF = FileUtils.listFiles(new File(path),
                new PatternFileFilter(regex, this.extension),
                TrueFileFilter.INSTANCE);

        return listF.size() > 0;
    }

    /**
     * Make a full path to a requested directory.
     * 
     * @return String path to the directory
     */
    protected String getPath() {
        String dirPath = System.getProperty("user.dir");
        if (this.folderName.equals("srv")) {
            return dirPath;
        }

        dirPath += File.separator + "..";
        if (this.folderName == null || this.folderName.length() == 0) {
            return dirPath;
        }
        return dirPath + File.separator + folderName;
    }
}
