package io.halemba.subscription;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
class ChangeSubscriptionProcessRepositoryInMemory implements ChangeSubscriptionProcessRepository {

    private final ConcurrentHashMap<ProcessId, ChangeSubscriptionProcess> database = new ConcurrentHashMap<>();

    @Override
    public void save(ChangeSubscriptionProcess process) {
        database.put(process.getProcessId(), process);
    }

    @Override
    public Optional<ChangeSubscriptionProcess> find(ProcessId processId) {
        return Optional.ofNullable(database.get(processId));
    }

}
