package org.ftn.upp.lass.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

/**
 * SOURCE: shorturl.at/rCGM7
 */
@Component
@Slf4j
public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * Returns the Spring managed bean instance of the given class type if it exists.
     * Returns null otherwise.
     * @param beanClass Class of the managed bean instance.
     * @return          Instance, if found, otherwise null.
     */
    public static <T> T getBean(Class<T> beanClass) throws UnsupportedOperationException {
        log.info("Retrieveing bean of {} class.", beanClass.getName());

        if (applicationContext == null) {
            throw new UnsupportedOperationException("Application context not set.");
        }

        return applicationContext.getBean(beanClass);
    }

    /**
     * Sets the ApplicationContext reference to access required beans later on.
     * @param context           Application context to be set.
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(@Nonnull ApplicationContext context) throws BeansException {
        log.info("Setting ApplicationContext: {}.", context.getDisplayName());

        SpringApplicationContext.applicationContext = context;
    }
}