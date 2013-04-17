package net.bcglex.gproto.controller;

import net.bcglex.gproto.controller.XtableController;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class XtableControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/Xtable");
        XtableController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }
}
