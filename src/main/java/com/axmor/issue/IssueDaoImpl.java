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
            issue.setSummary(rs.getString("summary"));
            issue.setAuthor(rs.getString("author"));
            issue.setDescription(rs.getString("description"));
            issue.setStartDate(rs.getDate("start_date"));
            issue.setStatus(rs.getString("status"));
            issue.setEditor(rs.getString("editor"));
            issue.setModifiedDate(rs.getDate("modified_date"));
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
                ResultSet rs = statement.executeQuery("SELECT * FROM issues WHERE id='" + id + "'");
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
                PreparedStatement ps = connection.prepareStatement("INSERT INTO issues VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, issue.getSummary());
                ps.setString(2, issue.getDescription());
                ps.setString(3, issue.getAuthor());
                ps.setTimestamp(4, new Timestamp(issue.getStartDate().getTime()));
                ps.setString(5, issue.getStatus());
                ps.setString(6, issue.getEditor());
                if (issue.getModifiedDate() != null) {
                    ps.setTimestamp(7, new Timestamp(issue.getModifiedDate().getTime()));
                } else {
                    ps.setTimestamp(7, null);
                }
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
        Connection connection = DatabaseHelper.getConnection();
        try {
            if (connection != null) {
                PreparedStatement ps = connection.prepareStatement("UPDATE issues SET summary=?, author=?, description=?, start_date=?, status=?, editor=?, modified_date=? WHERE id=?");
                ps.setString(1, issue.getSummary());
                ps.setString(2, issue.getAuthor());
                ps.setString(3, issue.getDescription());
                ps.setTimestamp(4, new Timestamp(issue.getStartDate().getTime()));
                ps.setString(5, issue.getStatus());
                ps.setString(6, issue.getEditor());
                if (issue.getModifiedDate() != null) {
                    ps.setTimestamp(7, new Timestamp(issue.getModifiedDate().getTime()));
                } else {
                    ps.setTimestamp(7, null);
                }
                ps.setInt(8, issue.getId());
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
    public boolean deleteIssue(Issue issue) {
        Connection connection = DatabaseHelper.getConnection();
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                int result = statement.executeUpdate("DELETE FROM issues WHERE id='" + issue.getId() + "'");
                if (result == 1) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
