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

//https://cloud.spring.io/spring-cloud-static/spring-cloud-function/3.0.1.RELEASE/reference/html/aws.html
// https://www.youtube.com/watch?v=A1rYiHTy9Lg&ab_channel=JamesEastham

public class SkillsLambdaHandler implements RequestStreamHandler {
//public class SkillsLambdaHandler extends SpringBootRequestHandler<String, Object> {

	private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
	ObjectReader objectReader;
	static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.registerModule(new AfterburnerModule());
    }
	static {
		try {
			handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(SkillsTrackerApp.class);
//			handler = new SpringBootProxyHandlerBuilder<AwsProxyRequest>().defaultProxy().asyncInit()
//					.springBootApplication(SkillsTrackerApp.class).buildAndInitialize();
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
		
//		try {
//			objectReader = objectMapper.readerFor(AwsProxyRequest.class);
//			AwsProxyRequest req = objectReader.readValue(input);
//			System.out.println("req.getPath() = " + req.getPath());
//			System.out.println("req.getHttpMethod() = " + req.getHttpMethod());
//			System.out.println("req.getBody() = " + req.getBody());
//			System.out.println("req.getRequestContext().getAccountId() = " + req.getRequestContext().getAccountId());
//			System.out.println("req.getResource() = " + req.getResource());
//			System.out.println("req.getPathParameters() = " + req.getPathParameters());
//        } catch (JsonParseException e) {
//        	System.out.println("Error while parsing request object stream");
//        } catch (JsonMappingException e) {
//        	System.out.println("Error while mapping object to RequestType class");
//        } catch (Exception e) {
//        	System.out.println("Error while mapping object to RequestType class");
//        }
		handler.proxyStream(input, output, context);
	}

}
