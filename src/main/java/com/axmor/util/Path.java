package com.axmor.util;

public class Path {

    public static class Web {
        public final static String ISSUES = "/issues/";
        public final static String ISSUES_NEW = "/issues/new/";
        public final static String ISSUES_ONE = "/issues/:id/";
    }

    public static class Template {
        public final static String ISSUE_ALL = "/velocity/issue/all.vm";
        public final static String ISSUE_NEW = "/velocity/issue/new.vm";
        public final static String ISSUE_ONE = "/velocity/issue/one.vm";
    }
}
