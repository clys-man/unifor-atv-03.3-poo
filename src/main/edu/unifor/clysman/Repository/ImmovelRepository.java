package edu.unifor.clysman.Repository;

import edu.unifor.clysman.Immovel.Immovel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ImmovelRepository {
    private final List<Immovel> immovels = new ArrayList<>();

    private List<Immovel> partialImmovels;

    public ImmovelRepository() {
        this.partialImmovels = immovels;
    }

    public List<Immovel> findAll() {
        return this.immovels;
    }

    public void save(Immovel immovel) {
        this.immovels.add(immovel);
    }

    public void delete(Immovel immovel) {
        this.immovels.remove(immovel);
        this.partialImmovels = this.immovels;
    }

    public ImmovelRepository where(Predicate<? super Immovel> condition) {
        this.partialImmovels = this.immovels.stream()
                .filter(condition)
                .collect(Collectors.toList());

        return this;
    }

    public ImmovelRepository sort(Comparator<Immovel> comparator, String order) {
        if (order.equalsIgnoreCase("desc")) {
            comparator = comparator.reversed();
        }

        this.immovels.sort(comparator);
        this.partialImmovels = this.immovels;

        return this;
    }

    public ImmovelRepository sort(Comparator<Immovel> comparator) {
        return this.sort(comparator, "asc");
    }

    public int count() {
        return this.partialImmovels.size();
    }

    public List<Immovel> get() {
        return this.partialImmovels;
    }

    public Immovel first() {
        return this.partialImmovels.get(0);
    }
}