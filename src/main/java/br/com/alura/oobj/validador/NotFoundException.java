package br.com.alura.oobj.validador;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String mesage) {
        super(mesage);
    }
}
