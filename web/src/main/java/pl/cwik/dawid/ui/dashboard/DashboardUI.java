package pl.cwik.dawid.ui.dashboard;

import java.util.Locale;

import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.spring.annotation.SpringUI;
import hep.aida.ref.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import pl.cwik.dawid.ui.dashboard.data.DataProvider;
import pl.cwik.dawid.ui.dashboard.data.dummy.DummyDataProvider;
import pl.cwik.dawid.ui.dashboard.domain.User;
import pl.cwik.dawid.ui.dashboard.event.DashboardEventBus;
import pl.cwik.dawid.ui.dashboard.view.LoginView;
import pl.cwik.dawid.ui.dashboard.view.MainView;
import com.vaadin.server.Page;
import com.vaadin.server.Page.BrowserWindowResizeEvent;
import com.vaadin.server.Page.BrowserWindowResizeListener;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import pl.cwik.dawid.ui.dashboard.event.DashboardEvent;
import pl.cwik.dawid.services.TestService;
import pl.cwik.dawid.ui.testProvider.TestProvider;

import javax.servlet.annotation.WebListener;

@Theme("dashboard")
@Widgetset("pl.cwik.dawid.ui.dashboard.DashboardWidgetSet")
@Title("QuickTickets Dashboard")
@SpringUI
@SuppressWarnings("serial")
public final class DashboardUI extends UI {

    /*
     * This field stores an access to the dummy backend layer. In real
     * applications you most likely gain access to your beans trough lookup or
     * injection; and not in the UI but somewhere closer to where they're
     * actually accessed.
     */
    private final DataProvider dataProvider = new DummyDataProvider();
    private final DashboardEventBus dashboardEventbus = new DashboardEventBus();

//    TestProvider testProvider;
//
//    @Required
//    public void setTestProvider(TestProvider testProvider) {
//        this.testProvider = testProvider;
//    }

    @Override
    protected void init(final VaadinRequest request) {
        setLocale(Locale.US);

        DashboardEventBus.register(this);
        Responsive.makeResponsive(this);
        addStyleName(ValoTheme.UI_WITH_MENU);

        updateContent();


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-web.xml");
        TestProvider testProvider = context.getBean(TestProvider.class);
        testProvider.test();

//        testProvider.test();


        // Some views need to be aware of browser resize events so a
        // BrowserResizeEvent gets fired to the event bus on every occasion.
        Page.getCurrent().addBrowserWindowResizeListener(
                new BrowserWindowResizeListener() {
                    @Override
                    public void browserWindowResized(
                            final BrowserWindowResizeEvent event) {
                        DashboardEventBus.post(new DashboardEvent.BrowserResizeEvent());
                    }
                });
    }

    /**
     * Updates the correct content for this UI based on the current user status.
     * If the user is logged in with appropriate privileges, main view is shown.
     * Otherwise login view is shown.
     */
    private void updateContent() {
        User user = (User) VaadinSession.getCurrent()
                .getAttribute(User.class.getName());
        if (user != null && "admin".equals(user.getRole())) {
            // Authenticated user
            setContent(new MainView());
            removeStyleName("loginview");
            getNavigator().navigateTo(getNavigator().getState());
        } else {
            setContent(new LoginView());
            addStyleName("loginview");
        }
    }

    @Subscribe
    public void userLoginRequested(final DashboardEvent.UserLoginRequestedEvent event) {
        User user = getDataProvider().authenticate(event.getUserName(),
                event.getPassword());
        VaadinSession.getCurrent().setAttribute(User.class.getName(), user);
        updateContent();
    }

    @Subscribe
    public void userLoggedOut(final DashboardEvent.UserLoggedOutEvent event) {
        // When the user logs out, current VaadinSession gets closed and the
        // page gets reloaded on the login screen. Do notice the this doesn't
        // invalidate the current HttpSession.
        VaadinSession.getCurrent().close();
        Page.getCurrent().reload();
    }

    @Subscribe
    public void closeOpenWindows(final DashboardEvent.CloseOpenWindowsEvent event) {
        for (Window window : getWindows()) {
            window.close();
        }
    }

    /**
     * @return An instance for accessing the (dummy) services layer.
     */
    public static DataProvider getDataProvider() {
        return ((DashboardUI) getCurrent()).dataProvider;
    }

    public static DashboardEventBus getDashboardEventbus() {
        return ((DashboardUI) getCurrent()).dashboardEventbus;
    }

    @WebListener
    public static class MyContextLoaderListener extends ContextLoaderListener{

    }
}
