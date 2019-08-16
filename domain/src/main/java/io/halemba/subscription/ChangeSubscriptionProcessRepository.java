package io.halemba.subscription;

import java.util.Optional;

interface ChangeSubscriptionProcessRepository {
    void save(ChangeSubscriptionProcess process);
    Optional<ChangeSubscriptionProcess> find(ProcessId processId);
}
