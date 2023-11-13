package com.yrgo.services.calls;

import com.yrgo.domain.Action;
import com.yrgo.domain.Call;
import com.yrgo.services.customers.CustomerManagementService;
import com.yrgo.services.customers.CustomerNotFoundException;
import com.yrgo.services.diary.DiaryManagementService;

import java.util.Collection;

public class CallHandlingServiceImpl implements CallHandlingService {
    private DiaryManagementService diaryManagementService;
    private CustomerManagementService customerManagementService;
    public CallHandlingServiceImpl (DiaryManagementService diaryManagementService, CustomerManagementService customerManagementService) {
        this.diaryManagementService = diaryManagementService;
        this.customerManagementService = customerManagementService;
    }

    @Override
    public void recordCall(final String customerId,
                           final Call newCall,
                           final Collection<Action> actions) throws CustomerNotFoundException {
        for (var action : actions) {
            diaryManagementService.recordAction(action);
        }
        customerManagementService.recordCall(customerId, newCall);
    }
}
