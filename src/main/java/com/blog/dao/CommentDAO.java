package com.blog.dao;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.blog.util.HibernateUtil;

import java.util.List;

public class CommentDAO {

    private SessionFactory sessionFactory;

    public CommentDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    // Save a new comment
    public void saveComment(Comment comment) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(comment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Retrieve comments by post
    public List<Comment> getCommentsByPost(Post post) {
        try (Session session = sessionFactory.openSession()) {
            Query<Comment> query = session.createQuery("FROM Comment WHERE post = :post", Comment.class);
            query.setParameter("post", post);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Retrieve comments by post ID
    public List<Comment> getCommentsByPostId(int postId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Comment> query = session.createQuery("FROM Comment WHERE post.id = :postId", Comment.class);
            query.setParameter("postId", postId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Retrieve comments by author (if needed)
    public List<Comment> getCommentsByAuthor(User author) {
        try (Session session = sessionFactory.openSession()) {
            Query<Comment> query = session.createQuery("FROM Comment WHERE author = :author", Comment.class);
            query.setParameter("author", author);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Delete a comment
    public void deleteComment(Comment comment) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(comment);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}