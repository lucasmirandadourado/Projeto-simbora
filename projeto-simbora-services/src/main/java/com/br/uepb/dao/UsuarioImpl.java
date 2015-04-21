package com.br.uepb.dao;


import java.util.List; 

import com.br.uepb.domain.Usuario;

public interface UsuarioImpl {

	public void save(Usuario usuario);
	public Usuario getUsuario(String login);
	public List<Usuario> list();
	public void remove(Usuario usuario);
	public void update(Usuario usuario);
	public void excluirTudo();
	 
}
