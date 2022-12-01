package rest.api;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiUrls {
    public static final String WELCOME = "/welcome";
    public static final String LOGIN = "/login";
    public static final String POST_LOGIN = "/login";
    public static final String GET_ALL_STUDENT = "/api/all-students";
    public static final String ADD_STUDENT = "/api/add-student";
    public static final String REMOVE_STUDENT = "/api/remove/{id}";

}
