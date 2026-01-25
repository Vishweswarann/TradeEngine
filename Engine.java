
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class Engine {

	// This is the main hashMap which stores the stockname as its key and their
	// corresponding OrderBook class as value

	static HashMap<String, OrderBook> stocks = new HashMap<>();

	// For each stock a new OrderBook class is created
	// The OrderBook class contains the two treeMaps 'buy' and 'sell' for that
	// particular stock
	//
	// The treeMap 'buy' and 'sell' has price as their key and Order class as its
	// value
	//
	// The Order class contains information about each buy and sell orders

	public static void main(String[] args) {

		try {

			String[] stockName = { "TATA", "RELIANCE" };

			stocks.put("TATA", new OrderBook());
			stocks.put("RELIANCE", new OrderBook());

			Input input = new Input(stocks);
			input.start();

			TreeMap<Double, Queue<Order>> buy = new TreeMap<>(Collections.reverseOrder());
			TreeMap<Double, Queue<Order>> sell = new TreeMap<>();

			while (true) {

				Thread.sleep(4000);

				for (String name : stockName) {
					buy = stocks.get(name).buy;
					sell = stocks.get(name).sell;

					Set<Double> buyPrices = buy.keySet();
					Set<Double> sellPrices = sell.keySet();

					Set<Double> matches = new HashSet<>(buyPrices);
					matches.retainAll(sellPrices);

					if (matches.iterator().hasNext()) {
						Double price = matches.iterator().next();

						while (!buy.get(price).isEmpty() && !sell.get(price).isEmpty()) {
							int buyQuantity = buy.get(price).peek().quantity;
							int sellQuantity = sell.get(price).peek().quantity;

							if (buyQuantity > sellQuantity) {

								System.out.println("Sold " + sellQuantity + " " + name + " stocks for " + price);
								sell.get(price).poll();
								buy.get(price).peek().quantity = buyQuantity - sellQuantity;

							} else if (buyQuantity < sellQuantity) {

								System.out.println("Sold " + buyQuantity + " " + name + " stocks for " + price);
								buy.get(price).poll();
								sell.get(price).peek().quantity = sellQuantity - buyQuantity;

							} else {
								System.out.println("Sold " + buyQuantity + " " + name + " stocks for " + price);
								buy.get(price).poll();
								sell.get(price).poll();
							}

						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

class OrderBook {

	static TreeMap<Double, Queue<Order>> buy = new TreeMap<>(Collections.reverseOrder());
	static TreeMap<Double, Queue<Order>> sell = new TreeMap<>();
}

class Order {

	public Order(String name, int quantity) {
		this.name = name;
		this.quantity = quantity;

	}

	String name;
	int quantity;
}
