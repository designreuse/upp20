package org.ftn.upp.lass.common;

public final class LogMessages {

    private LogMessages() { }

    public static final String EXCEPTION = "EXCEPTION: {}";
    public static final String EXECUTE = "EXECUTE: {}";
    public static final String FINISHED = "FINISHED: {}";

    public static final String STARTING_PROCESS = "Starting process instance of process {}.";
    public static final String PROCESS_STARTED = "Started process instance {} of process {}.";
    public static final String ASSIGNING_TASK_TO_CURRENT_USER = "Assigning task {} to current user.";
    public static final String ASSIGNED_TASK_TO_CURRENT_USER = "Assigned task {} to user {}.";
    public static final String RETRIEVEING_CURRENTLY_ACTIVE_TASK_FORM = "Retrieveing form for the currently active task of process instance {}.";
    public static final String RETRIEVED_CURRENTLY_ACTIVE_TASK_FORM = "Retrieved form for the currently active task {} of process instance {}.";
    public static final String RETRIEVEING_TASK_FORM = "Retrieveing form for task {}.";
    public static final String RETRIEVED_TASK_FORM = "Retrieved form for task {} of process instance {}.";
    public static final String SUBMITTING_FORM = "Submitting form for task {}.";
    public static final String FORM_SUBMITTED = "Submitted form for task {} of process instance {}.";
    public static final String LOADING_AVAILABLE_GENRES = "Loading available genres...";
    public static final String STORED_AVAILABLE_GENRES = "Stored available genres as process variable {} of process instance {}.";
    public static final String POPULATING_FORM_WITH_GENRES = "Populating form of task {} with genres.";
    public static final String POPULATED_FORM_WITH_GENRES = "Populated form of task {} with genres.";
    public static final String ADDING_USER_TO_CAMUNDA = "Adding user {} to Camunda Engine.";
    public static final String ADDED_USER_TO_CAMUNDA = "User {} added to Camunda Engine.";
    public static final String REMOVING_USER_FROM_CAMUNDA = "Removing user {} from Camunda Engine.";
    public static final String REMOVED_USER_FROM_CAMUNDA = "User {} removed from Camunda Engine.";
    public static final String VERIFYING_ACCOUNT = "Verifying account associated with {} verification code as part of {} process.";
    public static final String VERIFIED_ACCOUNT = "Successfully verified account associated with {} verification code as part of {} process.";
    public static final String PROCESSING_MEMBERSHIP_PAYMENT = "Processing membership payment as part of {} process.";
    public static final String PROCESSED_MEMBERSHIP_PAYMENT = "Successfully processed membership payment as part of {} process.";
    public static final String HANDLING_DOCUMENT_UPLOAD = "Handling document upload as part of {} process.";
    public static final String HANDLED_DOCUMENT_UPLOAD = "Successfully uploaded documents as part of {} process.";
}
