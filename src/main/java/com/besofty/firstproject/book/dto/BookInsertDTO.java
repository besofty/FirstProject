package com.besofty.firstproject.book.dto;
import java.time.LocalDateTime;

import com.besofty.firstproject.book.domain.BookDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

@Data
@Accessors(chain = true)
@ApiModel(description = "书籍新增")
public class BookInsertDTO {
    @ApiModelProperty(value = "作者")
    @NotBlank(message = "作者不能为空")
    private String author;

    @ApiModelProperty(value = "书编")
    @NotBlank(message = "书编不能为空")
    private String isbn;

    @ApiModelProperty(value = "标题")
    @NotBlank(message = "标题不能为空")
    private String title;

    @ApiModelProperty(value = "库存")
    @Min(value = 0, message = "库存不能为负数")
    private Integer inventory;

    public static BookDO toDO(BookInsertDTO bookInsertDTO){
        BookDO bookDO= new BookDO();
        bookDO.setAuthor(bookInsertDTO.getAuthor());
        bookDO.setIsbn(bookInsertDTO.getIsbn());
        bookDO.setTitle(bookInsertDTO.getTitle());
        bookDO.setInventory(bookInsertDTO.getInventory());
        bookDO.setCreatedDate(LocalDateTime.now());
        bookDO.setLastModifiedDate(LocalDateTime.now());
        return bookDO;
    }
}
