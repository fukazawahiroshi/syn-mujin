package net.bcglex.gproto.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AssetTest extends AppEngineTestCase {

    private Asset model = new Asset();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
