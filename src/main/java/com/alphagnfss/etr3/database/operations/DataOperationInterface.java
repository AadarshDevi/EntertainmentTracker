package com.alphagnfss.etr3.database.operations;

import com.alphagnfss.etr3.communication.Response;
import com.alphagnfss.etr3.data.Entertainment;

public interface DataOperationInterface {
    Response createData(Entertainment entertainment);

    Response updateData(String id, Entertainment entertainment);

    Response deleteData(String id);

    Response readData(String id);
}
