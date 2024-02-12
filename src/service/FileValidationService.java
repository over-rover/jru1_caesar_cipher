package service;

import consts.Consts;
import exceptions.FileException;

import java.util.List;

public class FileValidationService {
    LoggerService loggerService;

    public FileValidationService(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    public void isEmpty(List<String> sourceText) throws FileException {
        if (sourceText.isEmpty()) {
            FileException e = new FileException(Consts.FILE_IS_EMPTY);
            loggerService.logException(e.getMessage(), e);
            throw e;
        }
    }
}