package pl.cwik.dawid.ui.testProvider;

import org.springframework.beans.factory.annotation.Required;
import pl.cwik.dawid.services.TestService;

public class TestProviderImpl implements TestProvider {

    private TestService testService;

    @Required
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @Override
    public void test() {
        System.out.println("###################");
        System.out.println("Hello mr provider! ");

        testService.test();
    }
}
