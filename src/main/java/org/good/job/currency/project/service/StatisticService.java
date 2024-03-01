package org.good.job.currency.project.service;

import org.good.job.currency.project.entity.GeneralRate;
import org.good.job.currency.project.entity.RateStatisticData;

import java.util.List;


public interface StatisticService {

    RateStatisticData getStatistics(List<GeneralRate> generalRates);

}
