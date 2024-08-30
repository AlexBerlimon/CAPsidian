package com.leverx.capsidian.annotations.structural;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import com.leverx.capsidian.helpers.AppPatternFileValidator;

public class FioriTest {

        /**
         * Validator of app folder.
         */
        private AppPatternFileValidator validator = new AppPatternFileValidator();

        /**
         * Test if any fiori-service file has @odata.draft.enabled annotation.
         */
        @Test
        public void testODataDraftEnabled() {
                Pattern regex = Pattern.compile("\\@odata.draft.enabled",
                                Pattern.DOTALL);
                assertEquals("@odata.draft.enabled has been used", true,
                                validator.testMatches(regex));
        };

        /**
         * Test if any fiori-service file has @fiori.draft.enabled annotation.
         */
        @Test
        public void testFioriDraftEnabled() {
                Pattern regex = Pattern.compile("\\@fiori.draft.enabled",
                                Pattern.DOTALL);
                assertEquals("@fiori.draft.enabled has been used", true,
                                validator.testMatches(regex));
        };

        /**
         * Test if any fiori-service file annotate StoreService.Items.
         */
        @Test
        public void testItemService() {
                Pattern regex = Pattern.compile(
                                "annotate\\s+StoreService.Items\\s+with", Pattern.DOTALL);
                assertEquals("ItemsService has been used", true,
                                validator.testMatches(regex));
        };

        /**
         * Test if any fiori-service file use @UI.Hidden annotation.
         */
        @Test
        public void testUIHidden() {
                Pattern regex = Pattern.compile("\\@UI.Hidden", Pattern.DOTALL);
                assertEquals("@UI.Hidden has been used", true,
                                validator.testMatches(regex));
        };

        /**
         * Test if any fiori-service file use ValueList annotation.
         */
        @Test
        public void testValueList() {
                Pattern regex = Pattern.compile("ValueList", Pattern.DOTALL);
                assertEquals("ValueList has been used", true,
                                validator.testMatches(regex));
        };

        /**
         * Test if any fiori-service file use i18n.
         */
        @Test
        public void testI18N() {
                Pattern regex = Pattern.compile("\\{i18n>.+\\}", Pattern.DOTALL);
                assertEquals("i18n has been used", true, validator.testMatches(regex));
        };
}
