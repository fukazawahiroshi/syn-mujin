package net.bcglex.gproto.model;

import net.bcglex.gproto.model.AdInfo;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AdInfoTest extends AppEngineTestCase {

    private AdInfo model = new AdInfo();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
