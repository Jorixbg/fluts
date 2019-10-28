import java.util.*;

public class Fluts {
    private int maxProfit = 0;
    private static List<Schuur> schuurs = new ArrayList();
    public static void main(String[] args) {
        System.out.println("Welcome to Fluts");
        Scanner scanner = new Scanner(System.in);
        String input = "";
        try {
            while (true) {
                input = scanner.nextLine();
                if ("0".equals(input)) {
                    break;
                }

                int numberOfSchuurs = Integer.valueOf(input);
                for (int i = 0; i < numberOfSchuurs; i++) {
                    Schuur schuur = new Schuur();
                    input = scanner.nextLine();
                    String[] numbers = input.trim().split(" ");
                    int numberOfFluts = Integer.valueOf(numbers[0]);
                    for (int k = 1; k <= numberOfFluts; k++) {
                        schuur.addFlut(new Flut(Integer.valueOf(numbers[k])));
                    }
                    schuurs.add(schuur);
                }
            }
        } catch (Exception e) {
            System.out.println("Error while parsing input");
            main(null);
        }
        Fluts f = new Fluts();
        f.process();
    }

    private void process() {
        schuurs.stream().forEach(schuur -> {
            int schurProfit = 0;
            int value = 0;
            boolean wasLastEqual = false;
            for (int i = 0; i < schuur.getFluts().size(); i++) {
                int flutIndex = i+1;
                int flutPrice = ((Flut) schuur.getFluts().get(i)).getPrice();
                value +=flutPrice;
                int profit = (10*flutIndex) - value;
                if (profit > schurProfit) {
                    schurProfit = profit;
                    if (wasLastEqual) {
                        schuur.moveFlutIndexBack();
                    }
                    schuur.buyNewFlut(flutIndex);
                    wasLastEqual = false;
                } else if (profit == schurProfit) {
                    schuur.moveFlutIndexUp();
                    schuur.buyNewFlut(flutIndex);
                    wasLastEqual = true;
                } else {
                    schuur.moveFlutIndexUp();
                    wasLastEqual = false;
                }
            }
            maxProfit += schurProfit;
            schuur.trimFlutsToBuy();
            System.out.println("Number of fluts to buy: " + schuur.getFlutsToBuy());
        });
        System.out.println("Maximum profit is: " + maxProfit);
        System.out.println("Number of fluts to buy: " + getNumberOfFlutsToBuy());
    }

    private Set<Integer> getNumberOfFlutsToBuy() {
        Set<Integer> numberOfFlutsToBuy = new HashSet<>();
        schuurs.stream().forEach(schuur -> {
            schuur.generatePermutations(schuurs, numberOfFlutsToBuy, 0, 0);
        });
        return numberOfFlutsToBuy;
    }

}
