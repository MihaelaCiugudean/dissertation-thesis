package com.iquestgroup.advancedframeworks.ScrumTaskboard.util;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.persistence.EntityManagerFactory;

import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.management.ManagementService;



public class CacheStatisticsLoader {

	public void initStatistics()  {
	/*    ObjectName statsName = new ObjectName("org.hibernate:type=statistics");
	    MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();

	    final Statistics statistics = sessionFactory.getStatistics();
	    statistics.setStatisticsEnabled(true);
	    Object statisticsMBean = Proxy.newProxyInstance(getClass().getClassLoader(), new Class<?>[] { StatisticsMXBean.class }, new InvocationHandler() {

	            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	                return method.invoke(statistics, args);
	            }
	        });

	    mbeanServer.registerMBean(statisticsMBean, statsName);
	    */
	    
		// Get EhCache Statistics - method 1
		CacheManager manager = CacheManager.newInstance();
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		ManagementService.registerMBeans(manager, mBeanServer, true, true, true, true);
		
		
		// Get EhCache Statistics - method 2 - does NOT work
		/*
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/application-config.xml");
//		EntityManagerFactory myBean = (EntityManagerFactory) ctx.getBean("entityManagerFactory");
		EntityManagerFactoryInfo emfi = (EntityManagerFactoryInfo)myBean;
		EntityManagerFactory emf = emfi.getNativeEntityManagerFactory();
		EntityManagerFactoryImpl empImpl = (EntityManagerFactoryImpl)ctx.getBean("entityManagerFactory");
		System.out.println("STATISTICS: ");
		System.out.println(empImpl.getSessionFactory().getStatistics());
		*/
	}
}
