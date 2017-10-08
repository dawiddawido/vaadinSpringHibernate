//package pl.cwik.dawid.ui;
//
//import com.vaadin.annotations.Theme;
//import com.vaadin.server.VaadinRequest;
//import com.vaadin.spring.annotation.SpringUI;
//import com.vaadin.ui.*;
//import org.springframework.web.context.ContextLoaderListener;
//
//import javax.servlet.annotation.WebListener;
//import javax.xml.soap.Text;
//
///**
// * Created by Dawid on 25.09.2017.
// */
////@Theme("valo")
////@SpringUI
//public class MainUI extends UI {
//
//    private VerticalLayout viewContainer;
//
//    @Override
//    protected void init(VaadinRequest vaadinRequest) {
//
//        getPage().setTitle("eControl");
//
//        VerticalLayout generalLayout = createUILayout();
//
//        setContent(generalLayout);
//    }
//
//    private VerticalLayout createUILayout() {
//        VerticalLayout generalLayout = new VerticalLayout();
//        //LAYOUT MENU
//        Layout menuLayout = createMenu();
//
//        //LAYOUT VIEW
//        Layout viewLayout = createView();
//
//        generalLayout.addComponent(menuLayout);
//        generalLayout.addComponent(viewLayout);
//
//        generalLayout.setExpandRatio(menuLayout, 2);
//        generalLayout.setExpandRatio(viewLayout, 8);
//
//        return generalLayout;
//    }
//
//    private Layout createView() {
//        //GENERAL LAYOUT
//        viewContainer = new VerticalLayout();
//        viewContainer.addStyleName("view-container");
//        TextArea textArea = new TextArea("text");
//        textArea.setRows(10);
//        textArea.setSizeFull();
//        textArea.addValueChangeListener(event -> Notification.show("Value changed: ",
//                String.valueOf(event.getValue()), Notification.Type.TRAY_NOTIFICATION));
//        viewContainer.addComponent(textArea);
//        return viewContainer;
//    }
//
//    private Layout createMenu() {
//        VerticalLayout verticalLayout = new VerticalLayout();
//        verticalLayout.setMargin(true);
//        verticalLayout.setWidth("250px");
//
//        verticalLayout.addComponent(new Button("Menu"));
//        verticalLayout.addComponent(new Button("Menu2"));
//        verticalLayout.addComponent(new Button("Menu3"));
//
//        return verticalLayout;
//    }
//
////    @WebListener
////    public static class MyContextLoaderListener extends ContextLoaderListener {
////
////    }
//}
