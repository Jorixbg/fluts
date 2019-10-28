import java.util.*;

public class Schuur {

    private int flutToBuyIndex;
    private Stack<Flut> fluts;
    private List<Integer> flutsToBuy;

    public Schuur() {
        fluts = new Stack<>();
        flutsToBuy = new ArrayList<>();
        flutsToBuy.add(0);
    }

    public Stack getFluts() {
        return fluts;
    }

    public void setFluts(Stack fluts) {
        this.fluts = fluts;
    }

    public void addFlut(Flut flut) {
        fluts.push(flut);
    }

    public List<Integer> getFlutsToBuy() {
        return flutsToBuy;
    }

    public void setFlutsToBuy(List<Integer> flutsToBuy) {
        this.flutsToBuy = flutsToBuy;
    }

    public void buyNewFlut(int flutIndex) {
        flutsToBuy.set(flutToBuyIndex, flutIndex);
    }

    public void moveFlutIndexUp() {
        flutToBuyIndex++;
        flutsToBuy.add(0);
    }

    public void moveFlutIndexBack() {
        flutToBuyIndex--;
        flutsToBuy.remove(flutToBuyIndex);
    }

    public void trimFlutsToBuy() {
        flutsToBuy.removeIf(e -> e==0);
    }

    public Set<Integer> calculateCombinations(List<Schuur> schuurs) {
        Set<Integer> numberOfFlutsToBuy = new HashSet<>();
        for (int i = 0; i < this.getFlutsToBuy().size(); i++) {
            int index = this.getFlutsToBuy().get(i);
            int sum = index;
            for (Schuur schuur: schuurs) {
                if (this == schuur) {
                    continue;
                }

                sum += schuur.getFlutsToBuy().get(0);
            }
            numberOfFlutsToBuy.add(sum);
        };
        return numberOfFlutsToBuy;
    }

    public void generatePermutations(List<Schuur> schuurs, Set<Integer> result, int depth, int current) {
        if (depth == schuurs.size()) {
            result.add(current);
            return;
        }

        for (int i = 0; i < schuurs.get(depth).getFlutsToBuy().size(); i++) {
            generatePermutations(schuurs, result, depth + 1, current + schuurs.get(depth).getFlutsToBuy().get(i));
        }
    }
}
