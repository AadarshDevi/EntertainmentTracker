package com.alphagnfss.etr3.data;

import com.alphagnfss.etr3.data.extensions.StreamableExtension;
import com.alphagnfss.etr3.data.extensions.ViewableExtension;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Getter
@Setter
public class Episode extends Entertainment {
    private StreamableExtension stream;
    private ViewableExtension view;
    @NonNull private Integer episodeNum;
}
