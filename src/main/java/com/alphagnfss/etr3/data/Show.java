package com.alphagnfss.etr3.data;

import com.alphagnfss.etr3.data.extensions.ListableExtension;
import com.alphagnfss.etr3.data.extensions.StreamableExtension;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class Show extends Entertainment {
    private StreamableExtension stream;
    private ListableExtension list;
}
