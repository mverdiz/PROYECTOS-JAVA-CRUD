package net.miguel.uf1845.controladores;

import java.util.*;

import jakarta.servlet.http.*;
import net.miguel.uf1845.modelos.*;

public class SesionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		session.setAttribute("carrito", new ArrayList<Libro>());
	}
	
}
