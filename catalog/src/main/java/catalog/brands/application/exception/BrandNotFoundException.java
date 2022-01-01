package catalog.brands.application.exception;

public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException() {
        super("Brand not found!");
    }
}
