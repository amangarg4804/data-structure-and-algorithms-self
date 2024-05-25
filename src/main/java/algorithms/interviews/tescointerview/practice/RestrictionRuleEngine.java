package algorithms.interviews.tescointerview.practice;

import java.util.HashMap;
import java.util.Map;

// Tesco gets millions of orders every day with an average basket size of 100 items.
//    Tesco Business has got some regulations around selling products online and in stores.
//    These regulations are mandatory from legal and business perspective to enforce for all order transactions.
//        You are given an order with a list of products in the shopping cart/basket with productid, product Category and quantity. And also, Restriction Rules on Qty and Qty/Category.
//        Example:
//        Ordered items in the shopping cart/basket
//        Item-1 -> productid=1, category=Paracetamol, quantity=3
//        Item-2 -> productid=2, category=analgesic, quantity=3
//        Item-3 -> productid=3, category=chocolate, quantity=8
//        Item-4 -> productid=4, category= Paracetamol, quantity=2
//
//        Business Restriction rules:
//        Cannot buy more than 10 Quantity of any products - BulkBuyLimit
//        Cannot buy more than 5 Quantity of paracetamol products – BulkBuyLimitCategory
//
//        Write a restriction rule engine to run the restriction check against the shopping cart/basket and return the status as to MET/BREACHED indicating restriction
//        status for the given restriction rules.
//        For the above given example, the restriction status returned would be MET.
class ShoppingCartItem {
    private String productId;
    private String category;
    private int quantity;

    public ShoppingCartItem(String productId, String category, int quantity) {
        this.productId = productId;
        this.category = category;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }
}

class ShoppingCart {
    private ShoppingCartItem[] items;

    public ShoppingCart(ShoppingCartItem[] items) {
        this.items = items;
    }

    public ShoppingCartItem[] getItems() {
        return items;
    }
}

public class RestrictionRuleEngine {

    private Map<String, Integer> bulkBuyLimitPerProductId;
    private Map<String, Integer> bulkBuyLimitCategory;

    public RestrictionRuleEngine(Map<String, Integer> bulkBuyLimitPerProductId, Map<String, Integer> bulkBuyLimitCategory) {
        this.bulkBuyLimitPerProductId = bulkBuyLimitPerProductId;
        this.bulkBuyLimitCategory = bulkBuyLimitCategory;
    }

    public String checkRestriction(ShoppingCart cart) {
        Map<String, Integer> categoryQuantity = new HashMap<>();
        boolean ruleBreached = false;

        for (ShoppingCartItem item : cart.getItems()) {
            String productId = item.getProductId();
            String category = item.getCategory();
            int quantity = item.getQuantity();

            // Check BulkBuyLimit rule
            if (quantity > bulkBuyLimitPerProductId.getOrDefault(productId, Integer.MAX_VALUE)) {
                ruleBreached = true;
                break;
            }

            // Check BulkBuyLimitCategory rule for Paracetamol category
            if (categoryQuantity.containsKey(category)) {
                categoryQuantity.put(category, categoryQuantity.get(category) + quantity);
            } else {
                categoryQuantity.put(category, quantity);
            }

            if (categoryQuantity.get(category) > bulkBuyLimitCategory.getOrDefault(category, Integer.MAX_VALUE)) {
                ruleBreached = true;
                break;
            }
        }

        return ruleBreached ? "BREACHED" : "MET";
    }

    public static void main(String[] args) {
        ShoppingCartItem[] items = {
                new ShoppingCartItem("1", "Paracetamol", 3),
                new ShoppingCartItem("2", "analgesic", 3),
                new ShoppingCartItem("3", "chocolate", 8),
                new ShoppingCartItem("4", "Paracetamol", 2)
        };

        ShoppingCart cart = new ShoppingCart(items);

        Map<String, Integer> bulkBuyLimitPerProductId = new HashMap<>();
        bulkBuyLimitPerProductId.put("1", 10);
        bulkBuyLimitPerProductId.put("2", 10);
        bulkBuyLimitPerProductId.put("3", 10);
        bulkBuyLimitPerProductId.put("4", 10);

        Map<String, Integer> bulkBuyLimitCategory = new HashMap<>();
        bulkBuyLimitCategory.put("Paracetamol", 5);

        RestrictionRuleEngine ruleEngine = new RestrictionRuleEngine(bulkBuyLimitPerProductId, bulkBuyLimitCategory);

        String status = ruleEngine.checkRestriction(cart);
        System.out.println("Restriction status: " + status);
    }
}

