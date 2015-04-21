package com.br.uepb.dao;

import java.util.List; 

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.dao.impl.CaronaImpl;
import com.br.uepb.domain.Carona;
import com.br.uepb.util.HibernateUtil;

public class CaronaDao implements CaronaImpl{ 

	@Override
	public void save(Carona carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(carona);
		t.commit();
	}

	@Override
	public Carona getCarona(String idLogin) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Carona) session.load(Carona.class, idLogin);
	}

	@Override
	public List<Carona> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Carona> lista = session.createQuery("from Carona").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(Carona carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(carona);
		t.commit();
	}

	@Override
	public void update(Carona carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(carona);
		t.commit();
	}
	
	@Override
	public void excluirTudo() {  
        List<Carona> list = list();
        for(Carona carona:list){
        	remove(carona);
        }
    } 
	
}