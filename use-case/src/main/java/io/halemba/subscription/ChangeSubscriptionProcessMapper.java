package io.halemba.subscription;

import io.halemba.subscription.ChangeSubscriptionProcess.Status;
import io.halemba.subscription.answer.ChangeSubscriptionProcessStateAnswer;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ChangeSubscriptionProcessMapper {

    enum ExternalStatus {
        REQUESTED,
        SUCCESS,
        FAILED
    }

    private static final Map<Status, ExternalStatus> MAPPER =
            HashMap.of(Status.REQUESTED, ExternalStatus.REQUESTED)
                   .put(Status.REJECTED, ExternalStatus.FAILED)
                   .put(Status.CONFIRMED, ExternalStatus.SUCCESS);

    public static String toExternalStatus(String value) {
        return toExternalStatus(Status.valueOf(value)).name();
    }

    static ChangeSubscriptionProcessStateAnswer toAnswer(ChangeSubscriptionProcess process) {
        return ChangeSubscriptionProcessStateAnswer
                .builder()
                .id(process.getProcessId().getValueAsString())
                .status(toExternalStatus(process.getStatus()).name())
                .build();
    }

    private static ExternalStatus toExternalStatus(Status status) {
        return MAPPER.get(status).getOrElseThrow(IllegalStateException::new);
    }

}
