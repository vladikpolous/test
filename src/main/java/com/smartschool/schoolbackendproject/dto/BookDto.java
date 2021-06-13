package com.smartschool.schoolbackendproject.dto;

import com.smartschool.schoolbackendproject.model.Book;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
public class BookDto {
    private Long idUser;
    private String name;
    private String middleName;
    private String surname;
    private String bookName;
    private Timestamp dateOfTaking;

    public static List<BookDto> fromListBooks(List<Book> books) {
        List<BookDto> bookDtoList = new ArrayList<>();

        for (Book book : books) {
            bookDtoList.add(fromBook(book));
        }

        return bookDtoList;
    }

    public static BookDto fromBook(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setIdUser(book.getUser().getId());
        bookDto.setName(book.getUser().getName());
        bookDto.setMiddleName(book.getUser().getMiddleName());
        bookDto.setSurname(book.getUser().getSurname());
        bookDto.setBookName(book.getBookName());
        bookDto.setDateOfTaking(book.getDateOfTaking());
        return bookDto;
    }
}
