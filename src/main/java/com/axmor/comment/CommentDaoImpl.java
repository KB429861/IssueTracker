package com.axmor.comment;

import com.axmor.util.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {

    private Comment extractCommentFromResultSet(ResultSet rs) {
        try {
            Comment comment = new Comment();
            comment.setId(rs.getInt("id"));
            comment.setIssueId(rs.getInt("issue_id"));
            comment.setDate(rs.getTimestamp("date"));
            comment.setAuthor(rs.getString("author"));
            comment.setText(rs.getString("text"));
            return comment;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Comment getComment(int id) {
        Connection connection = DatabaseHelper.getConnection();
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM comments WHERE id='" + id + "'");
                if (rs.next()) {
                    return extractCommentFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<Comment> getIssueComments(int issueId) {
        Connection connection = DatabaseHelper.getConnection();
        try {
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM comments WHERE issue_id='" + issueId + "'");
                List<Comment> comments = new ArrayList<>();
                while (rs.next()) {
                    Comment comment = extractCommentFromResultSet(rs);
                    comments.add(comment);
                }
                return comments;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertComment(Comment comment) {
        Connection connection = DatabaseHelper.getConnection();
        try {
            if (connection != null) {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO comments VALUES (NULL, ?, ?, ?, ?)");
                ps.setInt(1, comment.getIssueId());
                ps.setTimestamp(2, new Timestamp(comment.getDate().getTime()));
                ps.setString(3, comment.getAuthor());
                ps.setString(4, comment.getText());
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
}
