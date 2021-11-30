public class Response extends Message {
    private boolean clientMessedUp = false;
    private boolean iMessedUp = false;

    public Response(Object object, DataType dataType, boolean clientMessedUp, boolean iMessedUp) {
        super(object, dataType);
        this.clientMessedUp = clientMessedUp;
        this.iMessedUp = iMessedUp;
    }

    public Response(String[] stringL, boolean clientMessedUp, boolean iMessedUp) {
        super(stringL, DataType.STRINGL);
        this.clientMessedUp = clientMessedUp;
        this.iMessedUp = iMessedUp;
    }
    
    public Response(boolean bool, boolean clientMessedUp, boolean iMessedUp) {
        super(bool, DataType.BOOLEAN);
        this.clientMessedUp = clientMessedUp;
        this.iMessedUp = iMessedUp;
    }

    public boolean wasError() {
        return clientMessedUp || iMessedUp;
    }

    public boolean wasExpectedError() {
        return clientMessedUp;
    }

    public boolean wasUnexpectedError() {
        return iMessedUp;
    }
}
