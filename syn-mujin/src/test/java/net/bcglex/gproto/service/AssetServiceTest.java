package net.bcglex.gproto.service;

import net.bcglex.gproto.service.AssetService;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AssetServiceTest extends AppEngineTestCase {

    private AssetService service = new AssetService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
}
