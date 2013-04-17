package net.bcglex.gproto.service;

import net.bcglex.gproto.service.AdmUserService;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AdmUserServiceTest extends AppEngineTestCase {

    private AdmUserService service = new AdmUserService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
