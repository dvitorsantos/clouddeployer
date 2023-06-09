package lsdi.clouddeployer.DataTransferObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
public class DeployRequest {
    @Nullable
    @JsonProperty("host_uuid")
    public String hostUuid;
    @Nullable
    @JsonProperty("webhook_url")
    public String webhookUrl;
    public List<RuleRequestResponse> rules;
}
