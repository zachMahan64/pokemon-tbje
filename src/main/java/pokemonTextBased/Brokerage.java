package pokemonTextBased;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Brokerage {
    public static HashMap<String, Long> stockPrices = getStockPrices();
    public static ArrayList<String> stockList = new ArrayList<>(stockPrices.keySet());

    public static void enterBrokerage(Scanner sc1) {
        String choice = "";
        stockPrices = getStockPrices();
        while (!choice.trim().equalsIgnoreCase("L")) {
            String additionToStockLog = "";
            Graphics.printBrokerage();

            choice = sc1.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1":
                    viewPrices(sc1);
                    break;
                case "2":
                    additionToStockLog = buyStock(sc1);
                    break;
                case "3":
                    additionToStockLog = sellStock(sc1);
                    break;
                case "4":
                    viewPortfolio(sc1);
                    break;
                case "L":
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    Game.pressEnterToContinue(sc1);
            }
            if(!additionToStockLog.isBlank()) {
                User.tradeHistory.add(User.getMonth().toUpperCase() + " 1998: " + additionToStockLog + ".");
            }
        }
    }
    private static void viewPrices(Scanner sc1) {
        printStockPriceChart();
        Game.pressEnterToContinue(sc1);
    } //add market projection?
    public static HashMap<String, Long> getStockPrices() {
        HashMap<String, Long> stockPrices = new HashMap<>();
        int numBadges = User.checkNumBadges();
        Random rand = new Random();
        stockPrices.put("Silph Co", 500L + (numBadges * 50L) + (rand.nextInt(-25, 25)));
        stockPrices.put("Rare Candy Inc", 75L + (numBadges * 10L) + (rand.nextInt(-3, 5)));
        stockPrices.put("Oak Labs", 100L - (numBadges * 2L) + (rand.nextInt(-5, 5)));
        stockPrices.put("Box Operations Corp", 250L + (numBadges * 3L) + (rand.nextInt(-15, 10)));
        stockPrices.put("Vaughan Media", 65L + (numBadges * 20L) + (rand.nextInt(-5, 5)));
        stockPrices.put("LAUNCH Enterprises", 120L + (numBadges * 10L) + (rand.nextInt(-10, 10)));
        stockPrices.put("Arbok Advisors", 450L - (numBadges * 15L) + (rand.nextInt(-25, 25)));
        stockPrices.put("Giga Impact Software", 350L + (numBadges * 30L) + (rand.nextInt(-10, 25)));
        stockPrices.put("Mewtual Funds", 550L - (numBadges * 15L) + (rand.nextInt(-40, 25)));
        stockPrices.put("Lapras Logistics", 380L + (numBadges * 20L) + (rand.nextInt(-25, 25)));
        return stockPrices;
    }
    public static String getStockOfInterest(Scanner sc1) {
        int stockNum = Location.getValidInt(sc1);
        String stockOfInterest = "";
        try {
            if(stockNum == 0) stockOfInterest = "0";
            else stockOfInterest = stockList.get(stockNum-1);
        }
        catch (IndexOutOfBoundsException e) {
            stockOfInterest = "-1";
        }
        return stockOfInterest;
    }
    public static String buyStock(Scanner sc1) {
        String additionToLog = "";
        printStockPriceChart();
        System.out.print("Enter the # of the stock you want to buy (0 to cancel): ");
        String stockToBuy = getStockOfInterest(sc1);
        if (stockPrices.containsKey(stockToBuy)) {
            long price = stockPrices.get(stockToBuy);
            int sharesToBuy = Location.getValidIntWithPrompt(sc1, "Enter number of shares to buy (0 to cancel): ");
            long totalCost = price * sharesToBuy;
            if (sharesToBuy == 0) {
                System.out.println("Oh, you changed your mind?");
                Game.pressEnterToContinue();
            }
            else if (sharesToBuy > 0 && Bag.getPokedollars() >= totalCost) {
                Bag.spendPokedollars((int) totalCost);
                Bag.addStock(stockToBuy, sharesToBuy);
                additionToLog = "You bought " + sharesToBuy + " share(s) of " + stockToBuy + " at " + stockPrices.get(stockToBuy);
                System.out.println(additionToLog + ".");
                Game.pressEnterToContinue();
            } else {
                System.out.println("Not enough funds or invalid amount.");
                Game.pressEnterToContinue();
            }
        } else if (stockToBuy.equalsIgnoreCase("0")){
            System.out.println("Oh, you changed your mind?");
            Game.pressEnterToContinue();
        } else {
            System.out.println("Invalid stock name.");
            Game.pressEnterToContinue();
        }
        return additionToLog;
    }
    public static String sellStock(Scanner sc1) {
        String additionToLog = "";
        printStockPriceChart();
        System.out.print("Enter the # of the stock you want to sell (0 to cancel): ");
        String stockToSell = getStockOfInterest(sc1);
        if (stockPrices.containsKey(stockToSell) && Bag.getStockCount(stockToSell) > 0) {
            System.out.print("Enter number of shares to sell (you have " + Bag.getStockCount(stockToSell) + " trading at " + stockPrices.get(stockToSell) + "): ");
            int sharesToSell = Location.getValidInt(sc1);
            if (sharesToSell == 0) {
                System.out.println("Oh, you changed your mind?");
                Game.pressEnterToContinue();
            }
            else if (sharesToSell > 0 && Bag.getStockCount(stockToSell) >= sharesToSell) {
                long totalSale = stockPrices.get(stockToSell) * sharesToSell;
                Bag.addStock(stockToSell, -sharesToSell);
                Bag.adjustPokedollarBalance((int) totalSale);
                additionToLog = "You sold " + sharesToSell + " share(s) of " + stockToSell + " at " + stockPrices.get(stockToSell) + " for " + totalSale + " total Pokedollars";
                System.out.println(additionToLog + ".");
                Game.pressEnterToContinue();
            } else {
                System.out.println("Invalid amount or not enough shares.");
                Game.pressEnterToContinue();
            }
        } else if (stockToSell.equalsIgnoreCase("0")){
            System.out.println("Oh, you changed your mind?");
            Game.pressEnterToContinue();
        } else {
            System.out.println("You don't own that stock or it doesn't exist.");
            Game.pressEnterToContinue();
        }
        return additionToLog;
    }
    public static void printStockPriceChart() {
        System.out.println("==================================================================");
        System.out.println("              Stock Prices (Pokedollars per share)");
        System.out.println("------------------------------------------------------------------");
        int i = 0;
        for (String entry : stockList) {
            i++;
            System.out.printf( "%-30s %10d%n", "[" + i + "] " + entry, stockPrices.get(entry));
        }
        System.out.println("==================================================================");
    }
    public static void printPortfolio() {
        long totalPortfolioValue = 0;
        for (HashMap.Entry<String, Integer> stockEntry : Bag.stockPortfolio.entrySet()) {
            String stockName = stockEntry.getKey();
            int shares = stockEntry.getValue();
            Long currentPrice = stockPrices.get(stockName);

            if (currentPrice != null && shares > 0) {
                totalPortfolioValue += (long) shares * currentPrice;
            }
        }

        System.out.println("                                     " + User.getUsername().toUpperCase() + "'S PORTFOLIO");
        System.out.println("=======================================================================================");
        if (Bag.stockPortfolio.isEmpty()) {
            System.out.println("You don't own any stocks.");
        } else {
            for (String stock : Bag.stockPortfolio.keySet()) {
                System.out.println("  " + stock + ": " + Bag.stockPortfolio.get(stock) + " share(s) | PPS: "
                        + stockPrices.get(stock) + " Pokedollars | Value: " + Bag.stockPortfolio.get(stock) * stockPrices.get(stock)
                        + " Pokedollars");
            }
        }
        System.out.println("=======================================================================================");
        System.out.println("  TOTAL PORTFOLIO VALUE: " + totalPortfolioValue);
        System.out.println("=======================================================================================");
        System.out.println("| [V] VIEW TRADE HISTORY                                                              |");
        System.out.println("| [B] BACK                                                                            |");
        System.out.println("---------------------------------------------------------------------------------------");
    }
    public static void viewPortfolio(Scanner sc1) {
        stockPrices = getStockPrices();
        String choicePortfolio = "";
        do {
            printPortfolio();
            choicePortfolio = sc1.nextLine().trim().toUpperCase();
            if (choicePortfolio.equalsIgnoreCase("V")) {
                viewTradeHistory(sc1);
            }
        } while (!choicePortfolio.trim().equalsIgnoreCase("B"));
    }
    public static void viewTradeHistory(Scanner sc1) {
        System.out.println("                                   TRADE HISTORY");
        System.out.println("=======================================================================================");
        if (User.tradeHistory.isEmpty()) {
            System.out.println("No trade history on record...");
        }
        else {
            for (String entry : User.tradeHistory) {
                System.out.println(entry);
            }
        }
        Game.pressEnterToContinue(sc1);
    }
}
