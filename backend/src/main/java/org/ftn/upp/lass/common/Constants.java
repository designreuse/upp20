package org.ftn.upp.lass.common;

/**
 * This class contains common constants
 */
public final class Constants {

    private Constants() { }

    public static final class ProcessVariables {

        private ProcessVariables() { }

        public static final String AVAILABLE_GENRES = "availableGenres";
        public static final String FAVORITE_GENRES = "favoriteGenres";
        public static final String BETA_ACCESS_GENRES = "betaAccessGenres";
        public static final String REGISTERED_READER = "registeredReader";
    }

    public static final class FormDataVariables {

        private FormDataVariables() { }

        public static final String GENERIC_FORM_DATA = "genericFormData";

        public static final String USER_DATA_FORM = "userDataForm";
        public static final String BETA_ACCESS_GENRES_FORM = "betaAccessGenresForm";
    }

    public static final class FormFieldVariables {

        private FormFieldVariables() { }

        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String EMAIL = "email";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String STREET = "street";
        public static final String CITY = "city";
        public static final String POSTAL_CODE = "postalCode";
        public static final String COUNTRY = "country";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String FAVORITE_GENRES = "favoriteGenres";
        public static final String IS_BETA_ACCESS_REQUESTED = "isBetaAccessRequested";
        public static final String BETA_ACCESS_GENRES = "betaAccessGenres";
    }

    public static final class ProcessConstants {

        private ProcessConstants() { }

        public static final String USER_ACCOUNT_VERIFIED_MESSAGE = "UserAccountVerifiedMessage";
    }
}
