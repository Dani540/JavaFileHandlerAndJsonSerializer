package com.application.utilities;

import com.application.data.imp.FileHandler;
import com.application.data.out.IFileHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {
    private final IFileHandler fileManagement = new FileHandler();

    private final String fileToTest = "test/to/test.txt";

    @BeforeEach
    void tearDown(){
        fileManagement.deleteFile(fileToTest);
    }

    @Test
    @DisplayName("Should create a new file when the directory exists")
    void createFileWhenDirectoryExists() {
        String path = fileManagement.getDir(fileToTest);
        fileManagement.createDir(path);
        boolean result = fileManagement.createFile(fileToTest);
        assertTrue(result);
        assertTrue(fileManagement.isFileExist(fileToTest));
    }

    @Test
    @DisplayName("Should create a new file and directory when the directory doesn't exist")
    void createFileAndDirectoryWhenDirectoryDoesNotExist() {
        if (fileManagement.isFileExist(fileToTest)) fileManagement.deleteFile(fileToTest);
        boolean result = fileManagement.createFile(fileToTest);

        assertTrue(result);
        assertTrue(fileManagement.isFileExist(fileToTest));
        assertTrue(fileManagement.isDirExist(fileManagement.getDir(fileToTest)));
    }

    @Test
    @DisplayName("Should return false when the file already exists")
    void createFileWhenFileAlreadyExistsTheReturnFalse(){
        fileManagement.createFile(fileToTest);  // First create a file
        assertFalse(fileManagement.createFile(fileToTest)); // Now, the file is already exists, so if we can try re-created, return false
    }

}