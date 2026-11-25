/**
 * By: Kaje Jack
 * Purpose: Acts as an abstraction to database operations
 */

package com.example
import java.util.function
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.Transaction;

public class Database {

    public Database() {
        
    }

    public <T> void createNew(T entity){
        Session session = new Configuration().addAnnotatedClass(entity.class).buildSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.persist(entity);
        tr.commit();

    }

    public <T, U> T retrieve(Class<T> class, U id){
        Session session = new Configuration().addAnnotatedClass(class).buildSessionFactory().openSession();
        T retrieved_entity = session.get(class, id);
        return retrieved_entity;
    }


    public <T> List<T> retrieveAll(Class<T> class, String hqlQuery){
        Session session = new Configuration().addAnnotatedClass(class).buildSessionFactory().openSession();
        List<T> data = session.createQuery(hqlQuery, class).getResultList();
        return data;
    }

    public <T> List<T> retrieveAll(Class<T> class){
        Session session = new Configuration().addAnnotatedClass(class).buildSessionFactory().openSession();
        List<T> data = session.createQuery("FROM "+class.getSimpleName(), class).getResultList();
        return data;
    }

    public <T> void update(T entity){
        Session session = new Configuration().addAnnotatedClass(entity.class).buildSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.merge(entity);
        tr.commit();
    }

    public <T> void delete(T entity){
        Session session = new Configuration().addAnnotatedClass(entity.class).buildSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.remove(entity);
        tr.commit();
    }



}