package com.axmor.issue;

import com.axmor.comment.Comment;
import com.axmor.util.Path;
import com.axmor.util.ViewUtil;
import spark.Request;
import spark.Route;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.axmor.Application.commentDao;
import static com.axmor.Application.issueDao;

public class IssueController {

    public static Route getAllIssues = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        Iterable<Issue> issues = issueDao.getAllIssues();
        model.put("issues", issues);
        return ViewUtil.render(model, Path.Template.ISSUE_ALL);
    };

    public static Route getNewIssue = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("redirect", Path.Web.ISSUES);
        return ViewUtil.render(model, Path.Template.ISSUE_NEW);
    };

    public static Route postNewIssue = (request, response) -> {
        createNewIssue(request);
        response.redirect(Path.Web.ISSUES);
        return null;
    };

    public static Route getOneIssue = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        int id = Integer.parseInt(request.params(":id"));
        Issue issue = issueDao.getIssue(id);
        model.put("issue", issue);
        Iterable<Comment> comments = commentDao.getIssueComments(id);
        model.put("comments", comments);
        return ViewUtil.render(model, Path.Template.ISSUE_ONE);
    };

    public static Route postOneIssue = (request, response) -> {
        createComment(request);
        response.redirect("#");
        return null;
    };

    private static void createComment(Request request) {
        int issueId = Integer.parseInt(request.params("id"));
        Date date = new Date();
        String author = request.queryParams("author");
        String text = request.queryParams("text");
        Comment comment = new Comment(issueId, date, author, text);
        commentDao.insertComment(comment);
    }

    private static void createNewIssue(Request request) {
        String summary = request.queryParams("summary");
        String author = request.queryParams("author");
        String description = request.queryParams("description");
        Issue issue = new Issue(summary, author, description);
        issue.setStartDate(new Date());
        issue.setStatus("Opened");
        issueDao.insertIssue(issue);
    }
}
