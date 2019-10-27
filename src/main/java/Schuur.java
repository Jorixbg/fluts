import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    public void moveFlutIndex() {
        flutToBuyIndex++;
        flutsToBuy.add(0);
    }

    public void trimFlutsToBuy() {
        flutsToBuy.removeIf(e -> e==0);
    }
}
