/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shelfie.daos;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.shelfie.entities.Book;
import com.mycompany.shelfie.guis.Abook;
import com.mycompany.shelfie.utils.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Amal
 */
public class BookDAO {
        
    private ConnectionRequest connectionRequest;
    public static Form listOfBooks;
    public void addBook(Book book){
        connectionRequest=new ConnectionRequest(){
            @Override
            protected void postResponse() {
            Dialog d = new Dialog("Add to my book shelf");
            TextArea popupBody = new TextArea("Book successfully added");
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, popupBody);
            d.showDialog();
            }
        };
        connectionRequest.setUrl("http://localhost/shelfie/insert.php?title=" + book.getTitle() + "&author=" + book.getAuthor()+"&category="+book.getCategory()+"&isbn="+book.getIsbn());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    public void removeBook(Book b){   // remove book by title
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
            Dialog d = new Dialog("Remove book from database");
            TextArea popupBody = new TextArea("Book successfully removed");
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, popupBody);
            d.showDialog();
            }           
        };
        connectionRequest.setUrl("http://localhost/shelfie/remove.php?title=" + b.getTitle());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    public void findAllBooks(){
        connectionRequest = new ConnectionRequest() {
        List<Book> books = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    books.clear();
                  
                    for (Map<String, Object> obj : content) {
                        books.add(new Book((String) obj.get("title"),(String) obj.get("author"),(String) obj.get("category"),Integer.parseInt((String) obj.get("isbn")))
                        );
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                listOfBooks = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Book l :books){
                    libsNoms.add(l.getTitle());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Book currentBook = books.get(uiLibsList.getCurrentSelected());
                        new Abook(currentBook.getTitle(),currentBook.getAuthor(),currentBook.getCategory(),currentBook.getIsbn()).show();
                    }
                });
                listOfBooks.setLayout(new BorderLayout());
                listOfBooks.add(BorderLayout.NORTH,uiLibsList);
                listOfBooks.add(BorderLayout.SOUTH,Statics.createBackBtn());
                listOfBooks.show();             
            }
        };
        connectionRequest.setUrl("http://localhost/shelfie/getbooks.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    public void updateBook(Book b){
        connectionRequest = new ConnectionRequest() {
            
            @Override
            protected void postResponse() { 
                Dialog d = new Dialog("Popup Title");
                TextArea popupBody = new TextArea("Book updated");
                popupBody.setUIID("PopupBody");
                popupBody.setEditable(false);
                d.setLayout(new BorderLayout());
                d.add(BorderLayout.CENTER, popupBody);
                d.show();
            }
        };
        connectionRequest.setUrl("http://localhost/shelfie/update.php?title="+b.getTitle()+"&author="+b.getAuthor()+
                                "&category="+b.getCategory()+"&isbn="+b.getIsbn()+"&id=3");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    
}
