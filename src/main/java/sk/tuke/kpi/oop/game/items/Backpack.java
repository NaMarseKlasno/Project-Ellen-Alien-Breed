package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Backpack implements ActorContainer<Collectible> {

    private int CAPACITY;
    private String NAME;
    private List<Collectible> ARRAY = new ArrayList<>();


    public Backpack(String name, int capacity)
    {
        this.NAME = name;
        this.CAPACITY = capacity;
    }

    @Override
    public @NotNull List<Collectible> getContent() {
        return List.copyOf(this.ARRAY);
    }

    @Override
    public int getCapacity() {
        return this.CAPACITY;
    }

    @Override
    public int getSize() {
        return this.ARRAY.size();
    }

    @Override
    public @NotNull String getName() {
        return this.NAME;
    }

    @Override
    public void add(@NotNull Collectible actor) {
        if (this.ARRAY.size() < getCapacity()) this.ARRAY.add(actor);
        else throw new IllegalStateException(getName() + " is full");
    }

    @Override
    public void remove(@NotNull Collectible actor) {
        if (this.ARRAY == null) return;
        this.ARRAY.remove(actor);
    }

    @Override
    public @Nullable Collectible peek() {
        if (!this.ARRAY.isEmpty()) return null;
        return this.ARRAY.get(this.ARRAY.size()-1);
    }

    @Override
    public void shift() {
        Collections.rotate(this.ARRAY, 1);
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {
        return this.ARRAY.iterator();
    }
}
