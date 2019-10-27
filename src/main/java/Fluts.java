import java.util.*;

public class Fluts {
    private int maxProfit = 0;
    private static List<Schuur> schuurs = new ArrayList();
    public static void main(String[] args) {
        System.out.println("Welcome to Fluts");
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (true) {
            input = scanner.nextLine();
            if("0".equals(input)) {
                break;
            }

            int numberOfSchuurs = Integer.valueOf(input);
            for(int i = 0; i<numberOfSchuurs; i++) {
                Schuur schuur = new Schuur();
                input = scanner.nextLine();
                String[] numbers = input.trim().split(" ");
                int numberOfFluts = Integer.valueOf(numbers[0]);
                for(int k = 1; k<=numberOfFluts; k++) {
                    schuur.addFlut(new Flut(Integer.valueOf(numbers[k])));
                }
                schuurs.add(schuur);
            }
        }


//
        Fluts f = new Fluts();
//        Schuur a = new Schuur();
//        Schuur b = new Schuur();
//        a.getFluts().addAll(Arrays.asList(new Flut(7),new Flut(3),new Flut(11),new Flut(9),new Flut(10)));
////        a.reverseStack();
//        b.getFluts().addAll(Arrays.asList(new Flut(1),new Flut(2),new Flut(3),new Flut(4),
//                new Flut(10),new Flut(16),new Flut(10),new Flut(4),new Flut(16)));
//        b.reverseStack();
        f.testFlut();
    }

    private void testFlut() {
        schuurs.stream().forEach(schuur -> {
            int schurProfit = 0;
            int value = 0;
            for (int i = 0; i < schuur.getFluts().size(); i++) {
                int flutIndex = i+1;
                int flutPrice = ((Flut) schuur.getFluts().get(i)).getPrice();
                value +=flutPrice;
                int profit = (10*flutIndex) - value;
                if (profit > schurProfit) {
                    schurProfit = profit;
                    schuur.buyNewFlut(flutIndex);
                } else if (profit == schurProfit) {
                    schuur.moveFlutIndex();
                    schuur.buyNewFlut(flutIndex);
                } else {
                    schuur.moveFlutIndex();
                }
            }
            maxProfit += schurProfit;
            schuur.trimFlutsToBuy();
            System.out.println("Number of flutes to buy: " + schuur.getFlutsToBuy());
        });
        System.out.println("Maximum profit is: " + maxProfit);
        System.out.println("Number of fluts to buy: " + getNumberOfFlutsToBuy());
    }

    private Set<Integer> getNumberOfFlutsToBuy() {
        Set<Integer> numberOfFlutsToBuy = new HashSet<>();
        schuurs.stream().forEach(schuur -> {
            numberOfFlutsToBuy.addAll(schuur.calculateCombinations(schuurs));
        });
        return numberOfFlutsToBuy;
    }

}
