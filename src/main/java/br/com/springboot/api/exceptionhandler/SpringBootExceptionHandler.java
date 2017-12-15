package br.com.springboot.api.exceptionhandler;

import br.com.springboot.api.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class SpringBootExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    private String getMsgUser(final String paramMessage) {
        return messageSource.getMessage(paramMessage, null, LocaleContextHolder.getLocale());
    }

    private List<Error> createListErrors(final BindingResult bindingResult) {
        final List<Error> errors = new ArrayList<>();
        bindingResult.getFieldErrors().stream().forEach(fe -> errors.add(new Error(messageSource.getMessage(fe, LocaleContextHolder.getLocale()), fe.toString())));
        return errors;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final String msgUser = getMsgUser("parametros.invalidos");
        final String msgDev = StringUtils.isEmpty(ex.getCause()) ? ex.toString() : ex.getCause().toString();
        final List<Error> errors = Arrays.asList(new Error(msgUser, msgDev));
        return super.handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, createListErrors(ex.getBindingResult()), headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(final EmptyResultDataAccessException ex, final WebRequest request) {
        final String msgUser = getMsgUser("recurso.nao.encontrado");
        final String msgDev = ex.toString();
        final List<Error> errors = Arrays.asList(new Error(msgUser, msgDev));
        return super.handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
