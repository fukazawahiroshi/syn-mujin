package net.bcglex.gproto.model;

import net.bcglex.gproto.model.AdmUser;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AdmUserTest extends AppEngineTestCase {

    private AdmUser model = new AdmUser();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
