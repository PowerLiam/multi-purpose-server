package com.liamnbtech.server;

import com.liamnbtech.server.engine.bgp.message.body.OriginType;
import com.liamnbtech.server.engine.bgp.router.RouterNeighbor;
import com.liamnbtech.server.engine.bgp.router.UdsRouterNeighbor;
import com.liamnbtech.server.engine.bgp.table.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

@SpringBootTest
class ServerApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    void testNaiveRoutingTable() {
/*
        RoutingTable table = new NaiveRoutingTable();

        Route routeA = new Route(null, "1.2.3.4", "255.0.0.0", "1", true, new ArrayList<>(), OriginType.UNK);
        Route routeB = new Route(null, "1.2.3.4", "255.255.0.0", "1", true, new ArrayList<>(), OriginType.UNK);
        Route routeC = new Route(null, "1.3.3.4", "255.255.0.0", "1", true, new ArrayList<>(), OriginType.UNK);

        table.update(routeA);
        table.update(routeB);
        table.update(routeC);

        List<RouteSummary> summaryList = table.getTableSummary();

        Route foundA = table.lookup("1.99.99.99");
        Route foundB = table.lookup("1.2.99.99");
        Route foundC = table.lookup("1.3.99.99");
        Route foundNone = table.lookup("2.2.3.4");

        table.revoke(routeA);

        foundNone = table.lookup("1.99.99.99");
        foundB = table.lookup("1.2.99.99");
        foundC = table.lookup("1.3.99.99");
        foundNone = table.lookup("2.2.3.4");

        table.update(routeA);

        foundNone = table.lookup("1.99.99.99");
        foundB = table.lookup("1.2.99.99");
        foundC = table.lookup("1.3.99.99");
        foundNone = table.lookup("2.2.3.4");

        table.revoke(routeA);
        table.revoke(routeB);

        foundNone = table.lookup("1.99.99.99");
        foundB = table.lookup("1.2.99.99");
        foundC = table.lookup("1.3.99.99");
        foundNone = table.lookup("2.2.3.4");

        // Route adjB = new Route(null, "1.2.3.4", "255.255.0.0", "1", true, new ArrayList<>(), OriginType.UNK);
*/

    }

}
