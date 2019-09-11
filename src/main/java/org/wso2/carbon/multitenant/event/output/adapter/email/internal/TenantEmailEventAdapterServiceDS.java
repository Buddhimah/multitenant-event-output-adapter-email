package org.wso2.carbon.multitenant.event.output.adapter.email.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.event.output.adapter.core.OutputEventAdapterFactory;
import org.wso2.carbon.multitenant.event.output.adapter.email.TenantEmailEventAdapterFactory;

/**
 * @scr.component component.name="multitenant.event.output.adapter.email" immediate="true"
 */

public class TenantEmailEventAdapterServiceDS  {
    private static final Log log = LogFactory.getLog(TenantEmailEventAdapterServiceDS.class);

    /**
     * initialize the email service here service here.
     *
     * @param context
     */
    protected void activate(ComponentContext context) {

        try {
            OutputEventAdapterFactory tenantEmailEventAdapterFactory = new TenantEmailEventAdapterFactory();
            context.getBundleContext().registerService(OutputEventAdapterFactory.class.getName(),
                    tenantEmailEventAdapterFactory, null);
                log.info("Successfully deployed the tenant output Email event adaptor service");
        } catch (RuntimeException e) {
            log.error("Can not create the tenant output Email event adaptor service ", e);
        }
    }

}
