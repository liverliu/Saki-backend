package saki.backend.config;

import org.springframework.web.util.Log4jConfigListener;
import saki.backend.config.context.SakiContextConfig;
import saki.backend.config.servlet.SakiServletConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by liverliu on 15/10/31.
 */
public class SakiInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{SakiContextConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SakiServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        fr.setInitParameter("encoding", "UTF-8");
        fr.setInitParameter("forceEncoding", "true");

        servletContext.setInitParameter( "log4jConfigLocation" , "classpath:/properties/log4j.properties" );
        Log4jConfigListener log4jListener = new Log4jConfigListener();
        servletContext.addListener(log4jListener);

    }
}
