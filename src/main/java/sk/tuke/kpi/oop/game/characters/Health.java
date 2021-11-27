package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health
{
    private int value;
    private int maxValue;
    private List<ExhaustionEffect> LIST;
    private boolean isAlive;

    public Health(int state, int max){
        this.value = state;
        this.maxValue = max;
        this.LIST = new ArrayList<>();
        this.isAlive = true;
    }

    public Health(int state){
        this.value = state;
        this.maxValue = state;
        this.LIST = new ArrayList<>();
        this.isAlive = true;
    }


    public int getValue() {
        return this.value;
    }

    public void refill(int amount) {
        if (this.value+amount > this.maxValue) this.value = this.maxValue;
        else this.value += amount;
    }

    public void restore() {
        this.value = this.maxValue;
    }

    public void drain(int amount) {
        if (this.value == 0) return;

        if (this.value - amount < 0) this.value = 0;
        else this.value -= amount;

        if (this.value == 0) exhaust();
    }

    public void exhaust() {
        this.value = 0;
        if (!this.isAlive) return;
        for (ExhaustionEffect oneEffect : this.LIST) oneEffect.apply();
        this.isAlive = false;
    }

    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

    public void onExhaustion(ExhaustionEffect effect) {
        this.LIST.add(effect);
    }
}
