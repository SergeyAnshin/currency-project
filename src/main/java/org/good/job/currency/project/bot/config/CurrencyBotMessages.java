package org.good.job.currency.project.bot.config;

import org.good.job.currency.project.bot.model.enums.UserStep;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CurrencyBotMessages {

    public final String selectBankMessage;
    public final String reselectBankMessage;
    public final String reselectCurrencyMessage;
    public final String selectedBankMessage;
    public final String selectedCurrencyMessage;
    public final String currencyByDateMessage;
    public final String statisticsMessage;
    public final String inputDateMessage;
    public final String inputRangeOfDatesMessage;

    public final String noBanksAvailableMessage;
    public final String bankIsNotSupportedMessage;
    public final String bankCurrencyTodayError;
    public final String bankCurrencyByDateError;
    public final String bankStatisticsError;
    public final String incorrectFeatureInput;
    public final String incorrectDateInput;
    public final String rateDoesNotSupportDate;


    public final Map<String, UserStep> featureSelectStringsMap;

    private CurrencyBotMessages(BotMessageProvider botMessageProvider) {
        this.selectBankMessage = botMessageProvider.getMessage("bot.action.message.SelectBank");
        this.reselectBankMessage = botMessageProvider.getMessage("bot.action.button-text.ReselectBank");
        this.reselectCurrencyMessage = botMessageProvider.getMessage("bot.action.button-text.ReselectCurrency");
        this.selectedBankMessage = botMessageProvider.getMessage("bot.action.message.SelectedBank");
        this.selectedCurrencyMessage = botMessageProvider.getMessage("bot.action.message.SelectedCurrency");
        this.noBanksAvailableMessage = botMessageProvider.getMessage("bot.action.error.NoBanksAvailable");
        this.bankIsNotSupportedMessage = botMessageProvider.getMessage("bot.action.error.BankIsNotSupported");
        this.bankCurrencyTodayError = botMessageProvider.getMessage("bot.action.error.BankCurrencyTodayError");
        this.incorrectFeatureInput = botMessageProvider.getMessage("bot.action.error.IncorrectFeatureInput");
        this.incorrectDateInput = botMessageProvider.getMessage("bot.action.error.IncorrectDateInput");
        this.currencyByDateMessage = botMessageProvider.getMessage("bot.action.feature.GetCurrencyByDate");
        this.inputDateMessage = botMessageProvider.getMessage("bot.action.message.InputDate");
        this.inputRangeOfDatesMessage = botMessageProvider.getMessage("bot.action.message.InputRangeOfDates");
        this.bankCurrencyByDateError = botMessageProvider.getMessage("bot.action.error.BankCurrencyByDateError");
        this.rateDoesNotSupportDate = botMessageProvider.getMessage("bot.action.error.RateDoesNotSupportDate");
        this.bankStatisticsError = botMessageProvider.getMessage("bot.action.error.BankStatisticsError");
        this.statisticsMessage = botMessageProvider.getMessage("bot.action.feature.GetStatistics");

        this.featureSelectStringsMap = Map.of(
                botMessageProvider.getMessage(UserStep.GET_CURRENCY_RATE_TODAY), UserStep.GET_CURRENCY_RATE_TODAY,
                botMessageProvider.getMessage(UserStep.GET_CURRENCY_RATE_BY_DATE), UserStep.GET_CURRENCY_RATE_BY_DATE,
                botMessageProvider.getMessage(UserStep.GET_STATISTIC), UserStep.GET_STATISTIC
        );
    }
}

