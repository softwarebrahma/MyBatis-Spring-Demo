package com.brocade.dcm.server;

import java.util.Collections;
import java.util.Map;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
//@MapperScan("com.brocade.dcm.domain.mapper")
public class ObjectCacheApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ObjectCacheApplication.class, args);
	}
	
	@Bean
	public Ignite ignite() {
		IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
		igniteConfiguration.setPeerClassLoadingEnabled(true);
		igniteConfiguration.setIgniteInstanceName("myBatisObjectGridClient");
		igniteConfiguration.setClientMode(true);
		Map<String, String> userAttrs = Collections.singletonMap("ROLE", "datagrid");
		igniteConfiguration.setUserAttributes(userAttrs);
		TcpDiscoverySpi tcpDiscoverySpi = new TcpDiscoverySpi();
		TcpDiscoveryMulticastIpFinder tcpDiscoveryMulticastIpFinder = new TcpDiscoveryMulticastIpFinder();
		tcpDiscoveryMulticastIpFinder.setMulticastGroup("224.1.4.3");
		tcpDiscoverySpi.setIpFinder(tcpDiscoveryMulticastIpFinder);
		igniteConfiguration.setDiscoverySpi(tcpDiscoverySpi);
		Ignite ignite = Ignition.start(igniteConfiguration);
		return ignite;
	}
	
}
