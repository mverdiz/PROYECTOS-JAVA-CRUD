package net.miguel.uf1845.configuracion;


import net.miguel.uf1845.dal.DaoClientes;
import net.miguel.uf1845.dal.DaoClientesMemoria;
import net.miguel.uf1845.dal.DaoLibros;
import net.miguel.uf1845.dal.DaoLibrosMemoria;

public class Globales {
	public static final DaoLibros daoLibros = DaoLibrosMemoria.getInstancia();
	public static final DaoClientes daoClientes = DaoClientesMemoria.getInstancia();
}
