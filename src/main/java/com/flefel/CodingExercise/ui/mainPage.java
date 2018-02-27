package com.flefel.CodingExercise.ui;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;

public class mainPage extends UI {
    @Override
    public void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        setContent(layout);
        layout.addComponent(new Label("Hello, world!"));
    }
    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = mainPage.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}