package com.axmor;

import com.axmor.issue.IssueController;
import com.axmor.issue.IssueDao;
import com.axmor.issue.IssueDaoImpl;
import com.axmor.util.Path;

import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Application entry point.
 */
public class Main {

    // Declare dependencies.
    public static IssueDao issueDao;

    public static void main(String[] args) {

        // Instantiate dependencies.
        issueDao = new IssueDaoImpl();

        // Set up port.
        port(8080);

        // Set up routes.
        get(Path.Web.ISSUES, IssueController.fetchAllIssues);
    }
}
