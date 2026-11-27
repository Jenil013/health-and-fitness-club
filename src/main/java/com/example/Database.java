/**
 * By: Kaje Jack
 * Purpose: Acts as an abstraction to database operations
 */

package com.example;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

import org.hibernate.Transaction;

public class Database {
    private SessionFactory sessionFactory;
    public Database() throws ExceptionInInitializerError{
        try{
            this.sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable e){
            System.err.println("Database could not be setup");
            throw new ExceptionInInitializerError(e);
        }
        
    }

    public <T> void createNew(T entity){
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        session.persist(entity);
        tr.commit();

    }

    public <T, U> T retrieve(Class<T> c, U id){
        Session session = sessionFactory.openSession();
        T retrieved_entity = session.get(c, id);
        return retrieved_entity;
    }

    public <T, U> T retrieve(Class<T> c, String where){
        Session session = sessionFactory.openSession();
        List<T> retrieved_list = session.createQuery("FROM "+ c.getSimpleName()+" WHERE "+where, c).getResultList();
        if(retrieved_list.size() == 0) return null;
        return retrieved_list.get(0);
    }


    public <T> List<T> retrieveAll(Class<T> c, String hqlQuery){
        Session session = sessionFactory.openSession();
        List<T> data = session.createQuery(hqlQuery, c).getResultList();
        return data;
    }

    public <T> List<T> retrieveAll(Class<T> c){
        Session session = sessionFactory.openSession();
        List<T> data = session.createQuery("FROM "+c.getSimpleName(), c).getResultList();
        return data;
    }

    public <T> void update(T entity){
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        session.merge(entity);
        tr.commit();
    }

    public <T> void delete(T entity){
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        session.remove(entity);
        tr.commit();
    }



}