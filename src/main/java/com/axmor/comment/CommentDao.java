package com.axmor.comment;

public interface CommentDao {

    Comment getComment(int id);

    Iterable<Comment> getIssueComments(int issueId);

    boolean insertComment(Comment comment);
}
