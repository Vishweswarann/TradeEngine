# TradeEngine

A multi-threaded stock trading engine that matches buy and sell orders in real-time using price-based order matching.

## Overview

TradeEngine is a Java-based trading system that simulates a stock exchange order matching engine. It supports multiple stocks and handles concurrent buy/sell orders using an efficient matching algorithm based on price levels.

## Features

- **Multi-Stock Support**: Handle multiple stocks (TATA, RELIANCE) simultaneously
- **Order Matching**: Automatic matching of buy and sell orders at identical price levels
- **Thread-Safe**: Uses Java threads for concurrent order processing and matching
- **Real-Time Execution**: Periodic matching cycle (4-second intervals) to execute matched orders
- **Queue-Based Orders**: Uses LinkedList queues to manage multiple orders at the same price point

## Architecture

### Core Components

1. **Engine.java**
   - Main orchestrator for the trading system
   - Maintains order books for all stocks
   - Implements the matching algorithm
   - Executes matched trades

2. **Input.java**
   - Handles user input in a separate thread
   - Accepts buy/sell orders with:
     - Stock selection (TATA or RELIANCE)
     - Order type (Buy or Sell)
     - Quantity
     - Price limit
   - Adds orders to the appropriate order book

3. **OrderBook.java**
   - Represents the order book for a single stock
   - Contains two TreeMaps:
     - `buy`: Stores buy orders (sorted in descending order by price)
     - `sell`: Stores sell orders (sorted in ascending order by price)

4. **Order.java**
   - Represents a single buy or sell order
   - Contains:
     - Stock name
     - Quantity

## How It Works

1. **Order Entry**: User enters orders through the interactive console
2. **Order Storage**: Orders are stored in TreeMaps organized by price level
3. **Order Matching**: The engine periodically scans for matching prices between buy and sell orders
4. **Trade Execution**: When matching prices are found:
   - Quantities are compared
   - Trades are executed with remaining quantities updated
   - Orders are fulfilled in FIFO order at each price level

## Usage

### Compilation
```bash
javac Engine.java Input.java
```

### Running the Engine
```bash
java Engine
```

### Placing an Order

1. Select a stock:
   - Enter `t` or `T` for TATA
   - Enter `r` or `R` for RELIANCE

2. Choose order type:
   - Enter `b` or `B` to buy
   - Enter `s` or `S` to sell

3. Enter the quantity of stocks

4. Enter the price limit (limit price)

### Example Interaction
```
Available stocks: 
(t) - TATA
(r) - RELIANCE
Enter the letter inside the paranthesis to book the stocks: t
Enter 'b' to buy or 's' to sell: b
Enter the quantity of the stocks: 100
Enter the limit amount: 1500.50

Available stocks: 
(t) - TATA
(r) - RELIANCE
Enter the letter inside the paranthesis to book the stocks: t
Enter 'b' to buy or 's' to sell: s
Enter the quantity of the stocks: 100
Enter the limit amount: 1500.50

Sold 100 TATA stocks for 1500.5
```

## Order Matching Logic

The matching algorithm works as follows:

1. Find prices that exist in both buy and sell order books
2. For each matching price, compare quantities:
   - **Buy > Sell**: Execute sell order completely, reduce buy quantity
   - **Sell > Buy**: Execute buy order completely, reduce sell quantity
   - **Buy = Sell**: Execute both orders completely

## Data Structures

- **TreeMap<Double, Queue<Order>>**: 
  - Buy orders: Sorted in descending order (highest prices first)
  - Sell orders: Sorted in ascending order (lowest prices first)
- **Queue<Order>**: FIFO queue for orders at the same price level

## Thread Management

- **Main Thread**: Handles order matching and trade execution
- **Input Thread**: Handles user input concurrently

## Limitations

- Static order books (shared across all instances)
- Simple FIFO matching at each price level
- No order cancellation or modification
- Limited to two stocks

## Future Enhancements

- Support for unlimited stocks
- Order cancellation and modification
- Order time limits and expiration
- Trade history and statistics
- GUI for order entry and monitoring
- Persistent storage of trades and order history
- Advanced matching algorithms
- Portfolio tracking

## License

Open source

## Author

Vishweswarann
