package com.blog.dao;

import com.blog.entities.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CategoryDAO {

    private SessionFactory sessionFactory;

    public CategoryDAO() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    // Retrieve a category by ID
    public Category getCategoryById(int categoryId) {
        Session session = sessionFactory.openSession();
        Category category = null;
        try {
            category = session.get(Category.class, categoryId);
        } finally {
            session.close();
        }
        return category;
    }

    // Save a new category
    public void saveCategory(Category category) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Retrieve all categories
    public List<Category> getAllCategories() {
        Session session = sessionFactory.openSession();
        List<Category> categories = null;
        try {
            categories = session.createQuery("from Category", Category.class).list();
        } finally {
            session.close();
        }
        return categories;
    }
}