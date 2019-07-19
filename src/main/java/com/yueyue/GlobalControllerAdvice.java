package com.yueyue;

import com.yueyue.exceptions.BadRequestException;
import com.yueyue.exceptions.ConflictException;
import com.yueyue.exceptions.ForbiddenException;
import com.yueyue.exceptions.NotFoundException;
import com.yueyue.model.ExceptionJsonInfo;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public
    @ResponseBody
    ExceptionJsonInfo handleNotFound(NotFoundException e) {
        return new ExceptionJsonInfo(e.getMessage());
    }

    @ExceptionHandler(org.springframework.dao.DataAccessException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public
    @ResponseBody
    ExceptionJsonInfo dataError(DataAccessException e) {
        return new ExceptionJsonInfo("Service Unavailable");
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    ExceptionJsonInfo badRequest(BadRequestException e) {
        return new ExceptionJsonInfo(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    ExceptionJsonInfo illegalArgument(IllegalArgumentException e) {
        return new ExceptionJsonInfo("Illegal Argument Exception");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    ExceptionJsonInfo missingParam(Exception e) {
        return new ExceptionJsonInfo("Missing Servelt Parmeter");
    }


    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public
    @ResponseBody
    ExceptionJsonInfo forbidden(ForbiddenException e) {
        return new ExceptionJsonInfo(e.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public
    @ResponseBody
    ExceptionJsonInfo conflict(ConflictException e) {
        return new ExceptionJsonInfo(e.getMessage());
    }

}
