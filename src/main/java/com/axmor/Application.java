package com.axmor;

import com.axmor.auth.LogInController;
import com.axmor.auth.SignUpController;
import com.axmor.comment.CommentDao;
import com.axmor.comment.CommentDaoImpl;
import com.axmor.issue.IssueController;
import com.axmor.issue.IssueDao;
import com.axmor.issue.IssueDaoImpl;
import com.axmor.user.UserDao;
import com.axmor.user.UserDaoImpl;
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
    public static UserDao userDao;

    public static void main(String[] args) {

        // Instantiate database.
        DatabaseHelper.createTables();

        // Instantiate dependencies.
        issueDao = new IssueDaoImpl();
        commentDao = new CommentDaoImpl();
        userDao = new UserDaoImpl();

        // Configure Spark.
        port(8080);
        staticFileLocation("/public");

        // Set up before-filters.
        before("*", Filters.addTrailingSlashes);

        // Set up routes.
        get(Path.Web.ISSUES, IssueController.getAllIssues);
        get(Path.Web.ISSUES_NEW, IssueController.getNewIssue);
        post(Path.Web.ISSUES_NEW, IssueController.postNewIssue);
        get(Path.Web.ISSUES_ONE, IssueController.getOneIssue);
        post(Path.Web.ISSUES_ONE, IssueController.postOneIssue);
        get(Path.Web.ISSUES_EDIT, IssueController.getEditIssue);
        post(Path.Web.ISSUES_EDIT, IssueController.postEditIssue);
        get(Path.Web.LOG_IN, LogInController.getLogIn);
        post(Path.Web.LOG_IN, LogInController.postLogIn);
        get(Path.Web.SIGN_UP, SignUpController.getSignUp);
        post(Path.Web.SIGN_UP, SignUpController.postSignUp);
    }
}
