package lsdi.clouddeployer;

import lsdi.clouddeployer.DataTransferObjects.IoTGatewayRequest;
import lsdi.clouddeployer.Services.IotCatalogerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ClouddeployerApplication {
	@Value("${node.uuid}")
	private String nodeUuid;
	@Value("${node.name}")
	private String nodeName;
	@Value("${node.url}")
	private String nodeUrl;
	@Value("${iotcataloger.url}")
	private String iotCatalogerUrl;

	public static void main(String[] args) {
		SpringApplication.run(ClouddeployerApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		selfRegister();
	}

	private void selfRegister() {
		IotCatalogerService iotCatalogerService = new IotCatalogerService(iotCatalogerUrl);
		IoTGatewayRequest request = new IoTGatewayRequest();
		request.setUuid(nodeUuid);
		request.setDistinguishedName(nodeName);
		request.setUrl(nodeUrl);
		request.setLatitude(1.0);
		request.setLongitude(1.0);

		iotCatalogerService.registerGateway(request);
	}
}
