package org.ftn.upp.lass.common.api;

/**
 * This class contains REST API endpoint constants
 */
public final class RestApiEndpoints {

    private RestApiEndpoints() { }

    private static final String API_ROOT = "/api/v1";

    public static final String PROCESS_MANAGEMENT = API_ROOT + "/process-management";
    public static final String START_PROCESS_INSTANCE = "/start-process";

    public static final String TASK_MANAGEMENT = API_ROOT + "/task-management";
    public static final String CURRENTLY_ACTIVE_TASK_FORM = "/currently-active-task-form";
    public static final String TASK_FORM = "/task-form";

    public static final String FORM_MANAGEMENT = API_ROOT + "/form-management";
    public static final String SUBMIT_FORM = "/submit-form";
}