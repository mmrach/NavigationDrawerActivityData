package com.amm.navigationdraweractivitydata;

import androidx.core.util.Pair;

public interface FragmentListener {
    Pair<String, Integer> getSelectedColor();
    void setSelectedColor(Pair<String, Integer> selectedColor);
}
