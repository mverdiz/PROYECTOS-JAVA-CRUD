package net.miguel.uf1845.controladores;

import java.io.*;
import java.time.*;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import net.miguel.uf1845.configuracion.Globales;
import net.miguel.uf1845.modelos.Cliente;
import net.miguel.uf1845.modelos.Factura;
import net.miguel.uf1845.modelos.Libro;
import net.miguel.uf1845.modelos.Linea;
import net.miguel.uf1845.modelos.Usuario;

@WebServlet("/factura")
public class FacturaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		ArrayList<Libro> carrito = (ArrayList<Libro>) request.getSession().getAttribute("carrito");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

		if (usuario == null) {
			request.setAttribute("mensaje", "Debes iniciar sesión para facturar");
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}

		Long id = usuario.getId();

		Cliente cliente = Globales.daoClientes.obtenerPorId(id);

		Factura factura = new Factura(null, LocalDate.now(), "001", cliente);

		for (Libro libro : carrito) {
			factura.getLineas().add(new Linea(libro, 1));
		}

		request.setAttribute("factura", factura);

		request.getRequestDispatcher("/WEB-INF/vistas/factura.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
