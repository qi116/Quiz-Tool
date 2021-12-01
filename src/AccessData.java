ipublic class AccessData {
    public static Response getResponse(Request request) {
        RequestType requestType = request.getRequestType();
        DataType dataType = request.getDataType();
        Object object = request.getObject();
        try {
            switch (dataType) {
                case ACCOUNT:
                    return account(requestType, object, new AccountAccessor());
                case STUDENT:
                    return student(requestType, object, new StudentAccessor());
                case COURSE:
                    return course(requestType, object, new CourseAccessor());
            }
            return new Response(false, true);
        } catch (NullPointerException e) {
            return new Response(true, false);
        } catch (FileAlreadyExistsException e) {
            return new Response(true, false);
        } catch (Exception e) {
            return new Response(false, true);
        }
    }

    private static Response account(RequestType requestType, Object object, AccountAccessor accessor) 
                        throws NullPointerException, FileAlreadyExistsException {
        switch (requestType) {
            case GET:
                String[] sLData = (String[]) object;
                return new Response(accessor.get(sLData[0], sLData[1]), DataType.ACCOUNT);
            case CHECK_EXISTS:
                String sData = (String) object;
                return new Response(accessor.checkExists(sData));
            case ADD:
                Account aData = (Account) object;
                accessor.add(aData);
                return new Response(true);
            case MODIFY:
                Account aData2 = (Account) object;
                accessor.modify(aData2);
                return new Response(true);
            }
        return new Response(true, false);
    }

    private static Response student(RequestType requestType, Object object, StudentAccessor accessor) 
                                throws NullPointerException, FileAlreadyExistsException {
        switch (requestType) {
            case GET:
                String sData = (String) object;
                return new Response(accessor.get(sData), DataType.ACCOUNT);
            case GET_LIST:
                return new Response(accessor.getList());
        }
        return new Response(false, true);
    }
    
    private static Response course(RequestType requestType, Object object, CourseAccessor accessor) 
                                throws NullPointerException, FileAlreadyExistsException {
        switch (requestType) {
            case GET:
                String sData = (String) object;
                return new Response(accessor.get(sData), DataType.COURSE);
            case CHECK_EXISTS:
                String sData2 = (String) object;
                return new Response(accessor.checkExists(sData2));
            case GET_LIST:
                return new Response(accessor.getList());
            case ADD:
                Course cData = (Course) object;
                accessor.add(cData);
                return new Response(true);
            case MODIFY:
                Course cData2 = (Course) object;
                accessor.modify(cData2);
                return new Response(true);
            case REMOVE:
                String sData3 = (String) object;
                accessor.remove(sData3);
                return new Response(true);
        }
        return new Response(true, false);
    }
}
