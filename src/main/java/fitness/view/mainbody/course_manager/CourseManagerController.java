package fitness.view.mainbody.course_manager;

import fitness.dao.DataBaseUtils;
import fitness.entity.Course;
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

public class CourseManagerController implements Initializable {

    public ImageView close_button;
    public TableView table;
    public Button insert;
    public Button delete;
    public Button search;
    public TextField searchText;
    public Pagination page;
    private ObservableList<Course> rawData = FXCollections.observableArrayList();//原始数据
    private ObservableList<Course> liveData = FXCollections.observableArrayList();//搜索数据
    private ObservableList<Course> pageData = FXCollections.observableArrayList();//每页显示的数据
    private Stage insertStage;
    private Integer pageSize = 13;//每页数据条数
    private Integer totalPageCount;//总页数

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setEditable(true);//设置tableview可编辑
        delete.setDisable(true);//未选中记录，delete不可使用
        TableColumn<Course, String> id = new TableColumn<>("编号");
        id.setStyle("-fx-alignment: CENTER;");
        id.setPrefWidth(75);
        id.setCellValueFactory(new PropertyValueFactory("id"));
        id.setCellFactory(TextFieldTableCell.forTableColumn());//设置具体的列进行单元格编辑
        id.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Course, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Course, String> t) {
                        ((Course) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setId(t.getNewValue());
                    }
                }
        );

        TableColumn<Course, String> name = new TableColumn("课程名");
        name.setStyle("-fx-alignment: CENTER;");
        name.setPrefWidth(150);
        name.setCellValueFactory(new PropertyValueFactory("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Course, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Course, String> t) {
                        ((Course) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setName(t.getNewValue());
                        //更新数据库
                        Course selectedItem = (Course) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCourse(new Course(selectedItem.getId(), selectedItem.getName(), selectedItem.getPlace(), selectedItem.getTeacher(), selectedItem.getDuration(), selectedItem.getStart(), selectedItem.getEnd(), selectedItem.getPrice(), selectedItem.getType(), selectedItem.getLessonType(), selectedItem.getNumber()));
                    }
                }
        );

        TableColumn<Course, String> place = new TableColumn("上课地点");
        place.setStyle("-fx-alignment: CENTER;");
        place.setPrefWidth(100);
        place.setCellValueFactory(new PropertyValueFactory("place"));
        place.setCellFactory(TextFieldTableCell.forTableColumn());
        place.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Course, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Course, String> t) {
                        ((Course) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setPlace(t.getNewValue());
                        //更新数据库
                        Course selectedItem = (Course) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCourse(new Course(selectedItem.getId(), selectedItem.getName(), selectedItem.getPlace(), selectedItem.getTeacher(), selectedItem.getDuration(), selectedItem.getStart(), selectedItem.getEnd(), selectedItem.getPrice(), selectedItem.getType(), selectedItem.getLessonType(), selectedItem.getNumber()));
                    }
                }
        );

        TableColumn<Course, String> teacher = new TableColumn("教练");
        teacher.setStyle("-fx-alignment: CENTER;");
        teacher.setPrefWidth(100);
        teacher.setCellValueFactory(new PropertyValueFactory("teacher"));
        teacher.setCellFactory(TextFieldTableCell.forTableColumn());
        teacher.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Course, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Course, String> t) {
                        ((Course) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setTeacher(t.getNewValue());
                        //更新数据库
                        Course selectedItem = (Course) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCourse(new Course(selectedItem.getId(), selectedItem.getName(), selectedItem.getPlace(), selectedItem.getTeacher(), selectedItem.getDuration(), selectedItem.getStart(), selectedItem.getEnd(), selectedItem.getPrice(), selectedItem.getType(), selectedItem.getLessonType(), selectedItem.getNumber()));
                    }
                }
        );

        TableColumn<Course, String> duration = new TableColumn("时长");
        duration.setStyle("-fx-alignment: CENTER;");
        duration.setPrefWidth(120);
        duration.setCellValueFactory(new PropertyValueFactory("duration"));
        duration.setCellFactory(TextFieldTableCell.forTableColumn());
        duration.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Course, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Course, String> t) {
                        ((Course) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setDuration(t.getNewValue());
                        //更新数据库
                        Course selectedItem = (Course) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCourse(new Course(selectedItem.getId(), selectedItem.getName(), selectedItem.getPlace(), selectedItem.getTeacher(), selectedItem.getDuration(), selectedItem.getStart(), selectedItem.getEnd(), selectedItem.getPrice(), selectedItem.getType(), selectedItem.getLessonType(), selectedItem.getNumber()));
                    }
                }
        );

        TableColumn<Course, String> start = new TableColumn("开始");
        start.setStyle("-fx-alignment: CENTER;");
        start.setPrefWidth(120);
        start.setCellValueFactory(new PropertyValueFactory("start"));
        start.setCellFactory(TextFieldTableCell.forTableColumn());
        start.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Course, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Course, String> t) {
                        ((Course) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setStart(t.getNewValue());
                        //更新数据库
                        Course selectedItem = (Course) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCourse(new Course(selectedItem.getId(), selectedItem.getName(), selectedItem.getPlace(), selectedItem.getTeacher(), selectedItem.getDuration(), selectedItem.getStart(), selectedItem.getEnd(), selectedItem.getPrice(), selectedItem.getType(), selectedItem.getLessonType(), selectedItem.getNumber()));
                    }
                }
        );

        TableColumn<Course, String> end = new TableColumn("结束");
        end.setStyle("-fx-alignment: CENTER;");
        end.setPrefWidth(120);
        end.setCellValueFactory(new PropertyValueFactory("end"));
        end.setCellFactory(TextFieldTableCell.forTableColumn());
        end.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Course, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Course, String> t) {
                        ((Course) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setEnd(t.getNewValue());
                        //更新数据库
                        Course selectedItem = (Course) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCourse(new Course(selectedItem.getId(), selectedItem.getName(), selectedItem.getPlace(), selectedItem.getTeacher(), selectedItem.getDuration(), selectedItem.getStart(), selectedItem.getEnd(), selectedItem.getPrice(), selectedItem.getType(), selectedItem.getLessonType(), selectedItem.getNumber()));
                    }
                }
        );

        TableColumn<Course, String> price = new TableColumn("价格");
        price.setStyle("-fx-alignment: CENTER;");
        price.setPrefWidth(80);
        price.setCellValueFactory(new PropertyValueFactory("price"));
        price.setCellFactory(TextFieldTableCell.forTableColumn());
        price.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Course, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Course, String> t) {
                        ((Course) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setPrice(t.getNewValue());
                        //更新数据库
                        Course selectedItem = (Course) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCourse(new Course(selectedItem.getId(), selectedItem.getName(), selectedItem.getPlace(), selectedItem.getTeacher(), selectedItem.getDuration(), selectedItem.getStart(), selectedItem.getEnd(), selectedItem.getPrice(), selectedItem.getType(), selectedItem.getLessonType(), selectedItem.getNumber()));
                    }
                }
        );

        TableColumn<Course, String> type = new TableColumn("类型");
        type.setStyle("-fx-alignment: CENTER;");
        type.setPrefWidth(85);
        type.setCellValueFactory(new PropertyValueFactory("type"));
        type.setCellFactory(TextFieldTableCell.forTableColumn());
        type.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Course, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Course, String> t) {
                        ((Course) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setType(t.getNewValue());
                        //更新数据库
                        Course selectedItem = (Course) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCourse(new Course(selectedItem.getId(), selectedItem.getName(), selectedItem.getPlace(), selectedItem.getTeacher(), selectedItem.getDuration(), selectedItem.getStart(), selectedItem.getEnd(), selectedItem.getPrice(), selectedItem.getType(), selectedItem.getLessonType(), selectedItem.getNumber()));
                    }
                }
        );

        TableColumn<Course, String> lessonType = new TableColumn("课程类型");
        lessonType.setStyle("-fx-alignment: CENTER;");
        lessonType.setPrefWidth(85);
        lessonType.setCellValueFactory(new PropertyValueFactory("type"));
        lessonType.setCellFactory(TextFieldTableCell.forTableColumn());
        lessonType.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Course, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Course, String> t) {
                        ((Course) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setLessonType(t.getNewValue());
                        //更新数据库
                        Course selectedItem = (Course) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCourse(new Course(selectedItem.getId(), selectedItem.getName(), selectedItem.getPlace(), selectedItem.getTeacher(), selectedItem.getDuration(), selectedItem.getStart(), selectedItem.getEnd(), selectedItem.getPrice(), selectedItem.getType(), selectedItem.getLessonType(), selectedItem.getNumber()));
                    }
                }
        );

        TableColumn<Course, String> number = new TableColumn("参课人数");
        number.setStyle("-fx-alignment: CENTER;");
        number.setPrefWidth(75);
        number.setCellValueFactory(new PropertyValueFactory("number"));
        number.setCellFactory(TextFieldTableCell.forTableColumn());
        number.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Course, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Course, String> t) {
                        ((Course) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setNumber(t.getNewValue());
                        //更新数据库
                        Course selectedItem = (Course) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCourse(new Course(selectedItem.getId(), selectedItem.getName(), selectedItem.getPlace(), selectedItem.getTeacher(), selectedItem.getDuration(), selectedItem.getStart(), selectedItem.getEnd(), selectedItem.getPrice(), selectedItem.getType(), selectedItem.getLessonType(), selectedItem.getNumber()));
                    }
                }
        );

        //从数据库获取Course数据
        List<Course> courses = DataBaseUtils.getAllCourses();
        //把查询到的Course数据封装到data中
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            rawData.add(iterator.next());
        }
        //给表添加列
        table.getColumns().addAll(id, name, place, teacher, duration, start, end, price, type, lessonType, number);

//        搜索框监听，当内容为空则显示原始数据
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

    private Integer getTotalPageCount(ObservableList<Course> data){//求总页数
       return data.size() % pageSize == 0 ? data.size() / pageSize : data.size() / pageSize + 1;
    }

    private TableView createPage(ObservableList<Course> data ,Integer pageIndex) {//每页展示内容
        int pageStart = pageIndex * pageSize;//每页的起始页码为：当前页码*每页行数
        pageData.clear();//每次都要清空原有内容
        for (int i = pageStart; i < pageStart + pageSize && i < data.size(); i++) {
            pageData.add(data.get(i));
        }
        table.setItems(pageData);
        return table;
    }

    private void flushPage(ObservableList<Course> data){ //刷新界面
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

    @FXML
    public void search_button_action() {
        Log.info("search_button_action");
        if (searchText.getText().equals("")) {
            table.setItems(rawData);
        } else {
            liveData.clear(); //每次都清空数据
            List<Course> courses = DataBaseUtils.FuzzySearchCourses(searchText.getText());
            Iterator<Course> iterator = courses.iterator();
            while (iterator.hasNext()) {
                liveData.add(iterator.next());
            }
            flushPage(liveData);
        }
    }

    public void search_button_OnMouseEntered() {
        search.setCursor(Cursor.HAND);
    }

    //添加
    @FXML
    public void insert_button_action() throws Exception {
        Log.info("insert_button_action");
        insertStage = new Stage();
        insertStage.setTitle("添加课程");
        insertStage.getIcons().add(new Image("file:///E:\\idea-project\\IdeaProjects\\FitnessClubSystem\\src\\main\\resources\\image\\添加.png"));
        insertStage.setResizable(false);
        AnchorPane pane = new AnchorPane();
        Label idLabel = new Label("课程编号");
        idLabel.setLayoutX(40);idLabel.setLayoutY(20);
        TextField id = new TextField();
        id.setPromptText("请输入课程编号");
        id.setPrefWidth(200);id.setPrefHeight(30);
        id.setLayoutX(120);id.setLayoutY(20);

        Label nameLabel = new Label("名称");
        nameLabel.setLayoutX(70);nameLabel.setLayoutY(60);
        TextField name = new TextField();
        name.setPromptText("请输入课程名称");
        name.setPrefWidth(200);name.setPrefHeight(30);
        name.setLayoutX(120);name.setLayoutY(60);

        Label placeLabel = new Label("上课地点");
        placeLabel.setLayoutX(40);placeLabel.setLayoutY(100);
        TextField place = new TextField();
        place.setPromptText("请输入上课地点");
        place.setPrefWidth(200);place.setPrefHeight(30);
        place.setLayoutX(120);place.setLayoutY(100);

        Label coachLabel = new Label("课程教练");
        coachLabel.setLayoutX(40);coachLabel.setLayoutY(140);
        TextField coach = new TextField();
        coach.setPromptText("请输入上课教练");
        coach.setPrefWidth(200);coach.setPrefHeight(30);
        coach.setLayoutX(120);coach.setLayoutY(140);

        Label durationLabel = new Label("时长");
        durationLabel.setLayoutX(70);durationLabel.setLayoutY(180);
        TextField duration = new TextField();
        duration.setPromptText("请输入时长");
        duration.setPrefWidth(200);duration.setPrefHeight(30);
        duration.setLayoutX(120);duration.setLayoutY(180);

        Label startLabel = new Label("起始时间");
        startLabel.setLayoutX(40);startLabel.setLayoutY(220);
        TextField start = new TextField();
        start.setPromptText("请输入起始时间");
        start.setPrefWidth(200);start.setPrefHeight(30);
        start.setLayoutX(120);start.setLayoutY(220);

        Label endLabel = new Label("结束时间");
        endLabel.setLayoutX(40);endLabel.setLayoutY(260);
        TextField end = new TextField();
        end.setPromptText("请输入结束时间");
        end.setPrefWidth(200);end.setPrefHeight(30);
        end.setLayoutX(120);end.setLayoutY(260);

        Label priceLabel = new Label("价格");
        priceLabel.setLayoutX(70);priceLabel.setLayoutY(300);
        TextField price = new TextField();
        price.setPromptText("请输入价格");
        price.setPrefWidth(200);price.setPrefHeight(30);
        price.setLayoutX(120);price.setLayoutY(300);

        Label typeLabel = new Label("训练类型");
        typeLabel.setLayoutX(40);typeLabel.setLayoutY(340);
        TextField type = new TextField();
        type.setPromptText("请输入课程训练类型");
        type.setPrefWidth(200);type.setPrefHeight(30);
        type.setLayoutX(120);type.setLayoutY(340);

        Label lessonTypeLabel = new Label("课程类型");
        lessonTypeLabel.setLayoutX(40);lessonTypeLabel.setLayoutY(380);
        TextField lessonType = new TextField();
        lessonType.setPromptText("请输入课程课程类型");
        lessonType.setPrefWidth(200);lessonType.setPrefHeight(30);
        lessonType.setLayoutX(120);lessonType.setLayoutY(380);

        Label numberLabel = new Label("人数");
        numberLabel.setLayoutX(70);numberLabel.setLayoutY(420);
        TextField number = new TextField();
        number.setPromptText("请输入上课人数");
        number.setPrefWidth(200);number.setPrefHeight(30);
        number.setLayoutX(120);number.setLayoutY(420);

        Button add = new Button("添加");
        add.setStyle("-fx-background-color: rgb(0,152,139);-fx-text-fill: white");
        add.setPrefWidth(100);add.setPrefHeight(30);
        add.setLayoutX(160);add.setLayoutY(460);
        pane.getChildren().addAll(idLabel, id, nameLabel, name, placeLabel, place, coachLabel, coach, durationLabel, duration, startLabel, start, endLabel, end, priceLabel, price, typeLabel, type, numberLabel, lessonTypeLabel, lessonType, number, add);
        insertStage.setScene(new Scene(pane, 400, 520));
        insertStage.show();
        //给确认绑定事件
        add.setOnAction(e -> {
            if (id.getText().equals("")) {
                new NoticeWindow("提示", "请输入课程编号").show();
                return;
            }
            if (name.getText().equals("")) {
                new NoticeWindow("提示", "请输入课程名称").show();
                return;
            }
            if (DataBaseUtils.getCourseById(id.getText())) {
                new NoticeWindow("提示", "课程编号已存在").show();
                return;
            }
            Course course = new Course(id.getText(), name.getText(), place.getText(), coach.getText(), duration.getText(), start.getText(), end.getText(), price.getText(), type.getText(), lessonType.getText(), number.getText());
            rawData.add(course);
            flushPage(rawData);
            insertStage.close();
            //更新数据库
            if (DataBaseUtils.InsertCourse(course)) {
                new NoticeWindow("提示", "课程添加成功").show();
            } else {
                new NoticeWindow("提示", "课程添加失败").show();
            }
        });
    }

    public void insert_button_OnMouseEntered() {
        insert.setCursor(Cursor.HAND);
    }

    //删除
    @FXML
    public void delete_button_action() {
        Log.info("delete_button_action");
        //获取表格中选中的行的记录
        Course selectedItem = (Course) table.getSelectionModel().getSelectedItem();
        //更新数据库
        if (DataBaseUtils.DeleteCourse(selectedItem.getId())) {
            new NoticeWindow("提示", "课程删除成功").show();
        } else {
            new NoticeWindow("提示", "课程删除失败").show();
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