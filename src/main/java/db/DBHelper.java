package db;

import models.Advert;
import models.Category;
import models.Comment;
import models.User;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBHelper {

    private static Transaction transaction;
    private static Session session;

    public static void save(Object object) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> List<T> getList(Criteria criteria) {
        List<T> results = null;
        try {
            transaction = session.beginTransaction();
            results = criteria.list();
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static <T> T getUnique(Criteria criteria) {
        T result = null;
        try {
            transaction = session.beginTransaction();
            result = (T) criteria.uniqueResult();
            ;
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public static <T> void deleteAll(Class classType) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(classType);
            List<T> results = cr.list();
            for (T result : results) {
                session.delete(result);
            }
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Object object) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> List<T> getAll(Class classType) {
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(classType);
        cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return getList(cr);
    }

    public static <T> T find(int id, Class classType) {
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(classType);
        cr.add(Restrictions.eq("id", id));
        return getUnique(cr);
    }

    public static List<Advert> usersAdverts(User user) {
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Advert.class);
        cr.createAlias("user", "user");
        cr.add(Restrictions.eq("user.id", user.getId()));
        return getList(cr);
    }

    public static List<User> sortByID(){
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Criteria cr = session.createCriteria(User.class);
        cr.addOrder(Order.desc("id"));
        return getList(cr);
    }

    public static User currentUser(){
        Integer highest;
        User user;
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Criteria cr = session.createCriteria(User.class);
        cr.setProjection(Projections.max("id"));
        highest = (Integer)cr.uniqueResult();
        Criteria crit = session.createCriteria(User.class);
        crit.add(Restrictions.eq("id", highest));
        user = getUnique(crit);
        return user;
    }


    public static List<Category> allCategories() {
        List<Category> categories = new ArrayList<>();
        Collections.addAll(categories, Category.values());
        return categories;
    }

    public static List<Advert> adverts(Category category){
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria cr = session.createCriteria(Advert.class);
        cr.add(Restrictions.eq("category", category));
        return getList(cr);
    }

    public static void favouriteAdvert(Advert advert, User user){
        advert.addUserToFavouriters(user);
        user.addAdvertToFavourites(advert);
        DBHelper.save(advert);
        DBHelper.save(user);
    }

    public static List<Advert> usersFavAdverts(User user) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.refresh(user);
        Hibernate.initialize(user.getFavourites());
        session.close();
        return user.getFavourites();
    }

}










