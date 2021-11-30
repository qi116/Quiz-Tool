public class Request extends Message {
    private RequestType requestType;

    public Request(Object object, DataType dataType, RequestType requestType) {
        super(object, dataType);
        this.requestType = requestType;
    }
    
    public Request(String[] stringL) {
        super(stringL, DataType.ACCOUNT);
        requestType = RequestType.GET;
    }

    public Request(String string, DataType dataType, RequestType requestType) {
        super(string, dataType);
        this.requestType = requestType;
    }

    public RequestType getRequestType() {
        return requestType;
    }
}
