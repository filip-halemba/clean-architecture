package io.halemba.invoice;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TestInvoiceRepository implements InvoiceRepository {

    private final ConcurrentHashMap<String, InvoiceSnapshot> database = new ConcurrentHashMap<>();

    @Override
    public void save(Invoice invoice) {
        InvoiceSnapshot snapshot = invoice.getSnapshot();
        database.put(snapshot.getId(), snapshot);
    }

    @Override
    public Invoice findById(InvoiceId invoiceId) {
        return Invoice.restore(database.get(invoiceId.asString()));
    }

    @Override
    public Optional<Invoice> findByNumber(InvoiceNumber number) {
        return database.entrySet()
                       .stream()
                       .filter(it -> it.getValue().getNumber().equals(number.getValue()))
                       .map(Map.Entry::getValue)
                       .map(Invoice::restore)
                       .findFirst();
    }

    //test purpose
    void saveSnapshot(InvoiceSnapshot snapshot) {
        database.put(snapshot.getId(), snapshot);
    }

}