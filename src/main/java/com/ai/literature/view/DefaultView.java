package com.ai.literature.view;


import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class DefaultView extends VerticalLayout {
    public DefaultView() {
        add(new Text("This is a simple view."));
    }
}
