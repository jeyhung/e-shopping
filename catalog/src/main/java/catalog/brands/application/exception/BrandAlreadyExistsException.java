package catalog.brands.application.exception;

public class BrandAlreadyExistsException extends RuntimeException {
    public BrandAlreadyExistsException() {
        super("Brand is already exists!");
    }
}
