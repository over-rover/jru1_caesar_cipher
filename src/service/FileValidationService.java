package service;

import consts.Consts;
import exceptions.FileException;

import java.util.List;

public class FileValidationService {
    FileService fileService = new FileService();

    public void isEmpty(List<String> sourceText) throws FileException {
        if (sourceText.isEmpty()) {
            FileException e = new FileException(Consts.FILE_IS_EMPTY);
            fileService.loggerService.logException(e.getMessage(), e);
            throw e;
        }
    }
}
