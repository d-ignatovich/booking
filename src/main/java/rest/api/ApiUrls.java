package rest.api;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiUrls {
    public static final String MAIN = "/";
    public static final String OVERVIEW = "/overview";
    public static final String REGISTRATION = "/registration";
    public static final String LOGIN = "/login";
    public static final String POST_LOGIN = "/login";
    public static final String ADD_RECORD = "/create";
    public static final String FIND_RECORD = "/{id}";
    public static final String BOOKINGS = "/bookings";
    public static final String BOOK_ID = "/book/{id}";
    public static final String BOOK_INFO = "/booking/{id}";
}
