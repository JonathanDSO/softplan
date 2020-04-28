package br.com.softplan.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.softplan.domain.view.UsuarioLogado;

public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

		HttpSession sess = ((HttpServletRequest) servletRequest).getSession(true);

		String newCurrentPage = ((HttpServletRequest) servletRequest).getServletPath();

		if (sess.getAttribute("currentPage") == null) {
			sess.setAttribute("lastPage", newCurrentPage);
			sess.setAttribute("currentPage", newCurrentPage);
		} else {

			String oldCurrentPage = sess.getAttribute("currentPage").toString();
			if (!oldCurrentPage.equals(newCurrentPage)) {
				sess.setAttribute("lastPage", oldCurrentPage);
				sess.setAttribute("currentPage", newCurrentPage);
			}
		}

		UsuarioLogado usuarioLogado = (UsuarioLogado) sess.getAttribute("usuarioLogado");
		if (usuarioLogado != null) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("init Filter");
	}

	@Override
	public void destroy() {
		// close resources
	}
}