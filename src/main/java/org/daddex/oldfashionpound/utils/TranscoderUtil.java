package org.daddex.oldfashionpound.utils;

import org.daddex.oldfashionpound.exception.AmountExceededException;
import org.daddex.oldfashionpound.model.bean.OldUkCurrency;
import org.junit.platform.commons.util.StringUtils;

import java.util.regex.Pattern;

import static org.daddex.oldfashionpound.utils.AppConstant.PENNIES_SIZE_ERROR_MESSAGE;
import static org.daddex.oldfashionpound.utils.AppConstant.SHILLING_SIZE_ERROR_MESSAGE;

public class TranscoderUtil {
    public enum OperationEnum {
        OPERATION_UNRECOGNIZED("x", Pattern.compile("x")),
        OPERATION_ADDITION("+", Pattern.compile("^([-]?[0-9]*p)? ?([-]?[0-9]*s)? ?([-]?[0-9]*d)? ? \\+ ([-]?[0-9]*p)? ?([-]?[0-9]*s)? ?([-]?[0-9]*d)?$")),
        OPERATION_SUBTRACTION("-", Pattern.compile("^([-]?[0-9]*p)? ?([-]?[0-9]*s)? ?([-]?[0-9]*d)? ? - ([-]?[0-9]*p)? ?([-]?[0-9]*s)? ?([-]?[0-9]*d)?$")),
        OPERATION_MULTIPLICATION("*", Pattern.compile("^([-]?[0-9]*p)? ?([-]?[0-9]*s)? ?([-]?[0-9]*d)? ? \\* ([-]?[0-9]*)$")),
        OPERATION_DIVISION("/", Pattern.compile("^([-]?[0-9]*p)? ?([-]?[0-9]*s)? ?([-]?[0-9]*d)? ? / ([-]?[0-9]*)$"));
        private String operation;
        private Pattern appliedPattern;

        OperationEnum(String op, Pattern operationPattern) {
            operation = op;
            appliedPattern = operationPattern;
        }

        public String getOperation() {
            return operation;
        }

        public Pattern getAppliedPattern() {
            return appliedPattern;
        }

    }

    public static OperationEnum fromType(final String type) {
        if (type == null) {
            return OperationEnum.OPERATION_UNRECOGNIZED;
        }
        for (OperationEnum classEnum : OperationEnum.values()) {
            if (type.equals(classEnum.getOperation())) {
                return classEnum;
            }
        }
        return OperationEnum.OPERATION_UNRECOGNIZED;
    }

    public static String extractAlgebraicOperator(String element) {
        if (element.contains("+")) {
            return "+";
        }
        if (element.contains("-")) {
            return "-";
        }
        if (element.contains("/")) {
            return "/";
        }
        if (element.contains("*")) {
            return "*";
        }
        return null;
    }

    public static Integer removeCharacterFromImport(final String input) {
        if (StringUtils.isNotBlank(input)) {
            return Integer.valueOf(Pattern.compile("[^0-9]").matcher(input).replaceAll(""));
        }
        return 0;
    }
    public static Integer  convertInputToPennies(OldUkCurrency oldUkCurrency){
        if(oldUkCurrency.getShillings() > 19){
            throw new AmountExceededException(SHILLING_SIZE_ERROR_MESSAGE,oldUkCurrency);
        }
        if(oldUkCurrency.getPennies() > 11){
            throw new AmountExceededException(PENNIES_SIZE_ERROR_MESSAGE,oldUkCurrency);
        }
        return 20 * 12 * oldUkCurrency.getPounds() + 12 * oldUkCurrency.getShillings() + oldUkCurrency.getPennies();
    }
    public static OldUkCurrency  convertPenniesToOutput(Integer resultInPennies){
        return OldUkCurrency.builder().pounds(resultInPennies / 240).shillings((resultInPennies/12)% 20).pennies(resultInPennies % 12).build();
    }
    public static OldUkCurrency  convertPenniesToOutputAsRemain(Integer resultInPennies){
        return OldUkCurrency.builder().shillings((resultInPennies/12)% 20).pennies(resultInPennies % 12).build();
    }
    public static String formatNumberToCurrency(OldUkCurrency oldUkCurrency){
        final StringBuilder stringBuilder = new StringBuilder(String.format("%dp %ds %dd", oldUkCurrency.getPounds(), oldUkCurrency.getShillings(), oldUkCurrency.getPennies()));
        if(oldUkCurrency.getRemainder() != null && oldUkCurrency.getRemainder() != 0){
            OldUkCurrency oldUkCurrencyRemainder = convertPenniesToOutputAsRemain(oldUkCurrency.getRemainder());
            stringBuilder.append(String.format(" (%ds %dd)",  oldUkCurrencyRemainder.getShillings(), oldUkCurrencyRemainder.getPennies()));
        }
        return stringBuilder.toString();
    }
}
