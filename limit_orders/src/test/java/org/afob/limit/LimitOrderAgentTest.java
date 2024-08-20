package org.afob.limit;

import org.junit.Assert;
import org.junit.Test;
import org.afob.execution.ExecutionClient;

public class LimitOrderAgentTest {

    @Test
    public void addTestsHere() {
        Assert.fail("not implemented");
    }
}

//package org.afob.limit;
//
//import org.afob.execution.ExecutionClient;
//import org.afob.execution.ExecutionClient.ExecutionException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class LimitOrderAgentTest {
//
//    private LimitOrderAgent limitOrderAgent;
//    private TestExecutionClient testExecutionClient;
//
//    @BeforeEach
//    void setUp() {
//        testExecutionClient = new TestExecutionClient();
//        limitOrderAgent = new LimitOrderAgent(testExecutionClient);
//    }
//
//    @Test
//    void testBuyOrderExecutedWhenPriceDropsBelowLimit() {
//        limitOrderAgent.addOrder(true, "IBM", 1000, new BigDecimal("100"));
//        limitOrderAgent.priceTick("IBM", new BigDecimal("99"));
//        assertTrue(testExecutionClient.wasBuyCalled);
//        assertEquals(1000, testExecutionClient.amount);
//    }
//
//    @Test
//    void testSellOrderExecutedWhenPriceRisesAboveLimit() {
//        limitOrderAgent.addOrder(false, "IBM", 500, new BigDecimal("150"));
//        limitOrderAgent.priceTick("IBM", new BigDecimal("151"));
//        assertTrue(testExecutionClient.wasSellCalled);
//        assertEquals(500, testExecutionClient.amount);
//    }
//
//    @Test
//    void testOrderNotExecutedWhenPriceIsNotAtLimit() {
//        limitOrderAgent.addOrder(true, "IBM", 1000, new BigDecimal("100"));
//        limitOrderAgent.priceTick("IBM", new BigDecimal("101"));
//        assertFalse(testExecutionClient.wasBuyCalled);
//    }
//}
