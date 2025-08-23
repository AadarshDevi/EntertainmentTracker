//package com.alphag947.v2.controller.custom;
//
//import com.alphag947.controller.ViewerController;
//import javafx.scene.control.ListCell;
//import javafx.scene.control.ListView;
//
//public class ArrayViewer<ViewerController.ViewerController.CenterLabel> extends ListView<CenterLabel> {
//    public ArrayViewer() {
//        setCellFactory((listView) -> new ListCell<ViewerController.CenterLabel>() {
//            @Override
//            protected void updateItem(ViewerController.CenterLabel item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty || item == null) {
//                    setGraphic(null);
//                    setText(null);
//                } else {
//                    // Set the CenterLabel as the graphic for this cell
//                    setGraphic(item);
//
//                    // Ensure text color remains black on selection and non-selection
//                    item.setTextFill(javafx.scene.paint.Color.BLACK);
//                }
//            }
//        });
//    }
//}
