package cache;

public class OptimisticException extends RuntimeException {

    private int existVersion;
    private int updatedVersion;
    private String message;

    public OptimisticException(String message) {
        super(message);
    }

    public OptimisticException(int existVersion, int updatedVersion) {
        this.existVersion = existVersion;
        this.updatedVersion = updatedVersion;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String toString() {
        return "OptimisticException{"
                + "existVersion=" + existVersion
                + ", newVersion=" + updatedVersion
                + '}';
    }
}
