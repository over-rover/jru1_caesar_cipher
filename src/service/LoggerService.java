package service;

import java.util.ArrayList;

public class LoggerService {
    private final String logExceptionFile = "logs/exceptions.log";
    //Приходится создавать fileService и передавать ему ссылку на самого себя.
    private final FileService fileService = new FileService(this);

    public void logException(String message, Exception e) {
        ArrayList<String> stackTraceList = new ArrayList<>();
        stackTraceList.add(message);
        for (StackTraceElement element : e.getStackTrace()) {
            stackTraceList.add(element.toString());
        }
        fileService.write(logExceptionFile, stackTraceList);
    }
}