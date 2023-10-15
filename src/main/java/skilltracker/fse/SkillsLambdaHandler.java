package skilltracker.fse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

public class SkillsLambdaHandler implements RequestStreamHandler {

	private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
	ObjectReader objectReader;
	static ObjectMapper objectMapper = new ObjectMapper();
	static {
		objectMapper.registerModule(new AfterburnerModule());
	}
	static {
		try {
			handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(SkillsTrackerApp.class);
			System.out.println("SkillsLambdaHandler is ready");
		} catch (ContainerInitializationException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not initialize Spring Boot application", e);
		}
	}

	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		System.out.println("SkillsLambdaHandler handleRequest called for input " + input.toString() + "\n"
				+ output.toString() + "\n");
		System.out.println(
				"SkillsLambdaHandler handleRequest context " + context.getFunctionName() + " " + context.toString());
		handler.proxyStream(input, output, context);
	}

}
