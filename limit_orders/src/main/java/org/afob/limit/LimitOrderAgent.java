
package org.afob.limit;

import org.afob.execution.ExecutionClient;
import org.afob.execution.ExecutionClient.ExecutionException;
import org.afob.prices.PriceListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LimitOrderAgent implements PriceListener {

    private final ExecutionClient executionClient;
    private final List<LimitOrder> orders;


    public LimitOrderAgent(final ExecutionClient ec) {
        this.executionClient = ec;
        this.orders = new ArrayList<>();
    }


    public void addOrder(boolean isBuy, String productId, int amount, BigDecimal limitPrice) {
        orders.add(new LimitOrder(isBuy, productId, amount, limitPrice));
    }

    @Override
    public void priceTick(String productId, BigDecimal price) {
        List<LimitOrder> executedOrders = new ArrayList<>();


        for (LimitOrder order : orders) {
            if (order.getProductId().equals(productId)) {
                try {
                    if (order.isBuy() && price.compareTo(order.getLimitPrice()) <= 0) {
                        executionClient.buy(order.getProductId(), order.getAmount());
                        executedOrders.add(order);
                    } else if (!order.isBuy() && price.compareTo(order.getLimitPrice()) >= 0) {
                        executionClient.sell(order.getProductId(), order.getAmount());
                        executedOrders.add(order);
                    }
                } catch (ExecutionException e) {
                    System.err.println("Order execution failed: " + e.getMessage());
                }
            }
        }

        orders.removeAll(executedOrders);
    }


    private static class LimitOrder {
        private final boolean isBuy;
        private final String productId;
        private final int amount;
        private final BigDecimal limitPrice;

        public LimitOrder(boolean isBuy, String productId, int amount, BigDecimal limitPrice) {
            this.isBuy = isBuy;
            this.productId = productId;
            this.amount = amount;
            this.limitPrice = limitPrice;
        }

        public boolean isBuy() {
            return isBuy;
        }

        public String getProductId() {
            return productId;
        }

        public int getAmount() {
            return amount;
        }

        public BigDecimal getLimitPrice() {
            return limitPrice;
        }
    }
}