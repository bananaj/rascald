package com.bananaj.mgr.domain;

import java.util.Comparator;

/**
 * Created by cio on 12/02/15.
 */
public class StepComparator implements Comparator<Step> {
    @Override
    public int compare(Step o1, Step o2) {
        return o1.getSortOrder().compareTo(o2.getSortOrder());
    }
}
