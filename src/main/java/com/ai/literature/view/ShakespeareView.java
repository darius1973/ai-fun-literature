package com.ai.literature.view;


import com.ai.literature.text.generator.TextGenerator;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.Image;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Nothing really interesting here, this is just ui view ...
 */
@Route("shakespeare-view")
public class ShakespeareView extends HorizontalLayout {


    public ShakespeareView(@Autowired TextGenerator textGenerator) {

        // Create input text boxes
        TextField input1 = new TextField("Starting word");
        TextField input2 = new TextField("Total number of words");
        input2.setValue("1000");
        input2.setMaxLength(1000);
        HorizontalLayout inputLayout = new HorizontalLayout(input1, input2);
        // Create the generate button
        Button generateButton = new Button("Generate");

        // Create a large text area to display the generated text
        TextArea outputText = new TextArea("Shakespeare says:");
        outputText.setWidth("600px");
        outputText.setHeight("500px");
        VerticalLayout verticalLayout = new VerticalLayout(inputLayout, generateButton, outputText);
        // Create an Image component for the Shakespeare photo
        Image shakespeareImage = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Shakespeare.jpg/800px-Shakespeare.jpg", "William Shakespeare");
        shakespeareImage.setWidth("1000px");
        shakespeareImage.setHeight("1000px");

        // Add the components to the layout
        add(shakespeareImage, verticalLayout);
        // Add click listener to the button
        generateButton.addClickListener(e ->
                outputText.setValue(textGenerator.generateSentence(input1.getValue(), Integer.parseInt(input2.getValue()))));
    }
}
