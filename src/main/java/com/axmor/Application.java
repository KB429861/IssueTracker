package com.axmor;

import com.axmor.comment.CommentDao;
import com.axmor.comment.CommentDaoImpl;
import com.axmor.issue.IssueController;
import com.axmor.issue.IssueDao;
import com.axmor.issue.IssueDaoImpl;
import com.axmor.util.DatabaseHelper;
import com.axmor.util.Filters;
import com.axmor.util.Path;

import static spark.Spark.*;

/**
 * Application entry point.
 */
public class Application {

    // Declare dependencies.
    public static IssueDao issueDao;
    public static CommentDao commentDao;

    public static void main(String[] args) {

        // Instantiate database.
        DatabaseHelper.createTables();

        // Instantiate dependencies.
        issueDao = new IssueDaoImpl();
        commentDao = new CommentDaoImpl();

        // Set up port.
        port(8080);

        // Set up before-filters.
        before("*", Filters.addTrailingSlashes);

        // Set up routes.
        get(Path.Web.ISSUES, IssueController.getAllIssues);
        get(Path.Web.ISSUES_NEW, IssueController.getNewIssue);
        post(Path.Web.ISSUES_NEW, IssueController.postNewIssue);
        get(Path.Web.ISSUES_ONE, IssueController.getOneIssue);
        post(Path.Web.ISSUES_ONE, IssueController.postOneIssue);
    }
}
