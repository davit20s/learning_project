package com.magento.tests.ui;

import com.magento.task.steps.ui.StepsUi;
import org.junit.jupiter.api.BeforeAll;

import static com.magento.task.steps.ui.proxy.ProxySteps.enableAndSetProxy;

public class BaseProxyTestUi extends BaseTestUi implements StepsUi {

    @BeforeAll
    public static void initProxy() {
        enableAndSetProxy();
    }
}
