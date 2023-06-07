package com.voltras.simplecrudjavaspringmaven.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.ConnectionString;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableMongoRepositories(basePackages = "com.voltras.simplecrudjavaspringmaven.repository", mongoTemplateRef = "tutorialTemplate")
public class MongoConfig {
	 @Autowired
	  private Environment env;
	  
	 @Bean(name = "tutorialTemplate")
	  public MongoTemplate tutorialTemplate() throws Exception {
		 var mongoTemplate = new MongoTemplate(new SimpleMongoClientDatabaseFactory(new ConnectionString(env.getProperty("tutorial.mongodb.url"))));
		 var mongoMapping = (MappingMongoConverter) mongoTemplate.getConverter();
		 mongoMapping.afterPropertiesSet();
		 
		 return mongoTemplate;
	  }
}
