package service;

import java.util.ArrayList;

public class LoggerService {
    private final String logExceptionFile = "logs/exceptions.log";
    private final FileService fileService;

    public LoggerService(FileService fileService) {
        this.fileService = fileService;
    }

    public void logException(String message, Exception e) {
        ArrayList<String> stackTraceList = new ArrayList<>();
        stackTraceList.add(message);
        for (StackTraceElement element : e.getStackTrace()) {
            stackTraceList.add(element.toString());
        }
        fileService.write(logExceptionFile, stackTraceList);
    }
}
