package com.example.SendEmailApp.exceptions;

import com.example.SendEmailApp.shared.utils.codes.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceException extends Exception{

    protected ErrorCode errorCode;
    protected String message;
}
