package com.thefisola.usermanagement.dto;

import com.thefisola.usermanagement.constant.CommonConstant;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserQuery implements Serializable {
    private int pageNumber = CommonConstant.DEFAULT_PAGE_NUMBER;
    private int pageSize = CommonConstant.DEFAULT_PAGE_SIZE;
}
