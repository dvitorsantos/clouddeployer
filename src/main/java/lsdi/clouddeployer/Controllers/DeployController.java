package lsdi.clouddeployer.Controllers;

import lsdi.clouddeployer.DataTransferObjects.DeployRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DeployController {
    RestTemplate restTemplate = new RestTemplate();
    private final String workerUrl = System.getenv("WORKER_URL");
    private final String collectorUrl = System.getenv("COLLECTOR_URL");

    @PostMapping("/deploy")
    public void deploy(@RequestBody DeployRequest deployRequest) {
        restTemplate.postForObject(workerUrl + "/deploy", deployRequest, String.class);
        deployRequest.getRules().forEach(rule -> {
            restTemplate.postForObject(collectorUrl + "/subscribe/" + rule.getEventType(), null, String.class);
        });
    }
}
