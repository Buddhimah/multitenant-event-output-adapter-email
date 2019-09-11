package org.wso2.carbon.multitenant.event.output.adapter.email;

import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.event.output.adapter.core.MessageType;
import org.wso2.carbon.event.output.adapter.core.OutputEventAdapter;
import org.wso2.carbon.event.output.adapter.core.OutputEventAdapterConfiguration;
import org.wso2.carbon.event.output.adapter.core.Property;
import org.wso2.carbon.event.output.adapter.email.EmailEventAdapter;
import org.wso2.carbon.event.output.adapter.email.EmailEventAdapterFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TenantEmailEventAdapterFactory  extends EmailEventAdapterFactory {


    public String getType() {
        return "email1";
    }

    public List<String> getSupportedMessageFormats() {
        List<String> supportedMessageFormats = new ArrayList<String>();
        supportedMessageFormats.add(MessageType.TEXT);
        supportedMessageFormats.add(MessageType.XML);
        supportedMessageFormats.add(MessageType.JSON);
        return supportedMessageFormats;
    }

    public List<Property> getStaticPropertyList() {
        return null;
    }


    public String getUsageTips() {
        return null;
    }


    public OutputEventAdapter createEventAdapter(OutputEventAdapterConfiguration outputEventAdapterConfiguration,
            Map<String, String> map) {
        Map<String, String> propertiesMap = new HashMap<String, String>();
        propertiesMap.put("mail.smtp.user","iam123");
        propertiesMap.put("mail.smtp.port","587");
        propertiesMap.put("maxThread","100");
        propertiesMap.put("keepAliveTimeInMillis","20000");
        propertiesMap.put("mail.smtp.password","xxxxxxx");
        propertiesMap.put("mail.smtp.from","iam123@gmail.com");
        propertiesMap.put("mail.smtp.starttls.enable","true");
        propertiesMap.put("mail.smtp.auth","true");
        propertiesMap.put("mail.jobQueueSize","10000");
        propertiesMap.put("mail.smtp.host","smtp.gmail.com");
        propertiesMap.put("minThread","8");
        int tenantId= PrivilegedCarbonContext.getThreadLocalCarbonContext().getTenantId();
        if (tenantId != -1234) {
            replaceGlobalPropertiesWithTenantProperties(map,propertiesMap);
            return new EmailEventAdapter(outputEventAdapterConfiguration, map);
        } else {
            return new EmailEventAdapter(outputEventAdapterConfiguration, map);

        }
    }

    private void replaceGlobalPropertiesWithTenantProperties(Map<String, String> globalProps,
            Map<String, String> tenantProps ){

        for(String key : tenantProps.keySet()){
            if(globalProps.containsKey(key)){
                globalProps.put(key,tenantProps.get(key));
            }

        }

    }
}
