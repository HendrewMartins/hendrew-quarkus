package br.hendrew.quarkus.exception;

public class MenssageNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public MenssageNotFoundException(String message) {
        super(message);
    }
}
