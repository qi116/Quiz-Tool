public class Message {
    private Object object;
    private DataType dataType;

    public Message(Object object, DataType dataType) {
        this.object = object;
        this.dataType = dataType;
    }

    public Object getObject() {
        return object;
    }

    public DataType getDataType() {
        return dataType;
    }
}
