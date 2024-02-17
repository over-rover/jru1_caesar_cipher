package service;

import consts.Consts;
import exceptions.ValidateException;

import java.util.List;

public class FileValidationService {
    private final LoggerService loggerService;

    public FileValidationService(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    protected void isEmpty(List<String> sourceText) throws ValidateException {
        if (sourceText.isEmpty()) {
            ValidateException e = new ValidateException(Consts.FILE_IS_EMPTY);
            loggerService.logException(e.getMessage(), e);
            throw e;
        }
    }

    protected void isSystemFile(String fileName) throws ValidateException {
        for (String extension : Consts.SYSTEM_FILE_EXTENSIONS) {
            if (fileName.endsWith(extension)) {
                ValidateException e = new ValidateException(Consts.UNPERMITTED_FILE_FORMAT);
                loggerService.logException(e.getMessage(), e);
                throw e;
            }
        }
    }
}