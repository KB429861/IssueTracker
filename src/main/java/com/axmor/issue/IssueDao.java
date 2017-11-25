package com.axmor.issue;

public interface IssueDao {

    Issue getIssue(int id);

    Iterable<Issue> getAllIssues();

    boolean insertIssue(Issue issue);
}
