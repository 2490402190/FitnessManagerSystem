package fitness.view.mainbody.equipment_manager;

import fitness.dao.DataBaseUtils;
import fitness.entity.Equipment;
import fitness.utils.Log;
import fitness.utils.NoticeWindow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class EquipmentManagerController implements Initializable {

    public ImageView close_button;
    public TableView table;
    public Button search;
    public Button insert;
    public Button delete;
    public TextField searchText;
    public Pagination page;
    //用于实现查询的筛选操作,监听搜索框实时更新liveData
    private ObservableList<Equipment> rawData = FXCollections.observableArrayList();//原始数据
    private ObservableList<Equipment> liveData = FXCollections.observableArrayList();//实时数据
    private ObservableList<Equipment> pageData = FXCollections.observableArrayList();//每页显示的数据
    private Stage insertStage;
    private Integer pageSize = 6;//每页数据条数
    private Integer totalPageCount;//总页数
    private String picturePath = "E:\\idea-project\\IdeaProjects\\FitnessClubSystem\\src\\main\\resources\\image\\默认.png";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setEditable(true);//设置tableview可编辑
        delete.setDisable(true);//未选中记录，delete不可使用
        TableColumn<Equipment, String> id = new TableColumn<>("编号");
        id.setStyle("-fx-alignment: CENTER;");//设置内容居中
        id.setPrefWidth(75);
        id.setCellValueFactory(new PropertyValueFactory("id"));
        id.setCellFactory(TextFieldTableCell.forTableColumn());//设置具体的列进行单元格编辑
        id.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Equipment, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Equipment, String> t) {
                        ((Equipment) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setId(t.getNewValue());
                    }
                }
        );

        TableColumn<Equipment, String> name = new TableColumn("器械名称");
        name.setStyle("-fx-alignment: CENTER;");
        name.setPrefWidth(100);
        name.setCellValueFactory(new PropertyValueFactory("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Equipment, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Equipment, String> t) {
                        ((Equipment) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setName(t.getNewValue());
                        //更新数据库
                        Equipment selectedItem = (Equipment) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateEquipment(new Equipment(selectedItem.getId(), selectedItem.getName(), selectedItem.getPlace(), selectedItem.getType(), selectedItem.getPrice(), selectedItem.getPicture(),selectedItem.getStatus(),selectedItem.getRenderId(),selectedItem.getRemark()));
                    }
                }
        );

        TableColumn<Equipment, String> place = new TableColumn("放置区域");
        place.setStyle("-fx-alignment: CENTER;");
        place.setPrefWidth(150);
        place.setCellValueFactory(new PropertyValueFactory("place"));
        place.setCellFactory(TextFieldTableCell.forTableColumn());
        place.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Equipment, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Equipment, String> t) {
                        ((Equipment) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setPlace(t.getNewValue());
                        //更新数据库
                        Equipment selectedItem = (Equipment) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateEquipment(new Equipment(selectedItem.getId(), selectedItem.getName(), selectedItem.getPlace(), selectedItem.getType(), selectedItem.getPrice(), selectedItem.getPicture(),selectedItem.getStatus(),selectedItem.getRenderId(),selectedItem.getRemark()));
                    }
                }
        );

        TableColumn<Equipment, String> type = new TableColumn("锻炼类型");
        type.setStyle("-fx-alignment: CENTER;");
        type.setPrefWidth(100);
        type.setCellValueFactory(new PropertyValueFactory("type"));
        type.setCellFactory(TextFieldTableCell.forTableColumn());
        type.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Equipment, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Equipment, String> t) {
                        ((Equipment) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setType(t.getNewValue());
                        //更新数据库
                        Equipment selectedItem = (Equipment) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateEquipment(new Equipment(selectedItem.getId(), selectedItem.getName(), selectedItem.getPlace(), selectedItem.getType(), selectedItem.getPrice(), selectedItem.getPicture(),selectedItem.getStatus(),selectedItem.getRenderId(),selectedItem.getRemark()));
                    }
                }
        );

        TableColumn<Equipment, String> price = new TableColumn("价格");
        price.setStyle("-fx-alignment: CENTER;");
        price.setPrefWidth(75);
        price.setCellValueFactory(new PropertyValueFactory("price"));
        price.setCellFactory(TextFieldTableCell.forTableColumn());
        price.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Equipment, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Equipment, String> t) {
                        ((Equipment) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setPrice(t.getNewValue());
                        //更新数据库
                        Equipment selectedItem = (Equipment) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateEquipment(new Equipment(selectedItem.getId(), selectedItem.getName(), selectedItem.getPlace(), selectedItem.getType(), selectedItem.getPrice(), selectedItem.getPicture(),selectedItem.getStatus(),selectedItem.getRenderId(),selectedItem.getRemark()));
                    }
                }
        );

        TableColumn<Equipment, ImageView> picture = new TableColumn("图片");
        picture.setStyle("-fx-alignment: CENTER;");
        picture.setPrefWidth(100);
        picture.setCellValueFactory(new PropertyValueFactory("picture"));
//        picture.setCellFactory(TextFieldTableCell.forTableColumn());
        picture.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Equipment, ImageView>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Equipment, ImageView> t) {
                        ((Equipment) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setPicture(t.getNewValue());
                        //更新数据库
                        Equipment selectedItem = (Equipment) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateEquipment(new Equipment(selectedItem.getId(), selectedItem.getName(), selectedItem.getPlace(), selectedItem.getType(), selectedItem.getPrice(), selectedItem.getPicture(),selectedItem.getStatus(),selectedItem.getRenderId(),selectedItem.getRemark()));
                    }
                }
        );

        TableColumn<Equipment, String> status = new TableColumn("状态");
        status.setStyle("-fx-alignment: CENTER;");
        status.setPrefWidth(75);
        status.setCellValueFactory(new PropertyValueFactory("status"));
        status.setCellFactory(TextFieldTableCell.forTableColumn());
        status.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Equipment, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Equipment, String> t) {
                        ((Equipment) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setStatus(t.getNewValue());
                        //更新数据库
                        Equipment selectedItem = (Equipment) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateEquipment(new Equipment(selectedItem.getId(), selectedItem.getName(), selectedItem.getPlace(), selectedItem.getType(), selectedItem.getPrice(), selectedItem.getPicture(),selectedItem.getStatus(),selectedItem.getRenderId(),selectedItem.getRemark()));
                    }
                }
        );

        TableColumn<Equipment, String> renderId = new TableColumn("租借者ID");
        renderId.setStyle("-fx-alignment: CENTER;");
        renderId.setPrefWidth(75);
        renderId.setCellValueFactory(new PropertyValueFactory("renderId"));
        renderId.setCellFactory(TextFieldTableCell.forTableColumn());
        renderId.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Equipment, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Equipment, String> t) {
                        ((Equipment) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setRenderId(t.getNewValue());
                        //更新数据库
                        Equipment selectedItem = (Equipment) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateEquipment(new Equipment(selectedItem.getId(), selectedItem.getName(), selectedItem.getPlace(), selectedItem.getType(), selectedItem.getPrice(), selectedItem.getPicture(),selectedItem.getStatus(),selectedItem.getRenderId(),selectedItem.getRemark()));
                    }
                }
        );

        TableColumn<Equipment, String> remark = new TableColumn("备注");
        remark.setStyle("-fx-alignment: CENTER;");
        remark.setPrefWidth(185);
        remark.setCellValueFactory(new PropertyValueFactory("remark"));
        remark.setCellFactory(TextFieldTableCell.forTableColumn());
        remark.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Equipment, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Equipment, String> t) {
                        ((Equipment) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setRemark(t.getNewValue());
                        //更新数据库
                        Equipment selectedItem = (Equipment) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateEquipment(new Equipment(selectedItem.getId(), selectedItem.getName(), selectedItem.getPlace(), selectedItem.getType(), selectedItem.getPrice(), selectedItem.getPicture(),selectedItem.getStatus(),selectedItem.getRenderId(),selectedItem.getRemark()));
                    }
                }
        );

        //从数据库获取Course数据
        List<Equipment> equipments = DataBaseUtils.getAllEquipments();
        //把查询到的Course数据封装到data中
        Iterator<Equipment> iterator = equipments.iterator();
        while (iterator.hasNext()) {
            rawData.add(iterator.next());
        }
        //给表添加列
        table.getColumns().addAll(id, name,place,type,price,picture,status,renderId,remark);

        //搜索框监听，当内容为空则显示原始数据
        searchText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (searchText.getText().equals("")) {
                    flushPage(rawData);
                }
            }
        });

        //分页
        flushPage(rawData);
    }

    private Integer getTotalPageCount(ObservableList<Equipment> data){//求总页数
        return data.size() % pageSize == 0 ? data.size() / pageSize : data.size() / pageSize + 1;
    }

    private TableView createPage(ObservableList<Equipment> data , Integer pageIndex) {//每页展示内容
        int pageStart = pageIndex * pageSize;//每页的起始页码为：当前页码*每页行数
        pageData.clear();//每次都要清空原有内容
        for (int i = pageStart; i < pageStart + pageSize && i < data.size(); i++) {
            pageData.add(data.get(i));
        }
        table.setItems(pageData);
        return table;
    }

    private void flushPage(ObservableList<Equipment> data){ //刷新界面
        totalPageCount = getTotalPageCount(data);
        page.setPageCount(totalPageCount);
        page.setPageFactory((Integer pageIndex) -> {//页面页码索引参数会自动在恰当时机传进分页控件，这个是由交互进行时态的控件运行时自动传进来的
            if (pageIndex >= data.size()) {//当前选中的页码数>文本页内容的条目数
                System.out.println(1);
                return null;//则返回展示的内容为空
            } else {
                return createPage(data , pageIndex);//返回页面要渲染展示的内容
            }
        });
    }

    public void table_click_action() {
        //如果选中了用户，则解除添加按钮
        if (table.getSelectionModel().getSelectedItem() != null) {
            delete.setDisable(false);
        }
    }

    //添加
    @FXML
    public void insert_button_action() throws Exception {
        Log.info("insert_button_action");
        insertStage = new Stage();
        insertStage.setTitle("添加器材");
        insertStage.getIcons().add(new Image("file:///E:\\idea-project\\IdeaProjects\\FitnessClubSystem\\src\\main\\resources\\image\\添加.png"));
        insertStage.setResizable(false);
        AnchorPane pane = new AnchorPane();
        Label idLabel = new Label("器材编号");
        idLabel.setLayoutX(34);idLabel.setLayoutY(45);
        TextField id = new TextField();
        id.setPromptText("请输入器材编号");
        id.setPrefWidth(250);id.setPrefHeight(30);
        id.setLayoutX(123);id.setLayoutY(40);

        Label nameLabel = new Label("器材名称");
        nameLabel.setLayoutX(34);nameLabel.setLayoutY(85);
        TextField name = new TextField();
        name.setPromptText("请输入器材名称");
        name.setPrefWidth(250);name.setPrefHeight(30);
        name.setLayoutX(123);name.setLayoutY(80);

        Label placeLabel = new Label("放置区域");
        placeLabel.setLayoutX(34);placeLabel.setLayoutY(125);
        TextField place = new TextField();
        place.setPromptText("请输入器材放置区域");
        place.setPrefWidth(250);place.setPrefHeight(30);
        place.setLayoutX(123);place.setLayoutY(120);

        Label typeLabel = new Label("锻炼类型");
        typeLabel.setLayoutX(34);typeLabel.setLayoutY(165);
        TextField type = new TextField();
        type.setPromptText("请输入锻炼类型");
        type.setPrefWidth(250);type.setPrefHeight(30);
        type.setLayoutX(123);type.setLayoutY(160);

        Label priceLabel = new Label("价格");
        priceLabel.setLayoutX(64);priceLabel.setLayoutY(205);
        TextField price = new TextField();
        price.setPromptText("请输入价格");
        price.setPrefWidth(250);price.setPrefHeight(30);
        price.setLayoutX(123);price.setLayoutY(200);

        Label statusLabel = new Label("状态");
        statusLabel.setLayoutX(64);statusLabel.setLayoutY(245);
        TextField status = new TextField();
        status.setPromptText("请输入器材状态");
        status.setPrefWidth(250);status.setPrefHeight(30);
        status.setLayoutX(123);status.setLayoutY(240);

        Label renderIdLabel = new Label("租借者ID");
        renderIdLabel.setLayoutX(34);renderIdLabel.setLayoutY(285);
        TextField renderId = new TextField();
        renderId.setPromptText("请输入租借者ID");
        renderId.setPrefWidth(250);renderId.setPrefHeight(30);
        renderId.setLayoutX(123);renderId.setLayoutY(280);

        Label remarkLabel = new Label("备注");
        remarkLabel.setLayoutX(64);remarkLabel.setLayoutY(325);
        TextField remark = new TextField();
        remark.setPromptText("请输入备注");
        remark.setPrefWidth(250);remark.setPrefHeight(30);
        remark.setLayoutX(123);remark.setLayoutY(320);

        ImageView picture = new ImageView(new Image("file:///E:\\idea-project\\IdeaProjects\\FitnessClubSystem\\src\\main\\resources\\image\\默认.png"));
        picture.setFitWidth(132);picture.setFitHeight(150);
        picture.setLayoutX(422);picture.setLayoutY(44);
        Button select = new Button("选择图片");
        select.setStyle("-fx-background-color: rgb(0,152,139);-fx-text-fill: white");
        select.setLayoutX(450);select.setLayoutY(200);

        Button add = new Button("添加");
        add.setStyle("-fx-background-color: rgb(0,152,139);-fx-text-fill: white");
        add.setPrefWidth(115);add.setPrefHeight(30);
        add.setLayoutX(191);add.setLayoutY(357);
        pane.getChildren().addAll(idLabel, id, nameLabel, name, placeLabel, place, typeLabel, type, priceLabel, price, statusLabel, status, renderIdLabel, renderId, remarkLabel, remark, picture, select, add);
        insertStage.setScene(new Scene(pane, 600, 400));
        insertStage.show();
        //选择头像事件
        select.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("打开");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));//初始化文件目录
            Stage stage = (Stage) select.getScene().getWindow();
            fileChooser.getExtensionFilters().addAll(//设置文件选择器所能打开的文件类型（利用给文件选择器对象添加扩展名过滤器功能来实现）
                    new FileChooser.ExtensionFilter("图像文件", "*.jpg", "*.png", "*.bmp", "*.jpeg", "gif")
            );
            File file = fileChooser.showOpenDialog(stage);
            try {
                picturePath = file.getCanonicalPath();//获取打开文件的绝对路径
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //更新头像显示
            picture.setImage(new Image("file:///" + picturePath));
        });
        //给添加绑定事件
        add.setOnAction(event -> {
            if (id.getText().equals("")) {
                new NoticeWindow("提示", "请输入器材编号").show();
                return;
            }
            if (name.getText().equals("")) {
                new NoticeWindow("提示", "请输入器材名称").show();
                return;
            }
            if(DataBaseUtils.getEquipmentById(id.getText())){
                new NoticeWindow("提示", "器材编号已存在").show();
                return;
            }
            Equipment equipment = new Equipment(id.getText(), name.getText(),place.getText(), type.getText(),price.getText(),new ImageView(new Image("file:///" + picturePath)),status.getText(),renderId.getText(),remark.getText());
            equipment.initImageSize();
            rawData.add(equipment);
            flushPage(rawData);//刷新界面
            insertStage.close();

            //更新数据库
            if (DataBaseUtils.InsertEquipment(equipment)) {
                new NoticeWindow("提示", "器材添加成功").show();
            } else {
                new NoticeWindow("提示", "器材添加失败").show();
            }
        });
    }

    public void insert_button_OnMouseEntered() {
        insert.setCursor(Cursor.HAND);
    }

    @FXML
    public void search_button_action() throws Exception {
        Log.info("search_button_action");
        if (searchText.getText().equals("")) {
            table.setItems(rawData);
        } else {
            liveData.clear();//每次都清空数据
            List<Equipment> equipmentes = DataBaseUtils.FuzzySearchEquipments(searchText.getText());
            Iterator<Equipment> iterator = equipmentes.iterator();
            while (iterator.hasNext()) {
                liveData.add(iterator.next());
            }
            flushPage(liveData);
        }
    }

    public void search_button_OnMouseEntered() {
        search.setCursor(Cursor.HAND);
    }

    //删除
    @FXML
    public void delete_button_action() {
        Log.info("delete_button_action");
        //获取表格中选中的行的记录
        Equipment selectedItem = (Equipment) table.getSelectionModel().getSelectedItem();
        //更新数据库
        if (DataBaseUtils.DeleteEquipment(selectedItem.getId())) {
            new NoticeWindow("提示", "器材信息删除成功").show();
        } else {
            new NoticeWindow("提示", "器材信息删除失败").show();
            return;
        }
        rawData.remove(selectedItem);
        table.getItems().remove(selectedItem);
        flushPage(rawData);//刷新界面
    }

    public void delete_button_OnMouseEntered() {
        delete.setCursor(Cursor.HAND);
    }

    //关闭
    @FXML
    public void close_button_action() {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void close_button_OnMouseEntered() {
        close_button.setCursor(Cursor.HAND);
    }

}
