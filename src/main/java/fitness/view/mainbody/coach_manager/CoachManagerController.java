package fitness.view.mainbody.coach_manager;

import fitness.dao.DataBaseUtils;
import fitness.entity.Coach;
import fitness.entity.Course;
import fitness.entity.Member;
import fitness.utils.Log;
import fitness.utils.NoticeWindow;
import fitness.view.mainbody.coach_manager.course_teaching.CourseTeachingScene;
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

public class CoachManagerController implements Initializable {
    public ImageView close_button;
    public TableView table;
    public Button search;
    public Button insert;
    public Button delete;
    public TextField searchText;
    public Pagination page;
    public Button course;
    //用于实现查询的筛选操作,监听搜索框实时更新liveData
    private ObservableList<Coach> rawData = FXCollections.observableArrayList();//原始数据
    private ObservableList<Coach> liveData = FXCollections.observableArrayList();//实时数据
    private ObservableList<Coach> pageData = FXCollections.observableArrayList();//每页显示的数据
    private Stage insertStage;
    private Integer pageSize = 8;//每页数据条数
    private Integer totalPageCount;//总页数
    private String headPicturePath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setEditable(true);//设置tableview可编辑
        delete.setDisable(true);//未选中记录，delete不可使用
        TableColumn<Coach, String> id = new TableColumn<>("工号");
        id.setStyle("-fx-alignment: CENTER;");//设置内容居中
        id.setPrefWidth(75);
        id.setCellValueFactory(new PropertyValueFactory("id"));
        id.setCellFactory(TextFieldTableCell.forTableColumn());//设置具体的列进行单元格编辑
        id.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Coach, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Coach, String> t) {
                        ((Coach) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setId(t.getNewValue());
                    }
                }
        );

        TableColumn<Coach, String> name = new TableColumn("姓名");
        name.setStyle("-fx-alignment: CENTER;");
        name.setPrefWidth(100);
        name.setCellValueFactory(new PropertyValueFactory("name"));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Coach, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Coach, String> t) {
                        ((Coach) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setName(t.getNewValue());
                        //更新数据库
                        Coach selectedItem = (Coach) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCoach(new Coach(selectedItem.getId(), selectedItem.getName(), selectedItem.getSex(), selectedItem.getAge(), selectedItem.getContact(), selectedItem.getLevel(), selectedItem.getPosts(), selectedItem.getHeadPicture()));
                    }
                }
        );

        TableColumn<Coach, String> sex = new TableColumn("性别");
        sex.setStyle("-fx-alignment: CENTER;");
        sex.setPrefWidth(100);
        sex.setCellValueFactory(new PropertyValueFactory("sex"));
        sex.setCellFactory(TextFieldTableCell.forTableColumn());
        sex.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Coach, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Coach, String> t) {
                        ((Coach) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setSex(t.getNewValue());
                        //更新数据库
                        Coach selectedItem = (Coach) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCoach(new Coach(selectedItem.getId(), selectedItem.getName(), selectedItem.getSex(), selectedItem.getAge(), selectedItem.getContact(), selectedItem.getLevel(), selectedItem.getPosts(), selectedItem.getHeadPicture()));
                    }
                }
        );

        TableColumn<Coach, String> age = new TableColumn("年龄");
        age.setStyle("-fx-alignment: CENTER;");
        age.setPrefWidth(100);
        age.setCellValueFactory(new PropertyValueFactory("age"));
        age.setCellFactory(TextFieldTableCell.forTableColumn());
        age.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Coach, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Coach, String> t) {
                        ((Coach) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setAge(t.getNewValue());
                        //更新数据库
                        Coach selectedItem = (Coach) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCoach(new Coach(selectedItem.getId(), selectedItem.getName(), selectedItem.getSex(), selectedItem.getAge(), selectedItem.getContact(), selectedItem.getLevel(), selectedItem.getPosts(), selectedItem.getHeadPicture()));
                    }
                }
        );

        TableColumn<Coach, String> contact = new TableColumn("电话号码");
        contact.setStyle("-fx-alignment: CENTER;");
        contact.setPrefWidth(185);
        contact.setCellValueFactory(new PropertyValueFactory("contact"));
        contact.setCellFactory(TextFieldTableCell.forTableColumn());
        contact.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Coach, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Coach, String> t) {
                        ((Coach) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setContact(t.getNewValue());
                        //更新数据库
                        Coach selectedItem = (Coach) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCoach(new Coach(selectedItem.getId(), selectedItem.getName(), selectedItem.getSex(), selectedItem.getAge(), selectedItem.getContact(), selectedItem.getLevel(), selectedItem.getPosts(), selectedItem.getHeadPicture()));
                    }
                }
        );

        TableColumn<Coach, String> level = new TableColumn("级别");
        level.setStyle("-fx-alignment: CENTER;");
        level.setPrefWidth(100);
        level.setCellValueFactory(new PropertyValueFactory("level"));
        level.setCellFactory(TextFieldTableCell.forTableColumn());
        level.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Coach, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Coach, String> t) {
                        ((Coach) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setLevel(t.getNewValue());
                        //更新数据库
                        Coach selectedItem = (Coach) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCoach(new Coach(selectedItem.getId(), selectedItem.getName(), selectedItem.getSex(), selectedItem.getAge(), selectedItem.getContact(), selectedItem.getLevel(), selectedItem.getPosts(), selectedItem.getHeadPicture()));
                    }
                }
        );

        TableColumn<Coach, String> posts = new TableColumn("职位");
        posts.setStyle("-fx-alignment: CENTER;");
        posts.setPrefWidth(100);
        posts.setCellValueFactory(new PropertyValueFactory("posts"));
        posts.setCellFactory(TextFieldTableCell.forTableColumn());
        posts.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Coach, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Coach, String> t) {
                        ((Coach) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setPosts(t.getNewValue());
                        //更新数据库
                        Coach selectedItem = (Coach) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCoach(new Coach(selectedItem.getId(), selectedItem.getName(), selectedItem.getSex(), selectedItem.getAge(), selectedItem.getContact(), selectedItem.getLevel(), selectedItem.getPosts(), selectedItem.getHeadPicture()));
                    }
                }
        );

        TableColumn<Coach, ImageView> headPicture = new TableColumn("头像");
        headPicture.setStyle("-fx-alignment: CENTER;");
        headPicture.setPrefWidth(185);
        headPicture.setCellValueFactory(new PropertyValueFactory("headPicture"));
//        headPicture.setCellFactory(TextFieldTableCell.forTableColumn());
        headPicture.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Coach, ImageView>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Coach, ImageView> t) {
                        ((Coach) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setHeadPicture(t.getNewValue());
                        //更新数据库
                        Coach selectedItem = (Coach) table.getSelectionModel().getSelectedItem();
                        DataBaseUtils.UpdateCoach(new Coach(selectedItem.getId(), selectedItem.getName(), selectedItem.getSex(), selectedItem.getAge(), selectedItem.getContact(), selectedItem.getLevel(), selectedItem.getPosts(), selectedItem.getHeadPicture()));
                    }
                }
        );

        //从数据库获取Course数据
        List<Coach> coaches = DataBaseUtils.getAllCoaches();
        //把查询到的Course数据封装到data中
        Iterator<Coach> iterator = coaches.iterator();
        while (iterator.hasNext()) {
            rawData.add(iterator.next());
        }
        //给表添加列
        table.getColumns().addAll(id, name, sex, age, contact, level, posts, headPicture);

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

    private Integer getTotalPageCount(ObservableList<Coach> data){//求总页数
        return data.size() % pageSize == 0 ? data.size() / pageSize : data.size() / pageSize + 1;
    }

    private TableView createPage(ObservableList<Coach> data , Integer pageIndex) {//每页展示内容
        int pageStart = pageIndex * pageSize;//每页的起始页码为：当前页码*每页行数
        pageData.clear();//每次都要清空原有内容
        for (int i = pageStart; i < pageStart + pageSize && i < data.size(); i++) {
            pageData.add(data.get(i));
        }
        table.setItems(pageData);
        return table;
    }

    private void flushPage(ObservableList<Coach> data){ //刷新界面
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
        insertStage.setTitle("添加教练");
        insertStage.getIcons().add(new Image("file:///E:\\idea-project\\IdeaProjects\\FitnessClubSystem\\src\\main\\resources\\image\\添加.png"));
        insertStage.setResizable(false);
        AnchorPane pane = new AnchorPane();
        Label idLabel = new Label("工号");
        idLabel.setLayoutX(64);idLabel.setLayoutY(45);
        TextField id = new TextField();
        id.setPromptText("请输入工号");
        id.setPrefWidth(250);id.setPrefHeight(30);
        id.setLayoutX(123);id.setLayoutY(40);

        Label nameLabel = new Label("姓名");
        nameLabel.setLayoutX(64);nameLabel.setLayoutY(85);
        TextField name = new TextField();
        name.setPromptText("请输入姓名");
        name.setPrefWidth(250);name.setPrefHeight(30);
        name.setLayoutX(123);name.setLayoutY(80);

        Label sexLabel = new Label("性别");
        sexLabel.setLayoutX(64);sexLabel.setLayoutY(125);
        ToggleGroup sex = new ToggleGroup();
        RadioButton male = new RadioButton("男");
        male.setLayoutX(132);male.setLayoutY(124);
        RadioButton female = new RadioButton("女");
        female.setLayoutX(232);female.setLayoutY(124);
        //将两个单选放在同一个group中实现互斥
        male.setToggleGroup(sex);
        female.setToggleGroup(sex);


        Label ageLabel = new Label("年龄");
        ageLabel.setLayoutX(64);ageLabel.setLayoutY(165);
        TextField age = new TextField();
        age.setPromptText("请输入年龄");
        age.setPrefWidth(250);age.setPrefHeight(30);
        age.setLayoutX(123);age.setLayoutY(160);

        Label contactLabel = new Label("联系方式");
        contactLabel.setLayoutX(34);contactLabel.setLayoutY(205);
        TextField contact = new TextField();
        contact.setPromptText("请输入联系方式");
        contact.setPrefWidth(250);contact.setPrefHeight(30);
        contact.setLayoutX(123);contact.setLayoutY(200);

        Label levelLabel = new Label("等级");
        levelLabel.setLayoutX(64);levelLabel.setLayoutY(245);
        TextField level = new TextField();
        level.setPromptText("请输入等级");
        level.setPrefWidth(250);level.setPrefHeight(30);
        level.setLayoutX(123);level.setLayoutY(240);

        Label postsLabel = new Label("职业");
        postsLabel.setLayoutX(64);postsLabel.setLayoutY(285);
        TextField posts = new TextField();
        posts.setPromptText("请输入职业");
        posts.setPrefWidth(250);posts.setPrefHeight(30);
        posts.setLayoutX(123);posts.setLayoutY(280);

        ImageView headPicture = new ImageView(new Image("file:///E:\\idea-project\\IdeaProjects\\FitnessClubSystem\\src\\main\\resources\\headpicture\\默认.jpeg"));
        headPicture.setFitWidth(132);headPicture.setFitHeight(150);
        headPicture.setLayoutX(422);headPicture.setLayoutY(44);
        Button select = new Button("选择头像");
        select.setStyle("-fx-background-color: rgb(0,152,139);-fx-text-fill: white");
        select.setLayoutX(450);select.setLayoutY(200);

        Button add = new Button("添加");
        add.setStyle("-fx-background-color: rgb(0,152,139);-fx-text-fill: white");
        add.setPrefWidth(115);add.setPrefHeight(30);
        add.setLayoutX(191);add.setLayoutY(324);
        pane.getChildren().addAll(idLabel, id, nameLabel, name, sexLabel, male,female, ageLabel, age, contactLabel, contact, levelLabel, level, postsLabel, posts, headPicture, select, add);
        insertStage.setScene(new Scene(pane, 600, 380));
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
                headPicturePath = file.getCanonicalPath();//获取打开文件的绝对路径
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //更新头像显示
            headPicture.setImage(new Image("file:///" + headPicturePath));
        });
        //给添加绑定事件
        add.setOnAction(event -> {
            if (id.getText().equals("")) {
                new NoticeWindow("提示", "请输入教练工号").show();
                return;
            }
            if (name.getText().equals("")) {
                new NoticeWindow("提示", "请输入教练姓名").show();
                return;
            }
            if(DataBaseUtils.getCoachById(id.getText())){
                new NoticeWindow("提示", "教练工号已存在").show();
                return;
            }
            RadioButton selectedToggle = (RadioButton)sex.getSelectedToggle();
            Coach coach = new Coach(id.getText(), name.getText(), selectedToggle.getText(), age.getText(), contact.getText(), level.getText(), posts.getText(), new ImageView(new Image("file:///" + headPicturePath)));
            coach.initImageSize();
            rawData.add(coach);
            flushPage(rawData);//刷新界面
            insertStage.close();//关闭界面
            //更新数据库
            if (DataBaseUtils.InsertCoach(coach)) {
                new NoticeWindow("提示", "教练添加成功").show();
            } else {
                new NoticeWindow("提示", "教练添加失败").show();
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
            List<Coach> coaches = DataBaseUtils.FuzzySearchCoaches(searchText.getText());
            Iterator<Coach> iterator = coaches.iterator();
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
        Coach selectedItem = (Coach) table.getSelectionModel().getSelectedItem();
        //更新数据库
        if (DataBaseUtils.DeleteCoach(selectedItem.getId())) {
            new NoticeWindow("提示", "教练信息删除成功").show();
        } else {
            new NoticeWindow("提示", "教练信息删除失败").show();
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
    public void course_button_action() throws Exception {
        Log.info("course_button_action");
        CourseTeachingScene courseTeachingScene = new CourseTeachingScene();
        courseTeachingScene.start(new Stage());
    }

    public void course_button_OnMouseEntered() {
        close_button.setCursor(Cursor.HAND);
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
