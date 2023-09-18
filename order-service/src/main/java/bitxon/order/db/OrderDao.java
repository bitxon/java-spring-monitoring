package bitxon.order.db;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.db.MetricsDSLContext;
import io.micrometer.observation.annotation.Observed;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;


@Repository
@Observed(name = "orderDao") // Requires Spring boot 3.2 to work out of the box
public class OrderDao {

    private final MetricsDSLContext dslContext;

    public OrderDao(DSLContext dslContext, MeterRegistry meterRegistry) {
        this.dslContext = MetricsDSLContext.withMetrics(dslContext, meterRegistry, Tags.empty());
    }

    public void create(String productIdentifier, String productName, int quantity, int totalAmount) {
        dslContext.tag("name", "SaveOrder")
            .insertInto(table("\"order\""))
            .set(field("product_identifier"), productIdentifier)
            .set(field("product_name"), productName)
            .set(field("quantity"), quantity)
            .set(field("total_amount"), totalAmount)
            .execute();
    }


}
