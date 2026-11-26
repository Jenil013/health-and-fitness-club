package com.example.repository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import org.hibernate.Transaction;

public class Database {

    public Database() {
        
    }

    public <T> void createNew(T entity){
        Session session = new Configuration().addAnnotatedClass(entity.getClass()).buildSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.persist(entity);
        tr.commit();

    }

    public <T, U> T retrieve(Class<T> clazz, U id){
        Session session = new Configuration().addAnnotatedClass(clazz).buildSessionFactory().openSession();
        T retrieved_entity = session.get(clazz, id);
        return retrieved_entity;
    }


    public <T> List<T> retrieveAll(Class<T> clazz, String hqlQuery){
        Session session = new Configuration().addAnnotatedClass(clazz).buildSessionFactory().openSession();
        List<T> data = session.createQuery(hqlQuery, clazz).getResultList();
        return data;
    }

    public <T> List<T> retrieveAll(Class<T> clazz){
        Session session = new Configuration().addAnnotatedClass(clazz).buildSessionFactory().openSession();
        List<T> data = session.createQuery("FROM " + clazz.getSimpleName(), clazz).getResultList();
        return data;
    }

    public <T> void update(T entity){
        Session session = new Configuration().addAnnotatedClass(entity.getClass()).buildSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.merge(entity);
        tr.commit();
    }

    public <T> void delete(T entity){
        Session session = new Configuration().addAnnotatedClass(entity.getClass()).buildSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.remove(entity);
        tr.commit();
    }



}