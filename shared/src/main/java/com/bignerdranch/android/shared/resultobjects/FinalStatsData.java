package com.bignerdranch.android.shared.resultobjects;

import java.util.List;

public class FinalStatsData {
    private List<Integer> stats;

    public FinalStatsData(List<Integer> stats){
        this.stats = stats;
    }

    public List<Integer> getStats() {
        return stats;
    }
}
