package net.miguel.uf1845.controladores;
import static net.miguel.uf1845.configuracion.Globales.*;



import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.miguel.uf1845.dal.DaoLibros;
import net.miguel.uf1845.dal.DaoLibrosMemoria;
import net.miguel.uf1845.modelos.Libro;

@WebServlet("/carrito")
public class CarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final DaoLibros dao = DaoLibrosMemoria.getInstancia();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		String id = request.getParameter("id");

		@SuppressWarnings("unchecked")
		ArrayList<Libro> carrito = (ArrayList<Libro>) request.getSession().getAttribute("carrito");
		
		switch (opcion) {
		case "quitar":
			carrito.remove(daoLibros.obtenerPorId(Long.parseLong(id)));
			break;
		case "agregar":
			carrito.add(daoLibros.obtenerPorId(Long.parseLong(id)));
		case "ver":
			break;
		default:
			throw new ServletException("No tengo esa opción");
		}
		
		request.getRequestDispatcher("/WEB-INF/vistas/carrito.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
