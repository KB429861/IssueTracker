package com.axmor.issue;

import com.axmor.auth.LogInController;
import com.axmor.comment.Comment;
import com.axmor.util.Path;
import com.axmor.util.ViewUtil;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static com.axmor.Application.commentDao;
import static com.axmor.Application.issueDao;
import static com.axmor.util.RequestUtil.*;

public class IssueController {

    public static Route getAllIssues = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        Iterable<Issue> issues = issueDao.getAllIssues();
        model.put(Path.Model.ISSUES, issues);
        return ViewUtil.render(request, model, Path.Template.ISSUE_ALL);
    };

    public static Route getNewIssue = (request, response) -> {
        LogInController.ensureUserIsLoggedIn(request, response);
        Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, Path.Template.ISSUE_NEW);
    };

    public static Route postNewIssue = (request, response) -> {
        String summary = getQuerySummary(request);
        String author = getSessionCurrentUsername(request);
        String description = getQueryDescription(request);
        Issue issue = new Issue(summary, author, description);
        issueDao.insertIssue(issue);
        response.redirect(Path.Web.ISSUES);
        return null;
    };

    public static Route getOneIssue = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        int id = getParamId(request);
        Issue issue = issueDao.getIssue(id);
        Iterable<Comment> comments = commentDao.getIssueComments(id);
        model.put(Path.Model.ISSUE, issue);
        model.put(Path.Model.COMMENTS, comments);
        return ViewUtil.render(request, model, Path.Template.ISSUE_ONE);
    };

    public static Route postOneIssue = (request, response) -> {
        LogInController.ensureUserIsLoggedIn(request, response);
        int id = getParamId(request);
        String author = getSessionCurrentUsername(request);
        String text = getQueryText(request);
        Comment comment = new Comment(id, author, text);
        commentDao.insertComment(comment);
        response.redirect("#");
        return null;
    };

    public static Route getEditIssue = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        int id = getParamId(request);
        Issue issue = issueDao.getIssue(id);
        model.put(Path.Model.ISSUE, issue);
        return ViewUtil.render(request, model, Path.Template.ISSUE_EDIT);
    };

    public static Route postEditIssue = (request, response) -> {
        LogInController.ensureUserIsLoggedIn(request, response);
        int id = getParamId(request);
        String summary = getQuerySummary(request);
        String description = getQueryDescription(request);
        Issue issue = issueDao.getIssue(id);
        issue.setSummary(summary);
        issue.setDescription(description);
        issueDao.updateIssue(issue);
        response.redirect(Path.Web.ISSUES + id);
        return null;
    };
}
