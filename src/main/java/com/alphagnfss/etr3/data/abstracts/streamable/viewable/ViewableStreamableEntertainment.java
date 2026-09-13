package com.alphagnfss.etr3.data.abstracts.streamable.viewable;

import com.alphagnfss.etr3.data.abstracts.streamable.StreamableEntertainment;
import com.alphagnfss.etr3.data.interfaces.Viewable;

public abstract class ViewableStreamableEntertainment extends StreamableEntertainment implements Viewable {
    private int duration;

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public void setDuration(int duration) {

    }
}
