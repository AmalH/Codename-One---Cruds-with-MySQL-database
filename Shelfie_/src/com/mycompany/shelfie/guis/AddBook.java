package com.mycompany.shelfie.guis;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.shelfie.daos.BookDAO;
import com.mycompany.shelfie.entities.Book;
import com.mycompany.shelfie.utils.Statics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amal
 */
public class AddBook extends Form {
    
    private final Label l1,l2,l3,l4,l5;
    private final TextField titleTf,authorTf,categoryTf,isbnTf;
    private final Container mainContainer;
    private final Button addBtn,backBtn;
    
   public AddBook(){
       
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("Add a new book");
        l1.setAlignment(CENTER);
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("Title:");
        titleTf = new TextField(""); 
        l3 = new Label("Author:");
        authorTf = new TextField("");
        l4 = new Label("Category:");
        categoryTf= new TextField("");
        l5 = new Label("ISBN:");
        isbnTf= new TextField("");
        addBtn= new Button("Add");
        addBtn.getUnselectedStyle().setFgColor(5542241);
        mainContainer.add(l1);
        mainContainer.add(new Label());
        Statics.setLabelStyle(l2);
        mainContainer.add(l2);
        Statics.setLabelStyle(l3);
        mainContainer.add(l3);
        mainContainer.add(titleTf);
        mainContainer.add(authorTf);
        Statics.setLabelStyle(l4);
        mainContainer.add(l4);
        Statics.setLabelStyle(l5);
        mainContainer.add(l5);
        mainContainer.add(categoryTf);
        mainContainer.add(isbnTf);
        mainContainer.add(addBtn);
        backBtn = Statics.createBackBtn(); 
        mainContainer.add(backBtn);
        addBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            // add a book
            Book typedBook = new Book(authorTf.getText(),titleTf.getText(),categoryTf.getText(),Integer.parseInt(isbnTf.getText()));
            new  BookDAO().addBook(typedBook);
            });
        this.add(BorderLayout.NORTH, mainContainer);
   }
    
}
