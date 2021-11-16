public class Request {
    Account account;
    Course course;
    String fileName;
    RequestType requestType;
    DataType dataType;

    public Request(Account account, RequestType requestType) {
        this.account = account;
        this.course = null;
        this.fileName = "data/accounts/" + account.getUsername() + ".obj";
        this.requestType = requestType;
        this.dataType = DataType.ACCOUNT;
    }

    public Request(Course course, RequestType requestType) {
        this.account = null;
        this.course = course;
        this.fileName = "data/courses/" + course.getName().replace(" ", "-") + ".obj";
        this.requestType = requestType;
        this.dataType = DataType.Course;
    }

    public Request(String fileName, DataType dataType) {
        this.account = null;
        this.course = null;
        this.fileName = "data/" + dataType.getFolderName() + "/" +
            fileName + ".obj";
        this.requestType = RequestType.GET;
        this.dataType = dataType;
    }

    public enum RequestType {GET, ADD, MODIFY, REMOVE}
    public enum DataType {
        ACCOUNT("accounts"), STUDENT("accounts"), COURSE("courses");

        private String folderName;

        public String getFolderName() {
            return this.folderName;
        }
        
        DataType(String folderName) {
            this.folderName = folderName;
        }
    }

    public Object[] getData() {
        Object[] toReturn = {null, dataType, requestType, fileName};
        if (account != null)
            toReturn[0] = account;
        else if (course != null)
            toReturn[0] = course;
        else
            toReturn[0] = null;
        return toReturn;
    }
}
