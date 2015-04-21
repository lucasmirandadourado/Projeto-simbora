package com.br.uepb.dao;

import java.util.List;   

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.domain.Usuario;
import com.br.uepb.util.HibernateUtil;

public class UsuarioDao implements UsuarioImpl{

	@Override
	public void save(Usuario usuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(usuario);
		t.commit();
	}

	@Override
	public Usuario getUsuario(String login) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (Usuario) session.load(Usuario.class, login);
	}

	@Override
	public List<Usuario> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Usuario> lista = session.createQuery("from Usuario").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(Usuario usuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(usuario);
		t.commit();
	}

	@Override
	public void update(Usuario usuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(usuario);
		t.commit();
	}
	
	@Override
	public void excluirTudo() {  
        List<Usuario> list = list();
        for(Usuario usuario:list){
        	remove(usuario);
        }
    } 
	
}