package com.sodemed.exceptions;

import java.nio.file.AccessDeniedException;

import javax.naming.AuthenticationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.sodemed.utils.response.ErrorResponse;
import com.sodemed.utils.response.ResponseData;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(NotFoundException ex) {
        return new ResponseEntity<>(
                new ResponseData<>(HttpStatus.NOT_FOUND, new ErrorResponse(ex.getMessage(), "No encontrado")),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotCreateException.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(NotCreateException ex) {
        return new ResponseEntity<>(
                new ResponseData<>(HttpStatus.NOT_ACCEPTABLE, new ErrorResponse(ex.getMessage(), "No válido ")),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(NumberFormatException ex) {
        return new ResponseEntity<>(
                new ResponseData<>(HttpStatus.BAD_REQUEST, new ErrorResponse(ex.getMessage(), "Solicitud errónea")),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(InvalidFormatException ex) {
        return new ResponseEntity<>(
                new ResponseData<>(HttpStatus.BAD_REQUEST, new ErrorResponse(ex.getMessage(), "Solicitud errónea")),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotUpdateException.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(NotUpdateException ex) {
        return new ResponseEntity<>(
                new ResponseData<>(HttpStatus.BAD_REQUEST, new ErrorResponse(ex.getMessage(), "Solicitud errónea")),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(new ResponseData<>(HttpStatus.BAD_REQUEST,
                new ErrorResponse(ex.getMessage(), "Datos recibidos incorrectos")), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(
                new ResponseData<>(HttpStatus.BAD_REQUEST, new ErrorResponse(ex.getMessage(), "Sucedió un error inesperado. ")),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidObject.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(InvalidObject ex) {
        return new ResponseEntity<>(
                new ResponseData<>(HttpStatus.BAD_REQUEST, new ErrorResponse(ex.getMessage(), "Datos recibidos incorrectos")),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(SignatureException ex) {
        return new ResponseEntity<>(
                new ResponseData<>(HttpStatus.BAD_REQUEST, new ErrorResponse(ex.getMessage(), "Token incorrecto")),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(ExpiredJwtException ex) {
        return new ResponseEntity<>(
                new ResponseData<>(HttpStatus.BAD_REQUEST, new ErrorResponse(ex.getMessage(), "Sesión expirada")),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(UsernameNotFoundException ex) {
        return new ResponseEntity<>(
                new ResponseData<>(HttpStatus.BAD_REQUEST, new ErrorResponse(ex.getMessage(), "Token incorrecto")),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(AccessDeniedException ex) {
        return new ResponseEntity<>(
                new ResponseData<>(HttpStatus.FORBIDDEN, new ErrorResponse(ex.getMessage(), "Acceso denegado")),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(AuthenticationException ex) {
        return new ResponseEntity<>(
                new ResponseData<>(HttpStatus.BAD_REQUEST, new ErrorResponse(ex.getMessage(), "Error en la autenticación")),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenNotValid.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(TokenNotValid ex) {
        return new ResponseEntity<>(
                new ResponseData<>(HttpStatus.FORBIDDEN, new ErrorResponse(ex.getMessage(), "Token incorrecto")),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(JwtException ex) {
        return new ResponseEntity<>(
                new ResponseData<>(HttpStatus.FORBIDDEN, new ErrorResponse(ex.getMessage(), "Token incorrecto")),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(BadCredentialsException ex) {
        return new ResponseEntity<>(
                new ResponseData<>(HttpStatus.BAD_REQUEST, new ErrorResponse(ex.getMessage(), "Datos incorrectos")),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(NoResourceFoundException ex) {
        return new ResponseEntity<>(
                new ResponseData<>(HttpStatus.NOT_FOUND, new ErrorResponse(ex.getMessage(), "No encontrado")),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity<>(new ResponseData<>(HttpStatus.METHOD_NOT_ALLOWED,
                new ErrorResponse(ex.getMessage(), "Método de solicitud HTTP no soportado")), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTakeException.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(InvalidTakeException ex) {
        return new ResponseEntity<>(
                new ResponseData<>(HttpStatus.BAD_REQUEST, new ErrorResponse(ex.getMessage(), "INVALID TAKE")),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(IllegalStateException ex) {
        return new ResponseEntity<>(new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR,
                new ErrorResponse("INTERNAL ERROR", "Sucedió un error inesperado")), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserLoginNotValid.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(UserLoginNotValid ex) {
        return new ResponseEntity<>(
                new ResponseData<>(HttpStatus.BAD_REQUEST, new ErrorResponse(ex.getMessage(), "Usuario no autorizado")),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseData<ErrorResponse>> handleException(Exception ex) {
        return new ResponseEntity<>(new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR,
                new ErrorResponse("INTERNAL ERROR", "Sucedió un error inesperado")), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
