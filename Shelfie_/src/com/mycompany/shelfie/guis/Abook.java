/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Amal
 */
public class Abook extends Form {
    
    private final Label l1,l2,l3,l4,l5;
    private final TextField titleTf,authorTf,categoryTf,isbnTf;
    private final Container mainContainer;
    private final Button editBtn,removeBtn,backBtn;
    private Book currentBook;
    
    public Abook(String title,String author,String category,int isbn){
        
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("Book "+isbn);
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("Title:");
        titleTf = new TextField(title); 
        l3 = new Label("Author:");
        authorTf = new TextField(author);
        l4 = new Label("Category:");
        categoryTf= new TextField(category);
        l5 = new Label("ISBN:");
        isbnTf= new TextField(isbn);
        editBtn= new Button("Edit");
        editBtn.getUnselectedStyle().setFgColor(5542241);
        removeBtn= new Button("Remove");
        removeBtn.getUnselectedStyle().setFgColor(5542241);
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
        mainContainer.add(editBtn);
        mainContainer.add(removeBtn);
        backBtn = Statics.createBackBtn(); 
        mainContainer.add(backBtn);
        currentBook = new Book(title, author, category, isbn);
        editBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            new BookDAO().updateBook(currentBook);
            });
        removeBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new BookDAO().removeBook(currentBook);
            }
        });
        this.add(BorderLayout.NORTH, mainContainer);
    }
}
