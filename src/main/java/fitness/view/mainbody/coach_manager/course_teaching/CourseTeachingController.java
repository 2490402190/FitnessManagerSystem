package fitness.view.mainbody.coach_manager.course_teaching;

import fitness.dao.DataBaseUtils;
import fitness.entity.CourseTeaching;
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
import javafx.stage.Stage;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class CourseTeachingController implements Initializable {
    public ImageView close_button;
    public TableView table;
    public Button search;
    public Button insert;
    public Button delete;
    public TextField searchText;
    public Pagination page;
    //用于实现查询的筛选操作,监听搜索框实时更新liveData
    private ObservableList<CourseTeaching> rawData = FXCollections.observableArrayList();//原始数据
    private ObservableList<CourseTeaching> liveData = FXCollections.observableArrayList();//实时数据
    private ObservableList<CourseTeaching> pageData = FXCollections.observableArrayList();//每页显示的数据
    private Stage insertStage;
    private Integer pageSize = 10;//每页数据条数
    private Integer totalPageCount;//总页数

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setEditable(true);//设置tableview可编辑
        delete.setDisable(true);//未选中记录，delete不可使用
        TableColumn<CourseTeaching, String> id = new TableColumn<>("编号");
        id.setStyle("-fx-alignment: CENTER;");//设置内容居中
        id.setPrefWidth(75);
        id.setCellValueFactory(new PropertyValueFactory("id"));
        id.setCellFactory(TextFieldTableCell.forTableColumn());//设置具体的列进行单元格编辑
        id.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<CourseTeaching, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<CourseTeaching, String> t) {
                        ((CourseTeaching) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setId(t.getNewValue());
                    }
                }
        );

        TableColumn<CourseTeaching, String> coachId = new TableColumn<>("教练工号");
        coachId.setStyle("-fx-alignment: CENTER;");//设置内容居中
        coachId.setPrefWidth(75);
        coachId.setCellValueFactory(new PropertyValueFactory("coachId"));
        coachId.setCellFactory(TextFieldTableCell.forTableColumn());//设置具体的列进行单元格编辑
        coachId.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<CourseTeaching, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<CourseTeaching, String> t) {
                        ((CourseTeaching) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setCoachId(t.getNewValue());
                        CourseTeaching selectedItem = (CourseTeaching) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCourseTeaching(new CourseTeaching(selectedItem.getId(),selectedItem.getCoachId(),selectedItem.getCoachName(),selectedItem.getCourseId(),selectedItem.getCourseName(),selectedItem.getNumber()));
                    }
                }
        );

        TableColumn<CourseTeaching, String> coachName = new TableColumn("教练名称");
        coachName.setStyle("-fx-alignment: CENTER;");
        coachName.setPrefWidth(100);
        coachName.setCellValueFactory(new PropertyValueFactory("coachName"));
        coachName.setCellFactory(TextFieldTableCell.forTableColumn());
        coachName.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<CourseTeaching, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<CourseTeaching, String> t) {
                        ((CourseTeaching) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setCoachName(t.getNewValue());
                        //更新数据库
                        CourseTeaching selectedItem = (CourseTeaching) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCourseTeaching(new CourseTeaching(selectedItem.getId(),selectedItem.getCoachId(),selectedItem.getCoachName(),selectedItem.getCourseId(),selectedItem.getCourseName(),selectedItem.getNumber()));
                    }
                }
        );

        TableColumn<CourseTeaching, String> courseId = new TableColumn("课程编号");
        courseId.setStyle("-fx-alignment: CENTER;");
        courseId.setPrefWidth(75);
        courseId.setCellValueFactory(new PropertyValueFactory("courseId"));
        courseId.setCellFactory(TextFieldTableCell.forTableColumn());
        courseId.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<CourseTeaching, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<CourseTeaching, String> t) {
                        ((CourseTeaching) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setCourseId(t.getNewValue());
                        CourseTeaching selectedItem = (CourseTeaching) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCourseTeaching(new CourseTeaching(selectedItem.getId(),selectedItem.getCoachId(),selectedItem.getCoachName(),selectedItem.getCourseId(),selectedItem.getCourseName(),selectedItem.getNumber()));
                    }
                }
        );

        TableColumn<CourseTeaching, String> courseName = new TableColumn("课程名称");
        courseName.setStyle("-fx-alignment: CENTER;");
        courseName.setPrefWidth(170);
        courseName.setCellValueFactory(new PropertyValueFactory("courseName"));
        courseName.setCellFactory(TextFieldTableCell.forTableColumn());
        courseName.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<CourseTeaching, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<CourseTeaching, String> t) {
                        ((CourseTeaching) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setCourseName(t.getNewValue());
                        CourseTeaching selectedItem = (CourseTeaching) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCourseTeaching(new CourseTeaching(selectedItem.getId(),selectedItem.getCoachId(),selectedItem.getCoachName(),selectedItem.getCourseId(),selectedItem.getCourseName(),selectedItem.getNumber()));
                    }
                }
        );

        TableColumn<CourseTeaching, String> number = new TableColumn("节数");
        number.setStyle("-fx-alignment: CENTER;");
        number.setPrefWidth(75);
        number.setCellValueFactory(new PropertyValueFactory("number"));
        number.setCellFactory(TextFieldTableCell.forTableColumn());
        number.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<CourseTeaching, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<CourseTeaching, String> t) {
                        ((CourseTeaching) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setNumber(t.getNewValue());
                        CourseTeaching selectedItem = (CourseTeaching) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCourseTeaching(new CourseTeaching(selectedItem.getId(),selectedItem.getCoachId(),selectedItem.getCoachName(),selectedItem.getCourseId(),selectedItem.getCourseName(),selectedItem.getNumber()));
                    }
                }
        );

        //从数据库获取Course数据
        List<CourseTeaching> CourseTeachings = DataBaseUtils.getAllCourseTeachings();
        //把查询到的Course数据封装到data中
        Iterator<CourseTeaching> iterator = CourseTeachings.iterator();
        while (iterator.hasNext()) {
            rawData.add(iterator.next());
        }
        //给表添加列
        table.getColumns().addAll(id,coachId,coachName,courseId,courseName,number);

        //搜索框监听，当内容为空则显示原始数据
        searchText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (searchText.getText().equals("")) {
                    flushPage(rawData);
                }
            }
        });
        flushPage(rawData);
    }

    private Integer getTotalPageCount(ObservableList<CourseTeaching> data){//求总页数
        return data.size() % pageSize == 0 ? data.size() / pageSize : data.size() / pageSize + 1;
    }

    private TableView createPage(ObservableList<CourseTeaching> data , Integer pageIndex) {//每页展示内容
        int pageStart = pageIndex * pageSize;//每页的起始页码为：当前页码*每页行数
        pageData.clear();//每次都要清空原有内容
        for (int i = pageStart; i < pageStart + pageSize && i < data.size(); i++) {
            pageData.add(data.get(i));
        }
        table.setItems(pageData);
        return table;
    }

    private void flushPage(ObservableList<CourseTeaching> data){ //刷新界面
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
        insertStage.setTitle("添加授课信息");
        insertStage.getIcons().add(new Image("file:///E:\\idea-project\\IdeaProjects\\FitnessClubSystem\\src\\main\\resources\\image\\添加.png"));
        insertStage.setResizable(false);
        AnchorPane pane = new AnchorPane();
        Label idLabel = new Label("编号");
        idLabel.setLayoutX(70);idLabel.setLayoutY(20);
        TextField id = new TextField();
        id.setPromptText("请输入编号");
        id.setPrefWidth(200);id.setPrefHeight(30);
        id.setLayoutX(120);id.setLayoutY(20);

        Label coachIdLabel = new Label("教练工号");
        coachIdLabel.setLayoutX(40);coachIdLabel.setLayoutY(60);
        TextField coachId = new TextField();
        coachId.setPromptText("请输入教练工号");
        coachId.setPrefWidth(200);coachId.setPrefHeight(30);
        coachId.setLayoutX(120);coachId.setLayoutY(60);

        Label coachNameLabel = new Label("教练名称");
        coachNameLabel.setLayoutX(40);coachNameLabel.setLayoutY(100);
        TextField coachName = new TextField();
        coachName.setPromptText("请输入教练名称");
        coachName.setPrefWidth(200);coachName.setPrefHeight(30);
        coachName.setLayoutX(120);coachName.setLayoutY(100);

        Label courseIdLabel = new Label("课程编号");
        courseIdLabel.setLayoutX(40);courseIdLabel.setLayoutY(140);
        TextField courseId = new TextField();
        courseId.setPromptText("请输入课程编号");
        courseId.setPrefWidth(200);courseId.setPrefHeight(30);
        courseId.setLayoutX(120);courseId.setLayoutY(140);

        Label courseNameLabel = new Label("课程名称");
        courseNameLabel.setLayoutX(40);courseNameLabel.setLayoutY(180);
        TextField courseName = new TextField();
        courseName.setPromptText("请输入课程名称");
        courseName.setPrefWidth(200);courseName.setPrefHeight(30);
        courseName.setLayoutX(120);courseName.setLayoutY(180);

        Label numberLabel = new Label("节数");
        numberLabel.setLayoutX(70);numberLabel.setLayoutY(220);
        TextField number = new TextField();
        number.setPromptText("请输入节数");
        number.setPrefWidth(200);number.setPrefHeight(30);
        number.setLayoutX(120);number.setLayoutY(220);

        Button add = new Button("添加");
        add.setStyle("-fx-background-color: rgb(0,152,139);-fx-text-fill: white");
        add.setPrefWidth(100);add.setPrefHeight(30);
        add.setLayoutX(160);add.setLayoutY(260);
        pane.getChildren().addAll(idLabel,id,coachIdLabel,coachId,coachNameLabel,coachName,courseIdLabel,courseId,courseNameLabel,courseName,numberLabel,number,add);
        insertStage.setScene(new Scene(pane, 400,320));
        insertStage.show();
        //给添加绑定事件
        add.setOnAction(event -> {
            if (id.getText().equals("")||coachId.getText().equals("")||coachName.getText().equals("")||courseId.getText().equals("")||courseName.getText().equals("")) {
                new NoticeWindow("提示", "请输入完整内容").show();
                return;
            }
            if(DataBaseUtils.getCourseTeachingById(id.getText())){
                new NoticeWindow("提示", "编号已存在").show();
                return;
            }
            CourseTeaching CourseTeaching = new CourseTeaching(id.getText(),coachId.getText(),coachName.getText(),courseId.getText(),courseName.getText(), number.getText());
            rawData.add(CourseTeaching);
            flushPage(rawData);//刷新界面
            insertStage.close();
            //更新数据库
            if (DataBaseUtils.InsertCourseTeaching(CourseTeaching)) {
                new NoticeWindow("提示", "授课信息添加成功").show();
            } else {
                new NoticeWindow("提示", "授课信息添加失败").show();
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
            List<CourseTeaching> CourseTeachings = DataBaseUtils.FuzzySearchCourseTeachings(searchText.getText());
            Iterator<CourseTeaching> iterator = CourseTeachings.iterator();
            while (iterator.hasNext()) {
                liveData.add(iterator.next());
            }
            flushPage(liveData);//刷新界面
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
        CourseTeaching selectedItem = (CourseTeaching) table.getSelectionModel().getSelectedItem();
        //更新数据库
        if (DataBaseUtils.DeleteCourseTeaching(selectedItem.getId())) {
            new NoticeWindow("提示", "授课信息删除成功").show();
        } else {
            new NoticeWindow("提示", "授课信息删除失败").show();
            return;
        }
        rawData.remove(selectedItem);
        table.getItems().remove(selectedItem);
        flushPage(rawData);//刷新界面
    }

    public void delete_button_OnMouseEntered() {
        delete.setCursor(Cursor.HAND);
    }

    @FXML
    public void close_button_action() {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }

    public void close_button_OnMouseEntered() {
        close_button.setCursor(Cursor.HAND);
    }

}
