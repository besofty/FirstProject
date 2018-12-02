package com.besofty.firstproject.book.dto;

import com.besofty.firstproject.book.domain.BookDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "书籍")
public class BookDTO {
    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "书编")
    private String isbn;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "库存")
    private Integer inventory;

    public static BookDTO fromDO(BookDO bookDO){
        BookDTO bookDTO= new BookDTO();
        bookDTO.setAuthor(bookDO.getAuthor());
        bookDTO.setIsbn(bookDO.getIsbn());
        bookDTO.setTitle(bookDO.getTitle());
        bookDTO.setInventory(bookDO.getInventory());
        return bookDTO;
    }
}
