package net.miguel.uf1845.filtros;

import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import net.miguel.uf1845.modelos.*;

@WebFilter("/admin/*") // urlPatterns = {"/principal", "/formulario", "/borrar"})
public class LoginFilter extends HttpFilter {
    
	private static final long serialVersionUID = 8046921525158160416L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		
		Usuario usuario = (Usuario)session.getAttribute("usuario");
		
		if(usuario == null) {
			res.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		chain.doFilter(request, response);
	}
}
