package com.besofty.firstproject.book;

import com.besofty.firstproject.book.domain.BookDO;
import com.besofty.firstproject.book.domain.QBookDO;
import com.besofty.firstproject.book.dto.BookDTO;
import com.besofty.firstproject.book.dto.BookInsertDTO;
import com.besofty.firstproject.book.dto.BookQueryDTO;
import com.besofty.firstproject.book.enums.BookErrorEnum;
import com.besofty.firstproject.book.repository.BookRepository;
import com.besofty.firstproject.exception.MicroserviceClientException;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookDTO addBook(BookInsertDTO bookInsertDTO){
        if (getBookByIsbn(bookInsertDTO.getIsbn()).isPresent()){
            throw new MicroserviceClientException(BookErrorEnum.ISBN_EXISTED.getMessage());
        }
        BookDO bookDO = bookRepository.save(BookInsertDTO.toDO(bookInsertDTO));
        return BookDTO.fromDO(bookDO);
    }

    private Optional<BookDO> getBookByIsbn(String isbn){
        BooleanExpression booleanExpression = QBookDO.bookDO.isbn.eq(isbn);
        return Optional.ofNullable(bookRepository.findOne(booleanExpression));
    }

    public Page<BookDTO> queryBooks(Pageable pageable, BookQueryDTO bookQueryDTO){
        BooleanBuilder booleanBuilder = generateQueryBooleanBuilder(bookQueryDTO);
        return bookRepository.findAll(booleanBuilder, pageable)
                .map(BookDTO::fromDO);
    }

    private BooleanBuilder generateQueryBooleanBuilder(BookQueryDTO bookQueryDTO) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (StringUtils.isNotBlank(bookQueryDTO.getIsbn())){
            booleanBuilder.and(QBookDO.bookDO.isbn.contains(bookQueryDTO.getIsbn()));
        }
        if (StringUtils.isNotBlank(bookQueryDTO.getTitle())){
            booleanBuilder.and(QBookDO.bookDO.title.contains(bookQueryDTO.getTitle()));
        }
        if (StringUtils.isNotBlank(bookQueryDTO.getAuthor())){
            booleanBuilder.and(QBookDO.bookDO.author.contains(bookQueryDTO.getAuthor()));
        }
        return booleanBuilder;
    }
}
