package org.ftn.upp.lass.common.api;

/**
 * This class contains REST API endpoint constants
 */
public final class RestApiEndpoints {

    private RestApiEndpoints() { }

    private static final String API_ROOT = "/api/v1";

    public static final String AUTH = API_ROOT + "/auth";
    public static final String LOG_IN = "/log-in";

    public static final String USER = API_ROOT + "/user";
    public static final String VERIFY_ACCOUNT = "/verify-account";

    public static final String PAYMENT = API_ROOT + "/payment";
    public static final String PAY_MEMBERSHIP_MOCK = "/pay-membership-mock";

    public static final String PROCESS_MANAGEMENT = API_ROOT + "/process-management";
    public static final String START_PROCESS_INSTANCE = "/start-process";

    public static final String TASK_MANAGEMENT = API_ROOT + "/task-management";
    public static final String CLAIM_TASK = "/claim-task";
    public static final String CURRENTLY_ACTIVE_TASK_FORM = "/currently-active-task-form";
    public static final String TASK_FORM = "/task-form";

    public static final String FORM_MANAGEMENT = API_ROOT + "/form-management";
    public static final String SUBMIT_FORM = "/submit-form";
    public static final String SUBMIT_FORM_WITHOUT_ASSIGNEE = "/submit-form-without-assignee";
}