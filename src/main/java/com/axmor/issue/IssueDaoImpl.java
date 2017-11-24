package com.axmor.issue;

import com.axmor.util.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IssueDaoImpl implements IssueDao {

    private Issue extractIssueFromResultSet(ResultSet rs) {
        try {
            Issue issue = new Issue();
            issue.setId(rs.getInt("id"));
            issue.setAuthor(rs.getString("author"));
            return issue;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Issue getIssue(int id) {
        Connection connection = DatabaseHelper.getConnection();
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM issues WHERE id=" + id);
                if (rs.next()) {
                    return extractIssueFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<Issue> getAllIssues() {
        Connection connection = DatabaseHelper.getConnection();
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM issues");
                List<Issue> issues = new ArrayList<>();
                while (rs.next()) {
                    Issue issue = extractIssueFromResultSet(rs);
                    issues.add(issue);
                }
                return issues;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertIssue(Issue issue) {
        Connection connection = DatabaseHelper.getConnection();
        try {
            if (connection != null) {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO issues VALUES (NULL, ?, ?, ?, ?, ?)");
                ps.setString(1, issue.getSummary());
                ps.setString(2, issue.getDescription());
                ps.setString(3, issue.getAuthor());
                ps.setDate(4, issue.getStartDate());
                ps.setString(5, issue.getStatus());
                int result = ps.executeUpdate();
                if (result == 1) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateIssue(Issue issue) {
        return false;
    }

    @Override
    public boolean deleteIssue(Issue issue) {
        return false;
    }
}
