package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health
{
    private int value;
    private int maxValue;
    private List<ExhaustionEffect> LIST;

    public Health(int state, int max){
        this.value = state;
        this.maxValue = max;
        this.LIST = new ArrayList<>();
    }

    public Health(int state){
        this.value = state;
        this.maxValue = state;
        this.LIST = new ArrayList<>();
    }


    public int getValue() {
        return this.value;
    }

    public void refill(int amount) {
        if (this.value+amount<=this.maxValue) this.value += amount;
    }

    public void restore() {
        this.value = this.maxValue;
    }

    public void drain(int amount) {
        if (this.value - amount < 0) this.value = 0;
        else this.value -= amount;

        if (this.value == 0)
            this.LIST.forEach(ExhaustionEffect::apply);
    }

    void exhaust() {
        this.value = 0;
    }

    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

    public void onExhaustion(ExhaustionEffect effect) {
        this.LIST.add(effect);
    }
}
