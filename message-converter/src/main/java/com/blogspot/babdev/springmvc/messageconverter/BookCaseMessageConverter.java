package com.blogspot.babdev.springmvc.messageconverter;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class BookCaseMessageConverter extends AbstractHttpMessageConverter<BookCase> {

    public BookCaseMessageConverter() {
    }

    public BookCaseMessageConverter(MediaType supportedMediaType) {
        super(supportedMediaType);
    }

    public BookCaseMessageConverter(MediaType... supportedMediaTypes) {
        super(supportedMediaTypes);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return BookCase.class.equals(clazz);
    }

    @Override
    protected BookCase readInternal(Class<? extends BookCase> clazz, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        CSVReader reader = new CSVReader(new InputStreamReader(httpInputMessage.getBody()));
        List<String[]> rows = reader.readAll();
        BookCase bookCase = new BookCase();
        for (String[] row : rows) {
            bookCase.add(new Book(row[0], row[1]));
        }
        return bookCase;
    }

    @Override
    protected void writeInternal(BookCase books, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        CSVWriter writer = new CSVWriter(new OutputStreamWriter(httpOutputMessage.getBody()));
        for (Book book : books) {
            writer.writeNext(new String[]{book.getIsbn(), book.getTitle()});
        }
        writer.close();
    }
}
