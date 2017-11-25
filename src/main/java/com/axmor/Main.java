package com.axmor;

import com.axmor.issue.IssueController;
import com.axmor.issue.IssueDao;
import com.axmor.issue.IssueDaoImpl;
import com.axmor.util.DatabaseHelper;
import com.axmor.util.Path;

import static spark.Spark.*;

/**
 * Application entry point.
 */
public class Main {

    // Declare dependencies.
    public static IssueDao issueDao;

    public static void main(String[] args) {

        // Instantiate database.
        DatabaseHelper.createTables();

        // Instantiate dependencies.
        issueDao = new IssueDaoImpl();

        // Set up port.
        port(8080);

        // Set up routes.
        get(Path.Web.ISSUES, IssueController.getAllIssues);
        get(Path.Web.ISSUES_NEW, IssueController.getNewIssue);
        post(Path.Web.ISSUES_NEW, IssueController.postNewIssue);
    }
}
