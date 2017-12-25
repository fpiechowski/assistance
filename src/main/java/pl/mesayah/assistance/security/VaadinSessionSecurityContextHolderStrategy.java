package pl.mesayah.assistance.security;

import com.vaadin.server.VaadinSession;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.context.SecurityContextImpl;

public class VaadinSessionSecurityContextHolderStrategy implements SecurityContextHolderStrategy {

    private static VaadinSession getSession() {

        VaadinSession session = VaadinSession.getCurrent();
        if (session == null) {
            throw new IllegalStateException("No VaadinSession bound to current thread.");
        }
        return session;
    }

    @Override
    public void clearContext() {

        getSession().setAttribute(SecurityContext.class, null);
    }

    @Override
    public SecurityContext getContext() {

        VaadinSession session = getSession();
        SecurityContext context = session.getAttribute(SecurityContext.class);
        if (context == null) {
            context = createEmptyContext();
            session.setAttribute(SecurityContext.class, context);
        }
        return context;
    }

    @Override
    public void setContext(SecurityContext securityContext) {

        getSession().setAttribute(SecurityContext.class, securityContext);
    }

    @Override
    public SecurityContext createEmptyContext() {

        return new SecurityContextImpl();
    }
}
