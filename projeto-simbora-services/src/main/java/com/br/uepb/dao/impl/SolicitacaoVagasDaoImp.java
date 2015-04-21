package com.br.uepb.dao.impl;

import java.util.List; 

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.domain.SolicitacaoVagas;
import com.br.uepb.util.HibernateUtil;


public class SolicitacaoVagasDaoImp implements SolicitacaoVagasImpl{

	@Override
	public void save(SolicitacaoVagas solicitacaoVagas) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(solicitacaoVagas);
		t.commit();
	}

	@Override
	public SolicitacaoVagas getSolicitacaoVagas(String idLogin) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (SolicitacaoVagas) session.load(SolicitacaoVagas.class, idLogin);
	}

	@Override
	public List<SolicitacaoVagas> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<SolicitacaoVagas> lista = session.createQuery("from SolicitacaoVagas").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(SolicitacaoVagas solicitacaoVagas) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(solicitacaoVagas);
		t.commit();
	}

	@Override
	public void update(SolicitacaoVagas solicitacaoVagas) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(solicitacaoVagas);
		t.commit();
	}
	
	@Override
	public void excluirTudo() {  
        List<SolicitacaoVagas> list = list();
        for(SolicitacaoVagas solicitacaoVagas:list){
        	remove(solicitacaoVagas);
        }
    } 
	
}