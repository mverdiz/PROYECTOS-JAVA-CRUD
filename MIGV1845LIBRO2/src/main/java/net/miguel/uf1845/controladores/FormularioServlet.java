package net.miguel.uf1845.controladores;

import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import net.miguel.uf1845.dal.*;
import net.miguel.uf1845.modelos.*;

@WebServlet("/admin/formulario")
public class FormularioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final DaoLibros dao = DaoLibrosMemoria.getInstancia();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		if(id != null) {
			Libro libro = dao.obtenerPorId(Long.parseLong(id));
			request.setAttribute("libro", libro);
		}
		
		request.getRequestDispatcher("/WEB-INF/vistas/formulario.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		String isbn = request.getParameter("isbn");
		String precio = request.getParameter("precio");
		
		Libro libro = new Libro(id, titulo, autor, isbn, precio);
		
		if(libro.tieneErrores()) {
			request.setAttribute("libro", libro);
			request.getRequestDispatcher("/WEB-INF/vistas/formulario.jsp").forward(request, response);
			
			return;
		}
		
		if(libro.getId() == null) {
			dao.insertar(libro);
		} else {
			dao.modificar(libro);
		}
		
		response.sendRedirect("principal");
	}

}
