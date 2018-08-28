package exceptions;

public class SerialNumberMalformed extends RuntimeException {
    public SerialNumberMalformed(String msg) {
        super(msg);
    }
}
