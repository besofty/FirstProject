package com.besofty.firstproject.common.dto;

import com.besofty.firstproject.common.enums.JsonCodeEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import java.io.Serializable;

@Slf4j
@Data
@Accessors(chain = true)
public class JsonResp<T> implements Serializable {
    /**
     * 代码消息
     */
    private JsonCodeEnum code;
    /**
     * 数据
     */
    private T data;
    /**
     * 消息
     */
    private String msg;

    private boolean success = false;

    public static <E> JsonResp<E> ok(E data){
        return new JsonResp<E>()
                .setCode(JsonCodeEnum.SUCCESS)
                .setData(data);
    }

    public static JsonResp fa(String message) {
        JsonResp json = new JsonResp();
        json.setCode(JsonCodeEnum.FAIL);
        json.setMsg(message);
        return json;
    }

    public static JsonResp dataPage(Page page){
        return JsonResp.ok(new PageDTO(page.getNumber(), page.getSize(), page.getContent(), page.getTotalElements()));
    }
}