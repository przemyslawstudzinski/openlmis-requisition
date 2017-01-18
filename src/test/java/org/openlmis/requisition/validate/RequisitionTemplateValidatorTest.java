package org.openlmis.requisition.validate;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.contains;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.openlmis.requisition.i18n.MessageKeys.ERROR_CANNOT_CALCULATE_AT_THE_SAME_TIME;
import static org.openlmis.requisition.i18n.MessageKeys.ERROR_DISPLAYED_WHEN_REQUESTED_QUANTITY_EXPLANATION_IS_DISPLAYED;
import static org.openlmis.requisition.i18n.MessageKeys.ERROR_DISPLAYED_WHEN_REQUESTED_QUANTITY_IS_DISPLAYED;
import static org.openlmis.requisition.i18n.MessageKeys.ERROR_MUST_BE_DISPLAYED_WHEN_CONSUMED_QUANTITY_IS_CALCULATED;
import static org.openlmis.requisition.i18n.MessageKeys.ERROR_MUST_BE_DISPLAYED_WHEN_CONSUMPTION_IS_CALCULATED;
import static org.openlmis.requisition.i18n.MessageKeys.ERROR_MUST_BE_DISPLAYED_WHEN_ON_HAND_IS_CALCULATED;
import static org.openlmis.requisition.i18n.MessageKeys.ERROR_OPTION_NOT_AVAILABLE;
import static org.openlmis.requisition.i18n.MessageKeys.ERROR_SOURCE_NOT_AVAILABLE;
import static org.openlmis.requisition.validate.RequisitionTemplateValidator.ADJUSTED_CONSUMPTION;
import static org.openlmis.requisition.validate.RequisitionTemplateValidator.AVERAGE_CONSUMPTION;
import static org.openlmis.requisition.validate.RequisitionTemplateValidator.COLUMNS_MAP;
import static org.openlmis.requisition.validate.RequisitionTemplateValidator.NUMBER_OF_PERIODS_TO_AVERAGE;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openlmis.requisition.domain.AvailableRequisitionColumn;
import org.openlmis.requisition.domain.AvailableRequisitionColumnOption;
import org.openlmis.requisition.domain.RequisitionTemplate;
import org.openlmis.requisition.domain.RequisitionTemplateColumn;
import org.openlmis.requisition.domain.SourceType;
import org.openlmis.requisition.i18n.MessageService;
import org.openlmis.utils.Message;
import org.springframework.validation.Errors;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("PMD.TooManyMethods")
@RunWith(MockitoJUnitRunner.class)
public class RequisitionTemplateValidatorTest {

  private static final String COLUMN_NAME = "test";
  private static final String MUST_BE_DISPLAYED_WHEN_ADJUSTED_CONSUMPTION_IS_CALCULATED =
      " must be displayed when adjusted consumption is calculated.";

  @Mock
  private MessageService messageService;

  @Mock
  private ValidatorUtil validatorUtil;

  @InjectMocks
  private RequisitionTemplateValidator validator;

  private Errors errors = mock(Errors.class);
  private Message.LocalizedMessage message =
      new Message("testKey").new LocalizedMessage("testMessage");

  @Before
  public void prepareMessageServiceMock() {
    Message message1 = new Message(
        ERROR_DISPLAYED_WHEN_REQUESTED_QUANTITY_IS_DISPLAYED,
        RequisitionTemplateValidator.REQUESTED_QUANTITY_EXPLANATION);
    Message message2 = new Message(
        ERROR_DISPLAYED_WHEN_REQUESTED_QUANTITY_EXPLANATION_IS_DISPLAYED,
        RequisitionTemplateValidator.REQUESTED_QUANTITY);
    Message message3 = new Message(
        ERROR_CANNOT_CALCULATE_AT_THE_SAME_TIME,
        RequisitionTemplateValidator.TOTAL_CONSUMED_QUANTITY,
        RequisitionTemplateValidator.STOCK_ON_HAND);
    Message message7 = new Message(
        ERROR_MUST_BE_DISPLAYED_WHEN_CONSUMED_QUANTITY_IS_CALCULATED,
        RequisitionTemplateValidator.STOCK_ON_HAND);
    Message message8 = new Message(ERROR_MUST_BE_DISPLAYED_WHEN_ON_HAND_IS_CALCULATED,
        RequisitionTemplateValidator.TOTAL_CONSUMED_QUANTITY);

    when(messageService.localize(message1)).thenReturn(message1.new LocalizedMessage(
        RequisitionTemplateValidator.REQUESTED_QUANTITY_EXPLANATION + " must be displayed when "
            + "requested quantity is displayed."));

    when(messageService.localize(message2)).thenReturn(message2.new LocalizedMessage(
        RequisitionTemplateValidator.REQUESTED_QUANTITY + " must be "
            + "displayed when requested quantity explanation is displayed."));

    when(messageService.localize(message3)).thenReturn(message3.new LocalizedMessage(
        RequisitionTemplateValidator.TOTAL_CONSUMED_QUANTITY + " and "
            + RequisitionTemplateValidator.STOCK_ON_HAND
            + " cannot be calculated at the same time."));

    when(messageService.localize(message7)).thenReturn(message7.new LocalizedMessage(
        "must be displayed when total consumed quantity is calculated."));

    when(messageService.localize(message8)).thenReturn(message8.new LocalizedMessage(
        "must be displayed when stock on hand is calculated."));

  }

  @Test
  public void shouldRejectIfRequestedQuantityAndExplanationIsDisplayedValuesAreDifferent() {

    RequisitionTemplate requisitionTemplate = generateTemplate();
    requisitionTemplate.changeColumnDisplay(
        RequisitionTemplateValidator.REQUESTED_QUANTITY_EXPLANATION, false);
    requisitionTemplate.changeColumnSource(RequisitionTemplateValidator.REQUESTED_QUANTITY,
        SourceType.USER_INPUT);

    requisitionTemplate.changeColumnSource(
        RequisitionTemplateValidator.REQUESTED_QUANTITY_EXPLANATION, SourceType.USER_INPUT);

    validator.validate(requisitionTemplate, errors);

    verify(errors).rejectValue(eq(COLUMNS_MAP),
        contains(RequisitionTemplateValidator.REQUESTED_QUANTITY_EXPLANATION
            + " must be displayed when requested quantity is displayed."));
  }

  @Test
  public void shouldRejectWhenSourceIsNotAvailableInColumn() {
    RequisitionTemplate requisitionTemplate = generateTemplate();

    requisitionTemplate.changeColumnSource(COLUMN_NAME, SourceType.USER_INPUT);
    requisitionTemplate.getColumnsMap()
        .get(COLUMN_NAME).getColumnDefinition().getSources().clear();

    Message message4 = new Message(ERROR_SOURCE_NOT_AVAILABLE, "test");
    when(messageService.localize(message4)).thenReturn(message4.new LocalizedMessage(
        "Source " + SourceType.USER_INPUT + " is not available for this column."));

    validator.validate(requisitionTemplate, errors);

    verify(errors).rejectValue(eq(COLUMNS_MAP),
        contains(RequisitionTemplate.SOURCE + SourceType.USER_INPUT
            + RequisitionTemplate.WARNING_SUFFIX));
  }

  @Test
  public void shouldRejectWhenOptionIsNotAvailableInColumn() {
    RequisitionTemplate requisitionTemplate = generateTemplate();
    AvailableRequisitionColumnOption option = new AvailableRequisitionColumnOption(
        requisitionTemplate.getColumnsMap().get(COLUMN_NAME)
            .getColumnDefinition(), "option1", "label1");
    Set<AvailableRequisitionColumnOption> options = new HashSet<>();
    options.add(option);
    requisitionTemplate.getColumnsMap().get(COLUMN_NAME).getColumnDefinition().setOptions(options);
    requisitionTemplate.changeColumnOption(COLUMN_NAME, option);

    requisitionTemplate.getColumnsMap().get(COLUMN_NAME)
        .getColumnDefinition().getOptions().clear();

    Message message5 = new Message(ERROR_OPTION_NOT_AVAILABLE, "test");
    when(messageService.localize(message5)).thenReturn(message5.new LocalizedMessage(
        "Option " + option.getOptionName() + " is not available for this column."));

    validator.validate(requisitionTemplate, errors);

    verify(errors).rejectValue(eq(COLUMNS_MAP),
        contains(RequisitionTemplate.OPTION + option.getOptionName()
            + RequisitionTemplate.WARNING_SUFFIX));
  }

  @Test
  public void shouldRejectWhenTotalStockoutDaysFieldIsNotDisplayed() {
    RequisitionTemplate requisitionTemplate =
        getRequisitionTemplateForTestAdjustedAndAverageConsumptionField();
    requisitionTemplate.changeColumnDisplay(
        RequisitionTemplateValidator.TOTAL_STOCKOUT_DAYS, false);

    Message message6 = new Message(ERROR_MUST_BE_DISPLAYED_WHEN_CONSUMPTION_IS_CALCULATED,
        RequisitionTemplateValidator.TOTAL_STOCKOUT_DAYS);
    when(messageService.localize(message6)).thenReturn(message6.new LocalizedMessage(
        RequisitionTemplateValidator.TOTAL_STOCKOUT_DAYS
            + MUST_BE_DISPLAYED_WHEN_ADJUSTED_CONSUMPTION_IS_CALCULATED));

    validator.validate(requisitionTemplate, errors);

    verify(errors).rejectValue(eq(COLUMNS_MAP),
        contains(RequisitionTemplateValidator.TOTAL_STOCKOUT_DAYS
            + MUST_BE_DISPLAYED_WHEN_ADJUSTED_CONSUMPTION_IS_CALCULATED));
  }

  @Test
  public void shouldRejectWhenTotalConsumedQuantityFieldIsNotDisplayed() {
    RequisitionTemplate requisitionTemplate =
        getRequisitionTemplateForTestAdjustedAndAverageConsumptionField();
    requisitionTemplate.changeColumnDisplay(
        RequisitionTemplateValidator.TOTAL_CONSUMED_QUANTITY, false);

    Message message9 = new Message(ERROR_MUST_BE_DISPLAYED_WHEN_CONSUMPTION_IS_CALCULATED,
        RequisitionTemplateValidator.TOTAL_CONSUMED_QUANTITY);

    when(messageService.localize(message9)).thenReturn(message9.new LocalizedMessage(
        RequisitionTemplateValidator.TOTAL_CONSUMED_QUANTITY
            + MUST_BE_DISPLAYED_WHEN_ADJUSTED_CONSUMPTION_IS_CALCULATED));

    validator.validate(requisitionTemplate, errors);

    verify(errors).rejectValue(eq(COLUMNS_MAP),
        contains(RequisitionTemplateValidator.TOTAL_CONSUMED_QUANTITY
            + MUST_BE_DISPLAYED_WHEN_ADJUSTED_CONSUMPTION_IS_CALCULATED));
  }

  @Test
  public void shouldRejectWhenAverageInTemplateAndAdjustedConsumptionNotInTemplate() {
    when(messageService.localize(
        new Message("requisition.error.validation.fieldMustBeInTemplate")))
        .thenReturn(message);

    Map<String, RequisitionTemplateColumn> columnMap = getRequisitionTemplateColumnMap();
    columnMap.put(AVERAGE_CONSUMPTION,
        generateTemplateColumn(AVERAGE_CONSUMPTION, "P", true));

    RequisitionTemplate requisitionTemplate = new RequisitionTemplate(columnMap);
    validator.validate(requisitionTemplate, errors);

    verify(validatorUtil).rejectValue(errors, COLUMNS_MAP, message);
  }

  @Test
  public void shouldRejectWhenAverageInTemplateAndNumberOfPreviousPeriodsIsNull() {
    when(messageService.localize(
        new Message("requisition.error.validation.fieldCanNotBeNull")))
        .thenReturn(message);

    RequisitionTemplate requisitionTemplate
        = getRequisitionTemplateForTestAdjustedAndAverageConsumptionField();
    requisitionTemplate.setNumberOfPeriodsToAverage(null);

    validator.validate(requisitionTemplate, errors);

    verify(validatorUtil).rejectValue(errors, NUMBER_OF_PERIODS_TO_AVERAGE, message);
  }

  @Test
  public void shouldRejectWhenNumberOfPreviousPeriodsLessThanTwo() {
    when(messageService.localize(
        new Message("requisition.error.validation.fieldMustBeGreaterOrEqual")))
        .thenReturn(message);

    Map<String, RequisitionTemplateColumn> columnMap = getRequisitionTemplateColumnMap();

    RequisitionTemplate requisitionTemplate = new RequisitionTemplate(columnMap);
    requisitionTemplate.setNumberOfPeriodsToAverage(1);

    validator.validate(requisitionTemplate, errors);

    verify(validatorUtil).rejectValue(errors, NUMBER_OF_PERIODS_TO_AVERAGE, message);
  }

  @Test
  public void shouldNotRejectWhenNumberOfPreviousPeriodsGreaterOrEqualThanTwo() {
    Map<String, RequisitionTemplateColumn> columnMap = getRequisitionTemplateColumnMap();

    RequisitionTemplate requisitionTemplate = new RequisitionTemplate(columnMap);
    requisitionTemplate.setNumberOfPeriodsToAverage(2);

    validator.validate(requisitionTemplate, errors);

    verify(errors, never()).rejectValue(eq(NUMBER_OF_PERIODS_TO_AVERAGE), any());
  }

  private RequisitionTemplate generateTemplate() {
    Map<String, RequisitionTemplateColumn> columnMap = getRequisitionTemplateColumnMap();
    RequisitionTemplateColumn column = generateTemplateColumn(COLUMN_NAME, "T", true);

    columnMap.put(COLUMN_NAME, column);

    return new RequisitionTemplate(columnMap);
  }

  private Map<String, RequisitionTemplateColumn> getRequisitionTemplateColumnMap() {
    Map<String, RequisitionTemplateColumn> columnMap = new HashMap<>();
    columnMap.put(RequisitionTemplateValidator.REQUESTED_QUANTITY,
        generateTemplateColumn(RequisitionTemplateValidator.REQUESTED_QUANTITY, "J", true));
    columnMap.put(RequisitionTemplateValidator.REQUESTED_QUANTITY_EXPLANATION,
        generateTemplateColumn(RequisitionTemplateValidator.REQUESTED_QUANTITY_EXPLANATION,
            "W", true));
    columnMap.put(RequisitionTemplateValidator.TOTAL_CONSUMED_QUANTITY,
        generateTemplateColumn(RequisitionTemplateValidator.TOTAL_CONSUMED_QUANTITY, "C", true));
    columnMap.put(RequisitionTemplateValidator.STOCK_ON_HAND,
        generateTemplateColumn(RequisitionTemplateValidator.STOCK_ON_HAND, "E", true));

    return columnMap;
  }

  private RequisitionTemplate getRequisitionTemplateForTestAdjustedAndAverageConsumptionField() {
    Map<String, RequisitionTemplateColumn> columnMap = getRequisitionTemplateColumnMap();
    columnMap.put(RequisitionTemplateValidator.TOTAL_STOCKOUT_DAYS,
        generateTemplateColumn(RequisitionTemplateValidator.TOTAL_STOCKOUT_DAYS,
            "X", true));
    columnMap.put(ADJUSTED_CONSUMPTION,
        generateTemplateColumn(ADJUSTED_CONSUMPTION,
            "N", true));
    columnMap.put(AVERAGE_CONSUMPTION,
        generateTemplateColumn(AVERAGE_CONSUMPTION,
            "P", true));

    RequisitionTemplate requisitionTemplate = new RequisitionTemplate(columnMap);
    requisitionTemplate.changeColumnSource(ADJUSTED_CONSUMPTION,
        SourceType.CALCULATED);
    requisitionTemplate.changeColumnSource(AVERAGE_CONSUMPTION,
        SourceType.CALCULATED);
    requisitionTemplate.changeColumnSource(
        RequisitionTemplateValidator.TOTAL_STOCKOUT_DAYS, SourceType.USER_INPUT);
    requisitionTemplate.changeColumnSource(
        RequisitionTemplateValidator.TOTAL_CONSUMED_QUANTITY, SourceType.USER_INPUT);
    return requisitionTemplate;
  }

  private RequisitionTemplateColumn generateTemplateColumn(String name, String indicator,
                                                           boolean displayed) {
    AvailableRequisitionColumn columnDefinition = new AvailableRequisitionColumn();
    columnDefinition.setName(name);
    columnDefinition.setIndicator(indicator);
    columnDefinition.setIsDisplayRequired(false);
    Set<SourceType> sources = new HashSet<>();
    sources.add(SourceType.USER_INPUT);
    sources.add(SourceType.CALCULATED);
    columnDefinition.setSources(sources);

    RequisitionTemplateColumn requisitionTemplateColumn =
        new RequisitionTemplateColumn(columnDefinition);
    requisitionTemplateColumn.setName(name);
    requisitionTemplateColumn.setIsDisplayed(displayed);
    return requisitionTemplateColumn;
  }
}
