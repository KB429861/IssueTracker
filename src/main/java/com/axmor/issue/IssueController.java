package com.axmor.issue;

import com.axmor.util.Path;
import com.axmor.util.ViewUtil;
import spark.Request;
import spark.Route;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.axmor.Application.issueDao;

public class IssueController {

    public static Route getAllIssues = (request, response) -> {
        Iterable<Issue> issues = issueDao.getAllIssues();
        Map<String, Object> model = new HashMap<>();
        model.put("issues", issues);
        return ViewUtil.render(model, Path.Template.ISSUE_ALL);
    };

    public static Route getNewIssue = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("redirect", Path.Web.ISSUES);
        return ViewUtil.render(model, Path.Template.ISSUE_NEW);
    };

    public static Route postNewIssue = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        createNewIssue(request);
        return ViewUtil.render(model, Path.Template.ISSUE_ALL);
    };

    public static Route getOneIssue = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        int id = Integer.parseInt(request.params("id"));
        Issue issue = issueDao.getIssue(id);
        model.put("issue", issue);
        return ViewUtil.render(model, Path.Template.ISSUE_ONE);
    };

    private static void createNewIssue(Request request) {
        String summary = request.queryParams("summary");
        String author = request.queryParams("author");
        String description = request.queryParams("description");
        Issue issue = new Issue(summary, author, description);
        issue.setStartDate(new Date(Calendar.getInstance().getTimeInMillis()));
        issue.setStatus("Opened");
        issueDao.insertIssue(issue);
    }
}
