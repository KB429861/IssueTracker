package com.axmor.issue;

import com.axmor.auth.LogInController;
import com.axmor.comment.Comment;
import com.axmor.util.Path;
import com.axmor.util.ViewUtil;
import spark.Route;

import java.util.Date;
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
        model.put(Path.Model.STATUS, new IssueStatus());
        return ViewUtil.render(request, model, Path.Template.ISSUE_ALL);
    };

    public static Route getNewIssue = (request, response) -> {
        if (LogInController.ensureUserIsLoggedIn(request, response)) {
            Map<String, Object> model = new HashMap<>();
            return ViewUtil.render(request, model, Path.Template.ISSUE_NEW);
        }
        return null;
    };

    public static Route postNewIssue = (request, response) -> {
        if (LogInController.ensureUserIsLoggedIn(request, response)) {
            String summary = getQuerySummary(request);
            String author = getSessionCurrentUsername(request);
            String description = getQueryDescription(request);
            Issue issue = new Issue(summary, author, description);
            issueDao.insertIssue(issue);
            response.redirect(Path.Web.ISSUES);
        }
        return null;
    };

    public static Route getOneIssue = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        int id = getParamId(request);
        Issue issue = issueDao.getIssue(id);
        Iterable<Comment> comments = commentDao.getIssueComments(id);
        model.put(Path.Model.ISSUE, issue);
        model.put(Path.Model.COMMENTS, comments);
        model.put(Path.Model.STATUS, new IssueStatus());
        return ViewUtil.render(request, model, Path.Template.ISSUE_ONE);
    };

    public static Route postOneIssue = (request, response) -> {
        if (LogInController.ensureUserIsLoggedIn(request, response)) {
            int id = getParamId(request);
            String author = getSessionCurrentUsername(request);
            String text = getQueryText(request);
            String status = getQueryStatus(request);
            Issue issue = issueDao.getIssue(id);
            issue.setStatus(status);
            issue.setEditor(author);
            issue.setModifiedDate(new Date());
            issueDao.updateIssue(issue);
            Comment comment = new Comment(id, author, text);
            commentDao.insertComment(comment);
            response.redirect("#");
        }
        return null;
    };

    public static Route getEditIssue = (request, response) -> {
        if (LogInController.ensureUserIsLoggedIn(request, response)) {
            Map<String, Object> model = new HashMap<>();
            int id = getParamId(request);
            Issue issue = issueDao.getIssue(id);
            model.put(Path.Model.ISSUE, issue);
            return ViewUtil.render(request, model, Path.Template.ISSUE_EDIT);
        }
        return null;
    };

    public static Route postEditIssue = (request, response) -> {
        if (LogInController.ensureUserIsLoggedIn(request, response)) {
            int id = getParamId(request);
            String summary = getQuerySummary(request);
            String description = getQueryDescription(request);
            Issue issue = issueDao.getIssue(id);
            issue.setSummary(summary);
            issue.setDescription(description);
            issueDao.updateIssue(issue);
            response.redirect(Path.Web.ISSUES + id);
        }
        return null;
    };

    public static Route postDeleteIssue = (request, response) -> {
        if (LogInController.ensureUserIsLoggedIn(request, response)) {
            int id = getParamId(request);
            Issue issue = issueDao.getIssue(id);
            issueDao.deleteIssue(issue);
            response.redirect(Path.Web.ISSUES);
        }
        return null;
    };
}
