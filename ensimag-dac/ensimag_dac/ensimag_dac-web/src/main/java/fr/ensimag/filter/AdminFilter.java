package fr.ensimag.filter;

import fr.ensimag.control.UtilisateurBean;
import fr.ensimag.foundation.INames;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter checks if utilisateurBean has loggednIn property set to true and if has admin role. If it is not
 * set then request is being redirected to the index.xhml page.
 */
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletrequest = (HttpServletRequest) request;
        // Get the utilisateurBean from session attribute
        UtilisateurBean userBean = (UtilisateurBean) httpServletrequest.getSession().getAttribute("utilisateurBean");
        if (userBean == null || !userBean.isLoggedIn() || !userBean.getUser().getRoleId().equals(INames.ROLE_ADMIN_ID)) {
            String contextPath = httpServletrequest.getContextPath();
            String redirect = contextPath + "/index.xhtml";
            ((HttpServletResponse) response).sendRedirect(redirect);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
// Nothing to do here!
    }

    @Override
    public void destroy() {
// Nothing to do here!
    }
}
