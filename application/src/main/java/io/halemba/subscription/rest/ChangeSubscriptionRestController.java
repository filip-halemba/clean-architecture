package io.halemba.subscription.rest;

import io.halemba.subscription.CustomerId;
import io.halemba.subscription.RequestChangeSubscriptionUseCase;
import io.halemba.subscription.SubscriptionCode;
import io.halemba.subscription.answer.ChangeSubscriptionProcessStateAnswer;
import io.halemba.subscription.command.RequestChangeSubscriptionCommand;
import io.halemba.subscription.query.ChangeSubscriptionQueryService;
import io.halemba.subscription.query.ChangeSubscriptionStateQuery;
import io.halemba.subscription.rest.request.RequestChangeSubscriptionRequestBody;
import io.halemba.subscription.rest.response.ChangeSubscriptionStateResponseBody;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/1/subscription-change-processes")
class ChangeSubscriptionRestController {

    private final ChangeSubscriptionQueryService changeSubscriptionQueryService;
    private final RequestChangeSubscriptionUseCase requestChangeSubscriptionUseCase;

    @GetMapping("/proposals/{subscriptionCode}")
    public List<String> getProposal(@PathVariable String subscriptionCode) {
        return changeSubscriptionQueryService.getProposal(subscriptionCode).getItems();
    }

    @PostMapping
    public ChangeSubscriptionStateResponseBody request(@Valid @RequestBody RequestChangeSubscriptionRequestBody body) {
        ChangeSubscriptionProcessStateAnswer execute = requestChangeSubscriptionUseCase.execute(
                RequestChangeSubscriptionCommand.builder()
                                                .customerId(CustomerId.of(body.getCustomerId()))
                                                .subscriptionCode(SubscriptionCode.of(body.getSubscriptionCode()))
                                                .build());
        return ChangeSubscriptionStateResponseBody.builder()
                                                  .processId(execute.getId())
                                                  .status(execute.getStatus())
                                                  .build();
    }

    @GetMapping("/{processId}/{customerId}")
    public ChangeSubscriptionStateResponseBody getState(@PathVariable String processId,
                                                        @PathVariable String customerId) {
        return changeSubscriptionQueryService.query(ChangeSubscriptionStateQuery.builder()
                                                                                .processId(processId)
                                                                                .customerId(customerId)
                                                                                .build())
                                             .map(it -> ChangeSubscriptionStateResponseBody.builder()
                                                                                           .processId(it.getProcessId())
                                                                                           .status(it.getCustomerId())
                                                                                           .build())
                                             .orElseThrow(IllegalStateException::new);
    }

}
