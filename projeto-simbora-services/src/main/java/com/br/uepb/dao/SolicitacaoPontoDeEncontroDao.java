package com.br.uepb.dao;

import java.util.List;  

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.dao.impl.SolicitacaoPontoDeEncontroImpl;
import com.br.uepb.domain.SolicitacaoPontoDeEncontro;
import com.br.uepb.util.HibernateUtil;

public class SolicitacaoPontoDeEncontroDao implements SolicitacaoPontoDeEncontroImpl {

	@Override
	public void save(SolicitacaoPontoDeEncontro solicitacaoPontoDeEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(solicitacaoPontoDeEncontro);
		t.commit();
	}

	@Override
	public SolicitacaoPontoDeEncontro getSolicitacaoPontoDeEncontro(String idLogin) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (SolicitacaoPontoDeEncontro) session.load(SolicitacaoPontoDeEncontro.class, idLogin);
	}

	@Override
	public List<SolicitacaoPontoDeEncontro> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<SolicitacaoPontoDeEncontro> lista = session.createQuery("from SolicitacaoPontoDeEncontro").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(SolicitacaoPontoDeEncontro solicitacaoPontoDeEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(solicitacaoPontoDeEncontro);
		t.commit();
	}

	@Override
	public void update(SolicitacaoPontoDeEncontro solicitacaoPontoDeEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(solicitacaoPontoDeEncontro);
		t.commit();
	}
	
	@Override
	public void excluirTudo() {  
        List<SolicitacaoPontoDeEncontro> list = list();
        for(SolicitacaoPontoDeEncontro solicitacaoPontoDeEncontro:list){
        	remove(solicitacaoPontoDeEncontro);
        }
    } 
	
}