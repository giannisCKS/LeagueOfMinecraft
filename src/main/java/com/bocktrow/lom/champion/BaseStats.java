package com.bocktrow.lom.champion;

import com.bocktrow.lom.statistic.Statistic;

public class BaseStats {

    public static double get(Statistic statistic) {
        switch (statistic) {
            case MOVEMENT_SPEED: return 100;
        }
        return 0;
    }

}
