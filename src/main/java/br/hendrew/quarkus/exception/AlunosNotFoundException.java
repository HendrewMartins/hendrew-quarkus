package br.hendrew.quarkus.exception;

public class AlunosNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public AlunosNotFoundException(String message) {
        super(message);
    }
}
