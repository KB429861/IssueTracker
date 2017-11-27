package com.axmor.issue;

public class IssueStatus {

    public final static String OPEN = "open";
    public final static String CLOSED = "closed";
    public final static String RESOLVED = "resolved";

    public final static String OPEN_NAME = "Open";
    public final static String CLOSED_NAME = "Closed";
    public final static String RESOLVED_NAME = "Resolved";
    public final static String UNKNOWN_NAME = "Unknown";

    public String getOpen() {
        return OPEN;
    }

    public String getClosed() {
        return CLOSED;
    }

    public String getResolved() {
        return RESOLVED;
    }

    public String getName(String status) {
        switch (status) {
            case OPEN:
                return OPEN_NAME;
            case CLOSED:
                return CLOSED_NAME;
            case RESOLVED:
                return RESOLVED_NAME;
            default:
                return UNKNOWN_NAME;
        }
    }

}
