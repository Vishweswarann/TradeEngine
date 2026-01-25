import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Input extends Thread {

	HashMap<String, OrderBook> stocks = new HashMap<>();

	public Input(HashMap<String, OrderBook> stocks) {
		this.stocks = stocks;
	}

	public void run() {

		Scanner sc = new Scanner(System.in);

		try {

			Boolean isBuy = null;
			HashMap<String, String> stockTableForInput = new HashMap<>();
			stockTableForInput.put("t", "TATA");
			stockTableForInput.put("T", "TATA");
			stockTableForInput.put("r", "RELIANCE");
			stockTableForInput.put("R", "RELIANCE");

			while (true) {

				System.out.println("Available stocks: ");
				System.out.println("(t) - TATA");
				System.out.println("(r) - RELIANCE");

				System.out.print("Enter the letter inside the paranthesis to book the stocks: ");
				String stock = sc.nextLine();

				System.out.print("Enter 'b' to buy or 's' to sell: ");
				String input = sc.nextLine();

				System.out.print("Enter the quantity of the stocks: ");
				int quantity = Integer.parseInt(sc.nextLine());

				System.out.print("Enter the limit amount: ");
				String amnt = sc.nextLine();

				Double amount = Double.parseDouble(amnt);

				OrderBook orderBook = stocks.get(stockTableForInput.get(stock)); // The variable 'stock' contains only
																					// 't', so we have to change it into
																					// 'TATA' that's why we give it to
																					// the variable
																					// stockTableForInput

				if (input.equals("b") || input.equals("B")) {
					isBuy = true;

					if (!orderBook.buy.containsKey(amount)) {

						orderBook.buy.put(amount, new LinkedList<Order>());
					}
					orderBook.buy.get(amount).offer(new Order(stockTableForInput.get(stock), quantity));

				} else if (input.equals("s") || input.equals("S")) {
					isBuy = false;

					if (!orderBook.sell.containsKey(amount)) {
						orderBook.sell.put(amount, new LinkedList<Order>());
					}

					orderBook.sell.get(amount).offer(new Order(stockTableForInput.get(stock), quantity));

				} else {
					System.out.println("Enter correct order");
					return;
				}
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

}
