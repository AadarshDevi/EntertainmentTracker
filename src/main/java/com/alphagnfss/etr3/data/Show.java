package com.alphagnfss.etr3.data;

import com.alphagnfss.etr3.data.extensions.ListableExtension;
import com.alphagnfss.etr3.data.extensions.StreamableExtension;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Show extends Entertainment {
    private StreamableExtension stream;
    private ListableExtension list;
    @Singular private List<Integer> episodeIds;
}
