package br.com.mrocha.exception;

public class RecordNotFoundException extends RuntimeException {

    private static final long serialVersion = 1L;

    public RecordNotFoundException(Long id) {
        super("Registro nao encontrado com o id: " + id);
    }
}
