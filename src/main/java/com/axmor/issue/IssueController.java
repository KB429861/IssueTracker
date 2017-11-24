package com.axmor.issue;

import com.axmor.util.Path;
import com.axmor.util.ViewUtil;
import spark.Route;

import java.util.HashMap;

import static com.axmor.Main.issueDao;

public class IssueController {

    public static Route fetchAllIssues = (request, response) -> {
        Iterable<Issue> issues = issueDao.getAllIssues();
        HashMap<String, Object> model = new HashMap<>();
        model.put("issues", issues);
        return ViewUtil.render(model, Path.Template.ISSUE_ALL);
    };
}
