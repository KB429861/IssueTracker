package com.axmor.util;

public class Path {

    public static class Web {
        public final static String LOG_IN = "/login/";
        public final static String LOG_OUT = "/logout/";
        public final static String SIGN_UP = "/register/";
        public final static String ISSUES = "/issues/";
        public final static String ISSUES_NEW = "/issues/new/";
        public final static String ISSUES_ONE = "/issues/:id/";
        public final static String ISSUES_EDIT = "/issues/edit/:id/";
    }

    public static class Template {
        public final static String LOG_IN = "/velocity/auth/log-in.vm";
        public final static String SIGN_UP = "/velocity/auth/sign-up.vm";
        public final static String ISSUE_ALL = "/velocity/issue/all.vm";
        public final static String ISSUE_NEW = "/velocity/issue/new.vm";
        public final static String ISSUE_ONE = "/velocity/issue/one.vm";
        public final static String ISSUE_EDIT = "/velocity/issue/edit.vm";
    }

    public class Model {
        public static final String FAILED = "failed";
        public static final String SUCCEEDED = "succeeded";
        public static final String REDIRECT = "redirect";
        public static final String COMMENTS = "comments";
        public static final String ISSUES = "issues";
        public static final String ISSUE = "issue";
    }
}
