package com.epam.lab.news.configuration;

import com.epam.lab.news.aop.interceptor.ServiceExceptionInterceptor;
import com.epam.lab.news.aop.logging.ApplicationAPILogger;
import com.epam.lab.news.aop.observer.ConnectionPoolObserver;
import com.epam.lab.news.database.jdbc.pool.ConnectionPool;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Locale;

/**
 * Contains configured application beans.
 */
@Configuration
public class ApplicationBeans {

    /**
     * Helps to controller in search required views
     *
     * @return Configured .jsp views resolver
     */
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * Configured
     *
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("en"));
        return localeResolver;
    }

    /**
     * Locale interceptor
     *
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
        changeInterceptor.setParamName("lang");
        return changeInterceptor;
    }

    /**
     * This bean helps to localize application views
     *
     * @return Configured message source
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setCacheSeconds(10);
        messageSource.setBasename("classpath:interface");
        return messageSource;
    }

    /**
     * This configurer needs for loading data from properties.
     *
     * @return PropertySourcesPlaceholderConfigurer object
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * Logger for watching public application REST API
     *
     * @return Aspect which observing public API
     */
    @Bean
    public ApplicationAPILogger getApplicationAPILogger(){
        return new ApplicationAPILogger();
    }

    /**
     * This bean handles exceptions that can be thrown from repositories
     *
     * @return Exception interceptor
     */
    @Bean
    public ServiceExceptionInterceptor getServiceExceptionInterceptor(){
        return new ServiceExceptionInterceptor();
    }

    /**
     * Observes and writing logs when connections taken from pool and returned in pool
     *
     * @return Observer for connection pool
     */
    @Bean
    public ConnectionPoolObserver getConnectionPoolObserver(){
        return new ConnectionPoolObserver();
    }

    /**
     * Connection pool bean
     *
     * @return Connection pool
     */
    @Bean(initMethod = "init")
    public ConnectionPool connectionPool(){
        return new ConnectionPool();
    }

}
