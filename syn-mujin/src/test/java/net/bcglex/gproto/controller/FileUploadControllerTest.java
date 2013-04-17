package net.bcglex.gproto.controller;

import net.bcglex.gproto.controller.FileUploadController;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FileUploadControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/FileUpload");
        FileUploadController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }
}
