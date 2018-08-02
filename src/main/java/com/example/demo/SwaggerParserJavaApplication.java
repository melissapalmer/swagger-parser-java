package com.example.demo;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.models.HttpMethod;
import io.swagger.models.Info;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import io.swagger.parser.util.ClasspathHelper;

@SpringBootApplication
public class SwaggerParserJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerParserJavaApplication.class, args);

		String swaggerAsString = ClasspathHelper.loadFileFromClasspath("petstore-swagger.json");
		// read a swagger description from the petstore
		Swagger swagger = new SwaggerParser().parse(swaggerAsString);

		String host = swagger.getHost();
		String basePath = swagger.getBasePath();
		Info info = swagger.getInfo();
		String title = info.getTitle();
		String description = info.getDescription();

		System.out.println("\n===============================================");
		System.out.println("host:: [" + host + "]");
		System.out.println("basePath:: [" + basePath + "]");
		System.out.println("title:: [" + title + "]");
		System.out.println("description:: [" + description + "]");
		System.out.println("===============================================");

		Map<String, Path> paths = swagger.getPaths();
		Set<String> keys = paths.keySet();
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String key = iterator.next();
			Path path = paths.get(key);
			System.out.println("" + key);

			Map<HttpMethod, Operation> operationMap = path.getOperationMap();
			Set<HttpMethod> operationsKeySet = operationMap.keySet();
			for (Iterator<HttpMethod> operIterator = operationsKeySet.iterator(); operIterator.hasNext();) {
				HttpMethod httpMethod = operIterator.next();
				// Operation operation = operationMap.get(httpMethod);

				System.out.println("	" + httpMethod);

			}
		}

		System.out.println("===============================================\n");
	}
}
