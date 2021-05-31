package com.thefisola.usermanagement.dto;

import com.thefisola.usermanagement.constant.CommonConstants;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserQuery implements Serializable {
    private int pageNumber = CommonConstants.DEFAULT_PAGE_NUMBER;
    private int pageSize = CommonConstants.DEFAULT_PAGE_SIZE;
}
