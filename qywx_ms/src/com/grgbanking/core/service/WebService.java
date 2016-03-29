package com.grgbanking.core.service;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class WebService<T> {

    protected T service;

    @SuppressWarnings("unchecked")
    public WebService(Class<T> clazz, String url) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(clazz);
        factory.setAddress(url);
        this.service = (T) factory.create();
    }

    @SuppressWarnings("rawtypes")
	protected Object initWebService(Class clazz, String url) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(clazz);
        factory.setAddress(url);
        return factory.create();
    }
}
