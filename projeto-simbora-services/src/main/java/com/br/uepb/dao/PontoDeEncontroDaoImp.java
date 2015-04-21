package com.br.uepb.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.domain.PontoDeEncontro;




public class PontoDeEncontroDaoImp implements PontoDeEncontroDao{

	@Override
	public void save(PontoDeEncontro pontoDeEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(pontoDeEncontro);
		t.commit();
	}

	@Override
	public PontoDeEncontro getPontoDeEncontro(String idLogin) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (PontoDeEncontro) session.load(PontoDeEncontro.class, idLogin);
	}

	@Override
	public List<PontoDeEncontro> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<PontoDeEncontro> lista = session.createQuery("from PontoDeEncontro").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(PontoDeEncontro pontoDeEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(pontoDeEncontro);
		t.commit();
	}

	@Override
	public void update(PontoDeEncontro pontoDeEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(pontoDeEncontro);
		t.commit();
	}
	
	@Override
	public void excluirTudo() {  
        List<PontoDeEncontro> list = list();
        for(PontoDeEncontro pontoDeEncontro:list){
        	remove(pontoDeEncontro);
        }
    } 
	
}