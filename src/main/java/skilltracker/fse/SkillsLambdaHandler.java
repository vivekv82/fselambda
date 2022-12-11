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

//https://cloud.spring.io/spring-cloud-static/spring-cloud-function/3.0.1.RELEASE/reference/html/aws.html
// https://www.youtube.com/watch?v=A1rYiHTy9Lg&ab_channel=JamesEastham

public class SkillsLambdaHandler implements RequestStreamHandler {
//public class SkillsLambdaHandler extends SpringBootRequestHandler<String, Object> {

	private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
	static {
		try {
			handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(SkillsTrackerApp.class);
		} catch (ContainerInitializationException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not initialize Spring Boot application", e);
		}
	}

	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		handler.proxyStream(input, output, context);
	}

}
