package org.springframework.samples.petclinic.util;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.MethodRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.test.context.TestContextManager;

import com.tngtech.jgiven.integration.spring.SpringCanWire;
import com.tngtech.jgiven.junit.ScenarioTest;

public class PetClinicSpringScenarioTest<GIVEN, WHEN, THEN> extends ScenarioTest<GIVEN, WHEN, THEN> {

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @ClassRule
    public static SpringContextManagerRule springRule = new SpringContextManagerRule();

    @Rule
    public SpringTestRule testRule = new SpringTestRule( springRule.testContextManager );

    public static class SpringContextManagerRule extends TestWatcher {
        TestContextManager testContextManager;

        @Override
        protected void starting( Description description ) {
            testContextManager = new TestContextManager( description.getTestClass() );
            try {
                testContextManager.beforeTestClass();
            } catch( Exception e ) {
                throw new RuntimeException( e );
            }
        }

        @Override
        protected void finished( Description description ) {
            try {
                testContextManager.afterTestClass();
            } catch( Exception e ) {
                throw new RuntimeException( e );
            }
        }
    }

    public static class SpringTestRule implements MethodRule {
        private TestContextManager testContextManager;

        public SpringTestRule( TestContextManager testContextManager ) {
            this.testContextManager = testContextManager;
        }

        @Override
        public Statement apply( final Statement base, final FrameworkMethod method, final Object target ) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    try {
                        testContextManager.prepareTestInstance( target );
                        testContextManager.beforeTestMethod( target, method.getMethod() );
                        base.evaluate();
                        testContextManager.afterTestMethod( target, method.getMethod(), null );
                    } catch( Throwable t ) {
                        testContextManager.afterTestMethod( target, method.getMethod(), t );
                        throw t;
                    }
                }
            };
        }
    }

    @Before
    public void setupSpring() throws Exception {
        wireSteps( new SpringCanWire( beanFactory ) );
    }

}
