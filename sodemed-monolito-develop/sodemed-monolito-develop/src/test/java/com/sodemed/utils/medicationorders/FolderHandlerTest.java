package com.sodemed.utils.medicationorders;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FolderHandlerTest {

    @Test
    public void testPath() {
        FolderHandler folderHandler = new FolderHandler();
        folderHandler.preparePath();


    }

}
