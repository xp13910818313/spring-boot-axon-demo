package command;

import command.addproduct.AddProductCommand;
import command.addproduct.AddProductEvent;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

public class ProductAggregate {

    @AggregateIdentifier
    private String id;
    private String name;
    private int quantity;

    public ProductAggregate(AddProductCommand cmd) {
        apply(new AddProductEvent(cmd.getId(), cmd.getName(), cmd.getQuantity()));
    }

    @EventSourcingHandler
    public void on(AddProductEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.quantity = event.getQuantity();
    }

    protected ProductAggregate() { }
}
